package com.emts.domain.repositories;

import com.emts.domain.models.Bill;
import com.emts.domain.repositories.common.Repository;

import java.util.concurrent.ConcurrentMap;

public class BillRepository extends Repository<Integer, Bill> {

    // In-memory repository for OOP practice

    public BillRepository(ConcurrentMap<Integer, Bill> database) {
        super(database);
    }
}
