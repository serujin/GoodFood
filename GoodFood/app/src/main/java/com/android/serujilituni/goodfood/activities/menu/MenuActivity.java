package com.android.serujilituni.goodfood.activities.menu;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.android.serujilituni.goodfood.R;
import com.android.serujilituni.goodfood.constants.Constants;
import com.android.serujilituni.goodfood.store.AppCache;

public class MenuActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
        AppCache.getInstance().setContext(this);
        new MenuController(findViewById(R.id.plates_layout), getIntent().getIntExtra(Constants.MENU_EXTRA_INTENT, 0));
    }
}