package com.emts.domain.repositories;

import com.emts.domain.models.Reservation;
import com.emts.domain.repositories.common.Repository;

import java.util.concurrent.ConcurrentMap;

public class ReservationRepository extends Repository<Integer, Reservation> {

    // In-memory repository for OOP practice

    public ReservationRepository(ConcurrentMap<Integer, Reservation> database) {
        super(database);
    }
}