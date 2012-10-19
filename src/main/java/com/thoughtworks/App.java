package com.thoughtworks;

import co.freeside.betamax.MatchRule;
import co.freeside.betamax.Recorder;
import co.freeside.betamax.TapeMode;
import groovy.lang.Closure;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: twer
 * Date: 10/17/12
 * Time: 2:12 PM
 * To change this template use File | Settings | File Templates.
 */
public class App {

    public static void main(String[] args) {
        final Weather weather = new Weather();

        Recorder recorder = new Recorder();
        Map<Object,Object> map = new HashMap<Object, Object>();
        map.put("mode",TapeMode.DEFAULT);
        map.put("match", new MatchRule[]{MatchRule.method,MatchRule.uri});

        recorder.withTape("my tape", map, new Closure(null) {
           public void doCall() throws IOException {
              weather.getWeather("Chicago");
           }
        });
    }


}
