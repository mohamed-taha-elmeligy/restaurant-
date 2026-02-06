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
            Console.print("6-  Table Page");
            Console.print("7-  Table Page");
            Console.print("8-  Table Page");
            Console.print("9-  Table Page");
            Console.print("10- Table Page");
            Console.print("11- Exit");

            Console.line();
            Console.print("Choose: ");
            choice = Console.intIn();

            switch (choice){
                case 1 -> restaurant.showTables();
                case 2 -> restaurant.showWaiters();
                case 3 -> restaurant.showCustomer();
                case 4 -> restaurant.showReservation();
                case 5 -> restaurant.showMenuItems();
                case 6 -> restaurant.showWaiters();
                case 7 -> restaurant.showWaiters();
                case 8 -> restaurant.showCustomer();
                case 9 -> restaurant.showWaiters();
                case 10 -> restaurant.showWaiters();
                default -> Console.print("Please choose a number from 1 to 11");
            }
        } while (choice != 11);
    }
}