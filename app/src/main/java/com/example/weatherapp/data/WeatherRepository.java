package com.example.weatherapp.data;

import com.example.weatherapp.WeatherApp;
import com.example.weatherapp.model.ForecastResponse;
import com.example.weatherapp.model.WeatherResponse;

import javax.inject.Inject;

import io.reactivex.Observable;
import retrofit2.Retrofit;

public class WeatherRepository {

    final String UNITS = "metric";
    @Inject
    Retrofit retrofit;

    private static WeatherRepository weatherRepository;
    private WeatherService weatherService;

    public static WeatherRepository getInstance() {
        if (weatherRepository == null) {
            weatherRepository = new WeatherRepository();
        }
        return weatherRepository;
    }

    private WeatherRepository() {
        WeatherApp.getApiComponent().inject(this);
        weatherService = retrofit.create(WeatherService.class);
    }

    public Observable<WeatherResponse> getCurrentWeather(double lat, double lon, String apikey) {
        return weatherService.getCurrentWeather(lat, lon, UNITS, apikey);
    }

    public Observable<ForecastResponse> getFiveDayForecast(double lat, double lon, String apikey) {
        return weatherService.getFiveDayForecast(lat, lon, UNITS, apikey);
    }
}
