package com.emts.domain.cli;

import com.emts.domain.cli.common.Cli;
import com.emts.domain.models.Menu;
import com.emts.domain.models.MenuItem;
import com.emts.domain.repositories.MenuRepository;
import com.emts.exception.MenuException;
import com.emts.util.Console;

import java.util.ArrayList;
import java.util.List;

public class MenuCli extends Cli<Menu> {

    private final MenuItemCli menuItemCli;

    public MenuCli(MenuRepository menuRepository, MenuItemCli menuItemCli) {
        super(menuRepository);
        this.menuItemCli = menuItemCli;
    }

    @Override
    protected String entityName() {
        return "Menu";
    }

    @Override
    public void add() {
        try {
            List<MenuItem> menuItems = getMenuItems();

            Menu menu = new Menu(menuItems);
            getCrudOperation().create(menu.getId(), menu).print();

        } catch (MenuException e) {
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

            List<MenuItem> menuItems = getMenuItems();

            getCrudOperation().update(id,new Menu(id, menuItems)).print();
        } catch (MenuException e) {
            printError("updating",e);
        } catch (Exception e) {
            printUnexpectedError(e);
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
