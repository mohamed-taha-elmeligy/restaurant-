package com.emts.domain.repositories;

import com.emts.domain.models.Table;
import com.emts.domain.repositories.common.Repository;

import java.util.concurrent.ConcurrentMap;

public class TableRepository extends Repository<Integer, Table> {

    // In-memory repository for OOP practice

    public TableRepository(ConcurrentMap<Integer, Table> database) {
        super(database);
    }
}