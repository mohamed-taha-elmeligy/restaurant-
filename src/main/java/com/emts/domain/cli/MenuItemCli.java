package com.emts.domain.cli;

import com.emts.domain.models.MenuItem;
import com.emts.domain.repositories.MenuItemRepository;
import com.emts.exception.MenuItemException;
import com.emts.util.Console;
import com.emts.util.cli.CliOperations;

import java.math.BigDecimal;

public class MenuItemCli implements CliOperations<Integer,MenuItem> {
    private final MenuItemRepository menuItemRepository;

    private static final String ENTER_ID = "Enter ID of MenuItem";
    private static final String ENTER_NAME = "Enter name of MenuItem";
    private static final String ENTER_PRICE = "Enter price of MenuItem";
    private static final String ENTER_DISCOUNT = "Enter discount of MenuItem";

    private static final String UNEXPECTED_ERROR = "Unexpected error: ";

    public MenuItemCli(MenuItemRepository menuItemRepository) {
        this.menuItemRepository = menuItemRepository;
    }

    @Override
    public void displayAll() {
        try {
            menuItemRepository.findAll().forEach(MenuItem::print);
        } catch (Exception e) {
            Console.print("Error displaying menuItems: " + e.getMessage());
        }
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
            menuItemRepository.create(menuItem.getId(), menuItem).print();
        } catch (MenuItemException e) {
            Console.print("Error adding menuItem: " + e.getMessage());
        } catch (Exception e) {
            Console.print(UNEXPECTED_ERROR + e.getMessage());
        }
    }

    @Override
    public void findById() {
        try {
            Console.print(ENTER_ID);
            int id = Console.intIn();

            MenuItem menuItem = menuItemRepository.findById(id);
            if (menuItem == null) {
                Console.print("MenuItem not found with ID: " + id);
                return;
            }
            menuItem.print();
        } catch (Exception e) {
            Console.print("Error finding menuItem: " + e.getMessage());
        }
    }

    @Override
    public MenuItem searchById(Integer id) {
        MenuItem menuItem;

        try {
            menuItem = menuItemRepository.findById(id);
            if (menuItem == null) {
                Console.print("MenuItem not found with ID: " + id);
                return null;
            }

            return menuItem;
        } catch (Exception e) {
            Console.print("Error finding menuItem: " + e.getMessage());
            return null;
        }
    }

    @Override
    public void exists() {
        try {
            Console.print(ENTER_ID);
            int id = Console.intIn();

            Console.print(menuItemRepository.exists(id));
        } catch (Exception e) {
            Console.print("Error checking existence: " + e.getMessage());
        }
    }

    @Override
    public void deleteById() {
        try {
            Console.print(ENTER_ID);
            int id = Console.intIn();

            MenuItem menuItem = menuItemRepository.delete(id);
            menuItem.print();

        } catch (MenuItemException e) {
            Console.print("Error deleting menuItem: " + e.getMessage());
        } catch (Exception e) {
            Console.print(UNEXPECTED_ERROR + e.getMessage());
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

            menuItemRepository.update(id,new MenuItem(id, name, price, discount)).print();
        } catch (MenuItemException e) {
            Console.print("Error updating menuItem: " + e.getMessage());
        } catch (Exception e) {
            Console.print(UNEXPECTED_ERROR + e.getMessage());
        }
    }
}
