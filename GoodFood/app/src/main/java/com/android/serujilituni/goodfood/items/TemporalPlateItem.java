package com.android.serujilituni.goodfood.items;

public class TemporalPlateItem {
    private String name;
    private int quantity;
    private float price;

    public TemporalPlateItem(String name, int quantity, float price) {
        this.name = name;
        this.quantity = quantity;
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public int getQuantity() {
        return quantity;
    }

    public float getPrice() {
        return price;
    }
}
