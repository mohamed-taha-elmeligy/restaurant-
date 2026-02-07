package com.emts.domain.cli;

import com.emts.domain.cli.common.Cli;
import com.emts.domain.models.Waiter;
import com.emts.domain.repositories.WaiterRepository;
import com.emts.exception.WaiterException;
import com.emts.util.Console;

import java.math.BigDecimal;

public class WaiterCli extends Cli<Waiter> {

    private static final String ENTER_NAME = "Enter name of Waiter";
    private static final String ENTER_SALARY = "Enter salary of Waiter";

    public WaiterCli(WaiterRepository waiterRepository) {
        super(waiterRepository);
    }

    @Override
    protected String entityName() {
        return "Waiter";
    }

    @Override
    public void add() {
        try {
            Console.print(ENTER_NAME);
            String name = Console.stringIn();

            Console.print(ENTER_SALARY);
            BigDecimal salary = Console.decimalIn();

            Waiter waiter = new Waiter(name, salary);
            getCrudOperation().create(waiter.getId(), waiter).print();

        } catch (WaiterException e) {
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

            Console.print(ENTER_SALARY);
            BigDecimal salary = Console.decimalIn();

            getCrudOperation().update(id,new Waiter(id, name, salary)).print();
        } catch (WaiterException e) {
            printError("updating",e);
        } catch (Exception e) {
            printUnexpectedError(e);
        }
    }
}
