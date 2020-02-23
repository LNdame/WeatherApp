package com.example.weatherapp.utils;

import android.view.View;
import android.widget.ImageView;

import androidx.annotation.ColorRes;
import androidx.core.content.ContextCompat;
import androidx.databinding.BindingAdapter;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

import timber.log.Timber;

public class AppUtils {
    private static final int REMOVE_BACKGROUND = -1;

    public static Date transformDate(String dateToConvert) {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.ENGLISH);
        Date date = new Date();
        try {
            date = format.parse(dateToConvert);
        } catch (ParseException e) {
            Timber.e(e);
        }
        return date;
    }

    public static int getDayOfWeek(String dateString) {
        Date date = transformDate(dateString);
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        int day = calendar.get(Calendar.DAY_OF_WEEK);
       return day;
    }

    public static int getDayOfWeek(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        int day = calendar.get(Calendar.DAY_OF_WEEK);
        return day;
    }

    public static String dayOfWeekString(int day) {
        switch (day) {
            case Calendar.SUNDAY:
                return "SUNDAY";
            case Calendar.MONDAY:
                return "MONDAY";
            case Calendar.TUESDAY:
                return "TUESDAY";
            case Calendar.WEDNESDAY:
                return "WEDNESDAY";
            case Calendar.THURSDAY:
                return "THURSDAY";
            case Calendar.FRIDAY:
                return "FRIDAY";
            case Calendar.SATURDAY:
                return "SATURDAY";
            default:
                return "Today";
        }
    }

    @BindingAdapter({"imageSource"})
    public static void setImageViewResource(ImageView imageView, int resource) {
        imageView.setImageResource(resource);
    }

    @BindingAdapter({"backgroundColor"})
    public static void setBackground(View view, @ColorRes int resource) {
        if (view == null) return;
        if (resource == REMOVE_BACKGROUND) {
            view.setBackground(null);
        } else {
            view.setBackground(ContextCompat.getDrawable(view.getContext(), resource));
        }
    }

}
