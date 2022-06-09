package com.bank.atm.service;

import com.bank.atm.model.ATM;
import com.bank.atm.model.Denomination;
import com.bank.atm.repository.ATMRepository;
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
import java.util.List;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ATMServiceTest {
    @MockBean
    private DatabaseUpdater databaseUpdater;
    @Autowired
    private ATMService atmService;

    @MockBean
    private ATMRepository atmRepository;

    @Test
    public void getAtms_Test() throws Exception{
        ATM atm = new ATM(1,1500);
        Mockito.when(
                atmRepository.findAll()).thenReturn(Arrays.asList(atm));
        List<ATM> atms = atmService.getATMs();
        Assert.assertEquals(atms.size(), 1);
        Assert.assertEquals(atms.get(0), atm);
    }

    @Test
    public void atmSave_Test() throws Exception{
        ATM atm = new ATM(1,1500);

        atmService.createATM(atm);
        verify(atmRepository, times(1)).save(atm);
    }

    @Test
    public void atmUpdate_Test() throws Exception{
        ATM atm = new ATM(1,1500);
        atmService.updateATM(atm);
        verify(atmRepository, times(1)).save(atm);
    }
}
