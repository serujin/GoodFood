package com.android.serujilituni.goodfood.model;

import java.util.List;

public class Restaurant {
    private String name;
    private List<Double> location;
    private List<Plate> plates;

    public Restaurant() {
    }

    public Restaurant(String name, List<Double> location, List<Plate> plates) {
        this.name = name;
        this.location = location;
        this.plates = plates;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Double> getAddress() {
        return location;
    }

    public void setAddress(List<Double> address) {
        this.location = address;
    }

    public List<Plate> getPlates() {
        return plates;
    }

    public void setPlates(List<Plate> plates) {
        this.plates = plates;
    }
}
