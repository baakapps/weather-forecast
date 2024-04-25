<div align="center">

# Weather Forecast Application


Weather forecast application that consumes the OpenWeather API to retrieve weather data and serves it as a REST API for client applications.
</div>

## How to run all things
### Run with Maven
*  mvn spring-boot:run

### Run with Docker
#### Before you start
* Install Docker
#### Run
* docker build -t weatherforecast .
* docker run -p 8080:8080 weatherforecast .

## How to use
#### GET Weather Forecast
```
GET http://localhost:8080/api/v1/weatherForecast/London

HTTP/1.1 200 
Content-Type: application/json

{
  "main": {
    "feelsLike": 282.86,
    "tempMax": 286.03,
    "humidity": 59
  }
}
```

### Error Response

```
GET http://localhost:8080/api/v1/weatherForecast/London123

HTTP/1.1 404 
Content-Type: application/problem+json

{
  "type": "about:blank",
  "title": "API_ERROR",
  "status": 404,
  "detail": "OpenWeatherMap API: city not found",
  "instance": "/api/v1/weatherForecast/London123"
}
```

```
GET http://localhost:8080/api/v1/weatherForecast123/London

HTTP/1.1 500 
Content-Type: application/problem+json

{
  "type": "about:blank",
  "title": "SYSTEM_ERROR",
  "status": 500,
  "detail": "The server encountered an error and could not complete your request. Please try again later.",
  "instance": "/api/v1/weatherForecast123/London"
}
```

### Important Endpoints
* http://localhost:8080/swagger-ui/index.html
