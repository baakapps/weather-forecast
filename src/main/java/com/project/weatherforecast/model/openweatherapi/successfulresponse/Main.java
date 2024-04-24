package com.project.weatherforecast.model.openweatherapi.successfulresponse;

import com.fasterxml.jackson.annotation.JsonView;
import com.project.weatherforecast.utility.WeatherForecastViews;

@lombok.Data
public class Main {
    private Double temp;
    @JsonView(WeatherForecastViews.WeatherForecastForNext48HoursView.class)
    private Double feelsLike;
    private Double tempMin;
    @JsonView(WeatherForecastViews.WeatherForecastForNext48HoursView.class)
    private Double tempMax;
    private Long pressure;
    @JsonView(WeatherForecastViews.WeatherForecastForNext48HoursView.class)
    private Long humidity;
    private Long seaLevel;
    private Long grndLevel;
}
