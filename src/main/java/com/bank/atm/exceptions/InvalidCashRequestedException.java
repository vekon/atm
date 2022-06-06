package com.bank.atm.exceptions;

public class InvalidCashRequestedException extends Exception {
    private String message;

    public InvalidCashRequestedException() {
    }

    public InvalidCashRequestedException(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
