package com.example.demo.Dto;

import lombok.Getter;

import java.util.Date;

@Getter
public class ErrorDetails {
    Date timestamp;
    String message;
    String Description;

    public ErrorDetails(Date timestamp, String message, String description) {
        this.timestamp = timestamp;
        this.message = message;
        Description = description;
    }
}
