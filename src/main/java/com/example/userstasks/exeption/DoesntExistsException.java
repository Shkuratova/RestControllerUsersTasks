package com.example.userstasks.exeption;

public class DoesntExistsException extends RuntimeException {
    public DoesntExistsException(String message){
        super(message);
    }
}
