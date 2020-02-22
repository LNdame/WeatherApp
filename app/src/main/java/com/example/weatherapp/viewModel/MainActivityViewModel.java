package com.example.weatherapp.viewModel;

import android.content.Context;
import android.view.View;
import androidx.annotation.ColorRes;
import androidx.annotation.DrawableRes;
import androidx.core.content.ContextCompat;
import androidx.databinding.Bindable;
import androidx.databinding.BindingAdapter;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


import com.example.weatherapp.R;
import com.example.weatherapp.WeatherApp;
import com.example.weatherapp.data.WeatherRepository;
import com.example.weatherapp.model.ForecastItem;
import com.example.weatherapp.model.ForecastResponse;
import com.example.weatherapp.model.MainTemp;
import com.example.weatherapp.model.Weather;
import com.example.weatherapp.model.WeatherResponse;
import com.example.weatherapp.utils.AppUtils;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import javax.inject.Inject;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class MainActivityViewModel extends BaseViewModel {

    @Inject
    Context context;
    public static final int REMOVE_BACKGROUND = -1;
    private MutableLiveData<WeatherResponse> weatherLiveData;
    private MutableLiveData<ForecastResponse> forecastData;
    private WeatherRepository weatherRepository;
    private MainTemp mainTemp;
    private Weather weather;

    private List<ForecastItem> forecastItemList;
    CompositeDisposable disposable = new CompositeDisposable();

    public MainActivityViewModel() {
        WeatherApp.getApiComponent().inject(this);
        weatherRepository = WeatherRepository.getInstance();
        forecastData = new MutableLiveData<>();
        getCurrentWeatherData();
        getForecastData();
    }

    public void getCurrentWeatherData() {
        weatherRepository.getCurrentWeather("Helsinki",
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

    private void getForecastData() {
        weatherRepository.getFiveDayForecast("Helsinki",
                "6c38b26ab71e1b2a1da8719a3db7134c").
                observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new Observer<ForecastResponse>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(ForecastResponse forecastResponse) {
                        setForecastData(forecastResponse);
                        forecastData.setValue(forecastResponse);
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    private void setData(WeatherResponse weatherResponse) {
        setMainTemp(weatherResponse.getMain());
        if (weatherResponse.getWeather().size() > 0) {
            setWeather(weatherResponse.getWeather().get(0));
        }
        notifyChange();
    }

    public LiveData<ForecastResponse> getForecastLiveData() {
        return forecastData;
    }

    private void setForecastData(ForecastResponse forecastResponse) {
        setForecastItemList(forecastResponse.getList());

        notifyChange();
    }

    public String getCurrentTemperature() {
        return mainTemp != null ? String.format(Locale.ENGLISH, "%.0f\u00B0", getMainTemp().getTemp()) : "";
    }

    public String getCurrentMaxTemperature() {
        return mainTemp != null ? String.format(Locale.ENGLISH, "%.0f\u00B0", getMainTemp().getTempMax()) : "";
    }

    public String getCurrentMinTemperature() {
        return mainTemp != null ? String.format(Locale.ENGLISH, "%.0f\u00B0", getMainTemp().getTempMin()) : "";
    }


    public String getCurrentWeather() {
        return weather != null ? getWeather().getMain().toUpperCase() : "";
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

    public List<ForecastItem> getForecastItemList() {
        return forecastItemList;
    }

    public void setForecastItemList(List<ForecastItem> forecastItemList) {
        this.forecastItemList = forecastItemList;
    }

    @Bindable
    public RecyclerView.LayoutManager getForecastLayoutManager() {
        return new LinearLayoutManager(getContext(), RecyclerView.VERTICAL, false);
    }

    @DrawableRes
    public int getWeatherImageBackground() {
        return setupWeatherImage(weather != null ? weather.getMain() : "");
    }

    @DrawableRes
    public int getWeatherImageColor() {
        return setupBackgroundColor(weather != null ? weather.getMain() : "");
    }

    public int setupWeatherImage(String desc) {
        switch (desc) {
            case Weather.CLOUDS:
                return R.drawable.cloudy;
            case Weather.RAIN:
                return R.drawable.rainy;
            case Weather.CLEAR:
                return R.drawable.sunny;
            default:
                return R.drawable.cloudy;
        }
    }

    @BindingAdapter({"backgroundColor"})
    public static void setBackground(View view, @ColorRes int resource) {
        if (view == null) return;
        if (resource == REMOVE_BACKGROUND) {
            view.setBackground(null);
        } else {
            view.setBackground(ContextCompat.getDrawable(view.getContext(), resource));
        }
    }

    public int setupBackgroundColor(String desc) {
        switch (desc) {
            case Weather.CLOUDS:
                return R.color.colorCloudy;
            case Weather.RAIN:
                return R.color.colorRainy;
            case Weather.CLEAR:
                return R.color.colorSunny;
            default:
                return R.color.colorCloudy;
        }
    }





}
