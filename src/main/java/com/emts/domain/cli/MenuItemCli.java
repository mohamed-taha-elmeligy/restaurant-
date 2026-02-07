package com.emts.domain.cli;

import com.emts.domain.cli.common.Cli;
import com.emts.domain.models.MenuItem;
import com.emts.domain.repositories.MenuItemRepository;
import com.emts.exception.MenuItemException;
import com.emts.util.Console;

import java.math.BigDecimal;

public class MenuItemCli extends Cli<MenuItem> {

    private static final String ENTER_NAME = "Enter name of MenuItem";
    private static final String ENTER_PRICE = "Enter price of MenuItem";
    private static final String ENTER_DISCOUNT = "Enter discount of MenuItem";

    public MenuItemCli(MenuItemRepository menuItemRepository) {
        super(menuItemRepository);
    }

    @Override
    protected String entityName() {
        return "MenuItem";
    }

    @Override
    public void add() {
        try {
            Console.print(ENTER_NAME);
            String name = Console.stringIn();

            Console.print(ENTER_PRICE);
            BigDecimal price = Console.decimalIn();

            Console.print(ENTER_DISCOUNT);
            BigDecimal discount = Console.decimalIn();

            MenuItem menuItem = new MenuItem(name, price, discount);
            getCrudOperation().create(menuItem.getId(), menuItem).print();
        } catch (MenuItemException e) {
            printError("adding",e);
        } catch (Exception e) {
            printUnexpectedError(e);
        }
    }

    @Override
    public void update() {
        try {
            Console.print(ENTER_ID);
            int id = Console.intIn();

            Console.print(ENTER_NAME);
            String name = Console.stringIn();

            Console.print(ENTER_PRICE);
            BigDecimal price = Console.decimalIn();

            Console.print(ENTER_DISCOUNT);
            BigDecimal discount = Console.decimalIn();

            getCrudOperation().update(id,new MenuItem(id, name, price, discount)).print();
        } catch (MenuItemException e) {
            printError("updating",e);
        } catch (Exception e) {
            printUnexpectedError(e);
        }
    }
}
