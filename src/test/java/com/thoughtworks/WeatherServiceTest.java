//package com.thoughtworks;
//
//import co.freeside.betamax.*;
//import org.junit.*;
//
//import static org.hamcrest.core.Is.is;
//import static org.junit.Assert.assertThat;
//
//public class WeatherServiceTest {
//    @Rule
//    public Recorder recorder = new Recorder();
//
//    @Betamax(tape = "WeatherService", mode = TapeMode.READ_WRITE)
//    @Test
//    public void shouldGetWeatherViaWebService() throws Exception {
//        WeatherService weatherService = new WeatherService();
//        String result = weatherService.sendRequest("Washington");
//
//        assertThat(result.isEmpty(), is(false));
//    }
//
//    @Test
//    public void abc() {
//        assertThat("asf", is("asf"));
//    }
//}
