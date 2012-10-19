package com.thoughtworks;

import co.freeside.betamax.MatchRule;
import co.freeside.betamax.Recorder;
import co.freeside.betamax.TapeMode;
import groovy.lang.Closure;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class App {

    public static void main(String[] args) throws IOException {
        WeatherService weatherService = new WeatherService();

        weatherService.sendRequest("Chicago");
    }
}
