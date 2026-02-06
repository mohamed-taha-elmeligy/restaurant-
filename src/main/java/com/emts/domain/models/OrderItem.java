package com.emts.domain.models;

import com.emts.domain.common.Model;
import com.emts.exception.OrderItemException;
import com.emts.util.Console;
import com.emts.util.Printable;

import java.math.BigDecimal;
import java.util.concurrent.atomic.AtomicInteger;

public class OrderItem extends Model implements Printable {

    private static final AtomicInteger baseId;

    private MenuItem menuItem;
    private int quantity;

    static {
        baseId = new AtomicInteger(0);
    }

    public OrderItem(MenuItem menuItem, int quantity) {
        super(baseId.incrementAndGet());

        checkMenuItem(menuItem);
        this.menuItem = menuItem;

        checkQuantity(quantity);
        this.quantity = quantity;
    }

    public MenuItem getMenuItem() {return menuItem;}
    public OrderItem setMenuItem(MenuItem menuItem) {
        checkMenuItem(menuItem);
        this.menuItem = menuItem;
        return this;
    }

    public int getQuantity() {return quantity;}
    public OrderItem setQuantity(int quantity) {
        checkQuantity(quantity);
        this.quantity = quantity;
        return this;
    }

    // helper method
    public BigDecimal getTotalPrice() {
        return menuItem.getPrice()
                .subtract(menuItem.getDiscount())
                .multiply(BigDecimal.valueOf(quantity));
    }
    private static void checkQuantity(int value){
        if (value < 1)
            throw new OrderItemException("Quantity must be at least 1");
    }
    private static void checkMenuItem(MenuItem menuItem){
        if (menuItem == null )
            throw new OrderItemException("MenuItem is null");
    }

    @Override
    public void print() {
        Console.plus();
        System.out.printf("%s x%d = %.2f%n", menuItem.getName(), quantity, getTotalPrice());
        Console.plus();
    }
}
