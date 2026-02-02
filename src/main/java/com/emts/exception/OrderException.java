package com.emts.exception;

import com.emts.exception.common.RestaurantException;

public class OrderException extends RestaurantException {

    public OrderException(String message, Throwable throwable) {
        super(message, throwable);
    }

    public OrderException(String message) {
        super(message);
    }
}
