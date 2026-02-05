package com.emts.domain.repositories;

import com.emts.domain.models.GroupMenuItem;
import com.emts.exception.GroupMenuItemException;
import com.emts.util.CrudOperation;

import java.util.ArrayList;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

public class GroupMenuItemRepository implements CrudOperation<Integer, GroupMenuItem> {

    // In-memory repository for OOP practice

    private static final ConcurrentMap<Integer,GroupMenuItem> Database = new ConcurrentHashMap<>();
    private static final GroupMenuItemRepository INSTANCE = new GroupMenuItemRepository();

    private GroupMenuItemRepository(){}
    public static GroupMenuItemRepository getInstance() {
        return INSTANCE;
    }

    @Override
    public GroupMenuItem create(GroupMenuItem groupMenuItem) {
        if (groupMenuItem == null)
            throw new GroupMenuItemException("GroupMenuItem cannot be null");
        if (Database.putIfAbsent(groupMenuItem.getId(),groupMenuItem)!=null)
            throw new GroupMenuItemException("GroupMenuItem already exist with ID: "+ groupMenuItem.getId());

        return groupMenuItem;
    }

    @Override
    public GroupMenuItem update(GroupMenuItem groupMenuItem) {
        if (groupMenuItem == null)
            throw new GroupMenuItemException("GroupMenuItem cannot be null");
        if (!Database.containsKey(groupMenuItem.getId()))
            throw new GroupMenuItemException("GroupMenuItem not found with ID: " + groupMenuItem.getId());

        Database.put(groupMenuItem.getId(), groupMenuItem);
        return groupMenuItem;
    }

    @Override
    public GroupMenuItem findById(Integer id) {
        return Database.get(id);
    }

    @Override
    public GroupMenuItem delete(Integer id) {
        GroupMenuItem groupMenuItem = Database.remove(id);
        if (groupMenuItem == null)
            throw new GroupMenuItemException("Cannot delete. GroupMenuItem not found with ID: " + id);
        return groupMenuItem;
    }

    @Override
    public ArrayList<GroupMenuItem> findAll() {
        return new ArrayList<>(Database.values());
    }

    @Override
    public boolean exists(Integer id) {
        return Database.containsKey(id);
    }
}
