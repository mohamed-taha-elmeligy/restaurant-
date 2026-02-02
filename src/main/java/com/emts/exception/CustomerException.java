package com.emts.exception;

import com.emts.exception.common.RestaurantException;

public class CustomerException extends RestaurantException {

    public CustomerException(String message, Throwable throwable) {
        super(message, throwable);
    }

    public CustomerException(String message) {
        super(message);
    }
}
