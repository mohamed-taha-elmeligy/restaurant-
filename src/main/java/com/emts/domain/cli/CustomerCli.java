package com.emts.domain.cli;

import com.emts.domain.models.Customer;
import com.emts.domain.repositories.CustomerRepository;
import com.emts.exception.CustomerException;
import com.emts.util.Console;
import com.emts.util.PhoneNumber;
import com.emts.util.cli.CliOperations;

public class CustomerCli implements CliOperations<Integer,Customer> {
    private final CustomerRepository customerRepository;
    private static final String ENTER_PHONE = "Enter phone number of customer";
    private static final String ENTER_ID = "Enter id of customer";
    private static final String ENTER_NAME = "Enter name of customer";
    private static final String UNEXPECTED_ERROR = "Unexpected error: ";

    public CustomerCli(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    @Override
    public void displayAll() {
        try {
            customerRepository.findAll().forEach(Customer::print);
        } catch (Exception e) {
            Console.print("Error displaying customers: " + e.getMessage());
        }
    }

    @Override
    public void add() {
        try {
            Console.print(ENTER_NAME);
            String name = Console.stringIn();

            Console.print(ENTER_PHONE);
            PhoneNumber phoneNumber = new PhoneNumber(Console.stringIn());

            Customer customer = new Customer(name,phoneNumber);
            customerRepository.create(customer.getId(), customer).print();

        } catch (CustomerException e) {
            Console.print("Error adding table: " + e.getMessage());
        } catch (Exception e) {
            Console.print(UNEXPECTED_ERROR + e.getMessage());
        }
    }

    @Override
    public void findById() {
        try {
            Console.print(ENTER_ID);
            int id = Console.intIn();

            Customer customer = customerRepository.findById(id);
            if (customer == null) {
                Console.print("Customer not found with ID: " + id);
                return;
            }

            customer.print();
        } catch (Exception e) {
            Console.print("Error finding customer: " + e.getMessage());
        }
    }

    @Override
    public Customer searchById(Integer id) {
        Customer customer;
        try {
            customer = customerRepository.findById(id);
            if (customer == null) {
                Console.print("Customer not found with ID: " + id);
                return null;
            }

            return customer;
        } catch (Exception e) {
            Console.print("Error finding customer: " + e.getMessage());
            return null;
        }
    }

    @Override
    public void exists() {
        try {
            Console.print(ENTER_ID);
            int id = Console.intIn();

            Console.print(customerRepository.exists(id));
        } catch (Exception e) {
            Console.print("Error checking existence: " + e.getMessage());
        }
    }

    @Override
    public void deleteById() {
        try {
            Console.print(ENTER_ID);
            int id = Console.intIn();

            Customer customer = customerRepository.delete(id);
            customer.print();

        } catch (CustomerException e) {
            Console.print("Error deleting customer: " + e.getMessage());
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

            Console.print(ENTER_PHONE);
            PhoneNumber phoneNumber = new PhoneNumber(Console.stringIn());

            customerRepository.update(id,new Customer(id, name, phoneNumber)).print();
        } catch (CustomerException e) {
            Console.print("Error adding customer: " + e.getMessage());
        } catch (Exception e) {
            Console.print(UNEXPECTED_ERROR + e.getMessage());
        }
    }

}
