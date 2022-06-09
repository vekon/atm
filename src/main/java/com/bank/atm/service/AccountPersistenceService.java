package com.bank.atm.service;

import com.bank.atm.dto.ATMCash;
import com.bank.atm.enums.ATMDenomination;
import com.bank.atm.model.ATM;
import com.bank.atm.model.Account;
import com.bank.atm.model.Denomination;
import com.bank.atm.repository.ATMRepository;
import com.bank.atm.repository.AccountRepository;
import com.bank.atm.repository.DenominationsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class AccountPersistenceService {

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private ATMRepository atmRepository;

    @Autowired
    private DenominationsRepository denominationsRepository;

    @Transactional
    public void processAccountUpdates(ATM atm, Account account, ATMCash atmCash) {
        atmRepository.save(atm);
        accountRepository.save(account);
        updateDenominations(atmCash, atm.getId());
    }

    private void updateDenominations(ATMCash atmCash, int atmId) {
        List<Denomination> atmDenominations = denominationsRepository.findByatmId(atmId);

        for (Denomination atmDenomination : atmDenominations){
            if (atmDenomination.getDenomination() == ATMDenomination.FIFTY.value()
                    && atmCash.getNoOf50Denominations() > 0) {
                atmDenomination.setCount(atmDenomination.getCount() - atmCash.getNoOf50Denominations());
                denominationsRepository.save(atmDenomination);
            }
            else if (atmDenomination.getDenomination() == ATMDenomination.TWENTY.value()
                    && atmCash.getNoOf20Denominations() > 0) {
                atmDenomination.setCount(atmDenomination.getCount() - atmCash.getNoOf20Denominations());
                denominationsRepository.save(atmDenomination);
            }
            else if (atmDenomination.getDenomination() == ATMDenomination.TEN.value()
                    && atmCash.getNoOf10Denominations() > 0) {
                atmDenomination.setCount(atmDenomination.getCount() - atmCash.getNoOf10Denominations());
                denominationsRepository.save(atmDenomination);
            }
            else if (atmDenomination.getDenomination() == ATMDenomination.FIVE.value()
                    && atmCash.getNoOf5Denominations() > 0) {
                atmDenomination.setCount(atmDenomination.getCount() - atmCash.getNoOf5Denominations());
                denominationsRepository.save(atmDenomination);
            }
        }
    }
}
