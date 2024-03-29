package com.example.demo.Exception;

import lombok.Getter;

@Getter
public class BlogApiException extends RuntimeException {
    private String message;

    public BlogApiException(String message) {
        super(message); // to initialize message thorw parent runtimeexception class
        this.message = message;
    }
}
