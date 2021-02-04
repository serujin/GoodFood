package com.android.serujilituni.goodfood.model;

import java.time.LocalDateTime;

public class Order {
    private int orderId;
    private float orderPrice;
    private LocalDateTime orderDateTime;
    private int userId;

    public Order() {}

    public Order(int orderId, float orderPrice, LocalDateTime orderDateTime, int userId) {
        this.orderId = orderId;
        this.orderPrice = orderPrice;
        this.orderDateTime = orderDateTime;
        this.userId = userId;
    }

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public float getOrderPrice() {
        return orderPrice;
    }

    public void setOrderPrice(float orderPrice) {
        this.orderPrice = orderPrice;
    }

    public LocalDateTime getOrderDateTime() {
        return orderDateTime;
    }

    public void setOrderDateTime(LocalDateTime orderDateTime) {
        this.orderDateTime = orderDateTime;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }
}
