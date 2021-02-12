package com.android.serujilituni.goodfood.model;

//THIS CLASS NEEDS TO HAVE ALL THOSE METHODS BC FIREBASE USE IT
public class User {

    public String fullName;
    public String email;

    public User() {

    }

    public User(String fullName, String email) {
        this.fullName = fullName;
        this.email = email;
    }
}
