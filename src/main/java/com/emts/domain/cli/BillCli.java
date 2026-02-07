package com.emts.domain.cli;

import com.emts.domain.cli.common.Cli;
import com.emts.domain.models.Bill;
import com.emts.domain.models.Order;
import com.emts.domain.repositories.BillRepository;
import com.emts.exception.BillException;
import com.emts.util.Console;

import java.math.BigDecimal;

public class BillCli extends Cli<Bill> {

    private final OrderCli orderCli;

    private static final String ENTER_ORDER = "Enter ID of Order";
    private static final String ENTER_TIPS = "Enter tips of Bill";

    public BillCli(BillRepository billRepository, OrderCli orderCli) {
        super(billRepository);
        this.orderCli = orderCli;
    }

    @Override
    protected String entityName() {
        return "Bill";
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
            getCrudOperation().create(bill.getId(), bill).print();

        } catch (BillException e) {
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

            Console.print(ENTER_TIPS);
            BigDecimal tips = Console.decimalIn();

            Console.print(ENTER_ORDER);
            int idOrder = Console.intIn();

            Order order = orderCli.searchById(idOrder);
            getCrudOperation().update(id,new Bill(id, tips, order)).print();

        } catch (BillException e) {
            printError("updating",e);
        } catch (Exception e) {
            printUnexpectedError(e);
        }
    }
}
