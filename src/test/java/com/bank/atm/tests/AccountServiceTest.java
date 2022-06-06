package com.bank.atm.tests;

import com.bank.atm.dto.ATMCash;
import com.bank.atm.model.ATM;
import com.bank.atm.model.Account;
import com.bank.atm.model.Denomination;
import com.bank.atm.service.ATMService;
import com.bank.atm.service.AccountService;
import com.bank.atm.service.DenominationService;
import com.bank.atm.utils.DatabaseUpdater;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.skyscreamer.jsonassert.JSONAssert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.Arrays;

@RunWith(SpringRunner.class)
@DataJpaTest
public class AccountServiceTest {
    @MockBean
    private DatabaseUpdater databaseUpdater;
    @MockBean
    private AccountService accountService;
    @MockBean
    private ATMService atmService;
    @MockBean
    private DenominationService denominationService;

    @Autowired
    private TestEntityManager entityManager;

    @Test
    public void withdraw_Test() throws Exception{
        Account account1 = new Account(2,123456789, 1234, 800, 200);
        Account account2 = new Account(3,987654321, 4321, 1230, 150);
        ATM atm = new ATM(1500);
        Denomination denomination1 = new Denomination(atm, 50, 20);
        Denomination denomination2 = new Denomination(atm, 20, 30);
        Denomination denomination3 = new Denomination(atm, 10, 30);
        Denomination denomination4 = new Denomination(atm, 5, 20);

        Mockito.when(
                accountService.getAccounts()).thenReturn(Arrays.asList(account1, account2));
        Mockito.when(
                atmService.getATMs()).thenReturn(Arrays.asList(atm));
        Mockito.when(
                denominationService.getDenominations()).thenReturn(Arrays.asList(denomination1, denomination2, denomination3, denomination4));

        ATMCash atmCash = accountService.withdraw(123456789, 1234, 100);
    }
}
