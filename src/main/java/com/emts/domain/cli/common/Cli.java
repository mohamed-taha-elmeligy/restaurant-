package com.emts.domain.cli.common;

import com.emts.exception.common.RestaurantException;
import com.emts.util.Console;
import com.emts.util.Printable;
import com.emts.util.cli.CliOperations;
import com.emts.util.crud.CrudOperation;


public abstract class Cli<T extends Printable> implements CliOperations<Integer,T> {

    private final CrudOperation<Integer,T> crudOperation;

    protected static final String ENTER_ID = "Enter ID of ";
    protected static final String UNEXPECTED_ERROR = "Unexpected error: ";
    protected static final String NOT_FOUND_WITH_ID = " not found with ID";

    protected Cli(CrudOperation<Integer,T> crudOperation) {
        this.crudOperation = crudOperation;
    }

    public CrudOperation<Integer, T> getCrudOperation() {
        return crudOperation;
    }

    protected abstract String entityName();
    protected void printError(String action, Exception e) {
        Console.print("Error " + action + " " + entityName() + ": " + e.getMessage());
    }
    protected void printUnexpectedError(Exception e) {
        Console.print(UNEXPECTED_ERROR+ ": " + e.getMessage());
    }


    @Override
    public void displayAll() {
        try {
            crudOperation.findAll().forEach(T::print);
        } catch (Exception e) {
            printError("displaying",e);
        }
    }

    @Override
    public void findById() {
        try {
            Console.print(ENTER_ID + entityName());
            int id = Console.intIn();

            T t = crudOperation.findById(id);
            if (t == null) {
                Console.print(entityName()+ NOT_FOUND_WITH_ID + id);
                return;
            }
            t.print();
        } catch (RuntimeException e) {
            printError("finding",e);
        }
    }

    @Override
    public T searchById(Integer k) {
        return crudOperation.findById(k);
    }

    @Override
    public void exists() {
        try {
            Console.print(ENTER_ID + entityName());
            int id = Console.intIn();

            Console.print(crudOperation.exists(id));
        } catch (RuntimeException e) {
            Console.print("Error checking existence: " + e.getMessage());
        }
    }

    @Override
    public void deleteById() {
        try {
            Console.print(ENTER_ID + entityName());
            int id = Console.intIn();

            T t = crudOperation.delete(id);
            if (t == null) {
                Console.print(entityName() + NOT_FOUND_WITH_ID + id);
                return;
            }

            t.print();

        } catch (RestaurantException e) {
            printError("deleting",e);
        } catch (RuntimeException e) {
            printUnexpectedError(e);
        }
    }

}
