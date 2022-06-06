package com.bank.atm.controller;

import com.bank.atm.dto.ATMCash;
import com.bank.atm.exceptions.InsufficientAccountBalanceException;
import com.bank.atm.exceptions.InsufficientFundsException;
import com.bank.atm.exceptions.InvalidCashRequestedException;
import com.bank.atm.exceptions.InvalidCredentialsException;
import com.bank.atm.service.AccountService;
import com.bank.atm.model.Account;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@RestController
public class AccountController extends BaseController{

    @Autowired
    private AccountService accountService;

    @RequestMapping("/accounts")
    public List<Account> accounts() {
        return accountService.getAccounts();
    }

    @RequestMapping("/accounts/{accountNumber}")
    public Account account(@PathVariable int accountNumber) {
        return accountService.getAccount(accountNumber);
    }

    @RequestMapping(method = RequestMethod.POST, value="/accounts")
    public void createAccount(@RequestBody Account account) {
        accountService.createAccount(account);
    }

    @RequestMapping(method = RequestMethod.PUT, value="/accounts")
    public void updateAccount(@RequestBody Account account) {
        accountService.updateAccount(account);
    }

    @RequestMapping(method = RequestMethod.DELETE, value="/accounts/{accountNumber}")
    public void deleteAccount(@PathVariable int accountNumber) {
        accountService.deleteAccount(accountNumber);
    }

    @RequestMapping(method = RequestMethod.POST, value="/withdraw")
    public ResponseEntity<Object> withdraw(@RequestParam int accountNumber, @RequestParam int pin, @RequestParam int amount) {
        ATMCash atmCash = null;
        try {
            atmCash = accountService.withdraw(accountNumber, pin, amount);
        } catch (InvalidCredentialsException e) {
            return buildInvalidCredentialsMessage(e);
        } catch (InsufficientAccountBalanceException e) {
            return buildInsufficientAccountBalanceMessage(e);
        } catch (InsufficientFundsException e) {
            return buildInsufficientFundsMessage(e);
        } catch (InvalidCashRequestedException e) {
            return buildInvalidAmountRequestedMessage(e);
        }
        return ResponseEntity.ok().body(atmCash);
    }

    @RequestMapping(method = RequestMethod.GET, value="/balance")
    public ResponseEntity<Object> balance(@RequestParam int accountNumber, @RequestParam int pin) {
        String accountBalance = "";
        try {
            Account account = accountService.balance(accountNumber, pin);
            accountBalance = "Balance in you account : "+account.getOpeningBalance();
        } catch (InvalidCredentialsException e) {
            return buildInvalidCredentialsMessage(e);
        }
        return ResponseEntity.ok().body(accountBalance);
    }
}
