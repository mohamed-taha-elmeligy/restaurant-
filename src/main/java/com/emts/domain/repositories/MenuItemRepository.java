package com.emts.domain.repositories;

import com.emts.domain.models.MenuItem;
import com.emts.domain.repositories.common.Repository;

import java.util.concurrent.ConcurrentMap;

public class MenuItemRepository extends Repository<Integer, MenuItem> {

    // In-memory repository for OOP practice

    public MenuItemRepository(ConcurrentMap<Integer, MenuItem> database) {
        super(database);
    }
}