package com.emts.domain.models;

import com.emts.exception.GroupMenuItemException;
import com.emts.util.Console;
import com.emts.util.Printable;

import java.math.BigDecimal;

public class GroupMenuItem extends MenuItem implements Printable {

    private int count ;

    public GroupMenuItem(String name, BigDecimal price, BigDecimal discount, int count) {
        super(name, price, discount);
        checkCount(count);
        this.count = count;
    }
    public GroupMenuItem(int id, String name, BigDecimal price, BigDecimal discount, int count) {
        super(id, name, price, discount);
        checkCount(count);
        this.count = count;
    }

    public GroupMenuItem(String name, BigDecimal price, int count) {
        this(name,price,BigDecimal.ZERO,count);
    }
    public GroupMenuItem(int id ,String name, BigDecimal price, int count) {
        this(id, name,price,BigDecimal.ZERO,count);
    }

    public int getCount() {
        return count;
    }

    public GroupMenuItem setCount(int count) {
        checkCount(count);
        this.count = count;
        return this;
    }

    // helper method
    private static void checkCount(int count) {
        if (count < 2)
            throw new GroupMenuItemException("Count must be at least 2");
    }

    @Override
    public void print() {
        Console.plus();
        System.out.printf("Item: %s%n", getName());
        System.out.printf("Price: %.2f%n", getPrice());
        System.out.printf("Discount: %.2f%%%n", getDiscount());
        System.out.printf("Count: %d%n", count);
        System.out.printf("Total: %.2f%n", getPrice().multiply(BigDecimal.valueOf(count)));
        Console.plus();
    }
}
