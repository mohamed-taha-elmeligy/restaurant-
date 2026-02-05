package com.emts.domain.repositories;

import com.emts.domain.models.Order;
import com.emts.exception.OrderException;
import com.emts.util.CrudOperation;

import java.util.ArrayList;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

public class OrderRepository implements CrudOperation<Integer, Order> {

    // In-memory repository for OOP practice

    private static final OrderRepository INSTANCE = new OrderRepository();
    private static final ConcurrentMap<Integer, Order> Database = new ConcurrentHashMap<>();

    private OrderRepository() {}

    public static OrderRepository getInstance() {
        return INSTANCE;
    }

    @Override
    public Order create(Order order) {
        if (order == null)
            throw new OrderException("Order cannot be null");

        if (Database.putIfAbsent(order.getId(), order) != null)
            throw new OrderException("Order already exists with ID: " + order.getId());

        return order;
    }

    @Override
    public Order update(Order order) {
        if (order == null)
            throw new OrderException("Order cannot be null");

        if (!Database.containsKey(order.getId()))
            throw new OrderException("Cannot update. Order not found with ID: " + order.getId());

        Database.put(order.getId(), order);
        return order;
    }

    @Override
    public Order findById(Integer id) {
        return Database.get(id);
    }

    @Override
    public Order delete(Integer id) {
        Order deleted = Database.remove(id);
        if (deleted == null)
            throw new OrderException("Cannot delete. Order not found with ID: " + id);

        return deleted;
    }

    @Override
    public ArrayList<Order> findAll() {
        return new ArrayList<>(Database.values());
    }

    @Override
    public boolean exists(Integer id) {
        return Database.containsKey(id);
    }
}