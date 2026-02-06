package com.emts.domain.repositories;

import com.emts.domain.models.Reservation;
import com.emts.exception.ReservationException;
import com.emts.util.crud.CrudOperation;

import java.util.ArrayList;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

public class ReservationRepository implements CrudOperation<Integer, Reservation> {

    // In-memory repository for OOP practice

    private static final ReservationRepository INSTANCE = new ReservationRepository();
    private final ConcurrentMap<Integer, Reservation> database = new ConcurrentHashMap<>();

    private ReservationRepository() {}

    public static ReservationRepository getInstance() {
        return INSTANCE;
    }

    @Override
    public Reservation create(Reservation reservation) {
        if (reservation == null)
            throw new ReservationException("Reservation cannot be null");

        if (database.putIfAbsent(reservation.getId(), reservation) != null)
            throw new ReservationException("Reservation already exists with ID: " + reservation.getId());

        return reservation;
    }

    @Override
    public Reservation update(Reservation reservation) {
        if (reservation == null)
            throw new ReservationException("Reservation cannot be null");

        if (!database.containsKey(reservation.getId()))
            throw new ReservationException("Cannot update. Reservation not found with ID: " + reservation.getId());

        database.put(reservation.getId(), reservation);
        return reservation;
    }

    @Override
    public Reservation findById(Integer id) {
        return database.get(id);
    }

    @Override
    public Reservation delete(Integer id) {
        Reservation deleted = database.remove(id);
        if (deleted == null)
            throw new ReservationException("Cannot delete. Reservation not found with ID: " + id);

        return deleted;
    }

    @Override
    public ArrayList<Reservation> findAll() {
        return new ArrayList<>(database.values());
    }

    @Override
    public boolean exists(Integer id) {
        return database.containsKey(id);
    }
}