package com.android.serujilituni.goodfood.model;

import java.time.LocalDateTime;
import java.util.List;

public class Order {
    private String address;
    private Restaurant restaurant;
    private List<Plate> plates;
    private LocalDateTime dateTime;

    public Order() {
    }

    public Order(String address, Restaurant restaurant, List<Plate> plates, LocalDateTime dateTime) {
        this.address = address;
        this.restaurant = restaurant;
        this.plates = plates;
        this.dateTime = dateTime;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Restaurant getRestaurant() {
        return restaurant;
    }

    public void setRestaurant(Restaurant restaurant) {
        this.restaurant = restaurant;
    }

    public List<Plate> getPlates() {
        return plates;
    }

    public void setPlates(List<Plate> plates) {
        this.plates = plates;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public void setDateTime(LocalDateTime dateTime) {
        this.dateTime = dateTime;
    }
}
