package com.thoughtworks;

import service.WeatherService;

import java.io.IOException;

public class App {

    public static void main(String[] args) throws IOException {
        WeatherService weatherService = new WeatherService();

        weatherService.getWeather("Chicago");
    }
}
