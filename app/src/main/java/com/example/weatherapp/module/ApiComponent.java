package com.example.weatherapp.module;

import com.example.weatherapp.MainActivity;
import com.example.weatherapp.WeatherApp;
import com.example.weatherapp.adapter.ForecastAdapter;
import com.example.weatherapp.data.WeatherRepository;
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
    void inject(MainActivityViewModel viewModel);
    void inject(ForecastAdapter adapter);
    void inject(WeatherRepository weatherRepository);
}
