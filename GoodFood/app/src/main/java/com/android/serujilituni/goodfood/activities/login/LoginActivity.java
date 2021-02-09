package com.android.serujilituni.goodfood.activities.login;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.widget.ImageView;

import com.android.serujilituni.goodfood.R;
import com.android.serujilituni.goodfood.store.DBManager;
import com.bumptech.glide.Glide;

public class LoginActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
    }

}