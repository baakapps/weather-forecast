package com.project.weatherforecast.unit;

import com.project.weatherforecast.controller.WeatherForecastController;
import com.project.weatherforecast.model.exception.OpenWeatherAPIException;
import com.project.weatherforecast.model.openweatherapi.successfulresponse.OpenWeatherAPIResponse;
import com.project.weatherforecast.service.WeatherForecastService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.junit.Assert.assertThrows;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(WeatherForecastController.class)
class WeatherForecastControllerTests {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private WeatherForecastService weatherService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void whenGetWeatherForecastThenReturns2xxHttpStatus() throws Exception {
        String city = "London";
        RequestBuilder requestBuilder =
                MockMvcRequestBuilders
                        .get("/api/v1/weatherForecast/" + city)
                        .accept(MediaType.APPLICATION_JSON);

        OpenWeatherAPIResponse openWeatherAPIResponse = new OpenWeatherAPIResponse();
        openWeatherAPIResponse.setName(city);
        when(weatherService.getWeatherForecastForNext48HoursByCity(anyString())).thenReturn(openWeatherAPIResponse);

        mockMvc.perform(requestBuilder).andExpect(status().isOk());
    }

    @Test
    void whenGetWeatherForecastThenThrowsError() {
        OpenWeatherAPIException openWeatherAPIException = new OpenWeatherAPIException("API error", "city not found", 404);

        when(weatherService.getWeatherForecastForNext48HoursByCity(anyString())).thenThrow(openWeatherAPIException);

        assertThrows(OpenWeatherAPIException.class, () -> weatherService.getWeatherForecastForNext48HoursByCity("London"));
    }

}

