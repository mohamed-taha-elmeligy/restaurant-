package com.emts.exception;

import com.emts.exception.common.RestaurantException;

public class PhoneNumberException extends RestaurantException {

    public PhoneNumberException(String message, Throwable throwable) {
        super(message, throwable);
    }

    public PhoneNumberException(String message) {
        super(message);
    }
}
