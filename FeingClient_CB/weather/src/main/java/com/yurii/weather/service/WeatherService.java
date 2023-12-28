package com.yurii.weather.service;

import com.yurii.weather.repository.WeatherRepository;
import com.yurii.weather.repository.model.WeatherModel;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class WeatherService {

    private final WeatherRepository weatherRepository;


    public Optional<WeatherModel> getWeather(
            final Double longitude,
            final Double latitude,
            final LocalDateTime dateTime) {
        Assert.notNull(longitude, "not null");
        Assert.notNull(longitude, "not null");
        return weatherRepository.getWeather(longitude, latitude,
                dateTime == null ? LocalDateTime.now() : dateTime);


    }

}
