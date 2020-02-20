package com.example.weatherapp;

import android.app.Application;

import com.example.weatherapp.module.AndroidModule;
import com.example.weatherapp.module.ApiComponent;
import com.example.weatherapp.module.ApiModule;
import com.example.weatherapp.module.DaggerApiComponent;

import dagger.android.DaggerApplication;

public class WeatherApp extends Application {

    private static ApiComponent apiComponent;

    @Override
    public void onCreate() {
        super.onCreate();

        apiComponent = DaggerApiComponent.builder()
                .androidModule(new AndroidModule(this))
                .apiModule(new ApiModule("https://api.openweathermap.org/data/2.5/"))
                .build();
    }

    public static ApiComponent getApiComponent() {
        return apiComponent;
    }
}
