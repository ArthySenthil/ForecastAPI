package com.senarita.forecastapi;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Arthy on 11/18/2015.
 */
public class JsonParsing {
    String jsonString;


    public JsonParsing(String jsonString) {
        this.jsonString = jsonString;
    }

    public List<DailyData> parseData(){
      List<DailyData> dailyDataList=new ArrayList<>();
        JsonParser jasonParser = new JsonParser();
        JsonObject jsonObject = jasonParser.parse(jsonString).getAsJsonObject();

        //data array of the daily json object
        JsonElement dailyElement = jsonObject.getAsJsonObject("daily");
        JsonElement dailyDataElement = dailyElement.getAsJsonObject().get("data");
        JsonArray dailyDataArray = dailyDataElement.getAsJsonArray();


        for (JsonElement object : dailyDataArray) {

            Gson gson=new Gson();
            DailyData data = gson.fromJson(object, DailyData.class);
            dailyDataList.add(data);
        }
        return dailyDataList;

    }
}
