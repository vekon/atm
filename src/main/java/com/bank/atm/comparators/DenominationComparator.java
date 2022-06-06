package com.bank.atm.comparators;

import com.bank.atm.model.Denomination;

import java.util.Comparator;

public class DenominationComparator implements Comparator<Denomination> {
    @Override
    public int compare(Denomination o1, Denomination o2) {
        return o2.getDenomination() - o1.getDenomination();
    }
}
