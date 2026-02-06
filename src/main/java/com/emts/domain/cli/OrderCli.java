package com.emts.domain.cli;

import com.emts.domain.models.Order;
import com.emts.domain.models.Table;
import com.emts.domain.models.Waiter;
import com.emts.domain.repositories.OrderRepository;
import com.emts.exception.OrderException;
import com.emts.util.Console;
import com.emts.util.cli.CliOperations;

public class OrderCli implements CliOperations<Order> {
    private final OrderRepository orderRepository;
    private final TableCli tableCli;
    private final WaiterCli waiterCli;

    private static final String ENTER_ID = "Enter id of order";
    private static final String UNEXPECTED_ERROR = "Unexpected error: ";

    public OrderCli(OrderRepository orderRepository, TableCli tableCli, WaiterCli waiterCli) {
        this.orderRepository = orderRepository;
        this.tableCli = tableCli;
        this.waiterCli = waiterCli;
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
            Table table = tableCli.searchById();
            Waiter waiter = waiterCli.searchById();


            Order order = new Order(table,waiter,);
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
    public Order searchById() {
        int id;
        Order order;

        try {
            Console.print(ENTER_ID);
            id = Console.intIn();

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

            Table table = tableCli.searchById();
            Waiter waiter = waiterCli.searchById();

            orderRepository.update(id,new Order(id,table,waiter,)).print();

        } catch (OrderException e) {
            Console.print("Error adding order: " + e.getMessage());
        } catch (Exception e) {
            Console.print(UNEXPECTED_ERROR + e.getMessage());
        }
    }

}
