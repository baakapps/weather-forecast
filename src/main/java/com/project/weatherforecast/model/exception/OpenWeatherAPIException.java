package com.project.weatherforecast.model.exception;

public class OpenWeatherAPIException extends RuntimeException {
    private final String errorMessage;
    private final int errorCode;

    public OpenWeatherAPIException(String message, String errorMessage, int errorCode) {
        super(message);
        this.errorMessage = errorMessage;
        this.errorCode = errorCode;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public int getErrorCode() {
        return errorCode;
    }
}

