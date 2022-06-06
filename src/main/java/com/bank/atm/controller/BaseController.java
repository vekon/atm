package com.bank.atm.controller;

import com.bank.atm.dto.ErrorMessage;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class BaseController {

    protected ResponseEntity<Object> buildInvalidCredentialsMessage(Exception ex) {
        ErrorMessage errorMessage = new ErrorMessage(ex.getMessage());
        System.out.println(ex.getMessage());
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(errorMessage);
    }

    protected ResponseEntity<Object> buildInsufficientAccountBalanceMessage(Exception ex) {
        ErrorMessage errorMessage = new ErrorMessage(ex.getMessage());
        System.out.println(ex.getMessage());
        return ResponseEntity.status(HttpStatus.FORBIDDEN).body(errorMessage);
    }

    protected ResponseEntity<Object> buildInsufficientFundsMessage(Exception ex) {
        ErrorMessage errorMessage = new ErrorMessage(ex.getMessage());
        System.out.println(ex.getMessage());
        return ResponseEntity.status(HttpStatus.FORBIDDEN).body(errorMessage);
    }

    protected ResponseEntity<Object> buildInvalidAmountRequestedMessage(Exception ex) {
        ErrorMessage errorMessage = new ErrorMessage(ex.getMessage());
        System.out.println(ex.getMessage());
        return ResponseEntity.status(HttpStatus.FORBIDDEN).body(errorMessage);
    }
}
