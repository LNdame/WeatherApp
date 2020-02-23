package com.example.weatherapp.viewModel;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.databinding.Bindable;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.weatherapp.WeatherApp;
import com.example.weatherapp.data.WeatherDatabase;
import com.example.weatherapp.model.City;
import com.example.weatherapp.utils.AppExecutors;
import com.example.weatherapp.view.LocationActivity;
import com.example.weatherapp.view.MapsActivity;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import static android.content.Intent.FLAG_ACTIVITY_NEW_TASK;

public class LocationActivityViewModel extends BaseViewModel {

    @Inject
    Context context;
    private MutableLiveData<ArrayList<City>> citiesMutableLiveData;
    private WeatherDatabase weatherDB;

    public LocationActivityViewModel() {
    }

    public void onCreate(Bundle savedInstanceState) {
        WeatherApp.getApiComponent().inject(this);
        weatherDB = WeatherDatabase.getInstance(context);
        citiesMutableLiveData = new MutableLiveData<>();
        retrieveLocations();
    }

    public void retrieveLocations() {
        AppExecutors.getInstance().getMainThread().execute(
                () -> {
                    final List<City> cities = weatherDB.cityDao().getCities();
                    citiesMutableLiveData.postValue((ArrayList<City>) cities);
                }
        );
    }

    public LiveData<ArrayList<City>> getCitiesLiveData() {
        return citiesMutableLiveData;
    }

    public Context getContext() {
        return context;
    }

    @Bindable
    public RecyclerView.LayoutManager getLayoutManager() {
        return new LinearLayoutManager(getContext(), RecyclerView.VERTICAL, false);
    }

    public void viewFavoritesMap() {
        Intent intent = new Intent(getContext(), MapsActivity.class);
        intent.addFlags(FLAG_ACTIVITY_NEW_TASK);
        getContext().startActivity(intent);
    }

}
