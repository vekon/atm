package com.bank.atm.service;

import com.bank.atm.model.ATM;
import com.bank.atm.repository.ATMRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ATMService {

    @Autowired
    private ATMRepository atmRepository;

    public List<ATM> getATMs() {
        List<ATM> atms = new ArrayList<>();
        atmRepository.findAll().forEach(atms::add);
        return atms;
    }

    public ATM getATM(int atmId) {
        return atmRepository.findOne(atmId);
    }

    public void createATM(ATM atm) {
        atmRepository.save(atm);
    }

    public void updateATM(ATM atm) {
        atmRepository.save(atm);
    }

    public void deleteATM(int atmId) {
        atmRepository.delete(atmId);
    }
}
