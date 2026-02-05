package com.emts.domain.models;

import com.emts.domain.common.Model;
import com.emts.exception.MenuItemException;
import com.emts.util.Printable;

import java.math.BigDecimal;
import java.util.concurrent.atomic.AtomicInteger;

public class MenuItem extends Model implements Printable {

    // ======= Static Attributes =======
    private static final AtomicInteger baseId;
    // ======= Instance Attributes ======
    private String name ;
    private BigDecimal price;
    private BigDecimal discount;

    // static block
    static {
        baseId = new AtomicInteger(0);
    }

    public MenuItem(String name, BigDecimal price, BigDecimal discount) {
        super(baseId.incrementAndGet());

        checkName(name);
        this.name = name;

        checkPrice(price);
        this.price = price;

        checkDiscount(discount,price);
        this.discount = discount;
    }

    public MenuItem(String name, BigDecimal price) {
        this(name,price,BigDecimal.ZERO);
    }

    public String getName() { return name;}
    public MenuItem setName(String name) {
        checkName(name);
        this.name = name;
        return this;
    }

    public BigDecimal getPrice() { return price;}
    public MenuItem setPrice(BigDecimal price) {
        checkPrice(price);
        this.price = price;
        return this;
    }

    public BigDecimal getDiscount() {return discount;}
    public MenuItem setDiscount(BigDecimal discount) {
        checkDiscount(discount,this.price);
        this.discount = discount;
        return this;
    }

    // helper method
    private static void checkPrice(BigDecimal value){
        if (value == null || value.compareTo(BigDecimal.ONE) < 0)
            throw new MenuItemException("Price must be at least 1");
    }
    private static void checkDiscount(BigDecimal value, BigDecimal price){
        if (value == null || value.compareTo(BigDecimal.ZERO) < 0 )
            throw new MenuItemException("Discount must be at least 0");
        if ((price== null) || value.compareTo(price) < 0)
            throw new MenuItemException("Discount cannot exceed price");
    }
    private static void checkName(String name){
        if (name == null || name.isBlank())
            throw new MenuItemException("Name is empty");
    }

    @Override
    public void print() {
        System.out.printf("%s | Price: %.2f | Discount: %.2f | Final: %.2f%n",
                name, price, discount, price.subtract(discount));
    }
}
