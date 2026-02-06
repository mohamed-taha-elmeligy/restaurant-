package com.emts.domain.repositories;

import com.emts.domain.models.Waiter;
import com.emts.exception.WaiterException;
import com.emts.util.crud.CrudOperation;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentMap;

public class WaiterRepository implements CrudOperation<Integer, Waiter> {

    // In-memory repository for OOP practice
    private final ConcurrentMap<Integer, Waiter> database;

    public WaiterRepository(ConcurrentMap<Integer, Waiter> database) {
        this.database = database;
    }

    @Override
    public Waiter create(Waiter waiter) {
        if (waiter == null)
            throw new WaiterException("Waiter cannot be null");

        if (database.putIfAbsent(waiter.getId(), waiter) != null)
            throw new WaiterException("Waiter already exists with ID: " + waiter.getId());

        return waiter;
    }

    @Override
    public Waiter update( Waiter waiter) {
        if (waiter == null)
            throw new WaiterException("Waiter cannot be null");

        Waiter previous = database.replace(waiter.getId(), waiter);
        if (previous == null)
            throw new WaiterException("Cannot update. Waiter not found with ID: " + waiter.getId());

        return waiter;
    }

    @Override
    public Waiter findById(Integer id) {
        return database.get(id);
    }

    @Override
    public Waiter delete(Integer id) {
        Waiter deleted = database.remove(id);
        if (deleted == null)
            throw new WaiterException("Cannot delete. Waiter not found with ID: " + id);

        return deleted;
    }

    @Override
    public List<Waiter> findAll() {
        return new ArrayList<>(database.values());
    }

    @Override
    public boolean exists(Integer id) {
        return database.containsKey(id);
    }
}