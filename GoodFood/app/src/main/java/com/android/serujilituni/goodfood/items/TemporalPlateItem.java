package com.android.serujilituni.goodfood.items;

import java.util.Objects;

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

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TemporalPlateItem that = (TemporalPlateItem) o;
        return quantity == that.quantity &&
                Float.compare(that.price, price) == 0 &&
                Objects.equals(name, that.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, quantity, price);
    }
}
