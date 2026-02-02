package com.emts.exception;

import com.emts.exception.common.RestaurantException;

public class MenuItemException extends RestaurantException {
    public MenuItemException(String message, Throwable throwable) {
        super(message, throwable);
    }

    public MenuItemException(String message) {
        super(message);
    }
}
