package com.bank.atm.controller;

import com.bank.atm.model.ATM;
import com.bank.atm.service.ATMService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@RestController
@RequestMapping("/atms")
public class ATMController {

    @Autowired
    private ATMService atmService;

    @RequestMapping
    public List<ATM> atms() {
        return atmService.getATMs();
    }

    @RequestMapping("/{atmId}")
    public ATM atm(@PathVariable int atmId) {
        return atmService.getATM(atmId);
    }

    @RequestMapping(method = RequestMethod.POST)
    public void createATM(@RequestBody ATM atm) {
        atmService.createATM(atm);
    }

    @RequestMapping(method = RequestMethod.PUT)
    public void updateATM(@RequestBody ATM atm) {
        atmService.updateATM(atm);
    }

    @RequestMapping(method = RequestMethod.DELETE, value="/{atmId}")
    public void deleteATM(@PathVariable int atmId) {
        atmService.deleteATM(atmId);
    }
}
