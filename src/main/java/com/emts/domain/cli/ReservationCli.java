package com.emts.domain.cli;

import com.emts.domain.models.Customer;
import com.emts.domain.models.Reservation;
import com.emts.domain.models.Table;
import com.emts.domain.repositories.ReservationRepository;
import com.emts.exception.ReservationException;
import com.emts.util.Console;
import com.emts.util.cli.CliOperations;

import java.time.LocalDate;

public class ReservationCli implements CliOperations<Reservation> {
    private final ReservationRepository reservationRepository;
    private final CustomerCli customerCli;
    private final TableCli tableCli;

    private static final String ENTER_ID = "Enter ID of reservation";
    private static final String ENTER_DATE = "Enter Table date ex("+ LocalDate.now()+")";
    private static final String UNEXPECTED_ERROR = "Unexpected error: ";

    public ReservationCli(ReservationRepository reservationRepository, CustomerCli customerCli, TableCli tableCli) {
        this.reservationRepository = reservationRepository;
        this.customerCli = customerCli;
        this.tableCli = tableCli;
    }

    @Override
    public void displayAll() {
        try {
            reservationRepository.findAll().forEach(Reservation::print);
        } catch (Exception e) {
            Console.print("Error displaying reservations: " + e.getMessage());
        }
    }

    @Override
    public void add() {
        try {
            Customer customer = customerCli.searchById();

            Table table = tableCli.searchById() ;

            Console.print(ENTER_DATE);
            LocalDate localDate = Console.dateIn() ;

            Reservation reservation = new Reservation(customer, table, localDate);
            reservationRepository.create(reservation.getId(), reservation).print();

        } catch (ReservationException e) {
            Console.print("Error adding reservation: " + e.getMessage());
        } catch (Exception e) {
            Console.print(UNEXPECTED_ERROR + e.getMessage());
        }
    }

    @Override
    public void findById() {
        try {
            Console.print(ENTER_ID);
            int id = Console.intIn();

            Reservation waiter = reservationRepository.findById(id);
            if (waiter == null) {
                Console.print("Reservation not found with ID: " + id);
                return;
            }

            waiter.print();
        } catch (Exception e) {
            Console.print("Error finding reservation: " + e.getMessage());
        }
    }

    @Override
    public Reservation searchById() {
        int id;
        Reservation waiter;

        try {
            Console.print(ENTER_ID);
            id = Console.intIn();

            waiter = reservationRepository.findById(id);
            if (waiter == null) {
                Console.print("Reservation not found with ID: " + id);
                return null;
            }

            return waiter;
        } catch (Exception e) {
            Console.print("Error finding reservation: " + e.getMessage());
            return null;
        }
    }

    @Override
    public void exists() {
        try {
            Console.print(ENTER_ID);
            int id = Console.intIn();

            Console.print(reservationRepository.exists(id));
        } catch (Exception e) {
            Console.print("Error checking existence: " + e.getMessage());
        }
    }

    @Override
    public void deleteById() {
        try {
            Console.print(ENTER_ID);
            int id = Console.intIn();

            Reservation waiter = reservationRepository.delete(id);
            waiter.print();

        } catch (ReservationException e) {
            Console.print("Error deleting reservation: " + e.getMessage());
        } catch (Exception e) {
            Console.print(UNEXPECTED_ERROR + e.getMessage());
        }
    }

    @Override
    public void update() {
        try {
            Console.print(ENTER_ID);
            int id = Console.intIn();

            Customer customer = customerCli.searchById();
            Table table = tableCli.searchById() ;

            Console.print(ENTER_DATE);
            LocalDate localDate = Console.dateIn() ;

            reservationRepository.update(id, new Reservation(id, customer, table, localDate)).print();
        } catch (ReservationException e) {
            Console.print("Error updating reservation: " + e.getMessage());
        } catch (Exception e) {
            Console.print(UNEXPECTED_ERROR + e.getMessage());
        }
    }

}
