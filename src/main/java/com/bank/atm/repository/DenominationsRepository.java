package com.bank.atm.repository;

import com.bank.atm.model.Denomination;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DenominationsRepository extends CrudRepository<Denomination, Integer> {
    public List<Denomination> findByatmId(int atmId);
}
