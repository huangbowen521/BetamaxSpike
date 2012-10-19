//package com.thoughtworks;
//
//import co.freeside.betamax.Betamax;
//import co.freeside.betamax.TapeMode;
//import NGRecorder;
//import NGRule;
//import RulesListener;
//import org.testng.annotations.Listeners;
//import org.testng.annotations.Test;
//
//@Listeners(RulesListener.class)
//public class WeatherServiceNGTest {
//    @NGRule
//    public NGRecorder recorder = new NGRecorder();
//
//    @Betamax(tape = "WeatherService", mode = TapeMode.READ_WRITE)
//    @Test
//    public void shouldGetWeatherViaWebService2() throws Exception {
//        WeatherService weatherService = new WeatherService();
//        String result = weatherService.sendRequest("Washington");
//        org.testng.Assert.assertEquals(result.isEmpty(), false);
//    }
//}
