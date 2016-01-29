package com.senarita.forecastapi;

/**
 * Created by Arthy on 11/13/2015.
 */
public class HourlyData {

    private String time;
    private String summary;
    private String icon;
    private String temperature;
    private String apparentTemperature;
    private String humidity;
    private String visibility;

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public String getTemperature() {
        return temperature;
    }

    public void setTemperature(String temperature) {
        this.temperature = temperature;
    }

    public String getApparentTemperature() {
        return apparentTemperature;
    }

    public void setApparentTemperature(String apparentTemperature) {
        this.apparentTemperature = apparentTemperature;
    }

    public String getHumidity() {
        return humidity;
    }

    public void setHumidity(String humidity) {
        this.humidity = humidity;
    }

    public String getVisibility() {
        return visibility;
    }

    public void setVisibility(String visibility) {
        this.visibility = visibility;
    }

    @Override
    public String toString() {
        return "HourlyData[time="+time+",summary="+summary+",icon="+icon
                +",temperature="+temperature +",apparentTemperature="+apparentTemperature
                +",humidity="+humidity+",visibility="+visibility+"]";
    }
}
