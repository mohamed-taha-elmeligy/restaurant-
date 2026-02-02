package com.emts.domain.models;

import com.emts.domain.common.Person;
import com.emts.util.PhoneNumber;
import java.util.concurrent.atomic.AtomicInteger;

public class Customer extends Person {

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

    // ======= Getter and Setter =======
    public PhoneNumber getPhoneNumber() {
        return phoneNumber;
    }
    public Customer setPhoneNumber(PhoneNumber phoneNumber) {
        this.phoneNumber = phoneNumber;
        return this;
    }

}
