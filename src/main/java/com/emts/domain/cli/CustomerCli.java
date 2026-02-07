package com.emts.domain.cli;

import com.emts.domain.cli.common.Cli;
import com.emts.domain.models.Customer;
import com.emts.domain.repositories.CustomerRepository;
import com.emts.exception.CustomerException;
import com.emts.util.Console;
import com.emts.util.PhoneNumber;

public class CustomerCli extends Cli<Customer> {

    private static final String ENTER_PHONE = "Enter phone number of customer";
    private static final String ENTER_NAME = "Enter name of customer";

    public CustomerCli(CustomerRepository customerRepository) {
        super(customerRepository);
    }

    @Override
    public void add() {
        try {
            Console.print(ENTER_NAME);
            String name = Console.stringIn();

            Console.print(ENTER_PHONE);
            PhoneNumber phoneNumber = new PhoneNumber(Console.stringIn());

            Customer customer = new Customer(name,phoneNumber);
            getCrudOperation().create(customer.getId(), customer).print();

        } catch (CustomerException e) {
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

            Console.print(ENTER_NAME);
            String name = Console.stringIn();

            Console.print(ENTER_PHONE);
            PhoneNumber phoneNumber = new PhoneNumber(Console.stringIn());

            getCrudOperation().update(id,new Customer(id, name, phoneNumber)).print();
        } catch (CustomerException e) {
            printError("updating",e);
        } catch (Exception e) {
            printUnexpectedError(e);
        }
    }

    @Override
    protected String entityName() {
        return "Customer";
    }
}
