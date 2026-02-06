package com.emts.domain.repositories;

import com.emts.domain.models.MenuItem;
import com.emts.exception.MenuItemException;
import com.emts.util.crud.CrudOperation;

import java.util.ArrayList;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

public class MenuItemRepository implements CrudOperation<Integer, MenuItem> {

    // In-memory repository for OOP practice

    private static final MenuItemRepository INSTANCE = new MenuItemRepository();
    private static final ConcurrentMap<Integer, MenuItem> Database = new ConcurrentHashMap<>();

    private MenuItemRepository() {}

    public static MenuItemRepository getInstance() {
        return INSTANCE;
    }

    @Override
    public MenuItem create(MenuItem menuItem) {
        if (menuItem == null)
            throw new MenuItemException("MenuItem cannot be null");

        if (Database.putIfAbsent(menuItem.getId(), menuItem) != null)
            throw new MenuItemException("MenuItem already exists with ID: " + menuItem.getId());

        return menuItem;
    }

    @Override
    public MenuItem update(MenuItem menuItem) {
        if (menuItem == null)
            throw new MenuItemException("MenuItem cannot be null");

        if (!Database.containsKey(menuItem.getId()))
            throw new MenuItemException("Cannot update. MenuItem not found with ID: " + menuItem.getId());

        Database.put(menuItem.getId(), menuItem);
        return menuItem;
    }

    @Override
    public MenuItem findById(Integer id) {
        return Database.get(id);
    }

    @Override
    public MenuItem delete(Integer id) {
        MenuItem deleted = Database.remove(id);
        if (deleted == null)
            throw new MenuItemException("Cannot delete. MenuItem not found with ID: " + id);

        return deleted;
    }

    @Override
    public ArrayList<MenuItem> findAll() {
        return new ArrayList<>(Database.values());
    }

    @Override
    public boolean exists(Integer id) {
        return Database.containsKey(id);
    }
}