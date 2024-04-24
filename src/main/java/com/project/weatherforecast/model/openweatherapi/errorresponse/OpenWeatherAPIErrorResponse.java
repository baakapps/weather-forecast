package com.project.weatherforecast.model.openweatherapi.errorresponse;

@lombok.Data
public class OpenWeatherAPIErrorResponse {
    private String message;
    private String cod;
}
