package com.emts.domain.models;

import com.emts.domain.common.Model;
import com.emts.enums.TableStatus;
import com.emts.exception.TableException;
import com.emts.util.Console;
import com.emts.util.Printable;

import java.util.concurrent.atomic.AtomicInteger;

public class Table extends Model implements Printable {

    private static final AtomicInteger baseId;

    private int maxCapacity ;
    private TableStatus tableStatus ;

    // static block
    static {
        baseId = new AtomicInteger(0);
    }

    // ======= constructors ========
    public Table(int maxCapacity, TableStatus tableStatus) {
        super(baseId.incrementAndGet());
        checkCapacity(maxCapacity);
        this.maxCapacity = maxCapacity;
        this.tableStatus = tableStatus;
    }
    public Table(int maxCapacity) {
        this(maxCapacity, TableStatus.FREE);

    }

    // ======= Getter and Setter =======
    public int getMaxCapacity() {
        return maxCapacity;
    }

    public Table setMaxCapacity(int maxCapacity) {
        checkCapacity(maxCapacity);
        this.maxCapacity = maxCapacity;
        return this;
    }

    public TableStatus getTableStatus() {
        return tableStatus;
    }

    public Table setTableStatus(TableStatus tableStatus) {
        this.tableStatus = tableStatus;
        return this;
    }

    // helper method
    private static void checkCapacity(int maxCapacity) {
        if (maxCapacity < 1)
            throw new TableException("Table capacity must be at least 1");
    }

    @Override
    public void print() {
        Console.plus();
        System.out.printf("Table #%d | Capacity: %d | Status: %s%n", getId(), maxCapacity, tableStatus);
        Console.plus();
    }
}
