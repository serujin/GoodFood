package com.android.serujilituni.goodfood.model;

public class Plate {
    private int plateId;
    private String plateName;
    private float platePrice;
    private String plateDesc;
    private int restaurantId;

    public Plate() {}

    public Plate(int plateId, String plateName, float platePrice, String plateDesc, int restaurantId) {
        this.plateId = plateId;
        this.plateName = plateName;
        this.platePrice = platePrice;
        this.plateDesc = plateDesc;
        this.restaurantId = restaurantId;
    }

    public int getPlateId() {
        return plateId;
    }

    public void setPlateId(int plateId) {
        this.plateId = plateId;
    }

    public String getPlateName() {
        return plateName;
    }

    public void setPlateName(String plateName) {
        this.plateName = plateName;
    }

    public float getPlatePrice() {
        return platePrice;
    }

    public void setPlatePrice(float platePrice) {
        this.platePrice = platePrice;
    }

    public String getPlateDesc() {
        return plateDesc;
    }

    public void setPlateDesc(String plateDesc) {
        this.plateDesc = plateDesc;
    }

    public int getRestaurantId() {
        return restaurantId;
    }

    public void setRestaurantId(int restaurantId) {
        this.restaurantId = restaurantId;
    }
}
