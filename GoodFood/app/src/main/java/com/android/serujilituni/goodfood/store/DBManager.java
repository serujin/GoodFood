package com.android.serujilituni.goodfood.store;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import com.android.serujilituni.goodfood.R;
import com.android.serujilituni.goodfood.activities.intermediate.IntermediateActivity;
import com.android.serujilituni.goodfood.activities.login.LoginActivity;
import com.android.serujilituni.goodfood.constants.Constants;
import com.android.serujilituni.goodfood.model.Order;
import com.android.serujilituni.goodfood.model.Plate;
import com.android.serujilituni.goodfood.model.Restaurant;
import com.android.serujilituni.goodfood.model.User;
import com.android.serujilituni.goodfood.utils.Utils;
import com.google.android.gms.tasks.OnCompleteListener;
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

    public void storeUser(String userUid, User user, boolean update) {
        if (update) {
            this.db.getReference(Constants.DB_USERS_REFERENCE).child(userUid).setValue(user)
                    .addOnCompleteListener(
                            (OnCompleteListener) task -> changeNameAction(task.isSuccessful())
                    );
        } else {
            this.db.getReference(Constants.DB_USERS_REFERENCE).child(userUid).setValue(user)
                    .addOnCompleteListener(
                            (OnCompleteListener) task -> registrationAction(task.isSuccessful())
                    );
        }
    }

    public void storeOrder(String uuid, Order order) {
        this.db.getReference(Constants.DB_ORDER_REFERENCE).child(uuid).push().setValue(order).addOnCompleteListener((OnCompleteListener) task -> storeOrderAction(task.isSuccessful()));
    }

    /**
     * public void setRestaurants(List<Restaurant> restaurants) {
     * this.db.getReference(Constants.DB_RESTAURANTS_REFERENCE).setValue(restaurants);
     * }
     */
    public void updateRestaurants() {
        Context context = AppCache.getInstance().getContext();
        if (ContextCompat.checkSelfPermission(context, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions((IntermediateActivity) context, new String[]{
                    Manifest.permission.ACCESS_FINE_LOCATION
            }, 100);
        }
        this.db.getReference(Constants.DB_RESTAURANTS_REFERENCE).addListenerForSingleValueEvent(getRestaurantEvent());
    }

    private void storeOrderAction(boolean wasSuccessful) {
        if (wasSuccessful) {
            Utils.showText(Utils.getStringFromID(R.string.successfully_order));
        } else {
            Utils.showText(Utils.getStringFromID(R.string.order_error));
        }
    }

    private void registrationAction(boolean wasSuccessful) {
        if (wasSuccessful) {
            Utils.showText(Utils.getStringFromID(R.string.successfully_registered));
        } else {
            Utils.showText(Utils.getStringFromID(R.string.register_error));
        }
    }

    private void changeNameAction(boolean wasSuccessful) {
        if (wasSuccessful) {
            Utils.showText(Utils.getStringFromID(R.string.successfully_updated));
            Utils.changeActivity(IntermediateActivity.class);
        } else {
            Utils.showText(Utils.getStringFromID(R.string.register_error));
        }
    }

    private ValueEventListener getRestaurantEvent() {
        return new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                List<Restaurant> restaurants = new ArrayList<>();
                if (snapshot.getValue() != null) {
                    for (DataSnapshot data : snapshot.getChildren()) {
                        HashMap<String, Object> restaurantData = (HashMap<String, Object>) data.getValue();
                        List<Double> address = (List<Double>) restaurantData.get(Constants.RESTAURANTS_ADDRESS_KEY);
                        List<HashMap<String, Object>> plates = (List<HashMap<String, Object>>) restaurantData.get(Constants.RESTAURANTS_PLATES_KEY);
                        List<Plate> platesData = new ArrayList<>();
                        for (HashMap plate : plates) {
                            platesData.add(new Plate((String) plate.get(
                                    Constants.PLATE_NAME_KEY),
                                    Float.parseFloat(String.valueOf(plate.get(Constants.PLATE_PRICE_KEY))))
                            );
                        }
                        String name = (String) restaurantData.get(Constants.PLATE_NAME_KEY);
                        restaurants.add(new Restaurant(name, address, platesData));
                    }
                    AppCache.getInstance().setRestaurants(restaurants);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Utils.showText(Utils.getStringFromID(R.string.loading_restaurant_error));
                Utils.changeActivity(LoginActivity.class);
            }
        };
    }

}






