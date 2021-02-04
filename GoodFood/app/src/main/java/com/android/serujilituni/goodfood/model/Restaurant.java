package com.android.serujilituni.goodfood.model;

public class Restaurant {
    private int restaurantId;
    private String restaurantAddress;
    private String restaurantName;

    public Restaurant() {
    }

    public Restaurant(int restaurantId, String restaurantAddress, String restaurantName) {
        this.restaurantId = restaurantId;
        this.restaurantAddress = restaurantAddress;
        this.restaurantName = restaurantName;
    }

    public int getRestaurantId() {
        return restaurantId;
    }

    public void setRestaurantId(int restaurantId) {
        this.restaurantId = restaurantId;
    }

    public String getRestaurantAddress() {
        return restaurantAddress;
    }

    public void setRestaurantAddress(String restaurantAddress) {
        this.restaurantAddress = restaurantAddress;
    }

    public String getRestaurantName() {
        return restaurantName;
    }

    public void setRestaurantName(String restaurantName) {
        this.restaurantName = restaurantName;
    }
}
