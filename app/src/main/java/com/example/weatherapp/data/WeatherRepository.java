package com.example.weatherapp.data;

import androidx.lifecycle.MutableLiveData;

import com.example.weatherapp.WeatherApp;
import com.example.weatherapp.model.ForecastResponse;
import com.example.weatherapp.model.WeatherResponse;

import javax.inject.Inject;

import io.reactivex.Observable;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
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

   public Observable<WeatherResponse> getCurrentWeather(String location, String apikey){
        return weatherService.getCurrentWeather(location,UNITS,apikey);
    }

   /* public MutableLiveData<WeatherResponse> getCurrentWeather(String location, String apikey) {
        final MutableLiveData<WeatherResponse> weatherData = new MutableLiveData<>();
        weatherService.getCurrentWeather(location,UNITS, apikey).enqueue(new Callback<WeatherResponse>() {
            @Override
            public void onResponse(Call<WeatherResponse> call, Response<WeatherResponse> response) {
                if (response.isSuccessful()) {
                    weatherData.setValue(response.body());
                }
            }

            @Override
            public void onFailure(Call<WeatherResponse> call, Throwable t) {
                weatherData.setValue(null);
            }
        });

        return weatherData;
    }*/

    /*public MutableLiveData<ForecastResponse> getFiveDayForecast(String location, String apikey){
        final MutableLiveData<ForecastResponse> forecastData = new MutableLiveData<>();
        weatherService.getFiveDayForecast(location, UNITS, apikey).enqueue(new Callback<ForecastResponse>() {
            @Override
            public void onResponse(Call<ForecastResponse> call, Response<ForecastResponse> response) {
                if(response.isSuccessful()){
                    forecastData.setValue(response.body());
                }
            }

            @Override
            public void onFailure(Call<ForecastResponse> call, Throwable t) {
                forecastData.setValue(null);
            }
        });
        return  forecastData;
    }*/


}
