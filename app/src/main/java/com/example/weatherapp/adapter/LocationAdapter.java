package com.example.weatherapp.adapter;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.example.weatherapp.R;
import com.example.weatherapp.databinding.LocationItemBinding;
import com.example.weatherapp.model.City;

import java.util.ArrayList;

public class LocationAdapter extends RecyclerView.Adapter<LocationAdapter.LocationViewHolder> {
    private ArrayList<City> cities;

    @NonNull
    @Override
    public LocationViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LocationItemBinding locationItemBinding=
                DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()),
                        R.layout.location_item,parent,false);
        return new LocationViewHolder(locationItemBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull LocationViewHolder holder, int position) {
        holder.locationItemBinding.setCity(cities.get(position));
    }

    @Override
    public int getItemCount() {
        return cities!=null?cities.size(): 0;
    }

    public void setCities(ArrayList<City> cities) {
        this.cities = cities;
        notifyDataSetChanged();
    }

    class LocationViewHolder extends RecyclerView.ViewHolder{
        private LocationItemBinding locationItemBinding;

        public LocationViewHolder(@NonNull  LocationItemBinding locationItemBinding) {
            super(locationItemBinding.getRoot());
            this.locationItemBinding = locationItemBinding;
        }
    }
}
