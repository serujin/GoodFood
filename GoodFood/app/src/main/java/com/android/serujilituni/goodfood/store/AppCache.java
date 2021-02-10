package com.android.serujilituni.goodfood.store;

import android.content.Context;

import com.android.serujilituni.goodfood.model.Plate;
import com.android.serujilituni.goodfood.model.Restaurant;

import java.util.ArrayList;
import java.util.List;

public class AppCache {

    private static AppCache instance;
    private List<Restaurant> restaurants;
    private List<Plate> currentOrder;
    private Context context;

    public static AppCache getInstance(){
        if(AppCache.instance == null) {
            AppCache.instance = new AppCache();
        }
        return AppCache.instance;
    }

    private AppCache() {
        this.restaurants = new ArrayList<>();
        this.currentOrder = new ArrayList<>();
    }

    public List<Restaurant> getRestaurants() {
        return this.restaurants;
    }

    public void setRestaurants(List<Restaurant> restaurants) {
        this.restaurants = restaurants;
    }

    public Context getContext() {
        return context;
    }

    public void setContext(Context context) {
        this.context = context;
    }
}
