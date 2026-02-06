package com.emts.domain.cli;

import com.emts.domain.models.Menu;
import com.emts.domain.models.MenuItem;
import com.emts.domain.repositories.MenuRepository;
import com.emts.exception.MenuException;
import com.emts.util.Console;
import com.emts.util.cli.CliOperations;

import java.util.ArrayList;
import java.util.List;

public class MenuCli implements CliOperations<Integer,Menu> {

    private final MenuRepository menuRepository;
    private final MenuItemCli menuItemCli;

    private static final String ENTER_ID = "Enter ID of Menu";
    private static final String UNEXPECTED_ERROR = "Unexpected error: ";

    public MenuCli(MenuRepository menuRepository, MenuItemCli menuItemCli) {
        this.menuRepository = menuRepository;
        this.menuItemCli = menuItemCli;
    }

    @Override
    public void displayAll() {
        try {
            menuRepository.findAll().forEach(Menu::print);
        } catch (Exception e) {
            Console.print("Error displaying menus: " + e.getMessage());
        }
    }

    @Override
    public void add() {
        try {
            List<MenuItem> menuItems = getMenuItems();

            Menu menu = new Menu(menuItems);
            menuRepository.create(menu.getId(), menu).print();

        } catch (MenuException e) {
            Console.print("Error adding menu: " + e.getMessage());
        } catch (Exception e) {
            Console.print(UNEXPECTED_ERROR + e.getMessage());
        }
    }

    @Override
    public void findById() {
        try {
            Console.print(ENTER_ID);
            int id = Console.intIn();

            Menu menu = menuRepository.findById(id);
            if (menu == null) {
                Console.print("Menu not found with ID: " + id);
                return;
            }
            menu.print();
        } catch (Exception e) {
            Console.print("Error finding menu: " + e.getMessage());
        }
    }

    @Override
    public Menu searchById(Integer id) {
        Menu menu;
        try {
            menu = menuRepository.findById(id);
            if (menu == null) {
                Console.print("Menu not found with ID: " + id);
                return null;
            }
            return menu;
        } catch (Exception e) {
            Console.print("Error finding menu: " + e.getMessage());
            return null;
        }
    }

    @Override
    public void exists() {
        try {
            Console.print(ENTER_ID);
            int id = Console.intIn();

            Console.print(menuRepository.exists(id));
        } catch (Exception e) {
            Console.print("Error checking existence: " + e.getMessage());
        }
    }

    @Override
    public void deleteById() {
        try {
            Console.print(ENTER_ID);
            int id = Console.intIn();

            Menu menu = menuRepository.delete(id);
            menu.print();

        } catch (MenuException e) {
            Console.print("Error deleting menu: " + e.getMessage());
        } catch (Exception e) {
            Console.print(UNEXPECTED_ERROR + e.getMessage());
        }
    }

    @Override
    public void update() {
        try {
            Console.print(ENTER_ID);
            int id = Console.intIn();

            List<MenuItem> menuItems = getMenuItems();

            menuRepository.update(id,new Menu(id, menuItems)).print();
        } catch (MenuException e) {
            Console.print("Error updating menu: " + e.getMessage());
        } catch (Exception e) {
            Console.print(UNEXPECTED_ERROR + e.getMessage());
        }
    }

    private List<MenuItem> getMenuItems() {
        int input;
        List<MenuItem> menuItems = new ArrayList<>();

        do {
            Console.print("Enter MenuItem ID or (0) to exit: ");
            input = Console.intIn();

            if (input == 0) {
                break;
            }

            MenuItem item = menuItemCli.searchById(input);
            if (item != null) {
                menuItems.add(item);
            } else {
                Console.print("Invalid MenuItem ID");
            }

        } while (true);

        return menuItems;
    }

}
