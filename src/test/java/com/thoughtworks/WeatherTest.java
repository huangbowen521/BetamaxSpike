package com.thoughtworks;

import co.freeside.betamax.*;
import org.junit.*;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * Created with IntelliJ IDEA.
 * User: twer
 * Date: 10/17/12
 * Time: 2:21 PM
 * To change this template use File | Settings | File Templates.
 */
public class WeatherTest {

    @Rule
    public Recorder recorder = new Recorder();

    @Betamax(tape = "my tape", mode = TapeMode.WRITE_ONLY)
    @Test
    public void shouldGetWeatherviaWebService() throws Exception {
        Weather weather = new Weather();
        String result = weather.getWeather("Washington");

        assertThat(result.isEmpty(), is(false));
    }
}
