package com.kingrandesigns.weameter;

import java.util.ArrayList;
import java.util.List;

/**
 * Holds an array of HourWeather objects that holds the hourly weather data for
 * a location.
 */
public class HourlyForecast {
    private List<HourWeather> hourForecast = new ArrayList<HourWeather>();

    /**
     * Adds a HourWeather object to the array
     * @param forecast HourWeather object to be added
     */
    public void addForecast(HourWeather forecast) {
        hourForecast.add(forecast);
    }

    /**
     * Gets the HourWeather object at the requested index of the array
     * @param hourNum the index of the array
     * @return HourWeather object to be returned
     */
    public HourWeather getForecast(int hourNum) {
        return hourForecast.get(hourNum);
    }
}
