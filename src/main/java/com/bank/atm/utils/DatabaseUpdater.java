package com.bank.atm.utils;

import com.bank.atm.model.ATM;
import com.bank.atm.model.Account;
import com.bank.atm.model.Denomination;
import com.bank.atm.service.ATMService;
import com.bank.atm.service.AccountService;
import com.bank.atm.service.DenominationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Component
public class DatabaseUpdater {

    @Autowired
    private ATMService atmService;

    @Autowired
    private AccountService accountService;

    @Autowired
    private DenominationService denominationService;

    public void prepareATMDataBase(){
        ATM atm = new ATM(1500);
        atmService.createATM(atm);

        Account account = new Account(123456789, 1234, 800, 200);
        accountService.createAccount(account);
        account = new Account(987654321, 4321, 1230, 150);
        accountService.createAccount(account);

        Denomination denomination = new Denomination(atm, 50, 20);
        denominationService.createDenomination(denomination);
        denomination = new Denomination(atm, 20, 30);
        denominationService.createDenomination(denomination);
        denomination = new Denomination(atm, 10, 30);
        denominationService.createDenomination(denomination);
        denomination = new Denomination(atm, 5, 20);
        denominationService.createDenomination(denomination);
    }
}
