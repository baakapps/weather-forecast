package com.project.weatherforecast.advice;

import com.project.weatherforecast.model.exception.OpenWeatherAPIException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler({OpenWeatherAPIException.class})
    public ProblemDetail handleOpenWeatherAPIErrors(OpenWeatherAPIException ex) {
        ProblemDetail problemDetail = ProblemDetail.forStatus(HttpStatus.valueOf(ex.getErrorCode()));
        problemDetail.setTitle("API_ERROR");
        problemDetail.setDetail(ex.getMessage());
        return problemDetail;
    }

    @ExceptionHandler({Exception.class})
    public ProblemDetail handleGenericErrors() {
        ProblemDetail problemDetail = ProblemDetail.forStatus(HttpStatus.INTERNAL_SERVER_ERROR);
        problemDetail.setTitle("SYSTEM_ERROR");
        problemDetail.setDetail("The server encountered an error and could not complete your request. Please try again later.");
        return problemDetail;
    }
}