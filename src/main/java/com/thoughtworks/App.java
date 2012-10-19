package com.thoughtworks;

import java.io.IOException;

public class App {

    public static void main(String[] args) throws IOException {
        WeatherService weatherService = new WeatherService();

        weatherService.sendRequest("Chicago");
    }
}
