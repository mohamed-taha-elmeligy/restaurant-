package com.emts.domain.repositories;

import com.emts.domain.models.Bill;
import com.emts.exception.BillException;
import com.emts.util.crud.CrudOperation;

import java.util.ArrayList;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

public class BillRepository implements CrudOperation<Integer,Bill> {

    // In-memory repository for OOP practice

    private static final BillRepository INSTANCE  = new BillRepository();
    private static final ConcurrentMap<Integer,Bill> Database = new ConcurrentHashMap<>();

    private BillRepository (){}
    public static BillRepository getInstance(){
        return INSTANCE;
    }

    @Override
    public Bill create(Bill bill) {
        if (bill == null)
            throw new BillException("Bill cannot be null");

        if (Database.putIfAbsent(bill.getId(), bill) != null)
            throw new BillException("Bill already exists with ID: " + bill.getId());

        return bill;
    }


    @Override
    public Bill update(Bill bill) {
        if (bill == null)
            throw new BillException("Bill cannot be null");

        if (!Database.containsKey(bill.getId()))
            throw new BillException("Cannot update. Bill not found with ID: " + bill.getId());

        Database.put(bill.getId(), bill);
        return bill;
    }


    @Override
    public Bill findById(Integer id) {
        return Database.get(id);
    }

    @Override
    public Bill delete(Integer id) {
        Bill deleted = Database.remove(id);
        if (deleted == null)
            throw new BillException("Cannot delete. Bill not found with ID: " + id);

        return deleted;
    }

    @Override
    public ArrayList<Bill> findAll() {
        return new ArrayList<>(Database.values());
    }

    @Override
    public boolean exists(Integer id) {
        return Database.containsKey(id);
    }

}
