package com.bank.atm.model;

import javax.persistence.*;

@Entity
public class Denomination {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    @ManyToOne
    private ATM atm;
    private int denomination;
    private int count;

    public Denomination() {
        super();
    }

    public Denomination( ATM atm, int denomination, int count) {
        super();
        this.atm = atm;
        this.denomination = denomination;
        this.count = count;
    }

    public Denomination(int id, ATM atm, int denomination, int count) {
        super();
        this.id = id;
        this.atm = atm;
        this.denomination = denomination;
        this.count = count;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public ATM getAtm() {
        return atm;
    }

    public void setAtm(ATM atm) {
        this.atm = atm;
    }

    public int getDenomination() {
        return denomination;
    }

    public void setDenomination(int denomination) {
        this.denomination = denomination;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }
}
