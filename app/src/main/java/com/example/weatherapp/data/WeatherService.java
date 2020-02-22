package com.example.weatherapp.data;

import com.example.weatherapp.model.ForecastResponse;
import com.example.weatherapp.model.WeatherResponse;

import io.reactivex.Observable;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface WeatherService {
    @GET("weather")
    Observable<WeatherResponse> getCurrentWeather(@Query("lat") double lat,
                                                  @Query("lon") double lon,
                                                  @Query("units") String metric,
                                                  @Query("appid") String apiKey);

    @GET("forecast")
    Observable<ForecastResponse> getFiveDayForecast(@Query("lat") double lat,
                                                    @Query("lon") double lon,
                                                    @Query("units") String metric,
                                                    @Query("appid") String apiKey);
}
