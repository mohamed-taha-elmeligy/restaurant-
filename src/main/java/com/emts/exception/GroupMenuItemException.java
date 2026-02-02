package com.emts.exception;

import com.emts.exception.common.RestaurantException;

public class GroupMenuItemException extends RestaurantException {

    public GroupMenuItemException(String message, Throwable throwable) {
        super(message, throwable);
    }

    public GroupMenuItemException(String message) {
        super(message);
    }
}
