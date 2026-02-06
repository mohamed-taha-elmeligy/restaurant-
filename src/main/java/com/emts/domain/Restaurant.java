package com.emts.domain;

import com.emts.domain.cli.*;
import com.emts.domain.repositories.*;
import com.emts.util.Console;
import com.emts.util.cli.CliOperations;

import java.util.concurrent.ConcurrentHashMap;

public class Restaurant {

    private final WaiterCli waiterCli ;
    private final TableCli tableCli ;
    private final CustomerCli customerCli ;
    private final ReservationCli reservationCli ;
    private final MenuItemCli menuItemCli;

    private CliOperations<?> cliOperations;

    public Restaurant() {

        waiterCli = new WaiterCli(new WaiterRepository(new ConcurrentHashMap<>()));
        tableCli = new TableCli(new TableRepository(new ConcurrentHashMap<>()));
        customerCli = new CustomerCli(new CustomerRepository(new ConcurrentHashMap<>()));
        menuItemCli = new MenuItemCli(new MenuItemRepository(new ConcurrentHashMap<>()));

        reservationCli = new ReservationCli(
                new ReservationRepository(new ConcurrentHashMap<>()),
                customerCli,
                tableCli
        );
    }

    public void showWaiters() {
        cliOperations = waiterCli;
        displayOption("Waiter",cliOperations);
    }

    public void showMenuItems() {
        cliOperations = menuItemCli;
        displayOption("MenuItem",cliOperations);
    }

    public void showTables() {
        cliOperations = tableCli;
        displayOption("Table",cliOperations);
    }

    public void showCustomer() {
        cliOperations = customerCli;
        displayOption("Customer",cliOperations);
    }

    public void showReservation() {
        cliOperations = reservationCli;
        displayOption("Reservation",cliOperations);
    }




    private static <T> void displayOption(String name, CliOperations<T> cliOperations){
        int choice;

        do {
            Console.print("1- Add "+name);
            Console.print("2- Update "+name);
            Console.print("3- Find "+name+" By ID ");
            Console.print("4- Delete "+name);
            Console.print("5- Display all "+name+"s");
            Console.print("6- Exist "+name+" by ID ");
            Console.print("7- Exit from "+name);

            Console.line();
            Console.print("Choose: ");
            choice = Console.intIn();

            switch (choice){
                case 1 -> cliOperations.add();
                case 2 -> cliOperations.update();
                case 3 -> cliOperations.findById();
                case 4 -> cliOperations.deleteById();
                case 5 -> cliOperations.displayAll();
                case 6 -> cliOperations.exists();
                default -> Console.print("Please choose a number from 1 to 7");
            }
        } while (choice != 7);
    }
}
