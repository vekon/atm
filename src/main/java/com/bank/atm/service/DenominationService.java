package com.bank.atm.service;

import com.bank.atm.model.Denomination;
import com.bank.atm.repository.DenominationsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class DenominationService {

    @Autowired
    private DenominationsRepository denominationsRepository;

    public List<Denomination> getDenominations() {
        List<Denomination> atmDenominations = new ArrayList<>();
        denominationsRepository.findAll().forEach(atmDenominations::add);
        return atmDenominations;
    }

    public Denomination getDenomination(int denominationId) {
        return denominationsRepository.findOne(denominationId);
    }

    public void createDenomination(Denomination atmDenomination) {
        denominationsRepository.save(atmDenomination);
    }

    public void updateDenomination(Denomination atmDenomination) {
        denominationsRepository.save(atmDenomination);
    }

    public void deleteDenomination(int denominationId) {
        denominationsRepository.delete(denominationId);
    }
}
