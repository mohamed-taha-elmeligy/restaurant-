package com.emts.domain.models;

import com.emts.domain.common.Model;
import com.emts.exception.MenuException;
import com.emts.util.Console;
import com.emts.util.Printable;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

public class Menu extends Model implements Printable {

    private static final AtomicInteger baseId;

    private List<MenuItem> menuItems ;

    static {
        baseId = new AtomicInteger(0);
    }

    public Menu(List<MenuItem> menuItems) {
        super(baseId.incrementAndGet());
        checkItems(menuItems);
        this.menuItems = new ArrayList<>(menuItems);
    }

    public List<MenuItem> getMenuItems() {
        return new ArrayList<>(menuItems);
    }

    public Menu setMenuItems(List<MenuItem> menuItems) {
        checkItems(menuItems);
        this.menuItems = new ArrayList<>(menuItems);
        return this;
    }

    public Menu addMenuItem(MenuItem item) {
        menuItems.add(item);
        return this;
    }

    // helper method
    private static void checkItems(List<MenuItem> menuItems) {
        if (menuItems == null || menuItems.isEmpty())
            throw new MenuException("Menu items cannot be empty");
    }

    @Override
    public void print() {
        Console.plus();
        Console.print("         MENU #" + getId());
        Console.separator();
        Console.line();

        for (MenuItem item : menuItems) {
            item.print();
        }

        Console.plus();
    }
}
