package com.emts.domain.cli;

import com.emts.domain.cli.common.Cli;
import com.emts.domain.models.Customer;
import com.emts.domain.models.Reservation;
import com.emts.domain.models.Table;
import com.emts.domain.repositories.ReservationRepository;
import com.emts.exception.ReservationException;
import com.emts.util.Console;

import java.time.LocalDate;

public class ReservationCli extends Cli<Reservation> {

    private final CustomerCli customerCli;
    private final TableCli tableCli;

    private static final String ENTER_DATE = "Enter Table date ex("+ LocalDate.now()+")";

    public ReservationCli(ReservationRepository reservationRepository, CustomerCli customerCli, TableCli tableCli) {
        super(reservationRepository);
        this.customerCli = customerCli;
        this.tableCli = tableCli;
    }

    @Override
    protected String entityName() {
        return "Reservation";
    }

    @Override
    public void add() {
        try {
            Console.print("Inter id of Customer");
            int idCustomer = Console.intIn();
            Customer customer = customerCli.searchById(idCustomer);

            Console.print("Inter id of Table");
            int idTable = Console.intIn();
            Table table = tableCli.searchById(idTable);

            Console.print(ENTER_DATE);
            LocalDate localDate = Console.dateIn() ;

            Reservation reservation = new Reservation(customer, table, localDate);
            getCrudOperation().create(reservation.getId(), reservation).print();

        } catch (ReservationException e) {
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

            Console.print("Inter id of Customer");
            int idCustomer = Console.intIn();
            Customer customer = customerCli.searchById(idCustomer);

            Console.print("Inter id Table");
            int idT = Console.intIn();
            Table table = tableCli.searchById(idT) ;

            Console.print(ENTER_DATE);
            LocalDate localDate = Console.dateIn() ;

            getCrudOperation().update(id, new Reservation(id, customer, table, localDate)).print();
        } catch (ReservationException e) {
            printError("updating",e);
        } catch (Exception e) {
            printUnexpectedError(e);
        }
    }
}
