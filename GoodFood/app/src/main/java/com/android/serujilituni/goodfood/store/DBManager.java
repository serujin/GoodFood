package com.android.serujilituni.goodfood.store;

import android.util.Log;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Random;

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
        initDatabase();
        Log.i("ADAd", "dasdawd");
    }

    private void initDatabase() {
        this.db = FirebaseDatabase.getInstance().getReference();
        if(this.db == null) {
            System.out.println("DB CREATED");
        }
        this.db.
    }

    public void storeOrder(Object[] data) {



    }





}
