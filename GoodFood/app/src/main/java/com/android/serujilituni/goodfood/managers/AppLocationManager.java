package com.android.serujilituni.goodfood.managers;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.pm.PackageManager;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.util.Log;

import androidx.annotation.NonNull;

import com.android.serujilituni.goodfood.items.TemporalPlateItem;
import com.android.serujilituni.goodfood.store.AppCache;
import com.android.serujilituni.goodfood.utils.Utils;

import java.io.IOException;
import java.util.List;
import java.util.Locale;
import java.util.Random;

import static android.content.Context.LOCATION_SERVICE;

public class AppLocationManager implements LocationListener {
    private static AppLocationManager instance;

    private float orderDistance;

    public static AppLocationManager getInstance() {
        if(AppLocationManager.instance == null) {
            AppLocationManager.instance = new AppLocationManager();
        }
        return AppLocationManager.instance;
    }

    private AppLocationManager() {
        updateGps();
        this.orderDistance = 0;
    }

    @SuppressLint("MissingPermission")
    public void updateGps() {
        if (canAccessLocation()) {
            LocationManager locationManager = (LocationManager) AppCache.getInstance().getContext().getSystemService(LOCATION_SERVICE);
            locationManager.requestLocationUpdates(android.location.LocationManager.GPS_PROVIDER, 0, 5, this);
        }
    }

    public float getOrderDistance() {
        Location restaurant = new Location("");
        Location destination = new Location("");
        List<Double> restaurantData = AppCache.getInstance().getRestaurants().get(AppCache.getInstance().getCurrentRestaurant()).getAddress();
        restaurant.setLatitude(restaurantData.get(0));
        restaurant.setLongitude(restaurantData.get(1));
        Double[] data = AppCache.getInstance().getLocation();
        destination.setLatitude(data[0]);
        destination.setLongitude(data[1]);
        this.orderDistance = restaurant.distanceTo(destination) / 1000;
        return this.orderDistance;
    }

    public int getOrderTime() {
        int time = 0;
        int distance = (int) Math.ceil(this.orderDistance);
        Random r = new Random();
        List<TemporalPlateItem> plates = AppCache.getInstance().getCurrentOrder();
        for (TemporalPlateItem plate : plates) {
            time += plate.getQuantity() * (r.nextInt(9) + 1);
        }
        time += distance * (r.nextInt(5) + 1);
        return time;
    }


    private boolean canAccessLocation() {
        return(hasPermission(Manifest.permission.ACCESS_FINE_LOCATION));
    }

    private boolean hasPermission(String perm) {
        return(PackageManager.PERMISSION_GRANTED==AppCache.getInstance().getContext().checkSelfPermission(perm));
    }

    @Override
    public void onLocationChanged(@NonNull Location location) {
        Geocoder geocoder = new Geocoder(AppCache.getInstance().getContext(), Locale.getDefault());
        AppCache.getInstance().setLocation(location.getLatitude(), location.getLongitude());
        try {
            AppCache.getInstance().setUserAddress(geocoder.getFromLocation(location.getLatitude(), location.getLongitude(), 1).get(0).getAddressLine(0));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
