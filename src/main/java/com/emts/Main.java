package com.emts;

import com.emts.domain.Restaurant;
import com.emts.util.Console;

public class Main {

    public static void main(String[] args) {
        displayRestaurant();
    }

    private static void displayRestaurant(){
        Restaurant restaurant = new Restaurant();

        int choice;
        do {
            Console.print("1-  Table Page");
            Console.print("2-  Waiter Page");
            Console.print("3-  Customer Page");
            Console.print("4-  Reservation Page");
            Console.print("5-  MenuItem Page");
            Console.print("6-  Menu Page");
            Console.print("7-  GroupMenuItem Page");
            Console.print("8-  OrderItem Page");
            Console.print("9-  Order Page");
            Console.print("10- Bill Page");
            Console.print("11- Exit");

            Console.line();
            Console.print("Choose: ");
            choice = Console.intIn();

            switch (choice){
                case 1 -> restaurant.showTables();
                case 2 -> restaurant.showWaiters();
                case 3 -> restaurant.showCustomers();
                case 4 -> restaurant.showReservations();
                case 5 -> restaurant.showMenuItems();
                case 6 -> restaurant.showMenus();
                case 7 -> restaurant.showGroupMenuItems();
                case 8 -> restaurant.showOrderItemClis();
                case 9 -> restaurant.showOrders();
                case 10 -> restaurant.showBillClis();
                default -> Console.print("Please choose a number from 1 to 11");
            }
        } while (choice != 11);
    }
}