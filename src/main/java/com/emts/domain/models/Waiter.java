package com.emts.domain.models;

import com.emts.domain.models.common.Person;
import com.emts.exception.WaiterException;
import com.emts.util.Console;
import com.emts.util.Printable;

import java.math.BigDecimal;
import java.util.concurrent.atomic.AtomicInteger;

public class Waiter extends Person implements Printable {

    private static final AtomicInteger baseId;
    private BigDecimal salary ;

    static {
        baseId = new AtomicInteger(0);
    }


    public Waiter(String name, BigDecimal salary) {
        super(baseId.incrementAndGet(), name);
        checkSalary(salary);
        this.salary = salary;
    }

    public Waiter(int id,String name, BigDecimal salary) {
        super(id, name);
        checkSalary(salary);
        this.salary = salary;
    }

    public BigDecimal getSalary() {
        return salary;
    }

    public Waiter setSalary(BigDecimal salary) {
        checkSalary(salary);
        this.salary = salary;
        return this;
    }

    // helper method
    private static void checkSalary(BigDecimal salary){
        if (salary == null || salary.compareTo(new BigDecimal("1")) < 0)
            throw new WaiterException("Salary must be at least 1");
    }

    @Override
    public void print() {
        Console.plus();
        System.out.printf("Waiter #%d | Name: %s | Salary: %.2f%n", getId(), getName(), salary);
        Console.plus();
    }
}
