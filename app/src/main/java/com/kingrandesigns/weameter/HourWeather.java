package com.kingrandesigns.weameter;

import com.kingrandesigns.weameter.CurrentCondition;

/**
 * Holds a CurrentCondition object, as well as hour field and chance of
 * precipitation field. Multiple instances of this class will be added into an
 * array within the HourlyForecast class.
 */
public class HourWeather {
    public CurrentCondition currentCondition = new CurrentCondition();
    private String hour;
    private String chanceprecip;

    /**
     * Gets the hour associated with this object
     * @return Hour requested
     */
    public String getHour() {
        return hour;
    }

    /**
     * Sets the hour associated with this object
     * @param hour Hour requested
     */
    public void setHour(String hour) {
        this.hour = hour;
    }

    /**
     * Gets the chance of precipitation for this hour
     * @return Chance of precipitation value
     */
    public String getChancePrecip() {
        return chanceprecip;
    }

    /**
     * Sets the chance of precipitation for this hour
     * @param chance Chance of precipitation value
     */
    public void setChancePrecip(String chance) {
        this.chanceprecip = chance;
    }
}
