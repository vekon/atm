package com.bank.atm.controller;

import com.bank.atm.model.Denomination;
import com.bank.atm.service.DenominationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@RestController
@RequestMapping("/denominations")
public class DenominationContoller {

    @Autowired
    private DenominationService denominationService;

    @RequestMapping
    public List<Denomination> denominations() {
        return denominationService.getDenominations();
    }

    @RequestMapping("/{denominationId}")
    public Denomination denomination(@PathVariable int denominationId) {
        return denominationService.getDenomination(denominationId);
    }

    @RequestMapping(method = RequestMethod.POST)
    public void createDenomination(@RequestBody Denomination atmDenomination) {
        denominationService.createDenomination(atmDenomination);
    }

    @RequestMapping(method = RequestMethod.PUT)
    public void updateDenomination(@RequestBody Denomination atmDenomination) {
        denominationService.updateDenomination(atmDenomination);
    }

    @RequestMapping(method = RequestMethod.DELETE, value="/{denominationId}")
    public void deleteDenomination(@PathVariable int denominationId) {
        denominationService.deleteDenomination(denominationId);
    }
}
