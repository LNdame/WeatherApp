package com.example.weatherapp.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.example.weatherapp.R;
import com.example.weatherapp.databinding.ForecastItemBinding;
import com.example.weatherapp.model.ForecastItem;

import java.util.ArrayList;

public class ForecastListAdapter extends RecyclerView.Adapter<ForecastListAdapter.ForecastViewHolder> {

private ArrayList<ForecastItem> forecastItems;
    @NonNull
    @Override
    public ForecastViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ForecastItemBinding forecastItemBinding =
                DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()),
                        R.layout.forecast_item,parent,false);
        return new ForecastViewHolder(forecastItemBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull ForecastViewHolder holder, int position) {
        holder.forecastItemBinding.setForecast(forecastItems.get(position));
    }

    @Override
    public int getItemCount() {
        return forecastItems!=null?forecastItems.size(): 0;
    }

    public void setForecastItems(ArrayList<ForecastItem> forecastItems) {
        this.forecastItems = forecastItems;
        notifyDataSetChanged();
    }

    class ForecastViewHolder extends RecyclerView.ViewHolder{
        private ForecastItemBinding forecastItemBinding;

        public ForecastViewHolder(@NonNull ForecastItemBinding forecastItemBinding) {
            super(forecastItemBinding.getRoot());
            this.forecastItemBinding = forecastItemBinding;
        }
    }
}
