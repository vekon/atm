package com.bank.atm.service;

import com.bank.atm.comparators.DenominationComparator;
import com.bank.atm.dto.ATMCash;
import com.bank.atm.enums.ATMDenomination;
import com.bank.atm.exceptions.InsufficientAccountBalanceException;
import com.bank.atm.exceptions.InsufficientFundsException;
import com.bank.atm.exceptions.InvalidCashRequestedException;
import com.bank.atm.exceptions.InvalidCredentialsException;
import com.bank.atm.model.ATM;
import com.bank.atm.model.Denomination;
import com.bank.atm.repository.ATMRepository;
import com.bank.atm.repository.AccountRepository;
import com.bank.atm.repository.DenominationsRepository;
import com.bank.atm.model.Account;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service
public class AccountService {

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private ATMRepository atmRepository;

    @Autowired
    private DenominationsRepository denominationsRepository;

    @Autowired
    private AccontBussinessLogic accontBussinessLogic;

    public List<Account> getAccounts() {
        List<Account> accounts = new ArrayList<>();
        accountRepository.findAll().forEach(accounts::add);
        return accounts;
    }

    public Account getAccount(int accountId) {
        return accountRepository.findByAccountNumber(accountId);
    }

    public void createAccount(Account account) {
        accountRepository.save(account);
    }

    public void updateAccount(Account account) {
        accountRepository.save(account);
    }

    public void deleteAccount(int accountNumber) {
        accountRepository.deleteByAccountNumber(accountNumber);
    }

    public ATMCash withdraw(int accountNumber, int pin, int requestedAmount) throws
            InvalidCredentialsException, InsufficientAccountBalanceException, InsufficientFundsException, InvalidCashRequestedException {
        Account account = validateAccount(accountNumber, pin);

        ATM atm = validateATMFunds(requestedAmount);
        atm.setAvailableCash(atm.getAvailableCash() - requestedAmount);

        validateAccountBalance(account, requestedAmount);
        account.setOpeningBalance(account.getOpeningBalance() - requestedAmount);

        ATMCash atmCash = processDenominations(atm.getId(), requestedAmount);
        atmCash.setRemainingBalance(account.getOpeningBalance());

        /*We need to update/reduce denominations in atm,
            deduct funds from atm &
            deduct amount from accounts on each withdrawal*/

        accontBussinessLogic.processAccountUpdates(atm, account, atmCash);
        return atmCash;
    }

    private ATMCash processDenominations(int atmId, int requestedAmount) throws InvalidCashRequestedException {
        ATMCash atmCash = new ATMCash();
        List<Denomination> atmDenominations = denominationsRepository.findByatmId(atmId);

        /*Sort denominations by desc order so that we process high value ones first
            and return minimum denominations for the requested cash*/
        sortDenominationsByDesc(atmDenominations);

        for (Denomination atmDenomination : atmDenominations){
            if (requestedAmount >= ATMDenomination.FIFTY.value() &&
                    atmDenomination.getDenomination() == ATMDenomination.FIFTY.value()) {
                int noOf50Denominations = requestedAmount/ATMDenomination.FIFTY.value();
                if (atmDenomination.getCount() >= noOf50Denominations) {
                    atmCash.setNoOf50Denominations(noOf50Denominations);
                    requestedAmount = requestedAmount % ATMDenomination.FIFTY.value();
                } else if (atmDenomination.getCount() > 0) {
                    noOf50Denominations = atmDenomination.getCount();
                    atmCash.setNoOf50Denominations(noOf50Denominations);
                    requestedAmount = requestedAmount - (noOf50Denominations * ATMDenomination.FIFTY.value());
                }
            }
            if (requestedAmount >= ATMDenomination.TWENTY.value() &&
                    atmDenomination.getDenomination() == ATMDenomination.TWENTY.value()) {
                int noOf20Denominations = requestedAmount/ATMDenomination.TWENTY.value();
                if (atmDenomination.getCount() >= noOf20Denominations) {
                    atmCash.setNoOf20Denominations(noOf20Denominations);
                    requestedAmount = requestedAmount % ATMDenomination.TWENTY.value();
                } else if (atmDenomination.getCount() > 0) {
                    noOf20Denominations = atmDenomination.getCount();
                    atmCash.setNoOf20Denominations(noOf20Denominations);
                    requestedAmount = requestedAmount - (noOf20Denominations * ATMDenomination.TWENTY.value());
                }
            }
            if (requestedAmount >= ATMDenomination.TEN.value() &&
                    atmDenomination.getDenomination() == ATMDenomination.TEN.value()) {
                int noOf10Denominations = requestedAmount/ATMDenomination.TEN.value();
                if (atmDenomination.getCount() >= noOf10Denominations) {
                    atmCash.setNoOf10Denominations(noOf10Denominations);
                    requestedAmount = requestedAmount % ATMDenomination.TEN.value();
                } else if (atmDenomination.getCount() > 0) {
                    noOf10Denominations = atmDenomination.getCount();
                    atmCash.setNoOf10Denominations(noOf10Denominations);
                    requestedAmount = requestedAmount - (noOf10Denominations * ATMDenomination.TEN.value());
                }
            }
            if (requestedAmount >= ATMDenomination.FIVE.value() &&
                    atmDenomination.getDenomination() == ATMDenomination.FIVE.value()) {
                int noOf5Denominations = requestedAmount/ATMDenomination.FIVE.value();
                if (atmDenomination.getCount() >= noOf5Denominations) {
                    atmCash.setNoOf5Denominations(noOf5Denominations);
                    requestedAmount = requestedAmount % ATMDenomination.FIVE.value();
                } else if (atmDenomination.getCount() > 0) {
                    noOf5Denominations = atmDenomination.getCount();
                    atmCash.setNoOf5Denominations(noOf5Denominations);
                    requestedAmount = requestedAmount - (noOf5Denominations * ATMDenomination.FIVE.value());
                }
            }
        }
        if (requestedAmount >0) {
            throw new InvalidCashRequestedException("Could not dispense requested cash, Please enter denominations in 50, 20, 10 & 5 only");
        }
        return atmCash;
    }

    private void sortDenominationsByDesc(List<Denomination> denominations) {
        Collections.sort(denominations, new DenominationComparator());
    }

    private Account validateAccount(int accountNumber, int pin) throws InvalidCredentialsException {
        Account account = accountRepository.findByAccountNumberAndPin(accountNumber, pin);
        if (account == null) {
            throw new InvalidCredentialsException("Invalid credentials entered.");
        }
        return account;
    }

    private Account validateAccountBalance(Account account, int requestedAmount) throws InsufficientAccountBalanceException {
        if (account.getOpeningBalance() + account.getOverDraft() < requestedAmount) {
            throw new InsufficientAccountBalanceException("Insufficient account balance.");
        }
        return account;
    }

    private ATM validateATMFunds(int requestedAmount) throws InsufficientFundsException {
        ATM atm = atmRepository.findAll().iterator().next();
        if (atm != null && atm.getAvailableCash() < requestedAmount) {
            throw new InsufficientFundsException("Insufficient funds in ATM");
        }
        return atm;
    }

    public Account balance(int accountNumber, int pin) throws InvalidCredentialsException {
        return validateAccount(accountNumber, pin);
    }
}
