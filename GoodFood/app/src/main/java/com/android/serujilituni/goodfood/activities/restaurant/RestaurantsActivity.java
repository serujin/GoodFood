package com.android.serujilituni.goodfood.activities.restaurant;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.android.serujilituni.goodfood.R;
import com.android.serujilituni.goodfood.store.AppCache;

public class RestaurantsActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_restaurants);
        AppCache.getInstance().setContext(this);
        new RestaurantController(findViewById(R.id.restaurants_layout));
    }
}
