package com.kingrandesigns.weameter;

import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * ForecastFragment class retrieves weather information from weather forecast
 * objects and sets them into TextViews. The Fragment will later be incorporated
 * into the ViewPager.
 */
public class ForecastFragment extends Fragment {

    /**
     * Extracts the weather information from the parameters and inserts them
     * into Bundle object.
     *
     * @param forecastWeather
     *            Forecast data for a location
     * @return the ForecastFragment object with forecast conditions
     *         within bundle
     */
    public static final ForecastFragment newInstance(
            WeatherForecast forecastWeather) {
        ForecastFragment cf = new ForecastFragment();
        Bundle bdl = new Bundle();

        DayForecast df0 = forecastWeather.getForecast(0);
        bdl.putString("day0", df0.getDayOfWeek().substring(0, 3));
        bdl.putString("icon0", df0.currentCondition.getIcon());
        bdl.putString("high0C", df0.currentCondition.getMaxTempC() + "°");
        bdl.putString("high0F", df0.currentCondition.getMaxTempF() + "°");
        bdl.putString("low0C", df0.currentCondition.getMinTempC() + "°");
        bdl.putString("low0F", df0.currentCondition.getMinTempF() + "°");

        DayForecast df1 = forecastWeather.getForecast(1);
        bdl.putString("day1", df1.getDayOfWeek().substring(0, 3));
        bdl.putString("icon1", df1.currentCondition.getIcon());
        bdl.putString("high1C", df1.currentCondition.getMaxTempC() + "°");
        bdl.putString("high1F", df1.currentCondition.getMaxTempF() + "°");
        bdl.putString("low1C", df1.currentCondition.getMinTempC() + "°");
        bdl.putString("low1F", df1.currentCondition.getMinTempF() + "°");

        DayForecast df2 = forecastWeather.getForecast(2);
        bdl.putString("day2", df2.getDayOfWeek().substring(0, 3));
        bdl.putString("icon2", df2.currentCondition.getIcon());
        bdl.putString("high2C", df2.currentCondition.getMaxTempC() + "°");
        bdl.putString("high2F", df2.currentCondition.getMaxTempF() + "°");
        bdl.putString("low2C", df2.currentCondition.getMinTempC() + "°");
        bdl.putString("low2F", df2.currentCondition.getMinTempF() + "°");

        DayForecast df3 = forecastWeather.getForecast(3);
        bdl.putString("day3", df3.getDayOfWeek().substring(0, 3));
        bdl.putString("icon3", df3.currentCondition.getIcon());
        bdl.putString("high3C", df3.currentCondition.getMaxTempC() + "°");
        bdl.putString("high3F", df3.currentCondition.getMaxTempF() + "°");
        bdl.putString("low3C", df3.currentCondition.getMinTempC() + "°");
        bdl.putString("low3F", df3.currentCondition.getMinTempF() + "°");

        DayForecast df4 = forecastWeather.getForecast(4);
        bdl.putString("day4", df4.getDayOfWeek().substring(0, 3));
        bdl.putString("icon4", df4.currentCondition.getIcon());
        bdl.putString("high4C", df4.currentCondition.getMaxTempC() + "°");
        bdl.putString("high4F", df4.currentCondition.getMaxTempF() + "°");
        bdl.putString("low4C", df4.currentCondition.getMinTempC() + "°");
        bdl.putString("low4F", df4.currentCondition.getMinTempF() + "°");

        DayForecast df5 = forecastWeather.getForecast(5);
        bdl.putString("day5", df5.getDayOfWeek().substring(0, 3));
        bdl.putString("icon5", df5.currentCondition.getIcon());
        bdl.putString("high5C", df5.currentCondition.getMaxTempC() + "°");
        bdl.putString("high5F", df5.currentCondition.getMaxTempF() + "°");
        bdl.putString("low5C", df5.currentCondition.getMinTempC() + "°");
        bdl.putString("low5F", df5.currentCondition.getMinTempF() + "°");

        DayForecast df6 = forecastWeather.getForecast(6);
        bdl.putString("day6", df6.getDayOfWeek().substring(0, 3));
        bdl.putString("icon6", df6.currentCondition.getIcon());
        bdl.putString("high6C", df6.currentCondition.getMaxTempC() + "°");
        bdl.putString("high6F", df6.currentCondition.getMaxTempF() + "°");
        bdl.putString("low6C", df6.currentCondition.getMinTempC() + "°");
        bdl.putString("low6F", df6.currentCondition.getMinTempF() + "°");

        cf.setArguments(bdl);
        return cf;
    }

    /**
     * Callback method called whenever the fragment is created. It retrieves the
     * information within the bundle and sets it to the UI TextViews
     */
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.activity_forecast_fragment,
                container, false);
        SharedPreferences settings = PreferenceManager
                .getDefaultSharedPreferences(getActivity());
        boolean useCelcius = settings.getBoolean("use_celcius", false);

        TextView weekday0 = (TextView) v.findViewById(R.id.weekday0);
        weekday0.setText(getArguments().getString("day0"));
        setImage((ImageView) v.findViewById(R.id.image0), getArguments()
                .getString("icon0"));
        TextView high0 = (TextView) v.findViewById(R.id.forecastTempHigh0);
        if (useCelcius)
            high0.setText(getArguments().getString("high0C"));
        else
            high0.setText(getArguments().getString("high0F"));
        TextView low0 = (TextView) v.findViewById(R.id.forecastTempLow0);
        if (useCelcius)
            low0.setText(getArguments().getString("low0C"));
        else
            low0.setText(getArguments().getString("low0F"));

        TextView weekday1 = (TextView) v.findViewById(R.id.weekday1);
        weekday1.setText(getArguments().getString("day1"));
        setImage((ImageView) v.findViewById(R.id.image1), getArguments()
                .getString("icon1"));
        TextView high1 = (TextView) v.findViewById(R.id.forecastTempHigh1);
        if (useCelcius)
            high1.setText(getArguments().getString("high1C"));
        else
            high1.setText(getArguments().getString("high1F"));
        TextView low1 = (TextView) v.findViewById(R.id.forecastTempLow1);
        if (useCelcius)
            low1.setText(getArguments().getString("low1C"));
        else
            low1.setText(getArguments().getString("low1F"));

        TextView weekday2 = (TextView) v.findViewById(R.id.weekday2);
        weekday2.setText(getArguments().getString("day2"));
        setImage((ImageView) v.findViewById(R.id.image2), getArguments()
                .getString("icon2"));
        TextView high2 = (TextView) v.findViewById(R.id.forecastTempHigh2);
        if (useCelcius)
            high2.setText(getArguments().getString("high2C"));
        else
            high2.setText(getArguments().getString("high2F"));
        TextView low2 = (TextView) v.findViewById(R.id.forecastTempLow2);
        if (useCelcius)
            low2.setText(getArguments().getString("low2C"));
        else
            low2.setText(getArguments().getString("low2F"));

        TextView weekday3 = (TextView) v.findViewById(R.id.weekday3);
        weekday3.setText(getArguments().getString("day3"));
        setImage((ImageView) v.findViewById(R.id.image3), getArguments()
                .getString("icon3"));
        TextView high3 = (TextView) v.findViewById(R.id.forecastTempHigh3);
        if (useCelcius)
            high3.setText(getArguments().getString("high3C"));
        else
            high3.setText(getArguments().getString("high3F"));
        TextView low3 = (TextView) v.findViewById(R.id.forecastTempLow3);
        if (useCelcius)
            low3.setText(getArguments().getString("low3C"));
        else
            low3.setText(getArguments().getString("low3F"));

        TextView weekday4 = (TextView) v.findViewById(R.id.weekday4);
        weekday4.setText(getArguments().getString("day4"));
        setImage((ImageView) v.findViewById(R.id.image4), getArguments()
                .getString("icon4"));
        TextView high4 = (TextView) v.findViewById(R.id.forecastTempHigh4);
        if (useCelcius)
            high4.setText(getArguments().getString("high4C"));
        else
            high4.setText(getArguments().getString("high4F"));
        TextView low4 = (TextView) v.findViewById(R.id.forecastTempLow4);
        if (useCelcius)
            low4.setText(getArguments().getString("low4C"));
        else
            low4.setText(getArguments().getString("low4F"));

        TextView weekday5 = (TextView) v.findViewById(R.id.weekday5);
        weekday5.setText(getArguments().getString("day5"));
        setImage((ImageView) v.findViewById(R.id.image5), getArguments()
                .getString("icon5"));
        TextView high5 = (TextView) v.findViewById(R.id.forecastTempHigh5);
        if (useCelcius)
            high5.setText(getArguments().getString("high5C"));
        else
            high5.setText(getArguments().getString("high5F"));
        TextView low5 = (TextView) v.findViewById(R.id.forecastTempLow5);
        if (useCelcius)
            low5.setText(getArguments().getString("low5C"));
        else
            low5.setText(getArguments().getString("low5F"));

        TextView weekday6 = (TextView) v.findViewById(R.id.weekday6);
        weekday6.setText(getArguments().getString("day6"));
        setImage((ImageView) v.findViewById(R.id.image6), getArguments()
                .getString("icon6"));
        TextView high6 = (TextView) v.findViewById(R.id.forecastTempHigh6);
        if (useCelcius)
            high6.setText(getArguments().getString("high6C"));
        else
            high6.setText(getArguments().getString("high6F"));
        TextView low6 = (TextView) v.findViewById(R.id.forecastTempLow6);
        if (useCelcius)
            low6.setText(getArguments().getString("low6C"));
        else
            low6.setText(getArguments().getString("low6F"));

        return v;
    }

    public static void setImage(ImageView imageView, String iconDescription) {

        if (iconDescription.equals("clear")) {
            imageView.setImageResource(R.drawable.icon_clear);

        } else if (iconDescription.equals("sunny")) {
            imageView.setImageResource(R.drawable.icon_clear);

        } else if (iconDescription.equals("mostlycloudy")) {
            imageView.setImageResource(R.drawable.icon_partlysunny);

        } else if (iconDescription.equals("mostlysunny")) {
            imageView.setImageResource(R.drawable.icon_partlysunny);

        } else if (iconDescription.equals("partlycloudy")) {
            imageView.setImageResource(R.drawable.icon_partlysunny);

        } else if (iconDescription.equals("partlysunny")) {
            imageView.setImageResource(R.drawable.icon_partlysunny);

        } else if (iconDescription.equals("cloudy")) {
            imageView.setImageResource(R.drawable.icon_cloud);

        } else if (iconDescription.equals("flurries")) {
            imageView.setImageResource(R.drawable.icon_snow);

        } else if (iconDescription.equals("chanceflurries")) {
            imageView.setImageResource(R.drawable.icon_snow);

        } else if (iconDescription.equals("sleet")) {
            imageView.setImageResource(R.drawable.icon_littlerain);

        } else if (iconDescription.equals("chancesleet")) {
            imageView.setImageResource(R.drawable.icon_littlerain);

        } else if (iconDescription.equals("rain")) {
            imageView.setImageResource(R.drawable.icon_rain);

        } else if (iconDescription.equals("chancerain")) {
            imageView.setImageResource(R.drawable.icon_rain);

        } else if (iconDescription.equals("tstorms")) {
            imageView.setImageResource(R.drawable.icon_thunderstorm);

        } else if (iconDescription.equals("chancetstorms")) {
            imageView.setImageResource(R.drawable.icon_thunderstorm);

        } else if (iconDescription.equals("snow")) {
            imageView.setImageResource(R.drawable.icon_snow);

        } else if (iconDescription.equals("chancesnow")) {
            imageView.setImageResource(R.drawable.icon_snow);

        } else if (iconDescription.equals("hazy")) {
            imageView.setImageResource(R.drawable.icon_dayfog);

        } else if (iconDescription.equals("fog")) {
            imageView.setImageResource(R.drawable.icon_dayfog);

        } else {
            imageView.setImageResource(R.drawable.icon_generic);

        }
    }
}
