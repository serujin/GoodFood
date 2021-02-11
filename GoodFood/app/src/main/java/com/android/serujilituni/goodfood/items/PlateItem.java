package com.android.serujilituni.goodfood.items;

import android.graphics.drawable.Drawable;

public class PlateItem {
    private Drawable imageResource;
    private String plateName;
    private float platePrice;
    private int plateQuantity;

    public PlateItem(Drawable imageResource, String plateName, float platePrice) {
        this.imageResource = imageResource;
        this.plateName = plateName;
        this.platePrice = platePrice;
        this.plateQuantity = 0;
    }

    public PlateItem(Drawable imageResource, String plateName, float platePrice, int quantity) {
        this.imageResource = imageResource;
        this.plateName = plateName;
        this.platePrice = platePrice;
        this.plateQuantity = quantity;
    }

    public Drawable getImageResource() {
        return imageResource;
    }

    public String getPlateName() {
        return plateName;
    }

    public int getPlateQuantity() {
        return plateQuantity;
    }

    public float getPlatePrice() {
        return platePrice;
    }
}

