package com.emts.domain.models;

import com.emts.domain.models.common.Person;
import com.emts.util.Console;
import com.emts.util.PhoneNumber;
import com.emts.util.Printable;

import java.util.concurrent.atomic.AtomicInteger;

public class Customer extends Person implements Printable {

    // ======= Static Attributes =======
    private static final AtomicInteger baseId;

    // ======= Instance Attributes ======
    private PhoneNumber phoneNumber;

    // static block
    static {
        baseId = new AtomicInteger(0);
    }

    // ======= constructors ========
    public Customer(String name, PhoneNumber phoneNumber) {
        super(baseId.incrementAndGet() , name);
        this.phoneNumber = phoneNumber;
    }
    public Customer(int id, String name, PhoneNumber phoneNumber) {
        super(id , name);
        this.phoneNumber = phoneNumber;
    }

    // ======= Getter and Setter =======
    public PhoneNumber getPhoneNumber() {
        return phoneNumber;
    }
    public Customer setPhoneNumber(PhoneNumber phoneNumber) {
        this.phoneNumber = phoneNumber;
        return this;
    }

    @Override
    public void print() {
        Console.plus();
        System.out.printf("Customer ID:        %s%n", getId());
        System.out.printf("Name:               %s%n", getName());
        System.out.printf("Phone:              %s%n", phoneNumber);
        Console.plus();
    }
}
