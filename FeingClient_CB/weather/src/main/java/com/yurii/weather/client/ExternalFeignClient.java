package com.yurii.weather.client;

import com.yurii.weather.client.model.WeatherApiResponse;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDateTime;

@FeignClient(name = "weather")
public interface ExternalFeignClient {

    @GetMapping
    @CircuitBreaker(name = "weather-breaker")
    WeatherApiResponse getWeather( //три параметра от контролера которого хотим получить данные
                                   @RequestParam(name = "lon") double longitude,
                                   @RequestParam(name = "lat") double latitude,
                                   @RequestParam(name = "dataTime") LocalDateTime dateTime);


}
