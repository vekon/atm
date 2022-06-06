package com.bank.atm.repository;

import com.bank.atm.model.Account;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AccountRepository extends CrudRepository<Account, Integer> {
    Account findByAccountNumber(int accountNumber);
    void deleteByAccountNumber(int accountNumber);
    Account findByAccountNumberAndPin(int accountNumber, int pin);
}
