package com.emts.domain.repositories;

import com.emts.domain.models.Menu;
import com.emts.domain.repositories.common.Repository;

import java.util.concurrent.ConcurrentMap;

public class MenuRepository extends Repository<Integer, Menu> {

    // In-memory repository for OOP practice

    public MenuRepository(ConcurrentMap<Integer, Menu> database) {
        super(database);
    }
}