package com.bank.atm.service;

import com.bank.atm.model.ATM;
import com.bank.atm.model.Denomination;
import com.bank.atm.repository.ATMRepository;
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
import java.util.List;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@RunWith(SpringRunner.class)
@SpringBootTest
public class DenominationsServiceTest {
    @MockBean
    private DatabaseUpdater databaseUpdater;
    @Autowired
    private DenominationService denominationService;

    @MockBean
    private DenominationsRepository denominationsRepository;

    @Test
    public void getDenominations_Test() throws Exception{
        ATM atm = new ATM(1,1500);
        Denomination denomination1 = new Denomination(atm, 50, 20);
        Denomination denomination2 = new Denomination(atm, 20, 30);
        Denomination denomination3 = new Denomination(atm, 10, 30);
        Denomination denomination4 = new Denomination(atm, 5, 20);

        Mockito.when(
                denominationsRepository.findAll()).thenReturn(Arrays.asList(denomination1, denomination2, denomination3, denomination4));

        List<Denomination> denominations = denominationService.getDenominations();
        Assert.assertEquals(denominations.size(), 4);
        Assert.assertEquals(denominations.get(0), denomination1);
    }

    @Test
    public void getDenomination_Test() throws Exception{
        ATM atm = new ATM(1,1500);
        Denomination denomination1 = new Denomination(atm, 50, 20);
        Mockito.when(
                denominationsRepository.findOne(1)).thenReturn(denomination1);

        Denomination denomination = denominationService.getDenomination(1);
        Assert.assertEquals(denomination, denomination1);
    }

    @Test
    public void denominationSave_Test() throws Exception{
        ATM atm = new ATM(1,1500);
        Denomination denomination1 = new Denomination(atm, 50, 20);

        denominationService.createDenomination(denomination1);
        verify(denominationsRepository, times(1)).save(denomination1);
    }

    @Test
    public void denominationUpdate_Test() throws Exception{
        ATM atm = new ATM(1,1500);
        Denomination denomination1 = new Denomination(atm, 50, 20);

        denominationService.updateDenomination(denomination1);
        verify(denominationsRepository, times(1)).save(denomination1);
    }
}
