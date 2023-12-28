package com.yurii.weather.client.model;

import java.time.LocalDateTime;

public record WeatherApiResponse(LocalDateTime dateTime, String value) {
}
