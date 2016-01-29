package com.senarita.forecastapi;

/**
 * Created by Arthy on 11/14/2015.
 */
public class Forecast {
    double latitude;
    double longitude;
    String currently;
    String daily;
    String hourly;

    public DailyObject getDailyObject() {
        return dailyObject;
    }

    public void setDailyObject(DailyObject dailyObject) {
        this.dailyObject = dailyObject;
    }

    DailyObject dailyObject=new DailyObject();

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public String getCurrently() {
        return currently;
    }

    public void setCurrently(String currently) {
        this.currently = currently;
    }

    public String getDaily() {
        return daily;
    }

    public void setDaily(String daily) {
        this.daily = daily;
    }

    public String getHourly() {
        return hourly;
    }

    public void setHourly(String hourly) {
        this.hourly = hourly;
    }

    @Override
    public String toString() {
        return "Forecast[latitude="+latitude+"longitude="+longitude+",currently="+currently+",daily="+daily+",hourly="+hourly+"]";
    }
}
