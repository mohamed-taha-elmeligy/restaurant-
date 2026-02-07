package com.emts.domain.cli;

import com.emts.domain.cli.common.Cli;
import com.emts.domain.models.Table;
import com.emts.domain.repositories.TableRepository;
import com.emts.enums.TableStatus;
import com.emts.exception.TableException;
import com.emts.util.Console;

public class TableCli extends Cli<Table> {

    private static final String ENTER_CAPACITY = "Enter capacity of table";
    private static final String ENTER_STATUS = "Enter status of table";

    public TableCli(TableRepository tableRepository) {
        super(tableRepository);
    }


    @Override
    public void add() {
        try {
            Console.print(ENTER_CAPACITY);
            int capacity = Console.intIn();

            Console.print(ENTER_STATUS);
            TableStatus tableStatus = statusOperation();

            Table table = new Table(capacity,tableStatus);
            getCrudOperation().create(table.getId(), table).print();

        } catch (TableException e) {
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

            Console.print(ENTER_CAPACITY);
            int capacity = Console.intIn();

            Console.print(ENTER_STATUS);
            TableStatus tableStatus = statusOperation();

            getCrudOperation().update(id,new Table(id,capacity,tableStatus)).print();

        } catch (TableException e) {
            printError("updating",e);
        } catch (Exception e) {
            printUnexpectedError(e);
        }
    }

    private TableStatus statusOperation(){
        int choice;
        TableStatus tableStatus;

        do {
            Console.print("1- Default Free");
            Console.print("2- "+TableStatus.CLOSED);
            Console.print("3- "+TableStatus.CLEANING);
            Console.print("4- "+TableStatus.SERVED);
            Console.print("5- "+TableStatus.ORDERED);
            Console.print("6- "+TableStatus.RESERVED);
            Console.print("7- Exit");

            Console.line();
            Console.print("Choose: ");
            choice = Console.intIn();

            tableStatus = switch (choice){
                case 2 -> TableStatus.CLOSED;
                case 3 -> TableStatus.CLEANING;
                case 4 -> TableStatus.SERVED;
                case 5 -> TableStatus.ORDERED;
                case 6 -> TableStatus.RESERVED;
                default -> TableStatus.FREE;
            };
        } while (choice != 7);

        return tableStatus;
    }

    @Override
    protected String entityName() {
        return "Table";
    }
}
