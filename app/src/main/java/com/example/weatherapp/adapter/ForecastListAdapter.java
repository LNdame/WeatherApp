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
import com.example.weatherapp.utils.AppUtils;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class ForecastListAdapter extends RecyclerView.Adapter<ForecastListAdapter.ForecastViewHolder> {

    private ArrayList<ForecastItem> forecastItems;

    @NonNull
    @Override
    public ForecastViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ForecastItemBinding forecastItemBinding =
                DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()),
                        R.layout.forecast_item, parent, false);
        return new ForecastViewHolder(forecastItemBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull ForecastViewHolder holder, int position) {
        holder.forecastItemBinding.setForecast(forecastItems.get(position));
    }

    @Override
    public int getItemCount() {
        return forecastItems != null ? forecastItems.size() : 0;
    }

    public void setForecastItems(ArrayList<ForecastItem> forecastItems) {
        this.forecastItems = forecastItems;
        this.forecastItems = sanitizeData(this.forecastItems);
        notifyDataSetChanged();
    }


    private ArrayList<ForecastItem> sanitizeData(ArrayList<ForecastItem> forecastItemList) {
        ArrayList<ForecastItem> forecastItems = new ArrayList<>();
        Date currentDate = Calendar.getInstance().getTime();
        for (ForecastItem item : forecastItemList) {
            if (AppUtils.getDayOfWeek(currentDate) != AppUtils.getDayOfWeek(item.getDtTxt())) {
                if (!isDayForecatAlreadyIn(forecastItems, item.getDtTxt())) {
                    forecastItems.add(item);
                }
            }
        }
        return forecastItems;
    }

    private boolean isDayForecatAlreadyIn(ArrayList<ForecastItem> forecastItems, String dateString) {
        if (forecastItems == null || forecastItems.size() == 0) return false;
        for (ForecastItem item : forecastItems) {
            if (AppUtils.getDayOfWeek(item.getDtTxt()) == AppUtils.getDayOfWeek(dateString)) {
                return true;
            }
        }
        return false;
    }

    class ForecastViewHolder extends RecyclerView.ViewHolder {
        private ForecastItemBinding forecastItemBinding;

        public ForecastViewHolder(@NonNull ForecastItemBinding forecastItemBinding) {
            super(forecastItemBinding.getRoot());
            this.forecastItemBinding = forecastItemBinding;
        }
    }
}
