package com.emts.domain.models;

import com.emts.domain.models.common.Model;
import com.emts.enums.TableStatus;
import com.emts.exception.ReservationException;
import com.emts.util.Console;
import com.emts.util.Printable;

import java.time.LocalDate;
import java.util.concurrent.atomic.AtomicInteger;

public class Reservation extends Model implements Printable {

    private static final AtomicInteger baseId;

    private Customer customer;
    private Table table;
    private LocalDate date;

    // static block
    static {
        baseId = new AtomicInteger(0);
    }

    public Reservation(Customer customer, Table table, LocalDate date) {
        super(baseId.incrementAndGet());

        checkCustomer(customer);
        checkTable(table);
        checkDate(date);

        this.customer = customer;
        this.table = table;
        this.date = date;
    }

    public Reservation(int id, Customer customer, Table table, LocalDate date) {
        super(id);

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

    public LocalDate getDate() {return date;}
    public Reservation setDate(LocalDate date) {
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
    private static void checkDate(LocalDate date) {
        if (date == null || date.isBefore(LocalDate.now()))
            throw new ReservationException("Reservation date must be in the future");
    }

    @Override
    public void print() {
        Console.plus();
        Console.print("      RESERVATION #" + getId());
        Console.separator();
        System.out.printf("Customer: %s | Phone: %s%n", customer.getName(), customer.getPhoneNumber());
        System.out.printf("Table: %d (Capacity: %d)%n", table.getId(), table.getMaxCapacity());
        System.out.printf("Date & Time: %s%n", date);
        Console.plus();
    }
}
