package com.example.java_project.Models;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class RegisterRequest {
    @Getter
    @Setter
    private String userName;

    @Getter
    @Setter
    private String password;

    @Getter
    @Setter
    private String samePassword;

    @Getter
    @Setter
    private boolean isActive = true;

    @Getter
    @Setter
    private String email;

    @Getter
    @Setter
    private String phoneNumber;

    @Override
    public String toString() {
        return "Tutorial [userName=" + userName +
                ", password=" + password +
                ", samePassword=" + samePassword +
                ", isActive=" + isActive +
                ", email=" + email +
                ", phoneNumber=" + phoneNumber + "]";
    }
}
