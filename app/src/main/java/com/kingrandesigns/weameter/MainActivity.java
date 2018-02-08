package com.kingrandesigns.weather;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Random;

import org.json.JSONException;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.drawable.Drawable;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.support.v4.widget.DrawerLayout;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.ProgressBar;

/**
 * The activity that starts at launch. Upon launch, it checks if network and
 * location services are enabled. The SharedPreferences file containing previous
 * saved locations is read and is displayed in the drawer layout. It gets the
 * current location of the user and feeds the information to the weather API,
 * which returns a JSON response. The response is parsed and is presented to the
 * user in a ViewPager.
 */
public class MainActivity extends FragmentActivity {

	private double latitude;
	private double longitude;
	LocationManager lm;
	JSONWeatherTask task = new JSONWeatherTask();
	public static String fileName = "SavedLocations";
	private SharedPreferences sp;
	private DrawerLayout mDrawerLayout;
	private ListView mDrawerList;
	private int[] sunnyPics = { R.drawable.sunny0, R.drawable.sunny1,
			R.drawable.sunny2, R.drawable.sunny3 };
	private int[] overcastPics = { R.drawable.overcast0, R.drawable.overcast2,
			R.drawable.overcast3, R.drawable.overcast4 };
	private int[] foggyPics = { R.drawable.foggy0, R.drawable.foggy1,
			R.drawable.foggy2, R.drawable.foggy3 };
	private int[] rainyPics = { R.drawable.rainy0, R.drawable.rainy1,
			R.drawable.rainy2, R.drawable.rainy3 };
	private int[] snowyPics = { R.drawable.snowy0, R.drawable.snowy1,
			R.drawable.snowy2, R.drawable.snowy3 };
	private int[] tstormPics = { R.drawable.tstorm0, R.drawable.tstorm1,
			R.drawable.tstorm2, R.drawable.tstorm3 };
	private ProgressBar spinner;

	/**
	 * Callback method called when app is first launched. Instances of XML views
	 * are initialized.
	 */
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		setContentView(R.layout.activity_main);
		sp = getSharedPreferences(fileName, 0);
		mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
		mDrawerList = (ListView) findViewById(R.id.left_drawer_listview);
		spinner = (ProgressBar) findViewById(R.id.progressBar1);
	}

	/**
	 * Callback method called when activity is brought to the front. Internet
	 * and location services are checked, and the current location of the user
	 * is received. The DrawerLayout is initialized.
	 */
	@Override
	protected void onResume() {
		super.onResume();
		ConnectivityManager connMgr = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
		NetworkInfo networkInfo = connMgr.getActiveNetworkInfo();
		if (networkInfo != null && networkInfo.isConnected()) {
			lm = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
			if (!lm.isProviderEnabled(LocationManager.NETWORK_PROVIDER)) {
				AlertDialog.Builder builder = new AlertDialog.Builder(
						MainActivity.this);
				builder.setTitle("Location Services Not Enabled");
				builder.setMessage("Go to Settings to enable location services?");
				builder.setPositiveButton("Yes",
						new DialogInterface.OnClickListener() {
							public void onClick(
									DialogInterface dialogInterface, int i) {
								startActivity(new Intent(
										android.provider.Settings.ACTION_LOCATION_SOURCE_SETTINGS));
							}
						});
				builder.setNegativeButton("Cancel", null);
				builder.create().show();
				return;
			}
			lm.requestSingleUpdate(LocationManager.NETWORK_PROVIDER,
					new LocationListener() {

						/**
						 * Starts the AsyncTask using the current latitude and
						 * longitude of the user.
						 */
						@Override
						public void onLocationChanged(Location location) {
							latitude = location.getLatitude();
							longitude = location.getLongitude();
							String thisLocation = Double.toString(latitude)
									+ "," + Double.toString(longitude);
							JSONWeatherTask task = new JSONWeatherTask();
							task.execute(new String[] { thisLocation });
						}

						@Override
						public void onProviderDisabled(String arg0) {

						}

						@Override
						public void onProviderEnabled(String arg0) {

						}

						@Override
						public void onStatusChanged(String arg0, int arg1,
								Bundle arg2) {

						}

					}, getMainLooper());
		} else {
			AlertDialog.Builder builder = new AlertDialog.Builder(
					MainActivity.this);
			builder.setTitle("Internet Not Enabled");
			builder.setMessage("Please turn on WiFi or mobile data");
			builder.create().show();
			return;
		}

		final ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
				R.layout.drawer_list_item, getPrefArray());

		mDrawerList.setAdapter(adapter);
		mDrawerList.setOnItemClickListener(new OnItemClickListener() {
			/**
			 * Starts a new activity, passing along the String of the item
			 * clicked. This String will be used to get the weather information
			 * for that city.
			 */
			@Override
			public void onItemClick(AdapterView<?> parent, View v,
					int position, long id) {
				if (!parent.getItemAtPosition(position).equals("Add Location")
						&& !parent.getItemAtPosition(position).equals(
								"Current Location")) {
					mDrawerLayout.closeDrawer(Gravity.LEFT);
					String selectedString = (String) parent
							.getItemAtPosition(position);
					Intent intent = new Intent(MainActivity.this,
							ResultActivity.class);
					intent.putExtra("query", selectedString);
					startActivity(intent);
				}
				else if (parent.getItemAtPosition(position).equals("Add Location")) {
					Intent intent = new Intent(MainActivity.this, SearchActivity.class);
					startActivity(intent);
				}
			}
		});

		mDrawerList
				.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
					/**
					 * Shows a dialog that allows the user to delete a location
					 * when the item is long pressed. If the user clicks "OK",
					 * the item is removed from the adapter and the ListView is
					 * reinitialized.
					 */
					@Override
					public boolean onItemLongClick(AdapterView<?> arg0,
							View arg1, int position, long arg3) {
						if (!arg0.getItemAtPosition(position).equals("Add Location")
								&& !arg0.getItemAtPosition(position).equals(
										"Current Location")) {
							final int positionToRemove = position;
							AlertDialog dialog;
							AlertDialog.Builder builder = new AlertDialog.Builder(
									MainActivity.this);
							builder.setTitle("Delete Location?");
							builder.setMessage("Would you like to delete this location?");
							builder.setPositiveButton("OK",
									new DialogInterface.OnClickListener() {
										public void onClick(DialogInterface dialog,
												int id) {
											String toRemove = adapter
													.getItem(positionToRemove);
											adapter.remove(toRemove);
											SharedPreferences.Editor editor = sp
													.edit();
											editor.clear();
											for (int i = 0; i < adapter.getCount(); i++) {
												String item = adapter.getItem(i);
												editor.putString("data" + i, item);
											}
											editor.commit();
										}
									});
							builder.setNegativeButton("Cancel",
									new DialogInterface.OnClickListener() {
										public void onClick(DialogInterface dialog,
												int id) {
											dialog.cancel();
										}
									});
	
							dialog = builder.create();
							dialog.show();
							return false;
						}
						return false;
					}
				});
	}

	/**
	 * AsyncTask that performs network operations in a background, asynchronous
	 * thread. A loading spinner is made visible while a JSON response is
	 * retrieved and parsed from the given location. Fragments for the ViewPager
	 * using the weather information are created.
	 */
	private class JSONWeatherTask extends AsyncTask<String, Void, Weather> {

		/**
		 * Method called before network operations are performed. In this case,
		 * a spinner is made visible.
		 */
		@Override
		protected void onPreExecute() {
			spinner.setVisibility(View.VISIBLE);
		}

		/**
		 * Method called that performs background operations. JSON responses are
		 * retrieved and parsed.
		 */
		@Override
		protected Weather doInBackground(String... params) {
			Weather weather = new Weather();
			WeatherHttpClient httpClient = new WeatherHttpClient();

			String currentData = ((httpClient).getCurrentData(params[0]));
			String forecastData = ((httpClient).getForecastData(params[0]));
			String hourlyData = ((httpClient).getHourlyData(params[0]));

			if (currentData.equals("Error!") || forecastData.equals("Error!")
					|| hourlyData.equals("Error!")) {
				reportError();
				return null;
			}

			try {
				weather.currentCondition = JSONWeatherParser
						.getCurrentCondition(currentData);
				weather.weatherForecast = JSONWeatherParser
						.getForecastWeather(forecastData);
				weather.hourlyForecast = JSONWeatherParser
						.getHourly(hourlyData);

			} catch (JSONException e) {
				e.printStackTrace();
			}
			return weather;

		}

		/**
		 * Method called after background operations are performed. Fragments
		 * are put into a FragmentPageAdapter and set to the ViewPager.
		 * OnPageChangeListener is implemented that decreases the background
		 * image alpha value as the page is scrolled.
		 */
		@Override
		protected void onPostExecute(Weather weather) {
			super.onPostExecute(weather);

			List<Fragment> fragments = getFragments(weather);

			MyPageAdapter pageAdapter = new MyPageAdapter(
					getSupportFragmentManager(), fragments);

			ViewPager pager = (ViewPager) findViewById(R.id.viewpager);
			setBackground(pager, weather);
			final Drawable background = pager.getBackground();
			background.setAlpha(230);
			pager.setAdapter(pageAdapter);
			pager.setOnPageChangeListener(new OnPageChangeListener() {

				@Override
				public void onPageScrollStateChanged(int arg0) {
				}

				/**
				 * Decreases the alpha value of the background image as the user
				 * scrolls right, measured by the offset value.
				 */
				@Override
				public void onPageScrolled(int position, float offset,
						int offsetPixels) {
					if (position == 0) {
						if (offset < 0.05)
							background.setAlpha(230);
						else if (offset < 0.1)
							background.setAlpha(220);
						else if (offset < 0.15)
							background.setAlpha(210);
						else if (offset < 0.2)
							background.setAlpha(200);
						else if (offset < 0.25)
							background.setAlpha(190);
						else if (offset < 0.3)
							background.setAlpha(180);
						else if (offset < 0.35)
							background.setAlpha(170);
						else if (offset < 0.4)
							background.setAlpha(160);
						else if (offset < 0.45)
							background.setAlpha(150);
						else if (offset < 0.5)
							background.setAlpha(140);
						else if (offset < 0.55)
							background.setAlpha(130);
						else if (offset < 0.6)
							background.setAlpha(120);
						else if (offset < 0.65)
							background.setAlpha(110);
						else if (offset < 0.7)
							background.setAlpha(100);
						else if (offset < 0.75)
							background.setAlpha(90);
						else if (offset < 0.8)
							background.setAlpha(80);
						else if (offset < 0.85)
							background.setAlpha(70);
						else if (offset < 0.9)
							background.setAlpha(60);
						else if (offset < 0.95)
							background.setAlpha(50);
						else if (offset < 1)
							background.setAlpha(40);
					}
				}

				@Override
				public void onPageSelected(int arg0) {
				}
			});
			spinner.setVisibility(View.GONE);
		}

		/**
		 * Helper method that returns an ArrayList of fragments, each with
		 * different types of weather information, to be used in the fragment
		 * adapter.
		 * 
		 * @param weather
		 *            The Weather object holding the weather information
		 * @return ArrayList of Fragments
		 */
		private List<Fragment> getFragments(Weather weather) {
			List<Fragment> fList = new ArrayList<Fragment>();

			fList.add(CityFragment.newInstance(weather.currentCondition,
					weather.weatherForecast));
			fList.add(CurrentConditionFragment
					.newInstance(weather.currentCondition));
			fList.add(HourlyForecastFragment
					.newInstance(weather.hourlyForecast));
			fList.add(ForecastFragment.newInstance(weather.weatherForecast));

			return fList;
		}

		/**
		 * Custom FragmentPagerAdapter to be set for the ViewPager.
		 */
		private class MyPageAdapter extends FragmentPagerAdapter {
			private List<Fragment> fragments;

			public MyPageAdapter(FragmentManager fm, List<Fragment> fragments) {
				super(fm);
				this.fragments = fragments;
			}

			/**
			 * Returns the fragment at the given position.
			 */
			@Override
			public Fragment getItem(int position) {
				return this.fragments.get(position);
			}

			/**
			 * Returns the number of fragments
			 */
			@Override
			public int getCount() {
				return this.fragments.size();
			}
		}
	}

	/**
	 * Returns an ArrayList of strings populated by Strings in the
	 * SharedPreferences file. This method will be used to populate the drawer
	 * layout with all saved locations.
	 * 
	 * @return ArrayList of Strings
	 */
	private ArrayList<String> getPrefArray() {
		ArrayList<String> array = new ArrayList<String>();
		array.add("Add Location");
		array.add("Current Location");
		sp = getSharedPreferences(fileName, 0);
		Map<String, ?> dataReturned = sp.getAll();

		for (int i = 0; i < dataReturned.size(); i++) {
			array.add(sp.getString("data" + i, "Couldn't load data"));
		}
		return array;

	}

	/**
	 * Shows dialog notifying user of an error in network processing.
	 */
	public void reportError() {
		Intent intent = new Intent(this, ShowError.class);
		startActivity(intent);
	}

	/**
	 * Chooses the appropriate background image based on the current weather
	 * description. A random number is used to get a random picture from an
	 * array of weather pictures.
	 * 
	 * @param viewpager
	 *            The ViewPager that the background image belongs to.
	 * @param weather
	 *            The Weather object that contains the weather information
	 */
	private void setBackground(ViewPager viewpager, Weather weather) {
		int random = new Random().nextInt(4);

		if (weather.currentCondition.getIcon().equals("clear"))
			viewpager.setBackgroundResource(sunnyPics[random]);
		else if (weather.currentCondition.getIcon().equals("cloudy"))
			viewpager.setBackgroundResource(overcastPics[random]);
		else if (weather.currentCondition.getIcon().equals("flurries"))
			viewpager.setBackgroundResource(snowyPics[random]);
		else if (weather.currentCondition.getIcon().equals("fog"))
			viewpager.setBackgroundResource(foggyPics[random]);
		else if (weather.currentCondition.getIcon().equals("hazy"))
			viewpager.setBackgroundResource(foggyPics[random]);
		else if (weather.currentCondition.getIcon().equals("mostlycloudy"))
			viewpager.setBackgroundResource(overcastPics[random]);
		else if (weather.currentCondition.getIcon().equals("mostlysunny"))
			viewpager.setBackgroundResource(sunnyPics[random]);
		else if (weather.currentCondition.getIcon().equals("partlycloudy"))
			viewpager.setBackgroundResource(sunnyPics[random]);
		else if (weather.currentCondition.getIcon().equals("partlysunny"))
			viewpager.setBackgroundResource(sunnyPics[random]);
		else if (weather.currentCondition.getIcon().equals("rain"))
			viewpager.setBackgroundResource(rainyPics[random]);
		else if (weather.currentCondition.getIcon().equals("sleet"))
			viewpager.setBackgroundResource(snowyPics[random]);
		else if (weather.currentCondition.getIcon().equals("snow"))
			viewpager.setBackgroundResource(snowyPics[random]);
		else if (weather.currentCondition.getIcon().equals("sunny"))
			viewpager.setBackgroundResource(sunnyPics[random]);
		else if (weather.currentCondition.getIcon().equals("tstorms"))
			viewpager.setBackgroundResource(tstormPics[random]);
		else if (weather.currentCondition.getIcon().equals("cloudy"))
			viewpager.setBackgroundResource(overcastPics[random]);
		else if (weather.currentCondition.getIcon().equals("partlycloudy"))
			viewpager.setBackgroundResource(overcastPics[random]);
	}

	/**
	 * Inflates the menu screen when the user presses the menu button.
	 */
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.settings, menu);
		return true;
	}

	/**
	 * Callback method used whenever the user selects an item on the menu
	 * screen. Selecting "Settings" will open the PreferenceActivity, allowing
	 * the user to toggle units of measurement. Selecting "Refresh" will update
	 * the weather information.
	 */
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {

		case R.id.menu_settings:
			Intent i = new Intent(this, SettingsActivity.class);
			startActivity(i);
			break;
		case R.id.menu_refresh:
			lm.requestSingleUpdate(LocationManager.NETWORK_PROVIDER,
					new LocationListener() {

						@Override
						public void onLocationChanged(Location location) {
							latitude = location.getLatitude();
							longitude = location.getLongitude();
							String newLocation = Double.toString(latitude)
									+ "," + Double.toString(longitude);
							JSONWeatherTask resumeTask = new JSONWeatherTask();
							resumeTask.execute(new String[] { newLocation });
						}

						@Override
						public void onProviderDisabled(String arg0) {

						}

						@Override
						public void onProviderEnabled(String arg0) {

						}

						@Override
						public void onStatusChanged(String arg0, int arg1,
								Bundle arg2) {

						}

					}, getMainLooper());
			break;
		}
		return true;
	}
}
