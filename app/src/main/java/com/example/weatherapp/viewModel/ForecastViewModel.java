package com.example.weatherapp.viewModel;

import com.example.weatherapp.model.MainTemp;
import com.example.weatherapp.model.Weather;

import java.util.Locale;

public class ForecastViewModel extends BaseViewModel {
    private MainActivityViewModel parent;
    private MainTemp temp;
   // private Weather weather;

    public ForecastViewModel(MainActivityViewModel parent, MainTemp temp ) {
        this.parent = parent;
        this.temp = temp;

    }

    public String getDayTemperature(){
        return String.format(Locale.ENGLISH,"%.0f",temp.getTemp());
    }
}
