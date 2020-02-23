package com.example.weatherapp.module;

import com.example.weatherapp.view.LocationActivity;
import com.example.weatherapp.view.MainActivity;
import com.example.weatherapp.WeatherApp;
import com.example.weatherapp.data.WeatherRepository;
import com.example.weatherapp.viewModel.LocationActivityViewModel;
import com.example.weatherapp.viewModel.MainActivityViewModel;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = {
        AndroidModule.class,
        ApiModule.class
})
public interface ApiComponent {
    void inject(WeatherApp weatherApp);
    void inject(MainActivity activity);
    void inject(LocationActivity activity);
    void inject(MainActivityViewModel viewModel);
    void inject(LocationActivityViewModel viewModel);
    void inject(WeatherRepository weatherRepository);
}
