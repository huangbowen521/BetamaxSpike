package com.thoughtworks;

import co.freeside.betamax.*;
import org.junit.*;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class WeatherServiceTest {
    @Rule
    public Recorder recorder = new Recorder();

    @Betamax(tape = "my tape", mode = TapeMode.READ_WRITE)
    @Test
    public void shouldGetWeatherViaWebService() throws Exception {
        WeatherService weatherService = new WeatherService();
        String result = weatherService.getWeather("Washington");

        assertThat(result.isEmpty(), is(false));
    }
}
