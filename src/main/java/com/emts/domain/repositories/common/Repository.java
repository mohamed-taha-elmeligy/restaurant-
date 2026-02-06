package com.emts.domain.repositories.common;

import com.emts.domain.models.common.Model;
import com.emts.exception.RepositoryExceptions;
import com.emts.exception.WaiterException;
import com.emts.util.crud.CrudOperation;

import java.util.ArrayList;
import java.util.concurrent.ConcurrentMap;

public class Repository<K,T extends Model>  implements CrudOperation<K,T> {

    // In-memory repository for OOP practice
    private final ConcurrentMap<K, T> database ;

    protected Repository(ConcurrentMap<K, T> database) {
        this.database = database;
    }

    @Override
    public T create(K k,T t) {
        if (t == null)
            throw new RepositoryExceptions(" cannot be null");

        if (database.putIfAbsent(k, t) != null)
            throw new RepositoryExceptions("already exists with ID: " + k);

        return t;
    }

    @Override
    public T update(K k,T t) {
        if (t == null)
            throw new RepositoryExceptions("cannot be null");

        T previous = database.replace(k, t);
        if (previous == null)
            throw new WaiterException("Cannot update.Not found with ID: " + k);

        return previous;
    }

    @Override
    public T findById(K k) {
        return database.get(k);
    }

    @Override
    public T delete(K k) {
        T deleted = database.remove(k);
        if (deleted == null)
            throw new RepositoryExceptions("Cannot delete. Not found with ID: " + k);

        return deleted;
    }

    @Override
    public ArrayList<T> findAll() {
        return new ArrayList<>(database.values());
    }

    @Override
    public boolean exists(K k) {
        return database.containsKey(k);
    }
}