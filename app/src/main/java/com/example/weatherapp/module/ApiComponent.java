package com.example.weatherapp.module;

import com.example.weatherapp.MainActivity;
import com.example.weatherapp.WeatherApp;
import com.example.weatherapp.data.WeatherRepository;

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
    void inject(WeatherRepository weatherRepository);
}
