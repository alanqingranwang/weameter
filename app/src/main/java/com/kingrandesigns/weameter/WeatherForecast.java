package com.kingrandesigns.weameter;

import com.kingrandesigns.weameter.DayForecast;

import java.util.ArrayList;
import java.util.List;

/**
 * Holds an array of DayForecast objects that holds the forecast weather data for
 * a location.
 */
public class WeatherForecast {

    private List<DayForecast> daysForecast = new ArrayList<DayForecast>();

    /**
     * Adds a DayForecast object to the array
     * @param forecast DayForecast object to be added
     */
    public void addForecast(DayForecast forecast) {
        daysForecast.add(forecast);
    }

    /**
     * Gets the DayForecast object at the requested index of the array
     * @param dayNum the index of the array
     * @return DayForecast object to be returned
     */
    public DayForecast getForecast(int dayNum) {
        return daysForecast.get(dayNum);
    }

}
