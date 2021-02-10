package com.android.serujilituni.goodfood.items;

import android.graphics.drawable.Drawable;

import com.android.serujilituni.goodfood.utils.Utils;

public class RestaurantItem {
    private int index;
    private Drawable imageResource;
    private String restaurantName;

    public RestaurantItem(int index, int imageResource, String restaurantName) {
        this.index = index;
        this.imageResource = Utils.getDrawableFromID(imageResource);
        this.restaurantName = restaurantName;
    }

    public int getIndex() {return index;}

    public Drawable getImageResource() {
        return imageResource;
    }

    public String getRestaurantName() {
        return restaurantName;
    }
}
