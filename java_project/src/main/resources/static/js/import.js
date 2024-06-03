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
    headers.forEach((item, index) => {
        if (item !== excelHeaders[index].t) {
            return false
        }
    })

    return true;
}

function getAccounts(excelReader) {
    let sheetName = excelReader.SheetNames;
    let accounts = [];
    sheetName.forEach((item) => {
        let objects = XLSX.utils.sheet_to_json(excelReader.Sheets[item])

        objects.forEach((object) => {
            accounts.push(new Account(object.key, object.name, object.password, object.isActive, object.email, object.phone, object.avatarName));
        })
    })
    
    return accounts
}

function importFile() {
    const file = document.getElementById("importFile").files[0];
    const headers = ["key", "userName", "password", "isActive", "email", "phoneNumber", "avatarName"];

    readFileExcel(file).then(excelReader => {
        let isMatched = matchingHeader(excelReader, headers);
        if (!isMatched) {
            console.log("Header not matching with form [key, userName, password, isActive, email, phoneNumber, avatarName]")
        } else {
            let accounts = getAccounts(excelReader);
            pushAccounts(accounts);
        }
    }).catch((e) => {
        console.log(e)
    })
}

function pushAccounts(accounts) {
    $.ajax({
        url: "/accounts/import",
        data: {accounts},
        success: function (response) {
            console.log(response)
        }
    })
}
