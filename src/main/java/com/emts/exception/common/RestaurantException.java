package com.emts.exception.common;

public class RestaurantException extends RuntimeException {
    public RestaurantException(String message, Throwable throwable){
        super(message, throwable);
    }

    public RestaurantException(String message){
        super(message);
    }
}