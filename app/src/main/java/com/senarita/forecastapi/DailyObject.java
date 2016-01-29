package com.senarita.forecastapi;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Arthy on 11/14/2015.
 */
public class DailyObject implements Serializable{

    private String summary;
    private String icon;
    private List<DailyData> data;

    public List<DailyData> getData() {
        return data;
    }

   public void setData(List<DailyData> data) {
        this.data = data;
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

    @Override
    public String toString() {
        return "DailyObject[summary="+summary+"icon="+icon+"data="+data+"]";
    }
}
