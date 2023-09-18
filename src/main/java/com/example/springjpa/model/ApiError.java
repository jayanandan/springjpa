package com.example.springjpa.model;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class ApiError {
    private String message;

    public ApiError(String message) {
        this.message = message;
    }
}
