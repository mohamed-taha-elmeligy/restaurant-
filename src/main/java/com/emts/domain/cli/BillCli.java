package com.emts.domain.cli;

import com.emts.domain.models.Bill;
import com.emts.domain.models.Order;
import com.emts.domain.repositories.BillRepository;
import com.emts.exception.BillException;
import com.emts.util.Console;
import com.emts.util.cli.CliOperations;

import java.math.BigDecimal;

public class BillCli implements CliOperations<Integer, Bill> {

    private final BillRepository billRepository;
    private final OrderCli orderCli;

    private static final String ENTER_ID = "Enter ID of Bill";
    private static final String ENTER_ORDER = "Enter ID of Order";
    private static final String ENTER_TIPS = "Enter tips of Bill";
    private static final String UNEXPECTED_ERROR = "Unexpected Bill: ";

    public BillCli(BillRepository billRepository, OrderCli orderCli) {
        this.billRepository = billRepository;
        this.orderCli = orderCli;
    }

    @Override
    public void displayAll() {
        try {
            billRepository.findAll().forEach(Bill::print);
        } catch (Exception e) {
            Console.print("Error displaying bills: " + e.getMessage());
        }
    }

    @Override
    public void add() {
        try {
            Console.print(ENTER_TIPS);
            BigDecimal tips = Console.decimalIn();

            Console.print(ENTER_ORDER);
            int idOrder = Console.intIn();
            Order order = orderCli.searchById(idOrder);

            Bill bill = new Bill(tips, order);
            billRepository.create(bill.getId(), bill).print();

        } catch (BillException e) {
            Console.print("Error adding bill: " + e.getMessage());
        } catch (Exception e) {
            Console.print(UNEXPECTED_ERROR + e.getMessage());
        }
    }

    @Override
    public void findById() {
        try {
            Console.print(ENTER_ID);
            int id = Console.intIn();

            Bill bill = billRepository.findById(id);
            if (bill == null) {
                Console.print("Bill not found with ID: " + id);
                return;
            }
            bill.print();
        } catch (Exception e) {
            Console.print("Error finding bill: " + e.getMessage());
        }
    }

    @Override
    public Bill searchById(Integer id) {
        Bill bill;

        try {
            bill = billRepository.findById(id);
            if (bill == null) {
                Console.print("Bill not found with ID: " + id);
                return null;
            }
            return bill;
        } catch (Exception e) {
            Console.print("Error finding bill: " + e.getMessage());
            return null;
        }
    }

    @Override
    public void exists() {
        try {
            Console.print(ENTER_ID);
            int id = Console.intIn();

            Console.print(billRepository.exists(id));
        } catch (Exception e) {
            Console.print("Error checking existence: " + e.getMessage());
        }
    }

    @Override
    public void deleteById() {
        try {
            Console.print(ENTER_ID);
            int id = Console.intIn();

            Bill bill = billRepository.delete(id);
            bill.print();

        } catch (BillException e) {
            Console.print("Error deleting bill: " + e.getMessage());
        } catch (Exception e) {
            Console.print(UNEXPECTED_ERROR + e.getMessage());
        }
    }

    @Override
    public void update() {
        try {
            Console.print(ENTER_ID);
            int id = Console.intIn();

            Console.print(ENTER_TIPS);
            BigDecimal tips = Console.decimalIn();

            Console.print(ENTER_ORDER);
            int idOrder = Console.intIn();
            Order order = orderCli.searchById(idOrder);

            billRepository.update(id,new Bill(id, tips, order)).print();
        } catch (BillException e) {
            Console.print("Error updating bill: " + e.getMessage());
        } catch (Exception e) {
            Console.print(UNEXPECTED_ERROR + e.getMessage());
        }
    }
}
