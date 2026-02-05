package com.emts.domain.repositories;

import com.emts.domain.models.Customer;
import com.emts.exception.CustomerException;
import com.emts.util.CrudOperation;

import java.util.ArrayList;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

public class CustomerRepository implements CrudOperation<Integer, Customer> {

    // In-memory repository for OOP practice

    private static final ConcurrentMap<Integer,Customer> Database = new ConcurrentHashMap<>();
    private static final CustomerRepository INSTANCE = new CustomerRepository();

    private CustomerRepository(){}
    public static CustomerRepository getInstance(){
        return INSTANCE;
    }

    @Override
    public Customer create(Customer customer) {
        if (customer == null)
            throw new CustomerException("Customer cannot be null");
        if (Database.putIfAbsent(customer.getId(),customer) != null)
            throw new CustomerException("Customer already exists with ID: "+ customer.getId());

        return customer;
    }

    @Override
    public Customer update(Customer customer) {
        if (customer == null)
            throw new CustomerException("Customer cannot be null");
        if (!Database.containsKey(customer.getId()))
            throw new CustomerException("Cannot update. Customer not found with ID: " + customer.getId());

        Database.put(customer.getId(), customer);
        return customer;
    }

    @Override
    public Customer findById(Integer id) {
        return Database.get(id);
    }

    @Override
    public Customer delete(Integer id) {
        Customer customer = Database.remove(id);
        if (customer == null)
            throw new CustomerException("Cannot delete. Customer not found with ID: " + id);

        return customer;
    }

    @Override
    public ArrayList<Customer> findAll() {
        return new ArrayList<>(Database.values());
    }

    @Override
    public boolean exists(Integer id) {
        return Database.containsKey(id);
    }
}
