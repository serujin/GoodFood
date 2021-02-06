package com.android.serujilituni.goodfood.model;

import java.util.List;

public class Restaurant {
    private String name;
    private String address;
    private List<Plate> plates;

    public Restaurant() {
    }

    public Restaurant(String name, String address, List<Plate> plates) {
        this.name = name;
        this.address = address;
        this.plates = plates;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public List<Plate> getPlates() {
        return plates;
    }

    public void setPlates(List<Plate> plates) {
        this.plates = plates;
    }
}
