package com.example.weatherapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModelProviders;

import android.os.Bundle;

import com.example.weatherapp.data.WeatherRepository;
import com.example.weatherapp.databinding.ActivityMainBinding;
import com.example.weatherapp.model.ForecastResponse;
import com.example.weatherapp.model.WeatherResponse;
import com.example.weatherapp.viewModel.MainActivityViewModel;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.schedulers.Schedulers;
import timber.log.Timber;

public class MainActivity extends AppCompatActivity {

    private MutableLiveData<WeatherResponse> weatherLiveData;
    private MutableLiveData<ForecastResponse> forecastData;
    private WeatherRepository weatherRepository;
    private ActivityMainBinding binding;
    private MainActivityViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        viewModel = ViewModelProviders.of(this).get(MainActivityViewModel.class);
        binding.setLifecycleOwner(this);
        binding.setViewModel(viewModel);

        viewModel.onCreate(savedInstanceState);

       /* weatherRepository = WeatherRepository.getInstance();
        getCurrentWeatherData();*/
        /*if(weatherLiveData!=null) return;

        weatherRepository = WeatherRepository.getInstance();
        weatherLiveData = weatherRepository.getCurrentWeather("Cape Town",
                "6c38b26ab71e1b2a1da8719a3db7134c");

        forecastData = weatherRepository.getFiveDayForecast("Cape Town",
                "6c38b26ab71e1b2a1da8719a3db7134c");


        WeatherResponse  wresp = weatherLiveData.getValue();
        ForecastResponse fresp = forecastData.getValue();
        Timber.d("done");*/

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


    public void setData(WeatherResponse weatherResponse){}
}
