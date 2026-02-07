package com.emts.domain.cli;

import com.emts.domain.cli.common.Cli;
import com.emts.domain.models.*;
import com.emts.domain.repositories.OrderRepository;
import com.emts.exception.OrderException;
import com.emts.util.Console;

import java.util.ArrayList;
import java.util.List;

public class OrderCli extends Cli<Order> {

    private final TableCli tableCli;
    private final WaiterCli waiterCli;
    private final OrderItemCli orderItemCli;

    public OrderCli(OrderRepository orderRepository, TableCli tableCli, WaiterCli waiterCli, OrderItemCli orderItemCli) {
        super(orderRepository);
        this.tableCli = tableCli;
        this.waiterCli = waiterCli;
        this.orderItemCli = orderItemCli;
    }

    @Override
    protected String entityName() {
        return "Order";
    }

    @Override
    public void add() {
        try {
            Console.print("Enter id of Table");
            int idTable = Console.intIn();
            Table table = tableCli.searchById(idTable);

            Console.print("Enter id of Waiter");
            int idWaiter = Console.intIn();
            Waiter waiter = waiterCli.searchById(idWaiter);

            List<OrderItem> orderItems = getMenuItems();

            Order order = new Order(table, waiter, orderItems);
            getCrudOperation().create(order.getId(), order).print();

        } catch (OrderException e) {
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

            Console.print("Enter id of Table");
            int idTable = Console.intIn();
            Table table = tableCli.searchById(idTable);

            Console.print("Enter id of Waiter");
            int idWaiter = Console.intIn();
            Waiter waiter = waiterCli.searchById(idWaiter);

            List<OrderItem> orderItems = getMenuItems();
            getCrudOperation().update(id,new Order(id,table,waiter,orderItems)).print();

        } catch (OrderException e) {
            printError("updating",e);
        } catch (Exception e) {
            printUnexpectedError(e);
        }
    }

    private List<OrderItem> getMenuItems() {
        int input;
        List<OrderItem> orderItems = new ArrayList<>();

        do {
            Console.print("Enter OrderItem ID or (0) to exit: ");
            input = Console.intIn();

            if (input == 0) {
                break;
            }

            OrderItem orderItem = orderItemCli.searchById(input);
            if (orderItem != null) {
                orderItems.add(orderItem);
            } else {
                Console.print("Invalid OrderItem ID");
            }

        } while (true);

        return orderItems;
    }
}
