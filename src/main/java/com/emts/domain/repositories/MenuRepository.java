package com.emts.domain.repositories;

import com.emts.domain.models.Menu;
import com.emts.exception.MenuException;
import com.emts.util.crud.CrudOperation;

import java.util.ArrayList;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

public class MenuRepository implements CrudOperation<Integer, Menu> {

    // In-memory repository for OOP practice

    private static final MenuRepository INSTANCE = new MenuRepository();
    private static final ConcurrentMap<Integer, Menu> Database = new ConcurrentHashMap<>();

    private MenuRepository() {}

    public static MenuRepository getInstance() {
        return INSTANCE;
    }

    @Override
    public Menu create(Menu menu) {
        if (menu == null)
            throw new MenuException("Menu cannot be null");

        if (Database.putIfAbsent(menu.getId(), menu) != null)
            throw new MenuException("Menu already exists with ID: " + menu.getId());

        return menu;
    }

    @Override
    public Menu update(Menu menu) {
        if (menu == null)
            throw new MenuException("Menu cannot be null");

        if (!Database.containsKey(menu.getId()))
            throw new MenuException("Cannot update. Menu not found with ID: " + menu.getId());

        Database.put(menu.getId(), menu);
        return menu;
    }

    @Override
    public Menu findById(Integer id) {
        return Database.get(id);
    }

    @Override
    public Menu delete(Integer id) {
        Menu deleted = Database.remove(id);
        if (deleted == null)
            throw new MenuException("Cannot delete. Menu not found with ID: " + id);

        return deleted;
    }

    @Override
    public ArrayList<Menu> findAll() {
        return new ArrayList<>(Database.values());
    }

    @Override
    public boolean exists(Integer id) {
        return Database.containsKey(id);
    }
}