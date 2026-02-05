package com.emts.domain.models;

import com.emts.domain.common.Model;
import com.emts.exception.BillException;
import com.emts.util.Printable;

import java.math.BigDecimal;
import java.util.concurrent.atomic.AtomicInteger;

public class Bill extends Model implements Printable {

    private static final AtomicInteger baseId = new AtomicInteger(0);

    private BigDecimal tips;
    private Order order;

    public Bill(BigDecimal tips, Order order) {
        super(baseId.incrementAndGet());

        checkTips(tips);
        checkOrder(order);

        this.tips = tips;
        this.order = order;
    }

    public BigDecimal getTips() { return tips; }
    public Bill setTips(BigDecimal tips) {
        checkTips(tips);
        this.tips = tips;
        return this;
    }

    public Order getOrder() { return order; }
    public Bill setOrder(Order order) {
        checkOrder(order);
        this.order = order;
        return this;
    }

    public BigDecimal getTotalAmount() {
        return order.getTotalPrice().add(tips);
    }

    private static void checkOrder(Order order) {
        if (order == null)
            throw new BillException("Order is empty");
    }

    private static void checkTips(BigDecimal tips) {
        if (tips == null || tips.compareTo(BigDecimal.ZERO) < 0)
            throw new BillException("Tips must be at least 0");
    }

    @Override
    public void print() {
        System.out.println("========================================");
        System.out.println("           BILL #" + getId());
        System.out.println("========================================");
        System.out.println();

        if (order != null) {
            order.print();
        }

        System.out.println("----------------------------------------");
        System.out.printf("Order Total:        %20.2f%n", order.getTotalPrice());
        System.out.printf("Tips:               %20.2f%n", tips);
        System.out.println("----------------------------------------");
        System.out.printf("TOTAL AMOUNT:       %20.2f%n", getTotalAmount());
        System.out.println("========================================");
    }
}
