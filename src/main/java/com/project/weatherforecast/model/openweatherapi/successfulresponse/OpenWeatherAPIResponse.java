package com.project.weatherforecast.model.openweatherapi.successfulresponse;


import com.fasterxml.jackson.annotation.JsonView;
import com.project.weatherforecast.utility.WeatherForecastViews;

import java.util.List;

@lombok.Data
public class OpenWeatherAPIResponse {
    private Coord coord;
    private List<Weather> weather;
    private String base;
    @JsonView(WeatherForecastViews.WeatherForecastForNext48HoursView.class)
    private Main main;
    private Long visibility;
    private Wind wind;
    private Rain rain;
    private Clouds clouds;
    private Long dt;
    private Sys sys;
    private Long timezone;
    private Long id;
    private String name;
    private Long cod;
}
