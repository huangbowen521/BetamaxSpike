package com.thoughtworks.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import service.WeatherService;

import java.io.IOException;
import java.net.MalformedURLException;

/**
 * Created with IntelliJ IDEA.
 * User: twer
 * Date: 10/24/12
 * Time: 2:15 PM
 * To change this template use File | Settings | File Templates.
 */
@Controller
@RequestMapping(value = "/index")
public class WeatherController {

    @RequestMapping(method= RequestMethod.POST)
    public String processSubmit(@RequestParam("city") String city, Model model, RedirectAttributes redirectAttrs) throws IOException {


        WeatherInfo weatherInfo = new WeatherInfo(city, "Sunny");
        weatherInfo.setWeather(new WeatherService().getWeather(weatherInfo.getCity()));

        redirectAttrs.addFlashAttribute("weatherInfo", weatherInfo);
        return "redirect:index";
    }


    @RequestMapping(method = RequestMethod.GET)
    public void getWeather(){
    }

    @ModelAttribute("weatherInfo")
    public WeatherInfo createWeather() throws IOException {
        WeatherInfo weatherInfo = new WeatherInfo("New York", "Sunny");
        weatherInfo.setWeather(new WeatherService().getWeather(weatherInfo.getCity()));
        return weatherInfo;
    }
}
