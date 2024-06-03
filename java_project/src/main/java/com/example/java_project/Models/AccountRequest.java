package com.example.java_project.Models;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class AccountRequest {
    @Getter
    @Setter
    private String key;

    @Getter
    @Setter
    private String userName;

    @Getter
    @Setter
    private String password;

    @Getter
    @Setter
    private boolean isActive;

    @Getter
    @Setter
    private String email;

    @Getter
    @Setter
    private String phoneNumber;

    @Getter
    @Setter
    private String avatarName;

    @Override
    public String toString() {
        return "Tutorial [key=" + key +
                ", userName=" + userName +
                ", password=" + password +
                ", isActive=" + isActive +
                ", email=" + email +
                ", phoneNumber=" + phoneNumber + "]";
    }
}

