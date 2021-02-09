package com.android.serujilituni.goodfood.store;

import com.android.serujilituni.goodfood.constants.Constants;
import com.android.serujilituni.goodfood.model.User;
import com.android.serujilituni.goodfood.utils.Utils;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.firebase.database.FirebaseDatabase;

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

    public void getRestaurants(ValueEventListener vel) {
        this.db.child("restaurants").addListenerForSingleValueEvent(vel);
    }
 */
}






