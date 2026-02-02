package com.emts.domain.models;

import com.emts.exception.GroupMenuItemException;

import java.math.BigDecimal;

public class GroupMenuItem extends MenuItem{

    private int count ;

    public GroupMenuItem(String name, BigDecimal price, BigDecimal discount, int count) {
        super(name, price, discount);
        checkCount(count);
        this.count = count;
    }

    public GroupMenuItem(String name, BigDecimal price, int count) {
        this(name,price,BigDecimal.ZERO,count);
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
}
