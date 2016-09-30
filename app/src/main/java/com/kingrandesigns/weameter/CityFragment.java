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
 * CityFragment class retrieves weather information from weather objects and
 * sets them into TextViews. The Fragment will later be incorporated into the
 * ViewPager.
 */
public class CityFragment extends Fragment {

    /**
     * Extracts the weather information from the parameters and inserts them
     * into Bundle object.
     *
     * @param currentCondition
     *            Current conditions of location
     * @param forecast
     *            10 day forecast of location
     * @return the CityFragment object with current and forecast conditions
     *         within bundle
     */
    public static final CityFragment newInstance(
            com.kingrandesigns.weameter.CurrentCondition currentCondition, WeatherForecast forecast) {
        CityFragment cf = new CityFragment();
        Bundle bdl = new Bundle();

        bdl.putString("city", currentCondition.mlocation.getCity());
        String tempC = currentCondition.getTempC() + "°";
        if (tempC.contains(".")) {
            String newTempC = tempC.substring(0, tempC.indexOf("."));
            bdl.putString("tempC", newTempC);
        } else
            bdl.putString("tempC", tempC);
        String tempF = currentCondition.getTempF() + "°";
        if (tempF.contains(".")) {
            String newTempF = tempF.substring(0, tempF.indexOf("."));
            bdl.putString("tempF", newTempF);
        } else
            bdl.putString("tempF", tempF);
        bdl.putString("description", currentCondition.getCondition());
        bdl.putString("highTempC",
                forecast.getForecast(0).currentCondition.getMaxTempC());
        bdl.putString("highTempF",
                forecast.getForecast(0).currentCondition.getMaxTempF());
        bdl.putString("lowTempC",
                forecast.getForecast(0).currentCondition.getMinTempC());
        bdl.putString("lowTempF",
                forecast.getForecast(0).currentCondition.getMinTempF());

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

        View v = inflater.inflate(R.layout.activity_city_fragment, container,
                false);
        //Typeface quicksand = Typeface.createFromAsset(
        //        getActivity().getAssets(), "Quicksand_Book.otf");

        SharedPreferences settings = PreferenceManager
                .getDefaultSharedPreferences(getActivity());
        boolean useCelcius = settings.getBoolean("use_celcius", false);

        TextView cityText = (TextView) v.findViewById(R.id.cityText);
        cityText.setText(getArguments().getString("city"));
        //cityText.setTypeface(quicksand);
        TextView condDescr = (TextView) v.findViewById(R.id.condDescr);
        condDescr.setText(getArguments().getString("description"));
        //condDescr.setTypeface(quicksand);
        TextView temp = (TextView) v.findViewById(R.id.temp);
        if (useCelcius)
            temp.setText(getArguments().getString("tempC") + "°");
        else
            temp.setText(getArguments().getString("tempF") + "°");
        //temp.setTypeface(quicksand);
        TextView lowTemp = (TextView) v.findViewById(R.id.lowTemp);
        if (useCelcius)
            lowTemp.setText(getArguments().getString("lowTempC") + "°");
        else
            lowTemp.setText(getArguments().getString("lowTempF") + "°");
        //lowTemp.setTypeface(quicksand);
        TextView highTemp = (TextView) v.findViewById(R.id.highTemp);
        if (useCelcius)
            highTemp.setText(getArguments().getString("highTempC") + "°");
        else
            highTemp.setText(getArguments().getString("highTempF") + "°");
        //highTemp.setTypeface(quicksand);
        return v;
    }
}
