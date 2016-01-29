package com.senarita.forecastapi;

import android.content.Intent;
import android.content.SharedPreferences;
import android.location.Address;
import android.location.Geocoder;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.squareup.okhttp.Call;
import com.squareup.okhttp.Callback;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemClickListener {

    public static final String LOG_TAG = "MainActivity";
    private OkHttpClient mOkHttpClient;
    String jsonString;
    String coordinates="";
    String details;
    List<DailyData> dailyDataList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        mOkHttpClient = new OkHttpClient();
        try {
            run();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public void run() throws Exception {

        String api_key = BuildConfig.FORECAST_VERIFICATION_API_KEY;

        //Get the coordinates from preferences

        String location=getCoordinates();

        // Make a request to url

        Request request = new Request.Builder()
                .url("https://api.forecast.io/forecast/" + api_key + "/" +location)
                .header("Content-Type", "application/json")
                .build();

        Call call = mOkHttpClient.newCall(request);

        call.enqueue(new Callback() {
            @Override
            public void onFailure(Request request, IOException e) {
                e.printStackTrace();
                Log.e(LOG_TAG,"Error connecting to url.");
            }

            @Override
            public void onResponse(Response response) throws IOException {
                if (!response.isSuccessful())
                    throw new IOException("Unexpected Code" + response);

                //Getting json data as response

                jsonString = response.body().string();

                JsonParsing jsonParsing = new JsonParsing(jsonString);
                dailyDataList = jsonParsing.parseData();

                MainActivity.this.runOnUiThread(new Runnable() {
                                                    @Override

                                                    public void run() {
                                                        try {
                                                            ListView listView = (ListView) findViewById(R.id.forecast_listView);
                                                            TextView emptyView = (TextView) findViewById(R.id.emptyView);
                                                            if (dailyDataList.size() > 0) {


                                                                for (DailyData data : dailyDataList)

                                                                {
                                                                    // Format the JSON date

                                                                    data.setTime(normalizeDate(data.getTime(),"EEEE MM-dd-yyyy"));
                                                                    data.setSunriseTime(normalizeDate(data.getSunriseTime(), "h:mm") + " A.M");
                                                                    data.setSunsetTime(normalizeDate(data.getSunsetTime(), "h:mm") + " P.M");

                                                                }

                                                                // Update parsed Daily Forecast data into ListView

                                                                ForecastAdapter forecastAdapter = new ForecastAdapter(getBaseContext(), dailyDataList);
                                                                listView.setAdapter(forecastAdapter);
                                                                listView.setEmptyView(emptyView);

                                                                listView.setOnItemClickListener(MainActivity.this);


                                                            }
                                                            else{
                                                                emptyView.setText("No data returned");
                                                            }
                                                        } catch (Exception e) {
                                                            e.printStackTrace();
                                                            Log.e(LOG_TAG,"Error loading data");
                                                        }
                                                    }
                                                }

                );


            }

        });
    }

    public String getCoordinates(){
        SharedPreferences prefs= PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        String zipCode=prefs.getString(getString(R.string.pref_location_key), getString(R.string.pref_location_default));
        final Geocoder geocoder = new Geocoder(this);
        String cityName,stateName,countryName="";
        TextView locationText=(TextView)findViewById(R.id.locationView);
        try {
            List<Address> addresses = geocoder.getFromLocationName(zipCode, 1);
            if (addresses != null && !addresses.isEmpty()) {
                Address address = addresses.get(0);
                // Get coordinates
                 coordinates =address.getLatitude()+","+address.getLongitude();
                 cityName=address.getLocality();
                 stateName=address.getAdminArea();
                 countryName=address.getCountryName();
                if(stateName==null){
                    locationText.setText(cityName+","+countryName);
                }
                else {
                    locationText.setText(cityName+","+stateName);
                }
//
                Toast.makeText(this, coordinates, Toast.LENGTH_LONG).show();
            } else {
                // Display appropriate message when Geocoder services are not available
                Toast.makeText(this, "Unable to geocode zipcode", Toast.LENGTH_LONG).show();
            }
        } catch (IOException e) {
            // Handle exception
            Log.e(LOG_TAG,"Error getting coordinates");
        }
            return coordinates;
    }

    //Format Unix date
    public String normalizeDate(String strDate,String dateFormat){
        Date date = new Date(Long.parseLong(strDate) * 1000);
        SimpleDateFormat df = new SimpleDateFormat(dateFormat,java.util.Locale.getDefault());
        strDate=df.format(date);
        return strDate;
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            Intent intent=new Intent(MainActivity.this,SettingsActivity.class);
            startActivityForResult(intent,RESULT_OK);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }


    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Gson gsonDet = new Gson();
        details = gsonDet.toJson(dailyDataList);

        //Extract details of the list item clicked.

        DailyData item = (DailyData) parent.getAdapter().getItem(position);

        // Pass on details to DetailActivity;
        Intent intent = new Intent(MainActivity.this, DetailActivity.class);
        intent.putExtra("dailyData",item);
        startActivity(intent);
    }

}




