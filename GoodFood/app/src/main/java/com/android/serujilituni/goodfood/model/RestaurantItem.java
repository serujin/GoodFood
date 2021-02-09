package com.android.serujilituni.goodfood.model;

public class RestaurantItem {
    private int imageResource;
    private String restaurantName;

    public RestaurantItem(int imageResource, String restaurantName) {
        this.imageResource = imageResource;
        this.restaurantName = restaurantName;
    }

    public int getImageResource() {
        return imageResource;
    }

    public String getRestaurantName() {
        return restaurantName;
    }
}
