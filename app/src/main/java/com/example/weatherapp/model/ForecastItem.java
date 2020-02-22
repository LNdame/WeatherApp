package com.example.weatherapp.model;
import android.widget.ImageView;

import androidx.annotation.DrawableRes;
import androidx.databinding.BindingAdapter;

import com.example.weatherapp.R;
import com.example.weatherapp.utils.AppUtils;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;


public class ForecastItem implements Serializable {

    @SerializedName("dt")
    @Expose
    private Integer dt;
    @SerializedName("main")
    @Expose
    private MainTemp main;
    @SerializedName("weather")
    @Expose
    private List<Weather> weather = null;
    @SerializedName("dt_txt")
    @Expose
    private String dtTxt;

    public Integer getDt() {
        return dt;
    }

    public void setDt(Integer dt) {
        this.dt = dt;
    }

    public MainTemp getMain() {
        return main;
    }

    public void setMain(MainTemp main) {
        this.main = main;
    }

    public List<Weather> getWeather() {
        return weather;
    }

    public void setWeather(List<Weather> weather) {
        this.weather = weather;
    }

    public String getDtTxt() {
        return dtTxt;
    }

    public void setDtTxt(String dtTxt) {
        this.dtTxt = dtTxt;
    }

    public String getDayTemperature() {
        return String.format(Locale.ENGLISH, "%.0f\u00B0", getMain().getTemp());
    }

    public String getDayOfWeek() {
        Date date = AppUtils.transformDate(getDtTxt());
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        int day = calendar.get(Calendar.DAY_OF_WEEK);
        return AppUtils.dayOfWeekString(day);
    }

    final static String CLOUDS ="Clouds";
    final static String RAIN ="Rain";
    final static String CLEAR ="Clear";

    @DrawableRes
    public int getWeatherImage(){
        return  setupWeatherImage(weather.get(0).getMain());
    }

    public int setupWeatherImage(String desc){
        switch (desc) {
            case CLOUDS:
                return R.drawable.ic_action_partlysunny;
            case RAIN:
                return R.drawable.ic_action_rain;
            case CLEAR:
                return R.drawable.ic_action_clear;
            default:
                return R.drawable.ic_action_partlysunny;
        }
    }

    @BindingAdapter({"imageSource"})
    public static void setImageViewResource(ImageView imageView, int resource) {
        imageView.setImageResource(resource);
    }
}
