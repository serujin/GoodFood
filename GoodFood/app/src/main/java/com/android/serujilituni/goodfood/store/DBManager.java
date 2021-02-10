package com.android.serujilituni.goodfood.store;

import android.widget.Toast;

import androidx.annotation.NonNull;

import com.android.serujilituni.goodfood.R;
import com.android.serujilituni.goodfood.activities.login.LoginActivity;
import com.android.serujilituni.goodfood.constants.Constants;
import com.android.serujilituni.goodfood.model.Restaurant;
import com.android.serujilituni.goodfood.model.User;
import com.android.serujilituni.goodfood.utils.Utils;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

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
/**
    public void storeOrder(FirebaseAuth auth, Order toStore) {
        this.db.child("orders").child(auth.getCurrentUser().getEmail()).setValue(toStore);
    }

    public void setRestaurants(List<Restaurant> restaurants) {
        this.db.child("restaurants").setValue(restaurants);
    }
 */
    public void updateRestaurants() {
        this.db.getReference(Constants.DB_RESTAURANTS_REFERENCE).addListenerForSingleValueEvent(getRestaurantEvent());
    }

    private ValueEventListener getRestaurantEvent() {
        return new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.getValue() != null) {
                    for (DataSnapshot child : snapshot.getChildren()) {
                        Restaurant r = child.getValue(Restaurant.class);
                        AppCache.getInstance().addRestaurant(r);
                    }
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






