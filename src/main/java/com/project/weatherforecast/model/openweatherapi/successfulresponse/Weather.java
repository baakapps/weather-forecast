package com.project.weatherforecast.model.openweatherapi.successfulresponse;

@lombok.Data
public class Weather {
    private Long id;
    private String main;
    private String description;
    private String icon;
}