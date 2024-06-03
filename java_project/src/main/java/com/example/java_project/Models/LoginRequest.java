package com.example.java_project.Models;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class LoginRequest {
    private String userName;

    private String password;

    @Override
    public String toString() {
        return "Tutorial [userName=" + userName + ", password=" + password + "]";
    }
}
