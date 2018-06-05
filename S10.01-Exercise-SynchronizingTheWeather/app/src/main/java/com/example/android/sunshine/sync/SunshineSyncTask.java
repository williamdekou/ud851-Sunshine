package com.example.android.sunshine.sync;

import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Context;

import com.example.android.sunshine.data.WeatherContract;
import com.example.android.sunshine.utilities.NetworkUtils;
import com.example.android.sunshine.utilities.OpenWeatherJsonUtils;

import org.json.JSONException;

import java.io.IOException;

//  COMPLETED (1) Create a class called SunshineSyncTask
public class SunshineSyncTask {
    //  COMPLETED (2) Within SunshineSyncTask, create a synchronized public static void method called syncWeather
    public static synchronized void syncWeather(Context context) {
//      COMPLETED (3) Within syncWeather, fetch new weather data

        try {
            ContentValues[] contentValues = OpenWeatherJsonUtils.getWeatherContentValuesFromJson(
                    context,
                    NetworkUtils.getResponseFromHttpUrl(
                            NetworkUtils.getUrl(context)
                    )
            );
    //      COMPLETED (4) If we have valid results, delete the old data and insert the new
            if (contentValues != null && contentValues.length != 0) {
                ContentResolver resolver = context.getContentResolver();
                resolver.delete(WeatherContract.WeatherEntry.CONTENT_URI, null, null);
                resolver.bulkInsert(WeatherContract.WeatherEntry.CONTENT_URI, contentValues);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}