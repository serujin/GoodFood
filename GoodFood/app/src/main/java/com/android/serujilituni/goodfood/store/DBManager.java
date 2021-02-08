package com.android.serujilituni.goodfood.store;

import androidx.annotation.NonNull;

import com.android.serujilituni.goodfood.model.Order;
import com.android.serujilituni.goodfood.model.Restaurant;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Set;

public class DBManager {

    private static DBManager instance;
    private DatabaseReference db;

    public static DBManager getInstance() {
        if (DBManager.instance == null) {
            DBManager.instance = new DBManager();
        }
        return DBManager.instance;
    }

    private DBManager() {
        this.db = FirebaseDatabase.getInstance().getReference();
    }

    public void storeOrder(FirebaseAuth auth, Order toStore) {
        this.db.child("orders").child(auth.getCurrentUser().getEmail()).setValue(toStore);
    }

    public void setRestaurants(List<Restaurant> restaurants) {
        this.db.child("restaurants").setValue(restaurants);
    }

    public void getRestaurants(ValueEventListener vel) {
        this.db.child("restaurants").addListenerForSingleValueEvent(vel);
    }








}






