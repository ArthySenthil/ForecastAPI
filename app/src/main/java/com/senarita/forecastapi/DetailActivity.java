package com.senarita.forecastapi;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.widget.TextView;

public class DetailActivity extends AppCompatActivity {

    public static final String LOG_TAG = "DetailActivity";


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        //Get data from Main Activity as a bundle.

        Bundle extras = getIntent().getExtras();
        DailyData dailyData=new DailyData();

        //Check if its created for the first time.

        if (savedInstanceState == null) {


            if (extras == null) {
                Log.d(LOG_TAG,"No data returned");

            } else {
                dailyData=extras.getParcelable("dailyData");
            }

        }
        //Get data if the orientation is changed

        else {
            dailyData=extras.getParcelable("dailyData");
        }

        //Initialize views
        TextView iconDescText = (TextView) findViewById(R.id.iconDescription);
        TextView timeText = (TextView) findViewById(R.id.timeText);
        TextView feelsLikeText = (TextView) findViewById(R.id.feelsLike);
        TextView summaryText = (TextView) findViewById(R.id.summary);
        TextView highLowText = (TextView) findViewById(R.id.hiloText);
        TextView visibilityText = (TextView) findViewById(R.id.visibleText);
        TextView humidityText = (TextView) findViewById(R.id.humidityText);
        TextView sunriseText = (TextView) findViewById(R.id.sunriseText);
        TextView sunsetText = (TextView) findViewById(R.id.sunsetText);

        //Set the values for the views

        iconDescText.setText(dailyData.getIcon());
        timeText.setText(dailyData.getTime());
        feelsLikeText.setText(dailyData.getApparentTemperatureMax()+" \u00B0" +"/"+dailyData.getApparentTemperatureMin()+" \u2109");
        summaryText.setText(dailyData.getSummary());
        highLowText.setText(dailyData.getTemperatureMax()+" \u00B0"+"/"+dailyData.getTemperatureMin()+" \u2109");
        visibilityText.setText(dailyData.getVisibility());
        humidityText.setText(dailyData.getHumidity());
        sunriseText.setText(dailyData.getSunriseTime());
        sunsetText.setText(dailyData.getSunsetTime());
    }


}

    
    
    
    
    


