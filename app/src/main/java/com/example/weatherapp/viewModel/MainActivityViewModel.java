package com.example.weatherapp.viewModel;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.widget.LinearLayout;

import androidx.databinding.Bindable;
import androidx.databinding.Observable;
import androidx.databinding.PropertyChangeRegistry;
import androidx.databinding.library.baseAdapters.BR;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


import com.example.weatherapp.WeatherApp;
import com.example.weatherapp.adapter.ForecastAdapter;
import com.example.weatherapp.data.WeatherRepository;
import com.example.weatherapp.model.ForecastResponse;
import com.example.weatherapp.model.MainTemp;
import com.example.weatherapp.model.Weather;
import com.example.weatherapp.model.WeatherResponse;

import java.util.Locale;

import javax.inject.Inject;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.schedulers.Schedulers;

public class MainActivityViewModel extends BaseViewModel  {

    @Inject
    Context context;

    private MutableLiveData<WeatherResponse> weatherLiveData;
    private MutableLiveData<ForecastResponse> forecastData;
    private WeatherRepository weatherRepository;
    private MainTemp mainTemp;
    private Weather weather;
    CompositeDisposable disposable = new CompositeDisposable();

    public MainActivityViewModel( ) {
        WeatherApp.getApiComponent().inject(this);
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


    private void setData(WeatherResponse weatherResponse){
        setMainTemp( weatherResponse.getMain() );
        if (weatherResponse.getWeather().size()>0){
            setWeather(weatherResponse.getWeather().get(0));
        }
        notifyChange();
    }

    public String getCurrentTemperature(){
        return mainTemp!=null?String.format(Locale.ENGLISH,"%.0f",getMainTemp().getTemp()):"";
    }

    public String getCurrentMaxTemperature(){
        return mainTemp!=null? String.format(Locale.ENGLISH,"%.0f",getMainTemp().getTempMax()):"";
    }

    public String getCurrentMinTemperature(){
        return mainTemp!=null?String.format(Locale.ENGLISH,"%.0f",getMainTemp().getTempMin()):"";
    }


    public String getCurrentWeather(){
        return weather!=null? getWeather().getMain().toUpperCase():"";
    }

    public MainTemp getMainTemp() {
        return mainTemp;
    }

    public void setMainTemp(MainTemp mainTemp) {
        this.mainTemp = mainTemp;
    }

    public Weather getWeather() {
        return weather;
    }

    public void setWeather(Weather weather) {
        this.weather = weather;
    }

    public Context getContext() {
        return context;
    }

   /* @Bindable
    public RecyclerView.Adapter<?> getForecastAdapter(){
       // return ForecastAdapter()
    }*/

    @Bindable
    public RecyclerView.LayoutManager getForecastLayoutManager(){
        return new LinearLayoutManager(getContext(), RecyclerView.VERTICAL, false);
    }


}
