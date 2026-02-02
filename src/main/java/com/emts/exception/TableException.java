package com.emts.exception;

import com.emts.exception.common.RestaurantException;

public class TableException extends RestaurantException {

    public TableException(String message, Throwable throwable) {
        super(message, throwable);
    }

    public TableException(String message) {
        super(message);
    }
}
