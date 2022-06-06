package com.bank.atm.model;

import javax.persistence.*;

@Entity
public class Transaction {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private String transactionType;
    private int transactionAmount;
    private int balance;
    @ManyToOne
    private Account account;

    public Transaction() {
        super();
    }

    public Transaction(int id, String transactionType, int transactionAmount, int balance, Account account) {
        super();
        this.id = id;
        this.transactionType = transactionType;
        this.transactionAmount = transactionAmount;
        this.balance = balance;
        this.account = account;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTransactionType() {
        return transactionType;
    }

    public void setTransactionType(String transactionType) {
        this.transactionType = transactionType;
    }

    public int getTransactionAmount() {
        return transactionAmount;
    }

    public void setTransactionAmount(int transactionAmount) {
        this.transactionAmount = transactionAmount;
    }

    public int getBalance() {
        return balance;
    }

    public void setBalance(int balance) {
        this.balance = balance;
    }

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }
}
