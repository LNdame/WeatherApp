package com.example.weatherapp.data;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.weatherapp.model.City;

import java.util.List;

@Dao
public interface CityDao {
    @Query("SELECT * FROM city")
    List<City> getCities();

    @Query("SELECT * FROM city where _id= :id LIMIT 1")
    City findCityByUID(String id);

    @Query("SELECT * FROM city where name= :name LIMIT 1")
    City findCityByName(String name);

    @Query("SELECT * FROM city where name= :name")
    List<City> checkCityExist(String name);

    @Insert
    void insertAll(City... cities);

    @Update
    void updateCity(City city);
}
