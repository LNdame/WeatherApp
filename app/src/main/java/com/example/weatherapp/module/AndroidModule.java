package com.example.weatherapp.module;

import android.app.Application;
import android.content.Context;

import androidx.lifecycle.ViewModelProvider;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class AndroidModule {
    private final ViewModelProvider.AndroidViewModelFactory viewModelFactory;
    private final Context applicationContext;

    public AndroidModule(Application application) {
        this.applicationContext = application.getBaseContext();
        this.viewModelFactory = ViewModelProvider.AndroidViewModelFactory.getInstance(application);
    }

    @Provides
    @Singleton
    Context provideApplicationContext() {
        return applicationContext;
    }

    @Provides
    @Singleton
    public ViewModelProvider.Factory provideViewModelFactory(Context context) {
        return viewModelFactory;
    }
}
