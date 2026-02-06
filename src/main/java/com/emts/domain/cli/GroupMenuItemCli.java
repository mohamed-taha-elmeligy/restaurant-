package com.emts.domain.cli;

import com.emts.domain.models.GroupMenuItem;
import com.emts.domain.repositories.GroupMenuItemRepository;
import com.emts.exception.GroupMenuItemException;
import com.emts.util.Console;
import com.emts.util.cli.CliOperations;

import java.math.BigDecimal;

public class GroupMenuItemCli implements CliOperations<Integer,GroupMenuItem> {
    private final GroupMenuItemRepository groupMenuItemRepository;

    private static final String ENTER_ID = "Enter ID of GroupMenuItem";
    private static final String ENTER_NAME = "Enter name of GroupMenuItem";
    private static final String ENTER_PRICE = "Enter price of GroupMenuItem";
    private static final String ENTER_DISCOUNT = "Enter discount of GroupMenuItem";
    private static final String ENTER_COUNT = "Enter count of GroupMenuItem";

    private static final String UNEXPECTED_ERROR = "Unexpected error: ";

    public GroupMenuItemCli(GroupMenuItemRepository groupMenuItemRepository) {
        this.groupMenuItemRepository = groupMenuItemRepository;
    }

    @Override
    public void displayAll() {
        try {
            groupMenuItemRepository.findAll().forEach(GroupMenuItem::print);
        } catch (Exception e) {
            Console.print("Error displaying groupMenuItems: " + e.getMessage());
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

            Console.print(ENTER_COUNT);
            int count = Console.intIn();

            GroupMenuItem groupMenuItem = new GroupMenuItem(name, price, discount,count);
            groupMenuItemRepository.create(groupMenuItem.getId(), groupMenuItem).print();
        } catch (GroupMenuItemException e) {
            Console.print("Error adding groupMenuItem: " + e.getMessage());
        } catch (Exception e) {
            Console.print(UNEXPECTED_ERROR + e.getMessage());
        }
    }

    @Override
    public void findById() {
        try {
            Console.print(ENTER_ID);
            int id = Console.intIn();

            GroupMenuItem groupMenuItem = groupMenuItemRepository.findById(id);
            if (groupMenuItem == null) {
                Console.print("GroupMenuItem not found with ID: " + id);
                return;
            }
            groupMenuItem.print();
        } catch (Exception e) {
            Console.print("Error finding groupMenuItem: " + e.getMessage());
        }
    }

    @Override
    public GroupMenuItem searchById(Integer id) {
        GroupMenuItem groupMenuItem;

        try {
            groupMenuItem = groupMenuItemRepository.findById(id);
            if (groupMenuItem == null) {
                Console.print("GroupMenuItem not found with ID: " + id);
                return null;
            }

            return groupMenuItem;
        } catch (Exception e) {
            Console.print("Error finding groupMenuItem: " + e.getMessage());
            return null;
        }
    }

    @Override
    public void exists() {
        try {
            Console.print(ENTER_ID);
            int id = Console.intIn();

            Console.print(groupMenuItemRepository.exists(id));
        } catch (Exception e) {
            Console.print("Error checking existence: " + e.getMessage());
        }
    }

    @Override
    public void deleteById() {
        try {
            Console.print(ENTER_ID);
            int id = Console.intIn();

            GroupMenuItem groupMenuItem = groupMenuItemRepository.delete(id);
            groupMenuItem.print();

        } catch (GroupMenuItemException e) {
            Console.print("Error deleting groupMenuItem: " + e.getMessage());
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

            Console.print(ENTER_COUNT);
            int count = Console.intIn();

            groupMenuItemRepository.update(id,new GroupMenuItem(id, name, price, discount, count)).print();
        } catch (GroupMenuItemException e) {
            Console.print("Error updating groupMenuItem: " + e.getMessage());
        } catch (Exception e) {
            Console.print(UNEXPECTED_ERROR + e.getMessage());
        }
    }
}
