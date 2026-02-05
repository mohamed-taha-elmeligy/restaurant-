package com.emts.domain.repositories;

import com.emts.domain.models.Table;
import com.emts.exception.TableException;
import com.emts.util.CrudOperation;

import java.util.ArrayList;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

public class TableRepository implements CrudOperation<Integer, Table> {

    // In-memory repository for OOP practice

    private static final TableRepository INSTANCE = new TableRepository();
    private static final ConcurrentMap<Integer, Table> Database = new ConcurrentHashMap<>();

    private TableRepository() {}

    public static TableRepository getInstance() {
        return INSTANCE;
    }

    @Override
    public Table create(Table table) {
        if (table == null)
            throw new TableException("Table cannot be null");

        if (Database.putIfAbsent(table.getId(), table) != null)
            throw new TableException("Table already exists with ID: " + table.getId());

        return table;
    }

    @Override
    public Table update(Table table) {
        if (table == null)
            throw new TableException("Table cannot be null");

        if (!Database.containsKey(table.getId()))
            throw new TableException("Cannot update. Table not found with ID: " + table.getId());

        Database.put(table.getId(), table);
        return table;
    }

    @Override
    public Table findById(Integer id) {
        return Database.get(id);
    }

    @Override
    public Table delete(Integer id) {
        Table deleted = Database.remove(id);
        if (deleted == null)
            throw new TableException("Cannot delete. Table not found with ID: " + id);

        return deleted;
    }

    @Override
    public ArrayList<Table> findAll() {
        return new ArrayList<>(Database.values());
    }

    @Override
    public boolean exists(Integer id) {
        return Database.containsKey(id);
    }
}