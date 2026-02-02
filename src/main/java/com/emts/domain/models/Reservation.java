package com.emts.domain.models;

import com.emts.domain.common.Model;
import com.emts.enums.TableStatus;
import com.emts.exception.ReservationException;

import java.time.LocalDateTime;
import java.util.concurrent.atomic.AtomicInteger;

public class Reservation extends Model {

    private static final AtomicInteger baseId;

    private Customer customer;
    private Table table;
    private LocalDateTime date;

    // static block
    static {
        baseId = new AtomicInteger(0);
    }

    protected Reservation(Customer customer, Table table, LocalDateTime date) {
        super(baseId.incrementAndGet());

        checkCustomer(customer);
        checkTable(table);
        checkDate(date);

        this.customer = customer;
        this.table = table;
        this.date = date;
    }

    public Customer getCustomer() {return customer;}
    public Reservation setCustomer(Customer customer) {
        checkCustomer(customer);
        this.customer = customer;
        return this;
    }

    public Table getTable() {return table;}
    public Reservation setTable(Table table) {
        checkTable(table);
        this.table = table;
        return this;
    }

    public LocalDateTime getDate() {return date;}
    public Reservation setDate(LocalDateTime date) {
        checkDate(date);
        this.date = date;
        return this;
    }

    // helper method
    private static void checkCustomer(Customer customer) {
        if (customer == null)
            throw new ReservationException("Customer is empty");
    }
    private static void checkTable(Table table) {
        if (table == null)
            throw new ReservationException("Table is empty");
        if (table.getTableStatus() != TableStatus.FREE)
            throw new ReservationException("Table is not available");
    }
    private static void checkDate(LocalDateTime date) {
        if (date == null || date.isBefore(LocalDateTime.now()))
            throw new ReservationException("Reservation date must be in the future");
    }
}
