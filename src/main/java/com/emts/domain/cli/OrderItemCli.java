package com.emts.domain.cli;

import com.emts.domain.cli.common.Cli;
import com.emts.domain.models.MenuItem;
import com.emts.domain.models.OrderItem;
import com.emts.domain.repositories.OrderItemRepository;
import com.emts.exception.OrderItemException;
import com.emts.util.Console;

public class OrderItemCli extends Cli<OrderItem> {

    private final MenuItemCli menuItemCli;

    private static final String ENTER_QUANTITY = "Enter quantity of ";

    public OrderItemCli(OrderItemRepository orderItemRepository, MenuItemCli menuItemCli) {
        super(orderItemRepository);
        this.menuItemCli = menuItemCli;
    }

    @Override
    public void add() {
        try {

            Console.print("Enter id of MenuItem");
            int idMenuItem = Console.intIn();
            MenuItem menuItem = menuItemCli.searchById(idMenuItem);

            Console.print(ENTER_QUANTITY + entityName());
            int quantity = Console.intIn();

            OrderItem orderItem = new OrderItem(menuItem,quantity);
            getCrudOperation().create(orderItem.getId(), orderItem).print();

        } catch (OrderItemException e) {
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

            Console.print("Enter id of MenuItem");
            int idMenuItem = Console.intIn();
            MenuItem menuItem = menuItemCli.searchById(idMenuItem);

            Console.print(ENTER_QUANTITY + entityName());
            int quantity = Console.intIn();

            getCrudOperation().update(id,new OrderItem(id, menuItem, quantity)).print();
        } catch (OrderItemException e) {
            printError("updating",e);
        } catch (Exception e) {
            printUnexpectedError(e);
        }
    }

    @Override
    protected String entityName() {
        return "OrderItem";
    }
}
