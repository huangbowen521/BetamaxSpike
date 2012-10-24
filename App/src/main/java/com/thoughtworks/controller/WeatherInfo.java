package com.thoughtworks.controller;

/**
 * Created with IntelliJ IDEA.
 * User: twer
 * Date: 10/24/12
 * Time: 2:50 PM
 * To change this template use File | Settings | File Templates.
 */
public class WeatherInfo {

    private String city;

    private String weather;

    public WeatherInfo()
    {}

    public WeatherInfo(String city, String weather)
    {
        this.city = city;
        this.weather =weather;
    }

    public String getCity()
    {
        return city;
    }

    public void setCity(String city)
    {
        this.city = city;
    }

    public String getWeather()
    {
        return weather;
    }

    public void setWeather(String weather)
    {
        this.weather = weather;
    }

}
