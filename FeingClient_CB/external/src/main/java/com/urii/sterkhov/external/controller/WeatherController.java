package com.urii.sterkhov.external.controller;

import lombok.SneakyThrows;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ProblemDetail;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.Random;
import java.util.concurrent.TimeUnit;

@RestController
@RequestMapping(path = "/api/weather", produces = MediaType.APPLICATION_JSON_VALUE)
public class WeatherController {

    private record WeatherResponse(LocalDateTime dateTime, String temperature) {

    }

    @SneakyThrows
    @GetMapping
    ResponseEntity<WeatherResponse> getWeather(@RequestParam("lon") double longitude, // вот эти параметры возрващаем
                                               @RequestParam("lat") double latitude,
                                               @RequestParam("dataTime") LocalDateTime dateTime) {

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
//        TimeUnit.SECONDS.sleep(10L);
//        return ResponseEntity.ok(new WeatherResponse(LocalDateTime.now(), String.valueOf(new Random().nextInt(+40))));
    }
}
