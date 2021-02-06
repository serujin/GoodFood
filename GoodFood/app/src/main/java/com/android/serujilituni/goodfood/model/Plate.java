package com.android.serujilituni.goodfood.model;

public class Plate {
    private String name;
    private String desc;
    private float price;

    public Plate() {
    }

    public Plate(String name, String desc, float price) {
        this.name = name;
        this.desc = desc;
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }
}
