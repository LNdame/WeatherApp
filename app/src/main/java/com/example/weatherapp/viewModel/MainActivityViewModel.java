package com.example.weatherapp.viewModel;

import android.os.Bundle;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.weatherapp.data.WeatherRepository;
import com.example.weatherapp.model.ForecastResponse;
import com.example.weatherapp.model.MainTemp;
import com.example.weatherapp.model.Weather;
import com.example.weatherapp.model.WeatherResponse;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.schedulers.Schedulers;

public class MainActivityViewModel extends ViewModel {

    private MutableLiveData<WeatherResponse> weatherLiveData;
    private MutableLiveData<ForecastResponse> forecastData;
    private WeatherRepository weatherRepository;
    CompositeDisposable disposable = new CompositeDisposable();

    public MainActivityViewModel() {
        weatherRepository = WeatherRepository.getInstance();
        getCurrentWeatherData();
    }

    public void onCreate(Bundle savedInstanceState) {
    }

    public void getCurrentWeatherData(){
      weatherRepository.getCurrentWeather("Cape Town",
                "6c38b26ab71e1b2a1da8719a3db7134c")
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new Observer<WeatherResponse>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(WeatherResponse weatherResponse) {
                        setData(weatherResponse);
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });

    }


    public void setData(WeatherResponse weatherResponse){
        MainTemp mainTemp = weatherResponse.getMain();
        Weather weather = weatherResponse.getWeather().get(0);
    }



}
