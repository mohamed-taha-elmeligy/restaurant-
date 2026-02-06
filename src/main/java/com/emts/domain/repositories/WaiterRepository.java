package com.emts.domain.repositories;

import com.emts.domain.models.Waiter;
import com.emts.domain.repositories.common.Repository;

import java.util.concurrent.ConcurrentMap;

public class WaiterRepository extends Repository<Integer, Waiter> {

    // In-memory repository for OOP practice

    public WaiterRepository(ConcurrentMap<Integer, Waiter> database) {
        super(database);
    }
}