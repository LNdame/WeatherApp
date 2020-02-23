package com.example.weatherapp.data;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

import com.example.weatherapp.model.City;

import timber.log.Timber;

@Database(entities = {City.class}, version = 1, exportSchema = false)
public abstract class WeatherDatabase extends RoomDatabase {
    private static String TAG = WeatherDatabase.class.getSimpleName();
    private static final Object LOCK = new Object();

    public abstract CityDao cityDao();

    private static final String DATABASE_NAME = "weatherdatabase";
    private static WeatherDatabase instance;

    public static WeatherDatabase getInstance(Context context) {
        if (instance == null) {
            synchronized (LOCK) {
                Timber.d("Creating new database instance");
                instance = Room.databaseBuilder(context.getApplicationContext(),
                        WeatherDatabase.class, WeatherDatabase.DATABASE_NAME)
                        .addCallback(weatherDatabaseCallback)
                        .build();
            }
        }
        Timber.d("Getting the database instance");
        return instance;
    }

    private static WeatherDatabase.Callback weatherDatabaseCallback =
            new WeatherDatabase.Callback() {
                @Override
                public void onOpen(@NonNull SupportSQLiteDatabase db) {
                    super.onOpen(db);
                }
            };

}
