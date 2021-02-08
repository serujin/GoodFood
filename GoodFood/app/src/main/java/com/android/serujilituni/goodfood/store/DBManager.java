package com.android.serujilituni.goodfood.store;

import com.android.serujilituni.goodfood.model.Order;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class DBManager {

    private static DBManager instance;
    private DatabaseReference db;

    public static DBManager getInstance(){
        if(DBManager.instance==null) {
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
}
