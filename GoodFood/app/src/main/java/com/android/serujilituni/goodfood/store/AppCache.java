package com.android.serujilituni.goodfood.store;

import com.android.serujilituni.goodfood.model.Restaurant;

import java.util.ArrayList;

public class AppCache {

    private static AppCache instance;
    private ArrayList<Restaurant> restaurants;

    public static AppCache getInstance(){
        if(AppCache.instance == null) {
            AppCache.instance = new AppCache();
        }
        return AppCache.instance;
    }

    public ArrayList<Restaurant> getRestaurants() {
        return this.restaurants;
    }

    public void addRestaurant(Restaurant r) {
        if(!this.restaurants.contains(r)){
            this.restaurants.add(r);
        }
    }
}
