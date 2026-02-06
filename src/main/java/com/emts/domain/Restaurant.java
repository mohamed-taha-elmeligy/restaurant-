package com.emts.domain;

import com.emts.domain.cli.WaiterCli;
import com.emts.domain.repositories.WaiterRepository;
import com.emts.util.Console;
import com.emts.util.cli.CliOperations;

import java.util.concurrent.ConcurrentHashMap;

public class Restaurant {

    public void showWaiters() {
        CliOperations cliOperations = new WaiterCli(new WaiterRepository(new ConcurrentHashMap<>()));
        displayOption("Waiter",cliOperations);
    }

    private static void displayOption(String name,CliOperations cliOperations){
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
