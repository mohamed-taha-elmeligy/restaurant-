package com.emts.domain.cli;

import com.emts.domain.models.Customer;
import com.emts.domain.models.Reservation;
import com.emts.domain.repositories.ReservationRepository;
import com.emts.exception.ReservationException;
import com.emts.util.Console;
import com.emts.util.cli.CliOperations;

import java.math.BigDecimal;

public class ReservationCli implements CliOperations {
    private final ReservationRepository reservationRepository;
    private static final String ENTER_ID = "Enter ID of reservation";
    private static final String ENTER_NAME = "Enter name of reservation";
    private static final String ENTER_SALARY = "Enter salary of reservation";
    private static final String UNEXPECTED_ERROR = "Unexpected error: ";

    public ReservationCli(ReservationRepository reservationRepository) {
        this.reservationRepository = reservationRepository;
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
            Console.print("Enter Customer Id");
            Customer customer ;
            Console.print(ENTER_SALARY);
            BigDecimal salary = Console.decimalIn();

            Reservation reservation = new Reservation(name, salary);
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
            Console.print(ENTER_NAME);
            String name = Console.stringIn();
            Console.print(ENTER_SALARY);
            BigDecimal salary = Console.decimalIn();
            reservationRepository.update(id, new Reservation(id, name, salary)).print();
        } catch (ReservationException e) {
            Console.print("Error updating reservation: " + e.getMessage());
        } catch (Exception e) {
            Console.print(UNEXPECTED_ERROR + e.getMessage());
        }
    }
}
