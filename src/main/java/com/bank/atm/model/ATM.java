package com.bank.atm.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class ATM {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private int availableCash;

    public ATM() {
        super();
    }

    public ATM(int availableCash) {
        super();
        this.availableCash = availableCash;
    }

    public ATM(int id, int availableCash) {
        this.id = id;
        this.availableCash = availableCash;
    }

    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public int getAvailableCash() {
        return availableCash;
    }
    public void setAvailableCash(int availableCash) {
        this.availableCash = availableCash;
    }
}
