package com.bank.atm.service;

import com.bank.atm.dto.ATMCash;
import com.bank.atm.exceptions.InsufficientAccountBalanceException;
import com.bank.atm.exceptions.InsufficientFundsException;
import com.bank.atm.exceptions.InvalidCashRequestedException;
import com.bank.atm.exceptions.InvalidCredentialsException;
import com.bank.atm.model.ATM;
import com.bank.atm.model.Account;
import com.bank.atm.model.Denomination;
import com.bank.atm.repository.ATMRepository;
import com.bank.atm.repository.AccountRepository;
import com.bank.atm.repository.DenominationsRepository;
import com.bank.atm.utils.DatabaseUpdater;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;

@RunWith(SpringRunner.class)
@SpringBootTest
public class AccountServiceTest {
    @MockBean
    private DatabaseUpdater databaseUpdater;
    @Autowired
    private AccountService accountService;

    @MockBean
    private AccountRepository accountRepository;
    @MockBean
    private ATMRepository atmRepository;
    @MockBean
    private DenominationsRepository denominationsRepository;

    @Test
    public void withdrawSuccess_Test() throws Exception{
        Account account1 = new Account(1,123456789, 1234, 800, 200);
        Account account2 = new Account(2,987654321, 4321, 1230, 150);
        ATM atm = new ATM(1,1500);
        Denomination denomination1 = new Denomination(atm, 50, 20);
        Denomination denomination2 = new Denomination(atm, 20, 30);
        Denomination denomination3 = new Denomination(atm, 10, 30);
        Denomination denomination4 = new Denomination(atm, 5, 20);

        Mockito.when(
                accountRepository.findByAccountNumberAndPin(123456789, 1234)).thenReturn(account1);
        Mockito.when(
                atmRepository.findAll()).thenReturn(Arrays.asList(atm));
        Mockito.when(
                denominationsRepository.findByatmId(1)).thenReturn(Arrays.asList(denomination1, denomination2, denomination3, denomination4));

        ATMCash atmCash = accountService.withdraw(123456789, 1234, 235);
        Assert.assertEquals(atmCash.getNoOf50Denominations(),4);
        Assert.assertEquals(atmCash.getNoOf20Denominations(),1);
        Assert.assertEquals(atmCash.getNoOf10Denominations(),1);
        Assert.assertEquals(atmCash.getNoOf5Denominations(),1);
        Assert.assertEquals(atmCash.getRemainingBalance(),565);
    }

    @Test(expected = InvalidCashRequestedException.class)
    public void withdrawInvalidCash_Test() throws Exception{
        Account account1 = new Account(1,123456789, 1234, 800, 200);
        Account account2 = new Account(2,987654321, 4321, 1230, 150);
        ATM atm = new ATM(1,1500);
        Denomination denomination1 = new Denomination(atm, 50, 20);
        Denomination denomination2 = new Denomination(atm, 20, 30);
        Denomination denomination3 = new Denomination(atm, 10, 30);
        Denomination denomination4 = new Denomination(atm, 5, 20);

        Mockito.when(
                accountRepository.findByAccountNumberAndPin(123456789, 1234)).thenReturn(account1);
        Mockito.when(
                atmRepository.findAll()).thenReturn(Arrays.asList(atm));
        Mockito.when(
                denominationsRepository.findByatmId(1)).thenReturn(Arrays.asList(denomination1, denomination2, denomination3, denomination4));

        accountService.withdraw(123456789, 1234, 122);
    }

    @Test(expected = InvalidCredentialsException.class)
    public void withdrawInvalidCreds_Test() throws Exception{
        Account account1 = new Account(1,123456789, 1234, 800, 200);
        Mockito.when(
                accountRepository.findByAccountNumberAndPin(123456789, 1234)).thenReturn(account1);
        accountService.withdraw(123456789, 1111, 100);
    }

    @Test(expected = InsufficientFundsException.class)
    public void withdrawInsufficentFunds_Test() throws Exception{
        Account account1 = new Account(1,123456789, 1234, 800, 200);
        ATM atm = new ATM(1,500);
        Mockito.when(
                accountRepository.findByAccountNumberAndPin(123456789, 1234)).thenReturn(account1);
        Mockito.when(
                atmRepository.findAll()).thenReturn(Arrays.asList(atm));
        accountService.withdraw(123456789, 1234, 600);
    }

    @Test(expected = InsufficientAccountBalanceException.class)
    public void withdrawInsufficentAccountBalance_Test() throws Exception{
        Account account1 = new Account(1,123456789, 1234, 800, 200);
        ATM atm = new ATM(1,1500);
        Mockito.when(
                accountRepository.findByAccountNumberAndPin(123456789, 1234)).thenReturn(account1);
        Mockito.when(
                atmRepository.findAll()).thenReturn(Arrays.asList(atm));
        accountService.withdraw(123456789, 1234, 1100);
    }
}
