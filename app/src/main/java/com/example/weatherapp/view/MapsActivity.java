package com.example.weatherapp.view;

import androidx.fragment.app.FragmentActivity;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;

import android.os.Bundle;

import com.example.weatherapp.R;
import com.example.weatherapp.data.WeatherDatabase;
import com.example.weatherapp.model.City;
import com.example.weatherapp.utils.AppExecutors;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.ArrayList;
import java.util.List;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    private WeatherDatabase weatherDB;
    private MutableLiveData<ArrayList<City>> citiesMutableLiveData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
        weatherDB = WeatherDatabase.getInstance(this);
        citiesMutableLiveData = new MutableLiveData<>();
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

    public void dropPins(ArrayList<City> cities) {
        for (City city : cities) {
            LatLng latLng = new LatLng(city.getLat(), city.getLon());
            mMap.addMarker(new MarkerOptions().position(latLng).title(String.format("Marker in %s", city.getName())));
        }
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        LatLng capetown = new LatLng(-33.92, 18.42);
        mMap.addMarker(new MarkerOptions().position(capetown).title("Marker in Cape Town"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(capetown));
        mMap.setMinZoomPreference(6.0f);
        retrieveLocations();
        getCitiesLiveData().observe(this, this::dropPins);
    }
}
