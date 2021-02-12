package com.android.serujilituni.goodfood.store;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import com.android.serujilituni.goodfood.R;
import com.android.serujilituni.goodfood.activities.login.LoginActivity;
import com.android.serujilituni.goodfood.activities.restaurant.RestaurantsActivity;
import com.android.serujilituni.goodfood.constants.Constants;
import com.android.serujilituni.goodfood.model.Order;
import com.android.serujilituni.goodfood.model.Plate;
import com.android.serujilituni.goodfood.model.Restaurant;
import com.android.serujilituni.goodfood.model.User;
import com.android.serujilituni.goodfood.utils.Utils;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class DBManager {

    private static DBManager instance;
    private FirebaseDatabase db;

    public static DBManager getInstance() {
        if (DBManager.instance == null) {
            DBManager.instance = new DBManager();
        }
        return DBManager.instance;
    }

    private DBManager() {
        this.db = FirebaseDatabase.getInstance();
    }

    public void storeUser(String userUid, User user) {
        this.db.getReference(Constants.DB_USERS_REFERENCE).child(userUid).setValue(user)
                .addOnCompleteListener(
                        (OnCompleteListener) task -> Utils.registrationAction(task.isSuccessful())
                );
    }

    public void storeOrder(String uuid, Order order) {
        this.db.getReference(Constants.DB_ORDER_REFERENCE).child(uuid).push().setValue(order);
    }

    public void setRestaurants(List<Restaurant> restaurants) {
        this.db.getReference(Constants.DB_RESTAURANTS_REFERENCE).setValue(restaurants);
    }

    public void updateRestaurants() {
        Context context = AppCache.getInstance().getContext();
        if (ContextCompat.checkSelfPermission(context, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions((LoginActivity) context, new String[]{
                    Manifest.permission.ACCESS_FINE_LOCATION
            }, 100);
        }
        this.db.getReference(Constants.DB_RESTAURANTS_REFERENCE).addListenerForSingleValueEvent(getRestaurantEvent());
    }

    private ValueEventListener getRestaurantEvent() {
        return new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                List<Restaurant> restaurants = new ArrayList<>();
                if (snapshot.getValue() != null) {
                    for (DataSnapshot data : snapshot.getChildren()) {
                        HashMap<String, Object> restaurantData = (HashMap<String, Object>) data.getValue();
                        List<Double> address = (List<Double>) restaurantData.get("address");
                        List<HashMap<String, Object>> plates = (List<HashMap<String, Object>>) restaurantData.get("plates");
                        List<Plate> platesData = new ArrayList<>();
                        for(HashMap plate : plates) {
                            platesData.add(new Plate((String) plate.get("name"), Float.parseFloat(String.valueOf(plate.get("price")))));
                        }
                        String name = (String) restaurantData.get("name");
                        restaurants.add(new Restaurant(name, address, platesData));
                    }
                    AppCache.getInstance().setRestaurants(restaurants);
                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Utils.showText(Utils.getStringFromID(R.string.loading_restaurant_error), Toast.LENGTH_LONG);
                Utils.changeActivity(LoginActivity.class);
            }
        };
    }

}






