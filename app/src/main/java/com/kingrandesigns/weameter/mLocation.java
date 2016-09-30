package com.kingrandesigns.weameter;

/**
 * Location class has getter and setter methods for various location attributes.
 */

public class mLocation {

    private float longitude;
    private float latitude;
    private long sunset;
    private long sunrise;
    private String country;
    private String city;
    private String state;
    private String zipCode;

    /**
     * Gets the longitude for the location
     * @return Float of longitude
     */
    public float getLongitude() {
        return longitude;
    }

    /**
     * Sets the longitude for the location
     * @param longitude Float of longitude
     */
    public void setLongitude(float longitude) {
        this.longitude = longitude;
    }

    /**
     * Gets the latitude for the location
     * @return Float of latitude
     */
    public float getLatitude() {
        return latitude;
    }

    /**
     * Sets the latitude for the location
     * @param latitude Float of latitude
     */
    public void setLatitude(float latitude) {
        this.latitude = latitude;
    }

    /**
     * Gets the time of sunset for the location
     * @return Long of sunset time
     */
    public long getSunset() {
        return sunset;
    }

    /**
     * Sets the time of sunset for the location
     * @param sunset Long of sunset time
     */
    public void setSunset(long sunset) {
        this.sunset = sunset;
    }

    /**
     * Gets the time of sunrise for the location
     * @return Long of sunrise
     */
    public long getSunrise() {
        return sunrise;
    }

    /**
     * Sets the time of sunrise for the location
     * @param sunrise Long of sunrise
     */
    public void setSunrise(long sunrise) {
        this.sunrise = sunrise;
    }

    /**
     * Gets the country of the location
     * @return String of country
     */
    public String getCountry() {
        return country;
    }

    /**
     * Sets the country of the location
     * @param country String of country
     */
    public void setCountry(String country) {
        this.country = country;
    }

    /**
     * Gets the city of the location
     * @return String of city
     */
    public String getCity() {
        return city;
    }

    /**
     * Sets the city of the location
     * @param city String of city
     */
    public void setCity(String city) {
        this.city = city;
    }

    /**
     * Gets the state of the location
     * @return String of state
     */
    public String getState() {
        return state;
    }

    /**
     * Sets the state of the location
     * @param state String of state
     */
    public void setState(String state) {
        this.state = state;
    }

    /**
     * Gets the zip code of the location
     * @return String of zip code
     */
    public String getZipCode() {
        return zipCode;
    }

    /**
     * Sets the zip code of the location
     * @param zipCode String of zip code
     */
    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }
}