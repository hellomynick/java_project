function showImportScreen() {
    let headerContent = document.getElementById('importWrapper');
    if (headerContent.style.display === 'none' || headerContent.style.display === '') {
        headerContent.style.display = 'flex';
    } else {
        headerContent.style.display = 'none';
    }
}

function closeImportScreen() {
    let headerContent = document.getElementById('importWrapper');
    headerContent.style.display = 'none';
}

function readFileExcel(file) {
    return new Promise((resolve, reject) => {
        const fileReader = new FileReader();
        let excelReader;

        fileReader.readAsArrayBuffer(file);

        fileReader.addEventListener('load', () => {
            try {
                let arrayBuffer = fileReader.result;
                let data = new Uint8Array(arrayBuffer);

                excelReader = XLSX.read(data, {
                    type: 'array'
                })

                resolve(excelReader);
            } catch (e) {
                reject(e);
            }
        });

        fileReader.addEventListener('error', () => {
            reject("Can not read file")
        })
    })
}

function matchingHeader(excelReader, headers) {
    let excelHeaders = excelReader.Strings;

    for (let i = 0; i < headers.length; i++) {
        if (headers[i] !== excelHeaders[i].t) {
            return false;
        }
    }

    return true;
}

function getAccounts(excelReader) {
    let sheetName = excelReader.SheetNames;
    let accounts = [];
    let messages = [];
    let isValid;

    for (let i = 0; i < sheetName.length; i++) {
        let objects = XLSX.utils.sheet_to_json(excelReader.Sheets[i])
        isValid = true;

        for (let j = 0; j < objects.length; j++) {
            const validObject = validateAccount(j, objects[i]);
            isValid = validObject.isValid;
            if (isValid === false) {
                messages.push(validObject.message);
                break;
            }

            accounts.push(new Account(object.key, object.user_name, object.password, object.isActive, object.email, object.phone, object.avatarName));
            messages.push(validObject.message);
        }

        if (isValid === false) {
            break;
        }
    }

    return {isValid, messages, accounts}
}

function validateAccount(index, account) {
    let message = "Row " + index;
    let isValid = true;

    if (account.user_name == null) {
        message += " User name null."
        isValid = false;
    }

    if (account.password == null) {
        message += " Password null."
        isValid = false;
    }

    if (account.email == null) {
        message += " Email null"
        isValid = false;
    }

    if (isValid) {
        message += " Ok"
    }

    return {isValid, message}
}

function importFile() {
    const file = document.getElementById("importFile").files[0];
    const headers = ["key", "userName", "password", "isActive", "email", "phoneNumber", "avatarName"];

    readFileExcel(file).then(excelReader => {
        let isMatched = matchingHeader(excelReader, headers);
        if (!isMatched) {
            console.log("Header not matching with form [key, userName, password, isActive, email, phoneNumber, avatarName]")
        } else {
            let object = getAccounts(excelReader);

            if (object.isValid === true) {
                pushAccounts(object.accounts);
                console.log(object.messages)
            } else {
                console.log(object.messages)
            }
        }
    }).catch((e) => {
        console.log(e)
    })
}

function pushAccounts(accounts) {
    $.ajax({
        url: "/accounts/import",
        type: 'POST',
        data: JSON.stringify(accounts),
        contentType: "application/json; charset=utf-8",
        dataType: "json",
        success: function (response) {
            console.log(response)
        }
    })
}
