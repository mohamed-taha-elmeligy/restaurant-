package com.emts.exception;

import com.emts.exception.common.RestaurantException;

public class OrderItemException extends RestaurantException {

    public OrderItemException(String message, Throwable throwable) {
        super(message, throwable);
    }

    public OrderItemException(String message) {
        super(message);
    }
}
