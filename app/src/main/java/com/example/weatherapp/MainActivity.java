package com.example.weatherapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.RecyclerView;
import android.os.Bundle;
import com.example.weatherapp.adapter.ForecastListAdapter;
import com.example.weatherapp.databinding.ActivityMainBinding;
import com.example.weatherapp.model.ForecastItem;
import com.example.weatherapp.viewModel.MainActivityViewModel;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;
    private MainActivityViewModel viewModel;
    private ForecastListAdapter forecastListAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        viewModel = ViewModelProviders.of(this).get(MainActivityViewModel.class);
        binding.setLifecycleOwner(this);
        binding.setViewModel(viewModel);

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
}
