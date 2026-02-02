package com.emts.exception;

import com.emts.exception.common.RestaurantException;

public class MenuException extends RestaurantException {

    public MenuException(String message, Throwable throwable) {
        super(message, throwable);
    }

    public MenuException(String message) {
        super(message);
    }
}
