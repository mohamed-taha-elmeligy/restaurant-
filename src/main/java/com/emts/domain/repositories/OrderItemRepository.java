package com.emts.domain.repositories;

import com.emts.domain.models.OrderItem;
import com.emts.exception.OrderItemException;
import com.emts.util.CrudOperation;

import java.util.ArrayList;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

public class OrderItemRepository implements CrudOperation<Integer, OrderItem> {

    // In-memory repository for OOP practice

    private static final OrderItemRepository INSTANCE = new OrderItemRepository();
    private static final ConcurrentMap<Integer, OrderItem> Database = new ConcurrentHashMap<>();

    private OrderItemRepository() {}

    public static OrderItemRepository getInstance() {
        return INSTANCE;
    }

    @Override
    public OrderItem create(OrderItem orderItem) {
        if (orderItem == null)
            throw new OrderItemException("OrderItem cannot be null");

        if (Database.putIfAbsent(orderItem.getId(), orderItem) != null)
            throw new OrderItemException("OrderItem already exists with ID: " + orderItem.getId());

        return orderItem;
    }

    @Override
    public OrderItem update(OrderItem orderItem) {
        if (orderItem == null)
            throw new OrderItemException("OrderItem cannot be null");

        if (!Database.containsKey(orderItem.getId()))
            throw new OrderItemException("Cannot update. OrderItem not found with ID: " + orderItem.getId());

        Database.put(orderItem.getId(), orderItem);
        return orderItem;
    }

    @Override
    public OrderItem findById(Integer id) {
        return Database.get(id);
    }

    @Override
    public OrderItem delete(Integer id) {
        OrderItem deleted = Database.remove(id);
        if (deleted == null)
            throw new OrderItemException("Cannot delete. OrderItem not found with ID: " + id);

        return deleted;
    }

    @Override
    public ArrayList<OrderItem> findAll() {
        return new ArrayList<>(Database.values());
    }

    @Override
    public boolean exists(Integer id) {
        return Database.containsKey(id);
    }
}