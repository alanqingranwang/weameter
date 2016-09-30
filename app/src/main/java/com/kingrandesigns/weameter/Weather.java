package com.kingrandesigns.weameter;

/**
 * Wrapper class that holds current condition, forecast, and hourly information
 * in a single package for easy mobility.
 */
public class Weather {

    public mLocation location;
    public CurrentCondition currentCondition = new CurrentCondition();
    public com.kingrandesigns.weameter.WeatherForecast weatherForecast = new com.kingrandesigns.weameter.WeatherForecast();
    public HourlyForecast hourlyForecast = new HourlyForecast();

}