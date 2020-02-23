package com.example.weatherapp.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.example.weatherapp.R;
import com.example.weatherapp.adapter.LocationAdapter;
import com.example.weatherapp.databinding.ActivityLocationBinding;
import com.example.weatherapp.model.City;
import com.example.weatherapp.viewModel.LocationActivityViewModel;
import com.example.weatherapp.viewModel.MainActivityViewModel;

import java.util.ArrayList;

public class LocationActivity extends AppCompatActivity {

    private ActivityLocationBinding binding;
    private LocationActivityViewModel viewModel;
    private LocationAdapter locationAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_location);
        viewModel =  ViewModelProviders.of(this).get(LocationActivityViewModel.class);
        binding.setLifecycleOwner(this);
        binding.setViewModel(viewModel);

        viewModel.onCreate(savedInstanceState);
        RecyclerView recyclerView = binding.locationRecycler;
        recyclerView.setHasFixedSize(true);
        locationAdapter = new LocationAdapter();
        recyclerView.setAdapter(locationAdapter);
        getAllLocation();
    }

    private void getAllLocation() {
        viewModel.getCitiesLiveData().observe(this,
                cities -> locationAdapter.setCities(cities)) ;
    }
}
