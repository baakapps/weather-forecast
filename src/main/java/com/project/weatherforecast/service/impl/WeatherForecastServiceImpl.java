package com.project.weatherforecast.service.impl;

import com.project.weatherforecast.model.exception.OpenWeatherAPIException;
import com.project.weatherforecast.model.openweatherapi.errorresponse.OpenWeatherAPIErrorResponse;
import com.project.weatherforecast.model.openweatherapi.successfulresponse.OpenWeatherAPIResponse;
import com.project.weatherforecast.service.WeatherForecastService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;


@Service
@RequiredArgsConstructor
@Slf4j
public class WeatherForecastServiceImpl implements WeatherForecastService {

    private final WebClient webClient;

    @Value("${openweather.api.key}")
    private String apiKey;

    @Value("${openweather.api.cnt}")
    private String cnt;

    @Override
    public OpenWeatherAPIResponse getWeatherForecastForNext48HoursByCity(String city) {
        return webClient.get()
                .uri(uriBuilder -> uriBuilder.path("/weather")
                        .queryParam("q", city)
                        .queryParam("appid", apiKey)
                        .queryParam("cnt", cnt)
                        .build())
                .retrieve()
                .onStatus(
                        status -> status.is4xxClientError() || status.is5xxServerError(),
                        response -> response.bodyToMono(OpenWeatherAPIErrorResponse.class)
                                .flatMap(error -> Mono.error(
                                        new OpenWeatherAPIException(
                                                "OpenWeatherMap API: " + error.getMessage(),
                                                error.getMessage(),
                                                Integer.parseInt(error.getCod())
                                        )
                                ))
                )
                .bodyToMono(OpenWeatherAPIResponse.class)
                .block();
    }
}