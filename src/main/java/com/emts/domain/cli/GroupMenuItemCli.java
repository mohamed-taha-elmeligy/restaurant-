package com.emts.domain.cli;

import com.emts.domain.cli.common.Cli;
import com.emts.domain.models.GroupMenuItem;
import com.emts.domain.repositories.GroupMenuItemRepository;
import com.emts.exception.GroupMenuItemException;
import com.emts.util.Console;

import java.math.BigDecimal;

public class GroupMenuItemCli extends Cli<GroupMenuItem> {

    private static final String ENTER_NAME = "Enter name of GroupMenuItem";
    private static final String ENTER_PRICE = "Enter price of GroupMenuItem";
    private static final String ENTER_DISCOUNT = "Enter discount of GroupMenuItem";
    private static final String ENTER_COUNT = "Enter count of GroupMenuItem";

    public GroupMenuItemCli(GroupMenuItemRepository groupMenuItemRepository) {
        super(groupMenuItemRepository);
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

            Console.print(ENTER_COUNT);
            int count = Console.intIn();

            GroupMenuItem groupMenuItem = new GroupMenuItem(name, price, discount,count);
            getCrudOperation().create(groupMenuItem.getId(), groupMenuItem).print();
        } catch (GroupMenuItemException e) {
            printError("adding",e);
        } catch (Exception e) {
            printUnexpectedError(e);
        }
    }

    @Override
    protected String entityName() {
        return "GroupMenuItem";
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

            Console.print(ENTER_COUNT);
            int count = Console.intIn();

            getCrudOperation().update(id,new GroupMenuItem(id, name, price, discount, count)).print();
        } catch (GroupMenuItemException e) {
            printError("updating",e);
        } catch (Exception e) {
            printUnexpectedError(e);
        }
    }
}
