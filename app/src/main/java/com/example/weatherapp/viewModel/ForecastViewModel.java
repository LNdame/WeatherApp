package com.example.weatherapp.viewModel;

import com.example.weatherapp.model.ForecastItem;
import java.util.Locale;

public class ForecastViewModel extends BaseViewModel {
    private MainActivityViewModel parent;
    private ForecastItem item;

    public ForecastViewModel(MainActivityViewModel parent, ForecastItem item ) {
        this.parent = parent;
        this.item = item;

    }

    public String getDayTemperature(){
        return String.format(Locale.ENGLISH,"%.0f",item.getMain().getTemp());
    }
}
