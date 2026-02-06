package com.emts.domain.cli;

import com.emts.domain.models.Waiter;
import com.emts.domain.repositories.WaiterRepository;
import com.emts.exception.WaiterException;
import com.emts.util.Console;
import com.emts.util.cli.CliOperations;

import java.math.BigDecimal;

public class WaiterCli implements CliOperations {
    private final WaiterRepository waiterRepository;
    private static final String ENTER_ID = "Enter ID of Waiter";
    private static final String ENTER_NAME = "Enter name of Waiter";
    private static final String ENTER_SALARY = "Enter salary of Waiter";
    private static final String UNEXPECTED_ERROR = "Unexpected error: ";

    public WaiterCli(WaiterRepository waiterRepository) {
        this.waiterRepository = waiterRepository;
    }

    @Override
    public void displayAll() {
        try {
            waiterRepository.findAll().forEach(Waiter::print);
        } catch (Exception e) {
            Console.print("Error displaying waiters: " + e.getMessage());
        }
    }

    @Override
    public void add() {
        try {
            Console.print(ENTER_NAME);
            String name = Console.stringIn();
            Console.print(ENTER_SALARY);
            BigDecimal salary = Console.decimalIn();
            waiterRepository.create(new Waiter(name, salary)).print();
        } catch (WaiterException e) {
            Console.print("Error adding waiter: " + e.getMessage());
        } catch (Exception e) {
            Console.print(UNEXPECTED_ERROR + e.getMessage());
        }
    }

    @Override
    public void findById() {
        try {
            Console.print(ENTER_ID);
            int id = Console.intIn();
            Waiter waiter = waiterRepository.findById(id);
            if (waiter == null) {
                Console.print("Waiter not found with ID: " + id);
                return;
            }
            waiter.print();
        } catch (Exception e) {
            Console.print("Error finding waiter: " + e.getMessage());
        }
    }

    @Override
    public void exists() {
        try {
            Console.print(ENTER_ID);
            int id = Console.intIn();
            Console.print(waiterRepository.exists(id));
        } catch (Exception e) {
            Console.print("Error checking existence: " + e.getMessage());
        }
    }

    @Override
    public void deleteById() {
        try {
            Console.print(ENTER_ID);
            int id = Console.intIn();
            Waiter waiter = waiterRepository.delete(id);
            waiter.print();
        } catch (WaiterException e) {
            Console.print("Error deleting waiter: " + e.getMessage());
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
            Console.print(ENTER_SALARY);
            BigDecimal salary = Console.decimalIn();
            waiterRepository.update(new Waiter(id, name, salary)).print();
        } catch (WaiterException e) {
            Console.print("Error updating waiter: " + e.getMessage());
        } catch (Exception e) {
            Console.print(UNEXPECTED_ERROR + e.getMessage());
        }
    }
}
