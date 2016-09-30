package com.kingrandesigns.weameter;

import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Map;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.location.Location;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnKeyListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Filter;
import android.widget.Filterable;


/**
 * Activity that allows the user to search for a city's weather information,
 * using either the name of zip code. An AutoCompleteTextView gives suggestions
 * as the user types, and when the user clicks a suggestion the ResultActivity
 * of that location is launched and the String of the location is saved in a
 * SharedPreferences file.
 */
public class SearchActivity extends Activity implements OnItemClickListener,
        OnKeyListener {

    private AutoCompleteTextView autoCompView;
    SharedPreferences sp;
    mLocation loc = new mLocation();

    public static String fileName = "SavedLocations";

    private static final String LOG_TAG = "ExampleApp";
    private static final String PLACES_API_BASE = "https://maps.googleapis.com/maps/api/place";
    private static final String TYPE_AUTOCOMPLETE = "/autocomplete";
    private static final String OUT_JSON = "/json";
    private static final String API_KEY = "AIzaSyAJ8U9TrZ2ckeekpTM87544yvMBSmng-Ew";

    /**
     * Method called at launch. XML instances are initialized.
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        autoCompView = (AutoCompleteTextView) findViewById(R.id.autoCompleteTextView1);
        autoCompView.setAdapter(new PlacesAutoCompleteAdapter(this,
                android.R.layout.simple_list_item_1));
        autoCompView.setOnItemClickListener(this);
        autoCompView.setOnKeyListener(this);
    }

    /**
     * Method called when the user clicks a suggestion from the
     * AutoCompleteTextView. The String is formatted and an AsyncTask is
     * executed to retrieve the weather information.
     */
    @Override
    public void onItemClick(AdapterView<?> parent, View v, int position, long id) {
        JSONWeatherTask task = new JSONWeatherTask();
        int count = 0;
        String selectedString = (String) parent.getItemAtPosition(position);
        char[] array = selectedString.toCharArray();
        for (char c : array) {
            if (c == ',')
                count++;
        }

        if (count > 1) {
            if (selectedString.contains("United States")) {
                int lastCommaValue = selectedString.lastIndexOf(",");
                String substringText = selectedString.substring(0,
                        lastCommaValue);
                String replacedText = substringText.replaceAll(" ", "%20");
                task.execute(new String[] { replacedText });
            } else {
                int firstCommaValue = selectedString.indexOf(",");
                int lastCommaValue = selectedString.lastIndexOf(",");
                String substringText = selectedString.substring(0,
                        firstCommaValue)
                        + selectedString.substring(lastCommaValue);
                String replacedText = substringText.replaceAll(" ", "%20");
                task.execute(new String[] { replacedText });
            }
        } else {
            String replacedText = selectedString.replace(" ", "%20");
            task.execute(new String[] { replacedText });
        }
    }

    /**
     * AsyncTask that performs network operations in a background, asynchronous
     * thread. The city name of the city is retrieved and saved in the
     * SharedPreferences file. If the city is unavailable, a Dialog notifys the
     * user.
     */
    private class JSONWeatherTask extends AsyncTask<String, Void, String> {

        /**
         * Method called that performs background operations. JSON responses are
         * retrieved and parsed.
         */
        @Override
        protected String doInBackground(String... params) {
            String city = "";
            String data = ((new WeatherHttpClient()).getCurrentData(params[0]));

            if (data.equals("Error!")) {
                return "Error!";
            } else {
                try {
                    city = JSONWeatherParser.getCity(data);

                } catch (JSONException e) {
                    e.printStackTrace();
                }
                return city;
            }
        }

        /**
         * Method called after background operations are performed. If the city
         * returns an empty string, a Dialog notifies the user. Otherwise, the
         * string is added to the SharedPreferences file, and the ResultActivity
         * of that string is launched.
         */
        @Override
        protected void onPostExecute(String city) {
            if (city.equals("")) {
                AlertDialog dialog;
                AlertDialog.Builder builder = new AlertDialog.Builder(
                        SearchActivity.this);
                builder.setTitle("City Could Not Be Found");
                builder.setMessage("The city you requested could not be found. If the problem persists, please report it.");
                builder.setNegativeButton("OK",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                dialog.cancel();
                            }
                        });

                dialog = builder.create();
                dialog.show();
                return;
            }
            sp = getSharedPreferences(fileName, 0);
            SharedPreferences.Editor editor = sp.edit();
            Map<String, ?> dataReturned = sp.getAll();
            int length = dataReturned.size();
            editor.putString("data" + length, city);
            editor.commit();
            Intent intent = new Intent(SearchActivity.this,
                    ResultActivity.class);
            intent.putExtra("query", city);
            startActivity(intent);
        }
    }

    /**
     * Shows dialog notifying user of an error in network processing.
     */
    public void reportError() {
        Intent intent = new Intent(this, ShowError.class);
        startActivity(intent);
    }

    /**
     * Handles Autocomplete capabilities. Connects to Google Places API, and
     * retrieves the JSON predictions to be populated into the ListView of
     * suggestions.
     *
     * @param input
     *            The string the user types in.
     * @return ArrayList of strings containing suggestions.
     */
    private ArrayList<String> autocomplete(String input) {
        ArrayList<String> resultList = null;

        HttpURLConnection con = null;
        StringBuilder jsonResults = new StringBuilder();
        try {
            StringBuilder sb = new StringBuilder(PLACES_API_BASE
                    + TYPE_AUTOCOMPLETE + OUT_JSON);
            sb.append("?sensor=false&types=(cities)&key=" + API_KEY);
            sb.append("&input=" + URLEncoder.encode(input, "utf8"));

            URL url = new URL(sb.toString());
            con = (HttpURLConnection) url.openConnection();
            InputStreamReader in = new InputStreamReader(con.getInputStream());

            // Load the results into a StringBuilder
            int read;
            char[] buff = new char[1024];
            while ((read = in.read(buff)) != -1) {
                jsonResults.append(buff, 0, read);
            }
        } catch (MalformedURLException e) {
            Log.e(LOG_TAG, "Error processing Places API URL", e);
            return resultList;
        } catch (IOException e) {
            Log.e(LOG_TAG, "Error connecting to Places API", e);
            return resultList;
        } finally {
            if (con != null) {
                con.disconnect();
            }
        }

        try {
            JSONObject jsonObj = new JSONObject(jsonResults.toString());
            JSONArray predsJsonArray = jsonObj.getJSONArray("predictions");

            resultList = new ArrayList<String>(predsJsonArray.length());
            for (int i = 0; i < predsJsonArray.length(); i++) {
                resultList.add(predsJsonArray.getJSONObject(i).getString(
                        "description"));
            }
        } catch (JSONException e) {
            Log.e(LOG_TAG, "Cannot process JSON results", e);
        }

        return resultList;
    }

    /**
     * Custom adapter for the suggestions ListView
     */
    private class PlacesAutoCompleteAdapter extends ArrayAdapter<String>
            implements Filterable {
        private ArrayList<String> resultList;

        public PlacesAutoCompleteAdapter(Context context, int textViewResourceId) {
            super(context, textViewResourceId);
        }

        /**
         * Returns the number of strings in the array.
         */
        @Override
        public int getCount() {
            return resultList.size();
        }

        /**
         * Returns the string at the given index.
         */
        @Override
        public String getItem(int index) {
            return resultList.get(index);
        }

        /**
         * Returns a Filter object that sets values and count of filterResults
         * and updates the ListView suggestions.
         */
        @Override
        public Filter getFilter() {
            Filter filter = new Filter() {
                @Override
                protected FilterResults performFiltering(CharSequence constraint) {
                    FilterResults filterResults = new FilterResults();
                    if (constraint != null) {
                        resultList = autocomplete(constraint.toString());

                        filterResults.values = resultList;
                        filterResults.count = resultList.size();
                    }
                    return filterResults;
                }

                @Override
                protected void publishResults(CharSequence constraint,
                                              FilterResults results) {
                    if (results != null && results.count > 0) {
                        notifyDataSetChanged();
                    } else {
                        notifyDataSetInvalidated();
                    }
                }
            };
            return filter;
        }
    }

    /**
     * Method called when a key on the software keyboard is pressed.
     * Specifically, the "Enter" key is listened for, during which the string in
     * the AutoCompleteTextView is formatted and used to begin an AsyncTask.
     */
    @Override
    public boolean onKey(View v, int keyCode, KeyEvent event) {
        if ((event.getAction() == KeyEvent.ACTION_DOWN)
                && (keyCode == KeyEvent.KEYCODE_ENTER)) {
            JSONWeatherTask task = new JSONWeatherTask();
            int count = 0;
            String selectedString = autoCompView.getText().toString();
            char[] array = selectedString.toCharArray();
            for (char c : array) {
                if (c == ',')
                    count++;
            }

            if (count > 1) {
                if (selectedString.contains("United States")) {
                    int lastCommaValue = selectedString.lastIndexOf(",");
                    String substringText = selectedString.substring(0,
                            lastCommaValue);
                    String replacedText = substringText.replaceAll(" ", "%20");
                    task.execute(new String[] { replacedText });
                } else {
                    int firstCommaValue = selectedString.indexOf(",");
                    int lastCommaValue = selectedString.lastIndexOf(",");
                    String substringText = selectedString.substring(0,
                            firstCommaValue)
                            + selectedString.substring(lastCommaValue);
                    String replacedText = substringText.replaceAll(" ", "%20");
                    task.execute(new String[] { replacedText });
                }
            } else {
                String replacedText = selectedString.replace(" ", "%20");
                task.execute(new String[] { replacedText });
            }
            return false;
        }
        return false;
    }
}
