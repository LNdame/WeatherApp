package com.example.weatherapp.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.RecyclerView;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;

import com.example.weatherapp.R;
import com.example.weatherapp.adapter.ForecastListAdapter;
import com.example.weatherapp.databinding.ActivityMainBinding;
import com.example.weatherapp.model.ForecastItem;
import com.example.weatherapp.viewModel.MainActivityViewModel;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;
    private MainActivityViewModel viewModel;
    private ForecastListAdapter forecastListAdapter;
    int PERMISSION_ID = 44;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        if (!checkPermissions()) {
            requestPermissions();
        }
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        viewModel = ViewModelProviders.of(this).get(MainActivityViewModel.class);
        binding.setLifecycleOwner(this);
        binding.setViewModel(viewModel);

        viewModel.onCreate(savedInstanceState);
        RecyclerView recyclerView = binding.forecastRecycler;
        recyclerView.setHasFixedSize(true);

        forecastListAdapter = new ForecastListAdapter();
        recyclerView.setAdapter(forecastListAdapter);
        getAllForecast();
    }

    private void getAllForecast() {
        viewModel.getForecastLiveData().observe(this,
                forecastResponse -> forecastListAdapter
                        .setForecastItems((ArrayList<ForecastItem>) forecastResponse.getList()));
    }

    private boolean checkPermissions() {
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) == PackageManager.PERMISSION_GRANTED &&
                ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
            return true;
        }
        return false;
    }

    private void requestPermissions() {
        ActivityCompat.requestPermissions(
                this,
                new String[]{Manifest.permission.ACCESS_COARSE_LOCATION, Manifest.permission.ACCESS_FINE_LOCATION},
                PERMISSION_ID
        );
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == PERMISSION_ID) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                startActivity(new Intent(this, MainActivity.class));
            } else {
                this.finish();
            }
        }
    }

    @Override
    protected void onStart() {
        super.onStart();
        viewModel.onStart();
    }
}
