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
    private final GroupMenuItemCli groupMenuItemCli;
    private final MenuItemCli menuItemCli;
    private final MenuCli menuCli;
    private final OrderItemCli orderItemCli;
    private final OrderCli orderCli;
    private final BillCli billCli;

    private CliOperations<Integer,?> cliOperations;

    public Restaurant() {

        this.waiterCli = new WaiterCli(new WaiterRepository(new ConcurrentHashMap<>()));
        this.tableCli = new TableCli(new TableRepository(new ConcurrentHashMap<>()));
        this.customerCli = new CustomerCli(new CustomerRepository(new ConcurrentHashMap<>()));
        this.menuItemCli = new MenuItemCli(new MenuItemRepository(new ConcurrentHashMap<>()));
        this.groupMenuItemCli = new GroupMenuItemCli(new GroupMenuItemRepository(new ConcurrentHashMap<>()));

        this.reservationCli = new ReservationCli(
                new ReservationRepository(new ConcurrentHashMap<>()),
                customerCli,
                tableCli
        );

        this.orderItemCli = new OrderItemCli(new OrderItemRepository(new ConcurrentHashMap<>()),menuItemCli);
        this.menuCli = new MenuCli(new MenuRepository(new ConcurrentHashMap<>()),menuItemCli);
        this.orderCli = new OrderCli(new OrderRepository(new ConcurrentHashMap<>()),tableCli,waiterCli,orderItemCli);
        this.billCli = new BillCli(new BillRepository(new ConcurrentHashMap<>()),orderCli);
    }

    public void showWaiters() {
        this.cliOperations = this.waiterCli;
        displayOption("Waiter", this.cliOperations);
    }

    public void showBillClis() {
        this.cliOperations = this.billCli;
        displayOption("Bill", this.cliOperations);
    }

    public void showOrders() {
        this.cliOperations = this.orderCli;
        displayOption("Order", this.cliOperations);
    }

    public void showGroupMenuItems() {
        this.cliOperations = this.groupMenuItemCli;
        displayOption("GroupMenuItem", this.cliOperations);
    }

    public void showMenuItems() {
        this.cliOperations = this.menuItemCli;
        displayOption("MenuItem", this.cliOperations);
    }

    public void showTables() {
        this.cliOperations = this.tableCli;
        displayOption("Table", this.cliOperations);
    }

    public void showCustomers() {
        this.cliOperations = this.customerCli;
        displayOption("Customer", this.cliOperations);
    }

    public void showReservations() {
        this.cliOperations = this.reservationCli;
        displayOption("Reservation", this.cliOperations);
    }

    public void showMenus() {
        this.cliOperations = this.menuCli;
        displayOption("Menu", this.cliOperations);
    }

    public void showOrderItemClis() {
        this.cliOperations = this.orderItemCli;
        displayOption("OrderItem", this.cliOperations);
    }




    private static <T> void displayOption(String name, CliOperations<Integer,T> cliOperations){
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
