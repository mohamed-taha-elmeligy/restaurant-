package com.emts.domain.repositories;

import com.emts.domain.models.Order;
import com.emts.domain.repositories.common.Repository;

import java.util.concurrent.ConcurrentMap;

public class OrderRepository extends Repository<Integer, Order> {

    // In-memory repository for OOP practice

    public OrderRepository(ConcurrentMap<Integer, Order> database) {
        super(database);
    }
}