package com.bank.atm.dto;

public class ATMCash {
    int noOf50Denominations;
    int noOf20Denominations;
    int noOf10Denominations;
    int noOf5Denominations;
    int remainingBalance;

    public int getNoOf50Denominations() {
        return noOf50Denominations;
    }

    public void setNoOf50Denominations(int noOf50Denominations) {
        this.noOf50Denominations = noOf50Denominations;
    }

    public int getNoOf20Denominations() {
        return noOf20Denominations;
    }

    public void setNoOf20Denominations(int noOf20Denominations) {
        this.noOf20Denominations = noOf20Denominations;
    }

    public int getNoOf10Denominations() {
        return noOf10Denominations;
    }

    public void setNoOf10Denominations(int noOf10Denominations) {
        this.noOf10Denominations = noOf10Denominations;
    }

    public int getNoOf5Denominations() {
        return noOf5Denominations;
    }

    public void setNoOf5Denominations(int noOf5Denominations) {
        this.noOf5Denominations = noOf5Denominations;
    }

    public int getRemainingBalance() {
        return remainingBalance;
    }

    public void setRemainingBalance(int remaingBalance) {
        this.remainingBalance = remaingBalance;
    }
}
