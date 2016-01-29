package com.senarita.forecastapi;

import android.os.Parcel;
import android.os.Parcelable;

public class DailyData implements Parcelable {
    private String time;
    private String summary;

    private String icon;
    private String sunriseTime;
    private String sunsetTime;
    private String temperatureMin;
    private String temperatureMax;
    private String apparentTemperatureMin;
    private String apparentTemperatureMax;
    private String humidity;
    private String visibility;

    public DailyData() {
    }

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

    public String getSunriseTime() {
        return sunriseTime;
    }

    public void setSunriseTime(String sunriseTime) {
        this.sunriseTime = sunriseTime;
    }

    public String getSunsetTime() {
        return sunsetTime;
    }

    public void setSunsetTime(String sunsetTime) {
        this.sunsetTime = sunsetTime;
    }

    public String getTemperatureMin() {
        return temperatureMin;
    }

    public void setTemperatureMin(String temperatureMin) {
        this.temperatureMin = temperatureMin;
    }

    public String getTemperatureMax() {
        return temperatureMax;
    }

    public void setTemperatureMax(String temperatureMax) {
        this.temperatureMax = temperatureMax;
    }

    public String getApparentTemperatureMin() {
        return apparentTemperatureMin;
    }

    public void setApparentTemperatureMin(String apparentTemperatureMin) {
        this.apparentTemperatureMin = apparentTemperatureMin;
    }

    public String getApparentTemperatureMax() {
        return apparentTemperatureMax;
    }

    public void setApparentTemperatureMax(String apparentTemperatureMax) {
        this.apparentTemperatureMax = apparentTemperatureMax;
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
        return "Data[time=" + time + ",summary=" + summary + ",icon=" + icon
                + ",temperatureMin=" + temperatureMin + ",temperatureMax=" + temperatureMax
                + ",apparentTemperatureMin=" + apparentTemperatureMin
                + ",apparentTemperatureMax=" + apparentTemperatureMax
                + ",humidity=" + humidity + ",visibility=" + visibility + "]";
    }

    protected DailyData(Parcel in) {
        time = in.readString();
        summary = in.readString();
        icon = in.readString();
        sunriseTime = in.readString();
        sunsetTime = in.readString();
        temperatureMin = in.readString();
        temperatureMax = in.readString();
        apparentTemperatureMin = in.readString();
        apparentTemperatureMax = in.readString();
        humidity = in.readString();
        visibility = in.readString();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(time);
        dest.writeString(summary);
        dest.writeString(icon);
        dest.writeString(sunriseTime);
        dest.writeString(sunsetTime);
        dest.writeString(temperatureMin);
        dest.writeString(temperatureMax);
        dest.writeString(apparentTemperatureMin);
        dest.writeString(apparentTemperatureMax);
        dest.writeString(humidity);
        dest.writeString(visibility);
    }

    @SuppressWarnings("unused")
    public static final Parcelable.Creator<DailyData> CREATOR = new Parcelable.Creator<DailyData>() {
        @Override
        public DailyData createFromParcel(Parcel in) {
            return new DailyData(in);
        }

        @Override
        public DailyData[] newArray(int size) {
            return new DailyData[size];
        }
    };
}