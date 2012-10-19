package com.thoughtworks;

import co.freeside.betamax.Betamax;
import co.freeside.betamax.TapeMode;
import com.thoughtworks.extension.NGRecorder;
import com.thoughtworks.extension.NGRule;
import org.testng.annotations.Test;

public class WeatherNGTest {
    @NGRule
    public NGRecorder recorder = new NGRecorder();

    @Betamax(tape = "my tape", mode = TapeMode.READ_WRITE)
    @Test
    public void shouldGetWeatherViaWebService2() throws Exception {
        WeatherService weatherService = new WeatherService();
        String result = weatherService.getWeather("Washington");
        org.testng.Assert.assertEquals(result.isEmpty(), false);
    }
}
