package com.yurii.weather.facade;

import com.yurii.weather.client.ExternalFeignClient;
import com.yurii.weather.controller.model.WeatherRequest;
import com.yurii.weather.controller.model.WeatherResponse;
import com.yurii.weather.service.WeatherService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class WeatherFacade {
    private final WeatherService weatherService;


    private final ExternalFeignClient externalFeignClient;

    public WeatherResponse getWeather(WeatherRequest weatherRequest) {
        return
                weatherService.getWeather(weatherRequest.longitude(), weatherRequest.latitude(), weatherRequest.dateTime()).
                        map(weatherModel ->
                                new WeatherResponse(weatherModel.dateTime(),
                                        weatherModel.temperature() + weatherModel.type().toString())).orElse(
                                getExternalWeatherApi(weatherRequest));
    }

    private WeatherResponse getExternalWeatherApi(final WeatherRequest weatherRequest) {
        return Optional.ofNullable(externalFeignClient.getWeather(weatherRequest.longitude(), weatherRequest.latitude(),weatherRequest.dateTime()
                )).map(response -> new WeatherResponse(response.dateTime(), response.value())).
                orElse(WeatherResponse.empty());
    }

}

