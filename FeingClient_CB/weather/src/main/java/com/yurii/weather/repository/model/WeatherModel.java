package com.yurii.weather.repository.model;

import java.time.LocalDateTime;

public record WeatherModel (LocalDateTime dateTime,Double temperature,Temperature type){


}
