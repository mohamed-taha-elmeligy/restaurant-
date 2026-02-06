package com.emts.domain.cli;

import com.emts.domain.models.Table;
import com.emts.domain.repositories.TableRepository;
import com.emts.enums.TableStatus;
import com.emts.exception.TableException;
import com.emts.util.Console;
import com.emts.util.cli.CliOperations;

public class TableCli implements CliOperations<Table> {

    private final TableRepository tableRepository;
    private static final String ENTER_CAPACITY = "Enter capacity of table";
    private static final String ENTER_ID = "Enter id of table";
    private static final String ENTER_STATUS = "Enter status of table";
    private static final String UNEXPECTED_ERROR = "Unexpected error: ";

    public TableCli(TableRepository tableRepository) {
        this.tableRepository = tableRepository;
    }

    @Override
    public void displayAll() {
        try {
            tableRepository.findAll().forEach(Table::print);
        } catch (Exception e) {
            Console.print("Error displaying tables: " + e.getMessage());
        }
    }

    @Override
    public void add() {
        try {
            Console.print(ENTER_CAPACITY);
            int capacity = Console.intIn();

            Console.print(ENTER_STATUS);
            TableStatus tableStatus = statusOperation();

            Table table = new Table(capacity,tableStatus);
            tableRepository.create(table.getId(), table).print();

        } catch (TableException e) {
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
            Table table = tableRepository.findById(id);
            if (table == null) {
                Console.print("Table not found with ID: " + id);
                return;
            }
            table.print();
        } catch (Exception e) {
            Console.print("Error finding table: " + e.getMessage());
        }
    }

    @Override
    public Table searchById() {
        int id;
        Table table;

        try {
            Console.print(ENTER_ID);
            id = Console.intIn();

            table = tableRepository.findById(id);
            if (table == null) {
                Console.print("Table not found with ID: " + id);
                return null;
            }
            return table;
        } catch (Exception e) {
            Console.print("Error finding table: " + e.getMessage());
            return null;
        }
    }

    @Override
    public void exists() {
        try {
            Console.print(ENTER_ID);
            int id = Console.intIn();
            Console.print(tableRepository.exists(id));
        } catch (Exception e) {
            Console.print("Error checking existence: " + e.getMessage());
        }
    }

    @Override
    public void deleteById() {
        try {
            Console.print(ENTER_ID);
            int id = Console.intIn();
            Table table = tableRepository.delete(id);
            table.print();
        } catch (TableException e) {
            Console.print("Error deleting table: " + e.getMessage());
        } catch (Exception e) {
            Console.print(UNEXPECTED_ERROR + e.getMessage());
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

            tableRepository.update(id,new Table(id,capacity,tableStatus)).print();

        } catch (TableException e) {
            Console.print("Error adding table: " + e.getMessage());
        } catch (Exception e) {
            Console.print(UNEXPECTED_ERROR + e.getMessage());
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
        } while (choice < 7 && choice > 0);

        return tableStatus;
    }

}
