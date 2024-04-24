package com.project.weatherforecast.controller;


import com.fasterxml.jackson.annotation.JsonView;
import com.project.weatherforecast.model.openweatherapi.successfulresponse.OpenWeatherAPIResponse;
import com.project.weatherforecast.service.WeatherForecastService;
import com.project.weatherforecast.utility.WeatherForecastViews;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/api/v1/weatherForecast")
public class WeatherForecastController {

    private final WeatherForecastService weatherService;

    @JsonView(WeatherForecastViews.WeatherForecastForNext48HoursView.class)
    @GetMapping(value = "/{city}")
    public ResponseEntity<OpenWeatherAPIResponse> getWeatherForecastForNext48HoursByCity(@PathVariable String city) {
        OpenWeatherAPIResponse response = weatherService.getWeatherForecastForNext48HoursByCity(city);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(response);
    }
}
