package com.kingrandesigns.weameter;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * The JSONWeatherParser class reads the JSON response parameter and stores the
 * information within the response into a Weather object.
 */
public class JSONWeatherParser {
    /**
     * Parses the current condition data into Weather object.
     *
     * @param data
     *            JSON response containing current condition data
     * @return Weather object with data
     * @throws JSONException
     *             No name is found or error occurs
     */
    public static CurrentCondition getCurrentCondition(String data)
            throws JSONException {
        CurrentCondition currentCondition = new CurrentCondition();
        JSONObject jObj = new JSONObject(data);
        mLocation loc = new mLocation();
        JSONObject currentObservationObj = getObject("current_observation",
                jObj);
        JSONObject displayLocationObj = getObject("display_location",
                currentObservationObj);
        loc.setCity(getString("city", displayLocationObj));
        loc.setState(getString("state", displayLocationObj));
        loc.setCountry(getString("country", displayLocationObj));
        loc.setZipCode(getString("zip", displayLocationObj));
        currentCondition.mlocation = loc;

        currentCondition.setDewPointC(getFloat("dewpoint_c",
                currentObservationObj));
        currentCondition.setDewPointF(getFloat("dewpoint_f",
                currentObservationObj));
        currentCondition.setFeelsLikeC(getString("feelslike_c",
                currentObservationObj));
        currentCondition.setFeelsLikeF(getString("feelslike_f",
                currentObservationObj));
        currentCondition.setTempC(getFloat("temp_c", currentObservationObj));
        currentCondition.setTempF(getFloat("temp_f", currentObservationObj));

        currentCondition.setObservationTime(getString("local_epoch",
                currentObservationObj));
        currentCondition.setPressure(getString("pressure_in",
                currentObservationObj));
        currentCondition.setHumidity(getString("relative_humidity",
                currentObservationObj));
        currentCondition.setVisibilityKm(getString("visibility_km",
                currentObservationObj));
        currentCondition.setVisibilityMi(getString("visibility_mi",
                currentObservationObj));
        currentCondition.setCondition(getString("weather",
                currentObservationObj));
        currentCondition.setIcon(getString("icon", currentObservationObj));

        currentCondition.setDirection(getString("wind_dir",
                currentObservationObj));
        currentCondition
                .setSpeedKph(getFloat("wind_kph", currentObservationObj));
        currentCondition
                .setSpeedMph(getFloat("wind_mph", currentObservationObj));

        return currentCondition;
    }

    /**
     * Parses the forecast data into Weather object.
     *
     * @param data
     *            JSON response containing forecast data
     * @return Weather object with data
     * @throws JSONException
     *             No name is found or error occurs
     */
    public static WeatherForecast getForecastWeather(String data)
            throws JSONException {

        WeatherForecast forecast = new WeatherForecast();

        JSONObject jObj = new JSONObject(data);
        JSONObject forecastObj = getObject("forecast", jObj);
        JSONObject simpleForecastObj = getObject("simpleforecast", forecastObj);
        JSONArray jArr = simpleForecastObj.getJSONArray("forecastday");

        for (int i = 0; i < jArr.length(); i++) {
            JSONObject jDayForecast = jArr.getJSONObject(i);

            DayForecast df = new DayForecast();

            JSONObject dateObj = jDayForecast.getJSONObject("date");
            df.setDayOfWeek(getString("weekday", dateObj));

            String icon = getString("icon", jDayForecast);
            df.currentCondition.setIcon(icon);

            JSONObject highTempObj = jDayForecast.getJSONObject("high");
            df.currentCondition.setMaxTempC(getString("celsius", highTempObj));
            df.currentCondition
                    .setMaxTempF(getString("fahrenheit", highTempObj));

            JSONObject lowTempObj = jDayForecast.getJSONObject("low");
            df.currentCondition.setMinTempC(getString("celsius", lowTempObj));
            df.currentCondition
                    .setMinTempF(getString("fahrenheit", lowTempObj));

            forecast.addForecast(df);
        }

        return forecast;
    }

    /**
     * Parses the hourly data into Weather object.
     *
     * @param data
     *            JSON response containing hourly data
     * @return Weather object with data
     * @throws JSONException
     *             No name is found or error occurs
     */
    public static HourlyForecast getHourly(String data) throws JSONException {
        HourlyForecast hourlyForecast = new HourlyForecast();
        JSONObject jObj = new JSONObject(data);
        JSONArray jArr = jObj.getJSONArray("hourly_forecast");

        for (int i = 0; i < jArr.length(); i++) {
            JSONObject jHourForecast = jArr.getJSONObject(i);

            HourWeather hw = new HourWeather();

            JSONObject timeObj = jHourForecast.getJSONObject("FCTTIME");
            hw.setHour(getString("civil", timeObj));

            JSONObject tempObj = jHourForecast.getJSONObject("temp");
            hw.currentCondition.setTempC(Float.parseFloat(getString("metric",
                    tempObj)));
            hw.currentCondition.setTempF(Float.parseFloat(getString("english",
                    tempObj)));

            hw.currentCondition.setIcon(getString("icon", jHourForecast));

            JSONObject windObj = jHourForecast.getJSONObject("wspd");
            hw.currentCondition.setSpeedKph(Float.parseFloat(getString(
                    "metric", windObj)));
            hw.currentCondition.setSpeedMph(Float.parseFloat(getString(
                    "english", windObj)));

            hw.setChancePrecip(getString("pop", jHourForecast));

            hourlyForecast.addForecast(hw);
        }

        return hourlyForecast;
    }

    /**
     * Parses only the city name data
     *
     * @param data
     *            JSON response containing current condition data
     * @return String of city
     * @throws JSONException
     *             No name is found or error occurs
     */
    public static String getCity(String data) throws JSONException {
        JSONObject jObj = new JSONObject(data);
        JSONObject currentObservationObj = getObject("current_observation",
                jObj);
        JSONObject displayLocationObj = getObject("display_location",
                currentObservationObj);
        return getString("full", displayLocationObj);
    }

    /**
     * Allows for instantiation of JSON object within response
     *
     * @param tagName
     *            Name of the object to be instantiated
     * @param jObj
     *            Parent JSON object
     * @return Child object
     * @throws JSONException
     *             No name is found or error occurs
     */
    private static JSONObject getObject(String tagName, JSONObject jObj)
            throws JSONException {
        JSONObject subObj = jObj.getJSONObject(tagName);
        return subObj;
    }

    /**
     * Allows for instantiation of JSON String within response
     *
     * @param tagName
     *            Name of the string to be instantiated
     * @param jObj
     *            Parent JSON object
     * @return Child String
     * @throws JSONException
     *             No name is found or error occurs
     */
    private static String getString(String tagName, JSONObject jObj)
            throws JSONException {
        return jObj.getString(tagName);
    }

    /**
     * Allows for instantiation of JSON float within response
     *
     * @param tagName
     *            Name of the float to be instantiated
     * @param jObj
     *            Parent JSON object
     * @return Child float
     * @throws JSONException
     *             No name is found or error occurs
     */
    private static float getFloat(String tagName, JSONObject jObj)
            throws JSONException {
        return (float) jObj.getDouble(tagName);
    }
}
