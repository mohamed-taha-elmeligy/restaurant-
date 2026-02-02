package com.emts.exception;

import com.emts.exception.common.RestaurantException;

public class WaiterException extends RestaurantException {

    public WaiterException(String message, Throwable throwable) {
        super(message, throwable);
    }

    public WaiterException(String message) {
        super(message);
    }
}
