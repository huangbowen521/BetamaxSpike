package service;


import org.junit.Test;

import static org.junit.Assert.assertTrue;

public class WeatherServiceIntegrationTest {

    @Test
    public void shouldReturnANonEmptyResultWhenGettingWeather() throws Exception {
        WeatherService weatherService = new WeatherService();
        String weatherResult = weatherService.getWeather("Chicago");
        assertTrue(weatherResult.length() > 0);
    }
}
