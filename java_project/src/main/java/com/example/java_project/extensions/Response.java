package com.example.java_project.extensions;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;

@Getter
@Setter
public class Response<T> {
    @Getter
    @Setter
    private HttpResult httpResult;

    @Getter
    @Setter
    private T response;

    @Getter
    @Setter
    private ArrayList<String> message;
}
