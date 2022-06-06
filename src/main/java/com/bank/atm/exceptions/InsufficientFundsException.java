package com.bank.atm.exceptions;

public class InsufficientFundsException extends Exception {

    private String message;

    public InsufficientFundsException() {
    }

    public InsufficientFundsException(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
