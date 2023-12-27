package com.yurii.weather.controller;

import com.yurii.weather.controller.model.WeatherRequest;
import com.yurii.weather.controller.model.WeatherResponse;
import com.yurii.weather.facade.WeatherFacade;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/weather")
@RequiredArgsConstructor
@ComponentScan
public class WeatherController {

    private final WeatherFacade weatherFacade;

    @PostMapping
    WeatherResponse getWeather(@RequestBody final WeatherRequest weatherRequest) {
        return weatherFacade.getWeather(weatherRequest);
    }

}
