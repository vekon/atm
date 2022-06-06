package com.bank.atm.exceptions;

public class InvalidCredentialsException extends Exception {

    private String message;

    public InvalidCredentialsException() {
    }

    public InvalidCredentialsException(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
