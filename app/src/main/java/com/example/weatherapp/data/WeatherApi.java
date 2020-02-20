package com.example.weatherapp.data;

import com.example.weatherapp.model.ForecastResponse;
import com.example.weatherapp.model.WeatherResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface WeatherApi {
    @GET("weather")
    Call<WeatherResponse> getCurrentWeather(@Query("q") String location,
                                            @Query("units") String metric,
                                            @Query("appid") String apiKey);

    @GET("forecast")
    Call<ForecastResponse> getFiveDayForecast(@Query("q") String location,
                                              @Query("units") String metric,
                                              @Query("appid") String apiKey);
}
