package com.emts.domain.repositories;

import com.emts.domain.models.Reservation;
import com.emts.exception.ReservationException;
import com.emts.util.CrudOperation;

import java.util.ArrayList;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

public class ReservationRepository implements CrudOperation<Integer, Reservation> {

    // In-memory repository for OOP practice

    private static final ReservationRepository INSTANCE = new ReservationRepository();
    private static final ConcurrentMap<Integer, Reservation> Database = new ConcurrentHashMap<>();

    private ReservationRepository() {}

    public static ReservationRepository getInstance() {
        return INSTANCE;
    }

    @Override
    public Reservation create(Reservation reservation) {
        if (reservation == null)
            throw new ReservationException("Reservation cannot be null");

        if (Database.putIfAbsent(reservation.getId(), reservation) != null)
            throw new ReservationException("Reservation already exists with ID: " + reservation.getId());

        return reservation;
    }

    @Override
    public Reservation update(Reservation reservation) {
        if (reservation == null)
            throw new ReservationException("Reservation cannot be null");

        if (!Database.containsKey(reservation.getId()))
            throw new ReservationException("Cannot update. Reservation not found with ID: " + reservation.getId());

        Database.put(reservation.getId(), reservation);
        return reservation;
    }

    @Override
    public Reservation findById(Integer id) {
        return Database.get(id);
    }

    @Override
    public Reservation delete(Integer id) {
        Reservation deleted = Database.remove(id);
        if (deleted == null)
            throw new ReservationException("Cannot delete. Reservation not found with ID: " + id);

        return deleted;
    }

    @Override
    public ArrayList<Reservation> findAll() {
        return new ArrayList<>(Database.values());
    }

    @Override
    public boolean exists(Integer id) {
        return Database.containsKey(id);
    }
}