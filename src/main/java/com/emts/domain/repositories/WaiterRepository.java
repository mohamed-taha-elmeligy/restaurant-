package com.emts.domain.repositories;

import com.emts.domain.models.Waiter;
import com.emts.exception.WaiterException;
import com.emts.util.CrudOperation;

import java.util.ArrayList;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

public class WaiterRepository implements CrudOperation<Integer, Waiter> {

    // In-memory repository for OOP practice

    private static final WaiterRepository INSTANCE = new WaiterRepository();
    private static final ConcurrentMap<Integer, Waiter> Database = new ConcurrentHashMap<>();

    private WaiterRepository() {}

    public static WaiterRepository getInstance() {
        return INSTANCE;
    }

    @Override
    public Waiter create(Waiter waiter) {
        if (waiter == null)
            throw new WaiterException("Waiter cannot be null");

        if (Database.putIfAbsent(waiter.getId(), waiter) != null)
            throw new WaiterException("Waiter already exists with ID: " + waiter.getId());

        return waiter;
    }

    @Override
    public Waiter update(Waiter waiter) {
        if (waiter == null)
            throw new WaiterException("Waiter cannot be null");

        if (!Database.containsKey(waiter.getId()))
            throw new WaiterException("Cannot update. Waiter not found with ID: " + waiter.getId());

        Database.put(waiter.getId(), waiter);
        return waiter;
    }

    @Override
    public Waiter findById(Integer id) {
        return Database.get(id);
    }

    @Override
    public Waiter delete(Integer id) {
        Waiter deleted = Database.remove(id);
        if (deleted == null)
            throw new WaiterException("Cannot delete. Waiter not found with ID: " + id);

        return deleted;
    }

    @Override
    public ArrayList<Waiter> findAll() {
        return new ArrayList<>(Database.values());
    }

    @Override
    public boolean exists(Integer id) {
        return Database.containsKey(id);
    }
}