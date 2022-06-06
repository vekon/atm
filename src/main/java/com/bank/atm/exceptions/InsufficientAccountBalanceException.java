package com.bank.atm.exceptions;

public class InsufficientAccountBalanceException extends Exception {
    private String message;

    public InsufficientAccountBalanceException() {
    }

    public InsufficientAccountBalanceException(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
