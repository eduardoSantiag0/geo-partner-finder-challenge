package com.eduardoSantiag0.ze_code.application.errors;

public class InvalidCredentialsException extends RuntimeException{

    public InvalidCredentialsException(String message) {
        super(message);
    }
}
