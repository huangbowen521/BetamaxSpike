package service;

import org.testng.annotations.Test;

import static org.testng.Assert.assertTrue;

public class WeatherServiceTest {
    @Test
    public void shouldReturnANonEmptyResultWhenGettingWeather() throws Exception {
        WeatherService weatherService = new WeatherService();
        String weatherResult = weatherService.getWeather("Chicago");
        assertTrue(weatherResult.length() > 0);
    }
}
