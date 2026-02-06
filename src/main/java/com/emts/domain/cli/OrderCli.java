package com.emts.domain.cli;

import com.emts.domain.models.*;
import com.emts.domain.repositories.OrderRepository;
import com.emts.exception.OrderException;
import com.emts.util.Console;
import com.emts.util.cli.CliOperations;

import java.util.ArrayList;
import java.util.List;

public class OrderCli implements CliOperations<Integer,Order> {

    private final OrderRepository orderRepository;
    private final TableCli tableCli;
    private final WaiterCli waiterCli;
    private final OrderItemCli orderItemCli;

    private static final String ENTER_ID = "Enter id of order";
    private static final String UNEXPECTED_ERROR = "Unexpected error: ";

    public OrderCli(OrderRepository orderRepository, TableCli tableCli, WaiterCli waiterCli, OrderItemCli orderItemCli) {
        this.orderRepository = orderRepository;
        this.tableCli = tableCli;
        this.waiterCli = waiterCli;
        this.orderItemCli = orderItemCli;
    }

    @Override
    public void displayAll() {
        try {
            orderRepository.findAll().forEach(Order::print);
        } catch (Exception e) {
            Console.print("Error displaying orders: " + e.getMessage());
        }
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
            orderRepository.create(order.getId(), order).print();

        } catch (OrderException e) {
            Console.print("Error adding order: " + e.getMessage());
        } catch (Exception e) {
            Console.print(UNEXPECTED_ERROR + e.getMessage());
        }
    }

    @Override
    public void findById() {
        try {
            Console.print(ENTER_ID);
            int id = Console.intIn();

            Order order = orderRepository.findById(id);
            if (order == null) {
                Console.print("Order not found with ID: " + id);
                return;
            }

            order.print();
        } catch (Exception e) {
            Console.print("Error finding order: " + e.getMessage());
        }
    }

    @Override
    public Order searchById(Integer id) {
        Order order;

        try {
            order = orderRepository.findById(id);
            if (order == null) {
                Console.print("Order not found with ID: " + id);
                return null;
            }
            return order;
        } catch (Exception e) {
            Console.print("Error finding order: " + e.getMessage());
            return null;
        }
    }

    @Override
    public void exists() {
        try {
            Console.print(ENTER_ID);
            int id = Console.intIn();

            Console.print(orderRepository.exists(id));
        } catch (Exception e) {
            Console.print("Error checking existence: " + e.getMessage());
        }
    }

    @Override
    public void deleteById() {
        try {
            Console.print(ENTER_ID);
            int id = Console.intIn();

            Order order = orderRepository.delete(id);
            order.print();

        } catch (OrderException e) {
            Console.print("Error deleting order: " + e.getMessage());
        } catch (Exception e) {
            Console.print(UNEXPECTED_ERROR + e.getMessage());
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

            orderRepository.update(id,new Order(id,table,waiter,orderItems)).print();

        } catch (OrderException e) {
            Console.print("Error adding order: " + e.getMessage());
        } catch (Exception e) {
            Console.print(UNEXPECTED_ERROR + e.getMessage());
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
