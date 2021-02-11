package com.android.serujilituni.goodfood.store;

import android.content.Context;

import com.android.serujilituni.goodfood.items.PlateItem;
import com.android.serujilituni.goodfood.items.TemporalPlateItem;
import com.android.serujilituni.goodfood.model.Plate;
import com.android.serujilituni.goodfood.model.Restaurant;

import java.util.ArrayList;
import java.util.List;

public class AppCache {

    private static AppCache instance;
    private List<Restaurant> restaurants;
    private List<TemporalPlateItem> currentOrder;
    private String address;
    private int currentRestaurant;
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
        this.address = "AAAAAAAAAAAAAAAWDDDDDDDDDAAAAAAWDDDDAAAAADDDDAAADDDDAAADDDDAAAADDDDAADDADA";
    }

    public void addPlate(TemporalPlateItem plate) {
        if(orderContainsPlate(plate)) {
            changePlateQuantity(plate, 1);
        } else {
            currentOrder.add(plate);
        }
    }

    public void resetOrder() {
        this.currentOrder = new ArrayList<>();
    }

    public void subPlate(TemporalPlateItem plate) {
        changePlateQuantity(plate, -1);
    }

    private boolean orderContainsPlate(TemporalPlateItem plate) {
        boolean toReturn = false;
        for(TemporalPlateItem p : currentOrder) {
            if(p.getName().equals(plate.getName())) {
                toReturn = true;
            }
        }
        return toReturn;
    }

    private void changePlateQuantity(TemporalPlateItem plate, int quantity) {
        for(int i = 0; i < currentOrder.size(); i++) {
            if(currentOrder.get(i).getName().equals(plate.getName())) {
                int quantityData = currentOrder.get(i).getQuantity() + quantity;
                if(quantityData > 0) {
                    currentOrder.get(i).setQuantity(quantityData);
                } else {
                    currentOrder.remove(i);
                }
            }
        }
    }

    public List<TemporalPlateItem> getCurrentOrder() {
        return this.currentOrder;
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

    public int getCurrentRestaurant() {
        return currentRestaurant;
    }

    public void setCurrentRestaurant(int currentRestaurant) {
        this.currentRestaurant = currentRestaurant;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
