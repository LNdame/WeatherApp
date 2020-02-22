package com.example.weatherapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.example.weatherapp.data.WeatherRepository;
import com.example.weatherapp.databinding.ActivityMainBinding;
import com.example.weatherapp.model.ForecastResponse;
import com.example.weatherapp.model.WeatherResponse;
import com.example.weatherapp.viewModel.MainActivityViewModel;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.schedulers.Schedulers;
import timber.log.Timber;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;
    private MainActivityViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        viewModel = ViewModelProviders.of(this).get(MainActivityViewModel.class);
        binding.setLifecycleOwner(this);
        binding.setViewModel(viewModel);

        viewModel.onCreate(savedInstanceState);

        RecyclerView recyclerView = binding.forecastRecycler;
        recyclerView.setHasFixedSize(true);
        recyclerView.setAdapter(viewModel.getForecastAdapter());
    }
}
