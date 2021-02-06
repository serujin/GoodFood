package com.android.serujilituni.goodfood.store;

import com.google.firebase.database.DatabaseReference;

public class DBManager {

    private static DBManager instance;
    private DatabaseReference mDatabase;

    public static DBManager getInstance(){
        if(DBManager.instance==null) {
            DBManager.instance = new DBManager();
        }
        return DBManager.instance;
    }

    public void storeOrder(Object[] data) {



    }





}
