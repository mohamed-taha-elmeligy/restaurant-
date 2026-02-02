package com.emts.exception;

import com.emts.exception.common.RestaurantException;

public class BillException extends RestaurantException {

    public BillException(String message, Throwable throwable) {
        super(message, throwable);
    }

    public BillException(String message) {
        super(message);
    }
}
