package com.project.weatherforecast.service;

import com.project.weatherforecast.model.openweatherapi.successfulresponse.OpenWeatherAPIResponse;

public interface WeatherForecastService {
    OpenWeatherAPIResponse getWeatherForecastForNext48HoursByCity(String city);
}
