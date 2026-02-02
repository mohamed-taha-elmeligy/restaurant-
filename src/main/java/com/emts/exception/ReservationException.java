package com.emts.exception;

import com.emts.exception.common.RestaurantException;

public class ReservationException extends RestaurantException {

    public ReservationException(String message, Throwable throwable) {
        super(message, throwable);
    }

    public ReservationException(String message) {
        super(message);
    }
}
