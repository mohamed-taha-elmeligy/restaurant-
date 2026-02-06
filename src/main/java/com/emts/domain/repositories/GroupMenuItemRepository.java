package com.emts.domain.repositories;

import com.emts.domain.models.GroupMenuItem;
import com.emts.domain.repositories.common.Repository;

import java.util.concurrent.ConcurrentMap;

public class GroupMenuItemRepository extends Repository<Integer, GroupMenuItem> {

    // In-memory repository for OOP practice

    public GroupMenuItemRepository(ConcurrentMap<Integer, GroupMenuItem> database) {
        super(database);
    }
}
