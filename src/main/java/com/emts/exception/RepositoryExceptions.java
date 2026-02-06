package com.emts.exception;

import com.emts.exception.common.RestaurantException;

public class RepositoryExceptions extends RestaurantException {
    public RepositoryExceptions(String message, Throwable throwable) {
        super(message, throwable);
    }

    public RepositoryExceptions(String message) {
        super(message);
    }
}
