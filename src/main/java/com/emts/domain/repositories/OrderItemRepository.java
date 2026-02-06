package com.emts.domain.repositories;

import com.emts.domain.models.OrderItem;
import com.emts.domain.repositories.common.Repository;

import java.util.concurrent.ConcurrentMap;

public class OrderItemRepository extends Repository<Integer, OrderItem> {

    // In-memory repository for OOP practice

    public OrderItemRepository(ConcurrentMap<Integer, OrderItem> database) {
        super(database);
    }
}