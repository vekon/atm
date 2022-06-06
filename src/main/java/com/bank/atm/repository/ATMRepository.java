package com.bank.atm.repository;

import com.bank.atm.model.ATM;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ATMRepository extends CrudRepository<ATM, Integer> {

}
