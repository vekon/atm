package com.bank.atm.enums;

public enum ATMDenomination {
    FIFTY(50),
    TWENTY(20),
    TEN(10),
    FIVE(5);

    private final int value;

    private ATMDenomination(int value) {
        this.value = value;
    }

    public int value() {
        return this.value;
    }
}
