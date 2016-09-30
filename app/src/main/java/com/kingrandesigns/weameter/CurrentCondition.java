package com.kingrandesigns.weameter;

/**
 * CurrentCondition class has getter and setter methods for various current
 * weather information
 */
public class CurrentCondition {

    public mLocation mlocation;

    private String condition;
    private String icon;
    private String observationTime;
    private String pressure;
    private String humidity;
    private String visibilityMi;
    private String visibilityKm;
    private float tempC;
    private float tempF;
    private float dewPointC;
    private float dewPointF;
    private String feelsLikeC;
    private String feelsLikeF;
    private String direction;
    private float speedKph;
    private float speedMph;
    private String maxTempC;
    private String maxTempF;
    private String minTempC;
    private String minTempF;

    /**
     * Gets the condition for a location, for example "rain"
     * @return String that describes the weather condition
     */
    public String getCondition() {
        return condition;
    }

    /**
     * Sets the condition for a location
     * @param condition String that describes the weather condition
     */
    public void setCondition(String condition) {
        this.condition = condition;
    }

    /**
     * Gets the phrase that will be used to set the appropriate weather icon
     * @return String phrase that describes an icon
     */
    public String getIcon() {
        return icon;
    }

    /**
     * Sets the icon phrase for a location
     * @param icon String phrase that describes an icon
     */
    public void setIcon(String icon) {
        this.icon = icon;
    }

    /**
     * Gets the pressure for a location
     * @return Pressure String, for example "30.02 in"
     */
    public String getPressure() {
        return pressure;
    }

    /**
     * Sets the pressure for a location
     * @param pressure Pressure String, for example "30.02 in"
     */
    public void setPressure(String pressure) {
        this.pressure = pressure;
    }

    /**
     * Gets the humidity for a location
     * @return Humidity value
     */
    public String getHumidity() {
        return humidity;
    }

    /**
     * Sets the humidity for a location
     * @param humidity the humidity value to be set
     */
    public void setHumidity(String humidity) {
        this.humidity = humidity;
    }

    /**
     * Gets the time at which the weather information was requested
     * @return The String of the observation time
     */
    public String getObservationTime() {
        return observationTime;
    }

    /**
     * Sets the time at which the weather information was requested
     * @param observationTime The String of the observation time
     */
    public void setObservationTime(String observationTime) {
        this.observationTime = observationTime;
    }

    /**
     * Gets the current visibility of a location in miles
     * @return Visibility value in miles
     */
    public String getVisibilityMi() {
        return visibilityMi;
    }

    /**
     * Sets the current visibility of a location in miles
     * @param visibilityMi Visibility value in miles
     */
    public void setVisibilityMi(String visibilityMi) {
        this.visibilityMi = visibilityMi;
    }

    /**
     * Sets the current visibility of a location in kilometers
     * @return Visibility value in kilometers
     */
    public String getVisibilityKm() {
        return visibilityKm;
    }

    /**
     * Sets the current visibility of a location in kilometers
     * @param visibilityKm Visibility value in kilometers
     */
    public void setVisibilityKm(String visibilityKm) {
        this.visibilityKm = visibilityKm;
    }

    /**
     * Gets the current temperature in degrees Celsius
     * @return Temperature value in degrees Celsius
     */
    public float getTempC() {
        return tempC;
    }

    /**
     * Sets the current temperature in degrees Celsius
     * @param tempC Temperature value in degrees Celsius
     */
    public void setTempC(float tempC) {
        this.tempC = tempC;
    }

    /**
     * Gets the current temperature in degrees Fahrenheit
     * @return Temperature value in degrees Fahrenheit
     */
    public float getTempF() {
        return tempF;
    }

    /**
     * Sets the current temperature in degrees Fahrenheit
     * @param tempF Temperature value in degrees Fahrenheit
     */
    public void setTempF(float tempF) {
        this.tempF = tempF;
    }

    /**
     * Gets the dew point for a location in degrees Celsius
     * @return Dew point value in degrees Celsius
     */
    public float getDewPointC() {
        return dewPointC;
    }

    /**
     * Sets the dew point for a location in degrees Celsius
     * @param dewPointC Dew point value in degrees Celsius
     */
    public void setDewPointC(float dewPointC) {
        this.dewPointC = dewPointC;
    }

    /**
     * Gets the dew point for a location in degrees Fahrenheit
     * @return Dew point value in degrees Fahrenheit
     */
    public float getDewPointF() {
        return dewPointF;
    }

    /**
     * Sets the dew point for a location in degrees Fahrenheit
     * @param dewPointF Dew point value in degrees Fahreheit
     */
    public void setDewPointF(float dewPointF) {
        this.dewPointF = dewPointF;
    }

    /**
     * Gets the "feels like" temperature for a location in degrees Celsius
     * @return "Feels like" value in degrees Celsius
     */
    public String getFeelsLikeC() {
        return feelsLikeC;
    }

    /**
     * Sets the "feels like" temperature for a location in degrees Celsius
     * @param feelsLikeC "Feels like" value in degrees Celsius
     */
    public void setFeelsLikeC(String feelsLikeC) {
        this.feelsLikeC = feelsLikeC;
    }

    /**
     * Gets the "feels like" temperature for a location in degrees Fahrenheit
     * @return "Feels like" value in degrees Fahrenheit
     */
    public String getFeelsLikeF() {
        return feelsLikeF;
    }

    /**
     * Sets the "feels like" temperature for a location in degrees Fahrenheit
     * @param feelsLikeF "Feels like" value in degrees Fahrenheit
     */
    public void setFeelsLikeF(String feelsLikeF) {
        this.feelsLikeF = feelsLikeF;
    }

    /**
     * Gets the wind direction for a location, for example "NNW"
     * @return String of the wind direction
     */
    public String getDirection() {
        return direction;
    }

    /**
     * Sets the wind direction for a location, for example "NNW"
     * @param direction String of the wind direction
     */
    public void setDirection(String direction) {
        this.direction = direction;
    }

    /**
     * Gets the wind speed for a location in kilometers per hour
     * @return Wind speed value in kilometers per hour
     */
    public float getSpeedKph() {
        return speedKph;
    }

    /**
     * Sets the wind speed for a location in kilometers per hour
     * @param speedKph Wind speed in kilometers per hour
     */
    public void setSpeedKph(float speedKph) {
        this.speedKph = speedKph;
    }

    /**
     * Gets the wind speed for a location in miles per hour
     * @return Wind speed value in miles per hour
     */
    public float getSpeedMph() {
        return speedMph;
    }

    /**
     * Sets the wind speed for a location in miles per hour
     * @param speedMph Wind speed value in miles per hour
     */
    public void setSpeedMph(float speedMph) {
        this.speedMph = speedMph;
    }

    /**
     * Gets the maximum temperature for a location in degrees Celsius
     * @return Maximum temperature value
     */
    public String getMaxTempC() {
        return maxTempC;
    }

    /**
     * Sets the maximum temperature for a location in degrees Celsius
     * @param maxTempC Maximum temperature value
     */
    public void setMaxTempC(String maxTempC) {
        this.maxTempC = maxTempC;
    }

    /**
     * Gets the maximum temperature for a location in degrees Fahrenheit
     * @return Maximum temperature value
     */
    public String getMaxTempF() {
        return maxTempF;
    }

    /**
     * Sets the maximum temperature for a location in degrees Fahrenheit
     * @param maxTempF Maximum temperature value
     */
    public void setMaxTempF(String maxTempF) {
        this.maxTempF = maxTempF;
    }

    /**
     * Gets the minimum temperature for a location in degrees Celsius
     * @return Minimum temperature value
     */
    public String getMinTempC() {
        return minTempC;
    }
    /**
     * Sets the minimum temperature for a location in degrees Celsius
     * @param minTempC Minimum temperature value
     */
    public void setMinTempC(String minTempC) {
        this.minTempC = minTempC;
    }

    /**
     * Gets the minimum temperature for a location in degrees Fahrenheit
     * @return Minimum temperature value
     */
    public String getMinTempF() {
        return minTempF;
    }

    /**
     * Gets the minimum temperature for a location in degrees Fahrenheit
     * @param minTempF Minimum temperature value
     */
    public void setMinTempF(String minTempF) {
        this.minTempF = minTempF;
    }
}