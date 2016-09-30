package com.kingrandesigns.weameter;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Performs all the network operations. Using a given location, an HTTP
 * connection is opened, connecting to the Weather Underground API which returns
 * a JSON response.
 */
public class WeatherHttpClient {

    /**
     * Returns the current condition information for a given location
     *
     * @param location
     *            The location to get weather information for.
     * @return JSON response to be parsed.
     */
    public String getCurrentData(String location) {
        HttpURLConnection con = null;
        InputStream is = null;
        String url = "http://api.wunderground.com/api/fc2573fb7eaaa5ed/conditions/q/"
                + location + ".json";
        try {
            con = (HttpURLConnection) (new URL(url)).openConnection();
            con.setRequestMethod("GET");
            con.setDoInput(true);
            con.setDoOutput(true);
            con.connect();

            StringBuffer buffer = new StringBuffer();
            is = con.getInputStream();
            BufferedReader br = new BufferedReader(new InputStreamReader(is));
            String line = null;
            while ((line = br.readLine()) != null)
                buffer.append(line + "\r\n");

            is.close();
            con.disconnect();
            return buffer.toString();
        } catch (Throwable t) {
            t.printStackTrace();
            return "Error!";
        } finally {
            try {
                is.close();
            } catch (Throwable t) {
            }
            try {
                con.disconnect();
            } catch (Throwable t) {
            }
        }
    }

    /**
     * Returns the forecast information for a given location
     *
     * @param location
     *            The location to get weather information for.
     * @return JSON response to be parsed.
     */
    public String getForecastData(String location) {
        HttpURLConnection con = null;
        InputStream is = null;

        try {
            String url = "http://api.wunderground.com/api/fc2573fb7eaaa5ed/forecast7day/q/"
                    + location + ".json";
            con = (HttpURLConnection) (new URL(url)).openConnection();
            con.setRequestMethod("GET");
            con.setDoInput(true);
            con.setDoOutput(true);
            con.connect();

            StringBuffer buffer1 = new StringBuffer();
            is = con.getInputStream();
            BufferedReader br1 = new BufferedReader(new InputStreamReader(is));
            String line1 = null;
            while ((line1 = br1.readLine()) != null)
                buffer1.append(line1 + "\r\n");

            is.close();
            con.disconnect();

            return buffer1.toString();
        } catch (Throwable t) {
            t.printStackTrace();
            return "Error!";
        } finally {
            try {
                is.close();
            } catch (Throwable t) {
            }
            try {
                con.disconnect();
            } catch (Throwable t) {
            }
        }
    }

    /**
     * Returns the hourly information for a given location
     *
     * @param location
     *            The location to get weather information for.
     * @return JSON response to be parsed.
     */
    public String getHourlyData(String location) {
        HttpURLConnection con = null;
        InputStream is = null;

        try {
            String url = "http://api.wunderground.com/api/fc2573fb7eaaa5ed/hourly/q/"
                    + location + ".json";
            con = (HttpURLConnection) (new URL(url)).openConnection();
            con.setRequestMethod("GET");
            con.setDoInput(true);
            con.setDoOutput(true);
            con.connect();

            StringBuffer buffer1 = new StringBuffer();
            is = con.getInputStream();
            BufferedReader br1 = new BufferedReader(new InputStreamReader(is));
            String line1 = null;
            while ((line1 = br1.readLine()) != null)
                buffer1.append(line1 + "\r\n");

            is.close();
            con.disconnect();

            return buffer1.toString();
        } catch (Throwable t) {
            t.printStackTrace();
            return "Error!";
        } finally {
            try {
                is.close();
            } catch (Throwable t) {
            }
            try {
                con.disconnect();
            } catch (Throwable t) {
            }
        }
    }
}
