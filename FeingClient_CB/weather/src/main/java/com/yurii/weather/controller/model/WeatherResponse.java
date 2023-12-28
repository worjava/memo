package com.yurii.weather.controller.model;

import java.time.LocalDateTime;

public record WeatherResponse(LocalDateTime dateTime,String temperature) {

    public static WeatherResponse empty(){
        return new WeatherResponse(LocalDateTime.now(),"no found data");
    }

}
