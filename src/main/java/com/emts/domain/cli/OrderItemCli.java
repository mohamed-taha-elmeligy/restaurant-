package com.emts.domain.cli;

import com.emts.domain.models.MenuItem;
import com.emts.domain.models.OrderItem;
import com.emts.domain.repositories.OrderItemRepository;
import com.emts.exception.OrderItemException;
import com.emts.util.Console;
import com.emts.util.cli.CliOperations;

public class OrderItemCli implements CliOperations<Integer,OrderItem> {

    private final OrderItemRepository orderItemRepository;
    private final MenuItemCli menuItemCli;

    private static final String ENTER_ID = "Enter ID of OrderItem";
    private static final String ENTER_QUANTITY = "Enter quantity of OrderItem";

    private static final String UNEXPECTED_ERROR = "Unexpected error: ";

    public OrderItemCli(OrderItemRepository orderItemRepository, MenuItemCli menuItemCli) {
        this.orderItemRepository = orderItemRepository;
        this.menuItemCli = menuItemCli;
    }

    @Override
    public void displayAll() {
        try {
            orderItemRepository.findAll().forEach(OrderItem::print);
        } catch (Exception e) {
            Console.print("Error displaying menus: " + e.getMessage());
        }
    }

    @Override
    public void add() {
        try {

            Console.print("Enter id of MenuItem");
            int idMenuItem = Console.intIn();
            MenuItem menuItem = menuItemCli.searchById(idMenuItem);

            Console.print(ENTER_QUANTITY);
            int quantity = Console.intIn();

            OrderItem orderItem = new OrderItem(menuItem,quantity);
            orderItemRepository.create(orderItem.getId(), orderItem).print();

        } catch (OrderItemException e) {
            Console.print("Error adding orderItems: " + e.getMessage());
        } catch (Exception e) {
            Console.print(UNEXPECTED_ERROR + e.getMessage());
        }
    }

    @Override
    public void findById() {
        try {
            Console.print(ENTER_ID);
            int id = Console.intIn();

            OrderItem orderItem = orderItemRepository.findById(id);
            if (orderItem == null) {
                Console.print("OrderItem not found with ID: " + id);
                return;
            }
            orderItem.print();
        } catch (Exception e) {
            Console.print("Error finding orderItem: " + e.getMessage());
        }
    }

    @Override
    public OrderItem searchById(Integer id) {
        OrderItem orderItem;

        try {
            orderItem = orderItemRepository.findById(id);
            if (orderItem == null) {
                Console.print("OrderItem not found with ID: " + id);
                return null;
            }
            return orderItem;
        } catch (Exception e) {
            Console.print("Error finding orderItem: " + e.getMessage());
            return null;
        }
    }

    @Override
    public void exists() {
        try {
            Console.print(ENTER_ID);
            int id = Console.intIn();

            Console.print(orderItemRepository.exists(id));
        } catch (Exception e) {
            Console.print("Error checking existence: " + e.getMessage());
        }
    }

    @Override
    public void deleteById() {
        try {
            Console.print(ENTER_ID);
            int id = Console.intIn();

            OrderItem orderItem = orderItemRepository.delete(id);
            orderItem.print();

        } catch (OrderItemException e) {
            Console.print("Error deleting orderItem: " + e.getMessage());
        } catch (Exception e) {
            Console.print(UNEXPECTED_ERROR + e.getMessage());
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

            Console.print(ENTER_QUANTITY);
            int quantity = Console.intIn();

            orderItemRepository.update(id,new OrderItem(id, menuItem, quantity)).print();
        } catch (OrderItemException e) {
            Console.print("Error updating orderItem: " + e.getMessage());
        } catch (Exception e) {
            Console.print(UNEXPECTED_ERROR + e.getMessage());
        }
    }
}
