package com.bank.atm.service;

import com.bank.atm.dto.ATMCash;
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

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import java.util.Arrays;

@RunWith(SpringRunner.class)
@SpringBootTest
public class AccountPersistanceServiceTest {
    @MockBean
    private DatabaseUpdater databaseUpdater;
    @Autowired
    private AccountPersistenceService accountPersistenceService;
    @Autowired
    private ATMService atmService;
    @Autowired
    private DenominationService denominationService;

    @MockBean
    private AccountRepository accountRepository;
    @MockBean
    private ATMRepository atmRepository;
    @MockBean
    private DenominationsRepository denominationsRepository;

    @Test
    public void denominationsUpdate_Test() throws Exception{
        Account account1 = new Account(1,123456789, 1234, 800, 200);
        ATM atm = new ATM(1,1500);
        Denomination denomination1 = new Denomination(atm, 50, 20);
        Denomination denomination2 = new Denomination(atm, 20, 30);
        Denomination denomination3 = new Denomination(atm, 10, 30);
        Denomination denomination4 = new Denomination(atm, 5, 20);
        ATMCash atmCash = new ATMCash();
        atmCash.setNoOf50Denominations(2);
        atmCash.setNoOf5Denominations(1);
        Mockito.when(
                denominationsRepository.findByatmId(1)).thenReturn(Arrays.asList(denomination1, denomination2, denomination3, denomination4));
        accountPersistenceService.processAccountUpdates(atm, account1, atmCash);
        verify(atmRepository, times(1)).save(atm);
        verify(accountRepository, times(1)).save(account1);
        verify(denominationsRepository, times(1)).save(denomination1);
        verify(denominationsRepository, times(1)).save(denomination4);
        verify(denominationsRepository, times(0)).save(denomination2);
        verify(denominationsRepository, times(0)).save(denomination3);
    }
}
