package com.example.weatherapp.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.weatherapp.WeatherApp;
import com.example.weatherapp.databinding.ForecastItemBinding;
import com.example.weatherapp.model.MainTemp;
import com.example.weatherapp.viewModel.ForecastViewModel;
import com.example.weatherapp.viewModel.MainActivityViewModel;

import java.util.List;

import javax.inject.Inject;

public class ForecastAdapter extends RecyclerView.Adapter<BindingHolder<ForecastItemBinding>> {

    @Inject
    Context context;
    private final MainActivityViewModel parentViewModel;
    private LayoutInflater inflater;
    private List<MainTemp> items;

    public ForecastAdapter(MainActivityViewModel parentViewModel, List<MainTemp> items) {
        WeatherApp.getApiComponent().inject(this);
        this.parentViewModel = parentViewModel;
        this.items = items;
        this.inflater = LayoutInflater.from(context);
    }

    @NonNull
    @Override
    public BindingHolder<ForecastItemBinding> onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new BindingHolder<>(ForecastItemBinding.inflate(inflater,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull BindingHolder<ForecastItemBinding> holder, int position) {
        ForecastItemBinding binding = holder.getBinding();
        binding.setViewModel(new ForecastViewModel(parentViewModel,items.get(position)));
    }

    @Override
    public int getItemCount() {
        return items!=null?items.size():0;
    }
}
