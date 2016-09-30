package com.kingrandesigns.weameter;

import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * HourlyForecastFragment class retrieves weather information from hourly forecast
 * objects and sets them into TextViews. The Fragment will later be incorporated
 * into the ViewPager.
 */
public class HourlyForecastFragment extends Fragment {

    /**
     * Extracts the weather information from the parameters and inserts them
     * into Bundle object.
     *
     * @param hourly
     *            Hourly data for a location
     * @return the HourlyForecastFragment object with hourly conditions
     *         within bundle
     */
    public static final HourlyForecastFragment newInstance(HourlyForecast hourly) {
        HourlyForecastFragment cf = new HourlyForecastFragment();
        Bundle bdl = new Bundle();

        HourWeather hw0 = hourly.getForecast(0);
        String hour0 = hw0.getHour();
        String newHour0 = hour0.substring(0, hour0.indexOf(":")) + hour0.substring(hour0.indexOf("M") - 1);
        bdl.putString("hour0", newHour0);
        bdl.putString("icon0", hw0.currentCondition.getIcon());
        String temp0C = hw0.currentCondition.getTempC() + "°";
        if(temp0C.contains(".")) {
            String newTemp0C = temp0C.substring(0, temp0C.indexOf(".")) + "°";
            bdl.putString("temp0C", newTemp0C);
        }
        else
            bdl.putString("temp0C", temp0C);
        String temp0F = hw0.currentCondition.getTempF() + "°";
        if(temp0F.contains(".")) {
            String newTemp0F = temp0F.substring(0, temp0F.indexOf(".")) + "°";
            bdl.putString("temp0F", newTemp0F);
        }
        else
            bdl.putString("temp0F", temp0F);
        String wind0Mph = hw0.currentCondition.getSpeedMph() + " mph";
        if(wind0Mph.contains(".")) {
            String newWind0Mph = wind0Mph.substring(0, wind0Mph.indexOf(".")) + " mph";
            bdl.putString("wind0Mph", newWind0Mph);
        }
        else
            bdl.putString("wind0Mph", wind0Mph);
        String wind0Kph = hw0.currentCondition.getSpeedKph() + " km/h";
        if(wind0Kph.contains(".")) {
            String newWind0Kph = wind0Kph.substring(0, wind0Kph.indexOf(".")) + " km/h";
            bdl.putString("wind0Kph", newWind0Kph);
        }
        else
            bdl.putString("wind0Kph", wind0Kph);
        bdl.putString("chance0", hw0.getChancePrecip() + "%");

        HourWeather hw1 = hourly.getForecast(1);
        String hour1 = hw1.getHour();
        String newHour1 = hour1.substring(0, hour1.indexOf(":")) + hour1.substring(hour1.indexOf("M") - 1);
        bdl.putString("hour1", newHour1);
        bdl.putString("icon1", hw1.currentCondition.getIcon());
        String temp1C = hw1.currentCondition.getTempC() + "°";
        if(temp1C.contains(".")) {
            String newTemp1C = temp1C.substring(0, temp1C.indexOf(".")) + "°";
            bdl.putString("temp1C", newTemp1C);
        }
        else
            bdl.putString("temp1C", temp1C);
        String temp1F = hw1.currentCondition.getTempF() + "°";
        if(temp1F.contains(".")) {
            String newTemp1F = temp1F.substring(0, temp1F.indexOf(".")) + "°";
            bdl.putString("temp1F", newTemp1F);
        }
        else
            bdl.putString("temp1F", temp1F);
        String wind1Mph = hw1.currentCondition.getSpeedMph() + " mph";
        if(wind1Mph.contains(".")) {
            String newWind1Mph = wind1Mph.substring(0, wind1Mph.indexOf(".")) + " mph";
            bdl.putString("wind1Mph", newWind1Mph);
        }
        else
            bdl.putString("wind1Mph", wind1Mph);
        String wind1Kph = hw1.currentCondition.getSpeedKph() + " km/h";
        if(wind1Kph.contains(".")) {
            String newWind1Kph = wind1Kph.substring(0, wind1Kph.indexOf(".")) + " km/h";
            bdl.putString("wind1Kph", newWind1Kph);
        }
        else
            bdl.putString("wind1Kph", wind1Kph);
        bdl.putString("chance1", hw1.getChancePrecip() + "%");

        HourWeather hw2 = hourly.getForecast(2);
        String hour2 = hw2.getHour();
        String newHour2 = hour2.substring(0, hour2.indexOf(":")) + hour2.substring(hour2.indexOf("M") - 1);
        bdl.putString("hour2", newHour2);
        bdl.putString("icon2", hw2.currentCondition.getIcon());
        String temp2C = hw2.currentCondition.getTempC() + "°";
        if(temp2C.contains(".")) {
            String newTemp2C = temp2C.substring(0, temp2C.indexOf(".")) + "°";
            bdl.putString("temp2C", newTemp2C);
        }
        else
            bdl.putString("temp2C", temp2C);
        String temp2F = hw2.currentCondition.getTempF() + "°";
        if(temp2F.contains(".")) {
            String newTemp2F = temp2F.substring(0, temp2F.indexOf(".")) + "°";
            bdl.putString("temp2F", newTemp2F);
        }
        else
            bdl.putString("temp2F", temp2F);
        String wind2Mph = hw2.currentCondition.getSpeedMph() + " mph";
        if(wind2Mph.contains(".")) {
            String newWind2Mph = wind2Mph.substring(0, wind2Mph.indexOf(".")) + " mph";
            bdl.putString("wind2Mph", newWind2Mph);
        }
        else
            bdl.putString("wind2Mph", wind2Mph);
        String wind2Kph = hw2.currentCondition.getSpeedKph() + " km/h";
        if(wind2Kph.contains(".")) {
            String newWind2Kph = wind2Kph.substring(0, wind2Kph.indexOf(".")) + " km/h";
            bdl.putString("wind2Kph", newWind2Kph);
        }
        else
            bdl.putString("wind2Kph", wind2Kph);
        bdl.putString("chance2", hw2.getChancePrecip() + "%");

        HourWeather hw3 = hourly.getForecast(3);
        String hour3 = hw3.getHour();
        String newHour3 = hour3.substring(0, hour3.indexOf(":")) + hour3.substring(hour3.indexOf("M") - 1);
        bdl.putString("hour3", newHour3);
        bdl.putString("icon3", hw3.currentCondition.getIcon());
        String temp3C = hw3.currentCondition.getTempC() + "°";
        if(temp3C.contains(".")) {
            String newTemp3C = temp3C.substring(0, temp3C.indexOf(".")) + "°";
            bdl.putString("temp3C", newTemp3C);
        }
        else
            bdl.putString("temp3C", temp3C);
        String temp3F = hw3.currentCondition.getTempF() + "°";
        if(temp3F.contains(".")) {
            String newTemp3F = temp3F.substring(0, temp3F.indexOf(".")) + "°";
            bdl.putString("temp3F", newTemp3F);
        }
        else
            bdl.putString("temp3F", temp3F);
        String wind3Mph = hw3.currentCondition.getSpeedMph() + " mph";
        if(wind3Mph.contains(".")) {
            String newWind3Mph = wind3Mph.substring(0, wind3Mph.indexOf(".")) + " mph";
            bdl.putString("wind3Mph", newWind3Mph);
        }
        else
            bdl.putString("wind3Mph", wind3Mph);
        String wind3Kph = hw3.currentCondition.getSpeedKph() + " km/h";
        if(wind3Kph.contains(".")) {
            String newWind3Kph = wind3Kph.substring(0, wind3Kph.indexOf(".")) + " km/h";
            bdl.putString("wind3Kph", newWind3Kph);
        }
        else
            bdl.putString("wind3Kph", wind3Kph);
        bdl.putString("chance3", hw3.getChancePrecip() + "%");

        HourWeather hw4 = hourly.getForecast(4);
        String hour4 = hw4.getHour();
        String newHour4 = hour4.substring(0, hour4.indexOf(":")) + hour4.substring(hour4.indexOf("M") - 1);
        bdl.putString("hour4", newHour4);
        bdl.putString("icon4", hw4.currentCondition.getIcon());
        String temp4C = hw4.currentCondition.getTempC() + "°";
        if(temp4C.contains(".")) {
            String newTemp4C = temp4C.substring(0, temp4C.indexOf(".")) + "°";
            bdl.putString("temp4C", newTemp4C);
        }
        else
            bdl.putString("temp4C", temp4C);
        String temp4F = hw4.currentCondition.getTempF() + "°";
        if(temp4F.contains(".")) {
            String newTemp4F = temp4F.substring(0, temp4F.indexOf(".")) + "°";
            bdl.putString("temp4F", newTemp4F);
        }
        else
            bdl.putString("temp4F", temp4F);
        String wind4Mph = hw4.currentCondition.getSpeedMph() + " mph";
        if(wind4Mph.contains(".")) {
            String newWind4Mph = wind4Mph.substring(0, wind4Mph.indexOf(".")) + " mph";
            bdl.putString("wind4Mph", newWind4Mph);
        }
        else
            bdl.putString("wind4Mph", wind4Mph);
        String wind4Kph = hw4.currentCondition.getSpeedKph() + " km/h";
        if(wind4Kph.contains(".")) {
            String newWind4Kph = wind4Kph.substring(0, wind4Kph.indexOf(".")) + " km/h";
            bdl.putString("wind4Kph", newWind4Kph);
        }
        else
            bdl.putString("wind4Kph", wind4Kph);
        bdl.putString("chance4", hw4.getChancePrecip() + "%");

        HourWeather hw5 = hourly.getForecast(5);
        String hour5 = hw5.getHour();
        String newHour5 = hour5.substring(0, hour5.indexOf(":")) + hour5.substring(hour5.indexOf("M") - 1);
        bdl.putString("hour5", newHour5);
        bdl.putString("icon5", hw5.currentCondition.getIcon());
        String temp5C = hw5.currentCondition.getTempC() + "°";
        if(temp5C.contains(".")) {
            String newTemp5C = temp5C.substring(0, temp5C.indexOf(".")) + "°";
            bdl.putString("temp5C", newTemp5C);
        }
        else
            bdl.putString("temp5C", temp5C);
        String temp5F = hw5.currentCondition.getTempF() + "°";
        if(temp5F.contains(".")) {
            String newTemp5F = temp5F.substring(0, temp5F.indexOf(".")) + "°";
            bdl.putString("temp5F", newTemp5F);
        }
        else
            bdl.putString("temp5F", temp5F);
        String wind5Mph = hw5.currentCondition.getSpeedMph() + " mph";
        if(wind5Mph.contains(".")) {
            String newWind5Mph = wind5Mph.substring(0, wind5Mph.indexOf(".")) + " mph";
            bdl.putString("wind5Mph", newWind5Mph);
        }
        else
            bdl.putString("wind5Mph", wind5Mph);
        String wind5Kph = hw5.currentCondition.getSpeedKph() + " km/h";
        if(wind5Kph.contains(".")) {
            String newWind5Kph = wind5Kph.substring(0, wind5Kph.indexOf(".")) + " km/h";
            bdl.putString("wind5Kph", newWind5Kph);
        }
        else
            bdl.putString("wind5Kph", wind5Kph);
        bdl.putString("chance5", hw5.getChancePrecip() + "%");

        HourWeather hw6 = hourly.getForecast(6);
        String hour6 = hw6.getHour();
        String newHour6 = hour6.substring(0, hour6.indexOf(":")) + hour6.substring(hour6.indexOf("M") - 1);
        bdl.putString("hour6", newHour6);
        bdl.putString("icon6", hw6.currentCondition.getIcon());
        String temp6C = hw6.currentCondition.getTempC() + "°";
        if(temp6C.contains(".")) {
            String newTemp6C = temp6C.substring(0, temp6C.indexOf(".")) + "°";
            bdl.putString("temp6C", newTemp6C);
        }
        else
            bdl.putString("temp6C", temp6C);
        String temp6F = hw6.currentCondition.getTempF() + "°";
        if(temp6F.contains(".")) {
            String newTemp6F = temp6F.substring(0, temp6F.indexOf(".")) + "°";
            bdl.putString("temp6F", newTemp6F);
        }
        else
            bdl.putString("temp6F", temp6F);
        String wind6Mph = hw6.currentCondition.getSpeedMph() + " mph";
        if(wind6Mph.contains(".")) {
            String newWind6Mph = wind6Mph.substring(0, wind6Mph.indexOf(".")) + " mph";
            bdl.putString("wind6Mph", newWind6Mph);
        }
        else
            bdl.putString("wind6Mph", wind6Mph);
        String wind6Kph = hw6.currentCondition.getSpeedKph() + " km/h";
        if(wind6Kph.contains(".")) {
            String newWind6Kph = wind6Kph.substring(0, wind6Kph.indexOf(".")) + " km/h";
            bdl.putString("wind6Kph", newWind6Kph);
        }
        else
            bdl.putString("wind6Kph", wind6Kph);
        bdl.putString("chance6", hw6.getChancePrecip() + "%");

        HourWeather hw7 = hourly.getForecast(7);
        String hour7 = hw7.getHour();
        String newHour7 = hour7.substring(0, hour7.indexOf(":")) + hour7.substring(hour7.indexOf("M") - 1);
        bdl.putString("hour7", newHour7);
        bdl.putString("icon7", hw7.currentCondition.getIcon());
        String temp7C = hw7.currentCondition.getTempC() + "°";
        if(temp7C.contains(".")) {
            String newTemp7C = temp7C.substring(0, temp7C.indexOf(".")) + "°";
            bdl.putString("temp7C", newTemp7C);
        }
        else
            bdl.putString("temp7C", temp7C);
        String temp7F = hw7.currentCondition.getTempF() + "°";
        if(temp7F.contains(".")) {
            String newTemp7F = temp7F.substring(0, temp7F.indexOf(".")) + "°";
            bdl.putString("temp7F", newTemp7F);
        }
        else
            bdl.putString("temp7F", temp7F);
        String wind7Mph = hw7.currentCondition.getSpeedMph() + " mph";
        if(wind7Mph.contains(".")) {
            String newWind7Mph = wind7Mph.substring(0, wind7Mph.indexOf(".")) + " mph";
            bdl.putString("wind7Mph", newWind7Mph);
        }
        else
            bdl.putString("wind7Mph", wind7Mph);
        String wind7Kph = hw7.currentCondition.getSpeedKph() + " km/h";
        if(wind7Kph.contains(".")) {
            String newWind7Kph = wind7Kph.substring(0, wind7Kph.indexOf(".")) + " km/h";
            bdl.putString("wind7Kph", newWind7Kph);
        }
        else
            bdl.putString("wind7Kph", wind7Kph);
        bdl.putString("chance7", hw7.getChancePrecip() + "%");

        HourWeather hw8 = hourly.getForecast(8);
        String hour8 = hw8.getHour();
        String newHour8 = hour8.substring(0, hour8.indexOf(":")) + hour8.substring(hour8.indexOf("M") - 1);
        bdl.putString("hour8", newHour8);
        bdl.putString("icon8", hw8.currentCondition.getIcon());
        String temp8C = hw8.currentCondition.getTempC() + "°";
        if(temp8C.contains(".")) {
            String newTemp8C = temp8C.substring(0, temp8C.indexOf(".")) + "°";
            bdl.putString("temp8C", newTemp8C);
        }
        else
            bdl.putString("temp8C", temp8C);
        String temp8F = hw8.currentCondition.getTempF() + "°";
        if(temp8F.contains(".")) {
            String newTemp8F = temp8F.substring(0, temp8F.indexOf(".")) + "°";
            bdl.putString("temp8F", newTemp8F);
        }
        else
            bdl.putString("temp8F", temp8F);
        String wind8Mph = hw8.currentCondition.getSpeedMph() + " mph";
        if(wind8Mph.contains(".")) {
            String newWind8Mph = wind8Mph.substring(0, wind8Mph.indexOf(".")) + " mph";
            bdl.putString("wind8Mph", newWind8Mph);
        }
        else
            bdl.putString("wind8Mph", wind8Mph);
        String wind8Kph = hw8.currentCondition.getSpeedKph() + " km/h";
        if(wind8Kph.contains(".")) {
            String newWind8Kph = wind8Kph.substring(0, wind8Kph.indexOf(".")) + " km/h";
            bdl.putString("wind8Kph", newWind8Kph);
        }
        else
            bdl.putString("wind8Kph", wind8Kph);
        bdl.putString("chance8", hw8.getChancePrecip() + "%");

        HourWeather hw9 = hourly.getForecast(9);
        String hour9 = hw9.getHour();
        String newHour9 = hour9.substring(0, hour9.indexOf(":")) + hour9.substring(hour9.indexOf("M") - 1);
        bdl.putString("hour9", newHour9);
        bdl.putString("icon9", hw9.currentCondition.getIcon());
        String temp9C = hw9.currentCondition.getTempC() + "°";
        if(temp9C.contains(".")) {
            String newTemp9C = temp9C.substring(0, temp9C.indexOf(".")) + "°";
            bdl.putString("temp9C", newTemp9C);
        }
        else
            bdl.putString("temp9C", temp9C);
        String temp9F = hw9.currentCondition.getTempF() + "°";
        if(temp9F.contains(".")) {
            String newTemp9F = temp9F.substring(0, temp9F.indexOf(".")) + "°";
            bdl.putString("temp9F", newTemp9F);
        }
        else
            bdl.putString("temp9F", temp9F);
        String wind9Mph = hw9.currentCondition.getSpeedMph() + " mph";
        if(wind9Mph.contains(".")) {
            String newWind9Mph = wind9Mph.substring(0, wind9Mph.indexOf(".")) + " mph";
            bdl.putString("wind9Mph", newWind9Mph);
        }
        else
            bdl.putString("wind9Mph", wind9Mph);
        String wind9Kph = hw9.currentCondition.getSpeedKph() + " km/h";
        if(wind9Kph.contains(".")) {
            String newWind9Kph = wind9Kph.substring(0, wind9Kph.indexOf(".")) + " km/h";
            bdl.putString("wind9Kph", newWind9Kph);
        }
        else
            bdl.putString("wind9Kph", wind9Kph);
        bdl.putString("chance9", hw9.getChancePrecip() + "%");

        HourWeather hw10 = hourly.getForecast(10);
        String hour10 = hw10.getHour();
        String newHour10 = hour10.substring(0, hour10.indexOf(":")) + hour10.substring(hour10.indexOf("M") - 1);
        bdl.putString("hour10", newHour10);
        bdl.putString("icon10", hw10.currentCondition.getIcon());
        String temp10C = hw10.currentCondition.getTempC() + "°";
        if(temp10C.contains(".")) {
            String newTemp10C = temp10C.substring(0, temp10C.indexOf(".")) + "°";
            bdl.putString("temp10C", newTemp10C);
        }
        else
            bdl.putString("temp10C", temp10C);
        String temp10F = hw10.currentCondition.getTempF() + "°";
        if(temp10F.contains(".")) {
            String newTemp10F = temp10F.substring(0, temp10F.indexOf(".")) + "°";
            bdl.putString("temp10F", newTemp10F);
        }
        else
            bdl.putString("temp10F", temp10F);
        String wind10Mph = hw10.currentCondition.getSpeedMph() + " mph";
        if(wind10Mph.contains(".")) {
            String newWind10Mph = wind10Mph.substring(0, wind10Mph.indexOf(".")) + " mph";
            bdl.putString("wind10Mph", newWind10Mph);
        }
        else
            bdl.putString("wind10Mph", wind10Mph);
        String wind10Kph = hw10.currentCondition.getSpeedKph() + " km/h";
        if(wind10Kph.contains(".")) {
            String newWind10Kph = wind10Kph.substring(0, wind10Kph.indexOf(".")) + " km/h";
            bdl.putString("wind10Kph", newWind10Kph);
        }
        else
            bdl.putString("wind10Kph", wind10Kph);
        bdl.putString("chance10", hw10.getChancePrecip() + "%");

        HourWeather hw11 = hourly.getForecast(11);
        String hour11 = hw11.getHour();
        String newHour11 = hour11.substring(0, hour11.indexOf(":")) + hour11.substring(hour11.indexOf("M") - 1);
        bdl.putString("hour11", newHour11);
        bdl.putString("icon11", hw11.currentCondition.getIcon());
        String temp11C = hw11.currentCondition.getTempC() + "°";
        if(temp11C.contains(".")) {
            String newTemp11C = temp11C.substring(0, temp11C.indexOf(".")) + "°";
            bdl.putString("temp11C", newTemp11C);
        }
        else
            bdl.putString("temp11C", temp11C);
        String temp11F = hw11.currentCondition.getTempF() + "°";
        if(temp11F.contains(".")) {
            String newTemp11F = temp11F.substring(0, temp11F.indexOf(".")) + "°";
            bdl.putString("temp11F", newTemp11F);
        }
        else
            bdl.putString("temp11F", temp11F);
        String wind11Mph = hw11.currentCondition.getSpeedMph() + " mph";
        if(wind11Mph.contains(".")) {
            String newWind11Mph = wind11Mph.substring(0, wind11Mph.indexOf(".")) + " mph";
            bdl.putString("wind11Mph", newWind11Mph);
        }
        else
            bdl.putString("wind11Mph", wind11Mph);
        String wind11Kph = hw11.currentCondition.getSpeedKph() + " km/h";
        if(wind11Kph.contains(".")) {
            String newWind11Kph = wind11Kph.substring(0, wind11Kph.indexOf(".")) + " km/h";
            bdl.putString("wind11Kph", newWind11Kph);
        }
        else
            bdl.putString("wind11Kph", wind11Kph);
        bdl.putString("chance11", hw11.getChancePrecip() + "%");

        cf.setArguments(bdl);
        return cf;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.activity_hourly_fragment, container, false);
        SharedPreferences settings = PreferenceManager.getDefaultSharedPreferences(getActivity());
        boolean useCelcius = settings.getBoolean("use_celcius", false);
        boolean useKmh = settings.getBoolean("use_kmh", false);

        TextView hour0 = (TextView) v.findViewById(R.id.hour0);
        hour0.setText(getArguments().getString("hour0"));
        TextView temp0 = (TextView) v.findViewById(R.id.temp0);
        if(useCelcius)
            temp0.setText(getArguments().getString("temp0C"));
        else
            temp0.setText(getArguments().getString("temp0F"));
        TextView wind0 = (TextView) v.findViewById(R.id.wind0);
        if(useKmh)
            wind0.setText(getArguments().getString("wind0Kph"));
        else
            wind0.setText(getArguments().getString("wind0Mph"));
        ((TextView) v.findViewById(R.id.chance0)).setText(getArguments().getString("chance0"));
        TextView chance0 = (TextView) v.findViewById(R.id.chance0);
        chance0.setText(getArguments().getString("chance0"));

        TextView hour1 = (TextView) v.findViewById(R.id.hour1);
        hour1.setText(getArguments().getString("hour1"));
        TextView temp1 = (TextView) v.findViewById(R.id.temp1);
        if(useCelcius)
            temp1.setText(getArguments().getString("temp1C"));
        else
            temp1.setText(getArguments().getString("temp1F"));
        TextView wind1 = (TextView) v.findViewById(R.id.wind1);
        if(useKmh)
            wind1.setText(getArguments().getString("wind1Kph"));
        else
            wind1.setText(getArguments().getString("wind1Mph"));
        TextView chance1 = (TextView) v.findViewById(R.id.chance1);
        chance1.setText(getArguments().getString("chance1"));

        TextView hour2 = (TextView) v.findViewById(R.id.hour2);
        hour2.setText(getArguments().getString("hour2"));
        TextView temp2 = (TextView) v.findViewById(R.id.temp2);
        if(useCelcius)
            temp2.setText(getArguments().getString("temp2C"));
        else
            temp2.setText(getArguments().getString("temp2F"));
        TextView wind2 = (TextView) v.findViewById(R.id.wind2);
        if(useKmh)
            wind2.setText(getArguments().getString("wind2Kph"));
        else
            wind2.setText(getArguments().getString("wind2Mph"));
        TextView chance2 = (TextView) v.findViewById(R.id.chance2);
        chance2.setText(getArguments().getString("chance2"));

        TextView hour3 = (TextView) v.findViewById(R.id.hour3);
        hour3.setText(getArguments().getString("hour3"));
        TextView temp3 = (TextView) v.findViewById(R.id.temp3);
        if(useCelcius)
            temp3.setText(getArguments().getString("temp3C"));
        else
            temp3.setText(getArguments().getString("temp3F"));
        TextView wind3 = (TextView) v.findViewById(R.id.wind3);
        if(useKmh)
            wind3.setText(getArguments().getString("wind3Kph"));
        else
            wind3.setText(getArguments().getString("wind3Mph"));
        TextView chance3 = (TextView) v.findViewById(R.id.chance3);
        chance3.setText(getArguments().getString("chance3"));

        TextView hour4 = (TextView) v.findViewById(R.id.hour4);
        hour4.setText(getArguments().getString("hour4"));
        TextView temp4 = (TextView) v.findViewById(R.id.temp4);
        if(useCelcius)
            temp4.setText(getArguments().getString("temp4C"));
        else
            temp4.setText(getArguments().getString("temp4F"));
        TextView wind4 = (TextView) v.findViewById(R.id.wind4);
        if(useKmh)
            wind4.setText(getArguments().getString("wind4Kph"));
        else
            wind4.setText(getArguments().getString("wind4Mph"));
        TextView chance4 = (TextView) v.findViewById(R.id.chance4);
        chance4.setText(getArguments().getString("chance4"));

        TextView hour5 = (TextView) v.findViewById(R.id.hour5);
        hour5.setText(getArguments().getString("hour5"));
        TextView temp5 = (TextView) v.findViewById(R.id.temp5);
        if(useCelcius)
            temp5.setText(getArguments().getString("temp5C"));
        else
            temp5.setText(getArguments().getString("temp5F"));
        TextView wind5 = (TextView) v.findViewById(R.id.wind5);
        if(useKmh)
            wind5.setText(getArguments().getString("wind5Kph"));
        else
            wind5.setText(getArguments().getString("wind5Mph"));
        TextView chance5 = (TextView) v.findViewById(R.id.chance5);
        chance5.setText(getArguments().getString("chance5"));

        TextView hour6 = (TextView) v.findViewById(R.id.hour6);
        hour6.setText(getArguments().getString("hour6"));
        TextView temp6 = (TextView) v.findViewById(R.id.temp6);
        if(useCelcius)
            temp6.setText(getArguments().getString("temp6C"));
        else
            temp6.setText(getArguments().getString("temp6F"));
        TextView wind6 = (TextView) v.findViewById(R.id.wind6);
        if(useKmh)
            wind6.setText(getArguments().getString("wind6Kph"));
        else
            wind6.setText(getArguments().getString("wind6Mph"));
        TextView chance6 = (TextView) v.findViewById(R.id.chance6);
        chance6.setText(getArguments().getString("chance6"));

        TextView hour7 = (TextView) v.findViewById(R.id.hour7);
        hour7.setText(getArguments().getString("hour7"));
        TextView temp7 = (TextView) v.findViewById(R.id.temp7);
        if(useCelcius)
            temp7.setText(getArguments().getString("temp7C"));
        else
            temp7.setText(getArguments().getString("temp7F"));
        TextView wind7 = (TextView) v.findViewById(R.id.wind7);
        if(useKmh)
            wind7.setText(getArguments().getString("wind7Kph"));
        else
            wind7.setText(getArguments().getString("wind7Mph"));
        TextView chance7 = (TextView) v.findViewById(R.id.chance7);
        chance7.setText(getArguments().getString("chance7"));

        TextView hour8 = (TextView) v.findViewById(R.id.hour8);
        hour8.setText(getArguments().getString("hour8"));
        TextView temp8 = (TextView) v.findViewById(R.id.temp8);
        if(useCelcius)
            temp8.setText(getArguments().getString("temp8C"));
        else
            temp8.setText(getArguments().getString("temp8F"));
        TextView wind8 = (TextView) v.findViewById(R.id.wind8);
        if(useKmh)
            wind8.setText(getArguments().getString("wind8Kph"));
        else
            wind8.setText(getArguments().getString("wind8Mph"));
        TextView chance8 = (TextView) v.findViewById(R.id.chance8);
        chance8.setText(getArguments().getString("chance8"));

        TextView hour9 = (TextView) v.findViewById(R.id.hour9);
        hour9.setText(getArguments().getString("hour9"));
        TextView temp9 = (TextView) v.findViewById(R.id.temp9);
        if(useCelcius)
            temp9.setText(getArguments().getString("temp9C"));
        else
            temp9.setText(getArguments().getString("temp9F"));
        TextView wind9 = (TextView) v.findViewById(R.id.wind9);
        if(useKmh)
            wind9.setText(getArguments().getString("wind9Kph"));
        else
            wind9.setText(getArguments().getString("wind9Mph"));
        TextView chance9 = (TextView) v.findViewById(R.id.chance9);
        chance9.setText(getArguments().getString("chance9"));

        TextView hour10 = (TextView) v.findViewById(R.id.hour10);
        hour10.setText(getArguments().getString("hour10"));
        TextView temp10 = (TextView) v.findViewById(R.id.temp10);
        if(useCelcius)
            temp10.setText(getArguments().getString("temp10C"));
        else
            temp10.setText(getArguments().getString("temp10F"));
        TextView wind10 = (TextView) v.findViewById(R.id.wind10);
        if(useKmh)
            wind10.setText(getArguments().getString("wind10Kph"));
        else
            wind10.setText(getArguments().getString("wind10Mph"));
        TextView chance10 = (TextView) v.findViewById(R.id.chance10);
        chance10.setText(getArguments().getString("chance10"));

        TextView hour11 = (TextView) v.findViewById(R.id.hour11);
        hour11.setText(getArguments().getString("hour11"));
        TextView temp11 = (TextView) v.findViewById(R.id.temp11);
        if(useCelcius)
            temp11.setText(getArguments().getString("temp11C"));
        else
            temp11.setText(getArguments().getString("temp11F"));
        TextView wind11 = (TextView) v.findViewById(R.id.wind11);
        if(useKmh)
            wind11.setText(getArguments().getString("wind11Kph"));
        else
            wind11.setText(getArguments().getString("wind11Mph"));
        TextView chance11 = (TextView) v.findViewById(R.id.chance11);
        chance11.setText(getArguments().getString("chance11"));

        return v;
    }
}
