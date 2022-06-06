package com.bank.atm.tests;

import com.bank.atm.controller.AccountController;
import com.bank.atm.model.Account;
import com.bank.atm.service.AccountService;
import com.bank.atm.utils.DatabaseUpdater;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.skyscreamer.jsonassert.JSONAssert;
import org.springframework.beans.factory.annotation.Autowired;
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
@WebMvcTest(value = AccountController.class)
public class AccountControllerTest {
    @MockBean
    private DatabaseUpdater databaseUpdater;

    @MockBean
    private AccountService accountService;
    @Autowired
    MockMvc mockMvc;

    @Test
    public void getAccounts_Test() throws Exception{
        Account account1 = new Account(2,123456789, 1234, 800, 200);
        Account account2 = new Account(3,987654321, 4321, 1230, 150);

        Mockito.when(
                accountService.getAccounts()).thenReturn(Arrays.asList(account1, account2));

        RequestBuilder requestBuilder = MockMvcRequestBuilders.get(
                "/accounts").accept(
                MediaType.APPLICATION_JSON);
        MvcResult result = mockMvc.perform(requestBuilder).andReturn();

        String expected = "[{\"id\":2,\"accountNumber\":123456789,\"pin\":1234,\"openingBalance\":800,\"overDraft\":200},{\"id\":3,\"accountNumber\":987654321,\"pin\":4321,\"openingBalance\":1230,\"overDraft\":150}]";

        JSONAssert.assertEquals(expected, result.getResponse()
                .getContentAsString(), false);
    }

    @Test
    public void getAccount_Test() throws Exception{
        Account account1 = new Account(2,123456789, 1234, 800, 200);

        Mockito.when(
                accountService.getAccount(123456789)).thenReturn(account1);

        RequestBuilder requestBuilder = MockMvcRequestBuilders.get(
                "/accounts/123456789").accept(
                MediaType.APPLICATION_JSON);
        MvcResult result = mockMvc.perform(requestBuilder).andReturn();

        String expected = "{\"id\":2,\"accountNumber\":123456789,\"pin\":1234,\"openingBalance\":800,\"overDraft\":200}";

        JSONAssert.assertEquals(expected, result.getResponse()
                .getContentAsString(), false);
    }
}
