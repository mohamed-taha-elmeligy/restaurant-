package com.emts.domain.models;

import com.emts.domain.models.common.Model;
import com.emts.exception.OrderException;
import com.emts.util.Console;
import com.emts.util.Printable;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

public class Order extends Model implements Printable {

    private static final AtomicInteger baseId;

    private Table table;
    private Waiter waiter;
    private List<OrderItem> orderItems;

    // static block
    static {
        baseId = new AtomicInteger(0);
    }

    protected Order(Table table, Waiter waiter, List<OrderItem> orderItems) {
        super(baseId.incrementAndGet());

        checkTable(table);
        checkWaiter(waiter);
        checkOrderItems(orderItems);

        this.table = table;
        this.waiter = waiter;
        this.orderItems = new ArrayList<>(orderItems);
    }

    protected Order(int id, Table table, Waiter waiter, List<OrderItem> orderItems) {
        super(id);

        checkTable(table);
        checkWaiter(waiter);
        checkOrderItems(orderItems);

        this.table = table;
        this.waiter = waiter;
        this.orderItems = new ArrayList<>(orderItems);
    }

    public Table getTable() {return table;}
    public Order setTable(Table table) {
        checkTable(table);
        this.table = table;
        return this;
    }

    public Waiter getWaiter() {return waiter;}
    public Order setWaiter(Waiter waiter) {
        checkWaiter(waiter);
        this.waiter = waiter;
        return this;
    }

    public List<OrderItem> getOrderItems() {return new ArrayList<>(orderItems);}
    public Order setOrderItems(List<OrderItem> orderItems) {
        checkOrderItems(orderItems);
        this.orderItems = new ArrayList<>(orderItems);
        return this;
    }

    // helper method
    public BigDecimal getTotalPrice() {
        return orderItems.stream().
                map(OrderItem::getTotalPrice).
                reduce(BigDecimal.ZERO,BigDecimal::add);
    }

    private static void checkOrderItems(List<OrderItem> orderItems) {
        if (orderItems == null || orderItems.isEmpty())
            throw new OrderException("Order items cannot be empty");
    }
    private static void checkWaiter(Waiter waiter) {
        if (waiter == null)
            throw new OrderException("Waiter is empty");
    }
    private static void checkTable(Table table) {
        if (table == null)
            throw new OrderException("Table is empty");
    }

    @Override
    public void print() {
        Console.plus();
        Console.print("         ORDER #" + getId());
        Console.separator();
        System.out.printf("Table: %d | Waiter: %s%n", table.getId(), waiter.getName());
        Console.separator();

        int count = 1;
        for (OrderItem item : orderItems) {
            System.out.printf("%d. ", count++);
            item.print();
        }

        Console.separator();
        System.out.printf("TOTAL: %.2f%n", getTotalPrice());
        Console.plus();
    }
}
