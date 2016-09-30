package com.kingrandesigns.weameter;

import com.kingrandesigns.weameter.CurrentCondition;

/**
 * Holds a currentCondition field as well as a dayOfWeek field. Multiple
 * instances of this object will be put into an array within the WeatherForecast
 * class.
 */
public class DayForecast {
    public CurrentCondition currentCondition = new CurrentCondition();
    private String dayOfWeek;

    /**
     * Gets the day of the week associated with this object
     *
     * @return String day of the week
     */
    public String getDayOfWeek() {
        return dayOfWeek;
    }

    /**
     * Sets the day of the week associated with this object
     *
     * @param dayOfWeek
     *            String day of the week
     */
    public void setDayOfWeek(String dayOfWeek) {
        this.dayOfWeek = dayOfWeek;
    }
}
