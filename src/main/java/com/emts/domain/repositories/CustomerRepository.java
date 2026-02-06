package com.emts.domain.repositories;

import com.emts.domain.models.Customer;
import com.emts.domain.repositories.common.Repository;

import java.util.concurrent.ConcurrentMap;

public class CustomerRepository extends Repository<Integer,Customer> {

    // In-memory repository for OOP practice

    public CustomerRepository(ConcurrentMap<Integer, Customer> database) {
        super(database);
    }
}
