package com.example.weatherapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.MutableLiveData;

import android.os.Bundle;

import com.example.weatherapp.data.WeatherRepository;
import com.example.weatherapp.model.ForecastResponse;
import com.example.weatherapp.model.WeatherResponse;

import timber.log.Timber;

public class MainActivity extends AppCompatActivity {

    private MutableLiveData<WeatherResponse> weatherLiveData;
    private MutableLiveData<ForecastResponse> forecastData;
    private WeatherRepository weatherRepository;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if(weatherLiveData!=null) return;

        weatherRepository = WeatherRepository.getInstance();
        weatherLiveData = weatherRepository.getCurrentWeather("Cape Town",
                "6c38b26ab71e1b2a1da8719a3db7134c");

        forecastData = weatherRepository.getFiveDayForecast("Cape Town",
                "6c38b26ab71e1b2a1da8719a3db7134c");


        WeatherResponse  wresp = weatherLiveData.getValue();
        ForecastResponse fresp = forecastData.getValue();
        Timber.d("done");

    }
}
