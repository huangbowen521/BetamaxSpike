package com.thoughtworks;

import java.io.IOException;
import java.net.MalformedURLException;

/**
 * Created with IntelliJ IDEA.
 * User: twer
 * Date: 10/17/12
 * Time: 2:12 PM
 * To change this template use File | Settings | File Templates.
 */
public class App {

    public static void main(String[] args) {
        try {
            Weather weather = new Weather();
            weather.getWeather("Washington");
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
