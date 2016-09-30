package com.kingrandesigns.weameter;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * CurrentConditionFragment class retrieves weather information about the
 * current conditions of a location and sets it into TextViews. The Fragment
 * will later be incorporated into the ViewPager.
 *
 */
public class CurrentConditionFragment extends Fragment {

    /**
     * Extracts the weather information from the parameters and inserts them
     * into Bundle object.
     *
     * @param currentCondition
     *            Current conditions for a location
     * @return CurrentConditionFragment object with current condition
     *         information within bundle
     */
    public static final CurrentConditionFragment newInstance(
            CurrentCondition currentCondition) {
        CurrentConditionFragment cf = new CurrentConditionFragment();
        Bundle bdl = new Bundle();

        String wind = currentCondition.getSpeedMph() + " mph";
        if (wind.contains(".")) {
            String newWind = wind.substring(0, wind.indexOf("."));
            bdl.putString("wind", newWind);
        } else
            bdl.putString("wind", wind);
        bdl.putString("humidity", currentCondition.getHumidity());
        bdl.putString("pressure", currentCondition.getPressure());
        String visibility = currentCondition.getVisibilityMi();
        if (visibility.contains(".")) {
            String newVisibility = visibility.substring(0,
                    visibility.indexOf("."));
            bdl.putString("visibility", newVisibility);
        } else
            bdl.putString("visibility", visibility);
        String dewPointC = currentCondition.getDewPointC() + "°";
        if (dewPointC.contains(".")) {
            String newDewPointC = dewPointC
                    .substring(0, dewPointC.indexOf("."));
            bdl.putString("dewPointC", newDewPointC);
        } else
            bdl.putString("dewPointC", dewPointC);
        String dewPointF = currentCondition.getDewPointF() + "°";
        if (dewPointF.contains(".")) {
            String newDewPointF = dewPointF
                    .substring(0, dewPointF.indexOf("."));
            bdl.putString("dewPointF", newDewPointF);
        } else
            bdl.putString("dewPointF", dewPointF);
        bdl.putString("feelsLikeC", currentCondition.getFeelsLikeC());
        bdl.putString("feelsLikeF", currentCondition.getFeelsLikeF());

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

        View v = inflater.inflate(R.layout.activity_conditions_fragment,
                container, false);
        SharedPreferences settings = PreferenceManager
                .getDefaultSharedPreferences(getActivity());
        boolean useCelcius = settings.getBoolean("use_celcius", false);

        TextView wind = (TextView) v.findViewById(R.id.wind);
        wind.setText("WIND");
        TextView windLabel = (TextView) v.findViewById(R.id.windLabel);
        windLabel.setText(getArguments().getString("wind") + " mph");
        TextView hum = (TextView) v.findViewById(R.id.hum);
        hum.setText("HUMIDITY");
        TextView humLabel = (TextView) v.findViewById(R.id.humLabel);
        humLabel.setText(getArguments().getString("humidity"));
        TextView pressure = (TextView) v.findViewById(R.id.pressure);
        pressure.setText("PRESSURE");
        TextView pressureLabel = (TextView) v.findViewById(R.id.pressureLabel);
        pressureLabel.setText(getArguments().getString("pressure") + " in");
        TextView visibility = (TextView) v.findViewById(R.id.visibility);
        visibility.setText("VISIBILITY");
        TextView visibilityLabel = (TextView) v
                .findViewById(R.id.visibilityLabel);
        visibilityLabel.setText(getArguments().getString("visibility") + " mi");
        TextView dewPoint = (TextView) v.findViewById(R.id.dewPoint);
        dewPoint.setText("DEWPOINT");
        TextView dewPointLabel = (TextView) v.findViewById(R.id.dewPointLabel);
        if (useCelcius)
            dewPointLabel.setText(getArguments().getString("dewPointC") + "°");
        else
            dewPointLabel.setText(getArguments().getString("dewPointF") + "°");
        TextView feelsLike = (TextView) v.findViewById(R.id.feelsLike);
        feelsLike.setText("FEELS LIKE");
        TextView feelsLikeLabel = (TextView) v
                .findViewById(R.id.feelsLikeLabel);
        if (useCelcius)
            feelsLikeLabel
                    .setText(getArguments().getString("feelsLikeC") + "°");
        else
            feelsLikeLabel
                    .setText(getArguments().getString("feelsLikeF") + "°");

        return v;
    }
}