package com.yurii.weather.repository;

import com.yurii.weather.controller.model.WeatherResponse;
import com.yurii.weather.repository.model.Temperature;
import com.yurii.weather.repository.model.WeatherModel;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.Optional;
import java.util.Random;

@Component
public class WeatherRepository {

    Random random = new Random();

    public Optional<WeatherModel> getWeather(final Double longitude,
                                             final Double latitude,
                                             final LocalDateTime dateTime) {
        if (longitude > 20 && latitude > 20) {
            return Optional.of(new WeatherModel(
                    dateTime.truncatedTo(ChronoUnit.HOURS),
                    random.nextDouble(40d), Temperature.C));

        } else {
            return Optional.empty();
        }

    }
}
