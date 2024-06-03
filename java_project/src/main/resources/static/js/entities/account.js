class Account {
    constructor(key, userName, password, isActive, email, phoneNumber, avatarName) {
        this.key = key;
        this.userName = userName;
        this.password = password;
        this.isActive = isActive;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.avatarName = avatarName
    }

    validateEmail() {
        return String(this.email).toLowerCase().match(
            /^(([^<>()[\]\\.,;:\s@"]+(\.[^<>()[\]\\.,;:\s@"]+)*)|.(".+"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/
        );
    }
}