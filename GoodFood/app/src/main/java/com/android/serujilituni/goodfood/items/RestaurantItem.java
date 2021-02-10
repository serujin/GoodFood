package com.android.serujilituni.goodfood.items;

import android.graphics.drawable.Drawable;

import com.android.serujilituni.goodfood.utils.Utils;

public class RestaurantItem {
    private Drawable imageResource;
    private String restaurantName;

    public RestaurantItem(int imageResource, String restaurantName) {
        this.imageResource = Utils.getDrawableFromID(imageResource);
        this.restaurantName = restaurantName;
    }

    public Drawable getImageResource() {
        return imageResource;
    }

    public String getRestaurantName() {
        return restaurantName;
    }
}
