package com.android.serujilituni.goodfood.activities.restaurant;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.android.serujilituni.goodfood.R;
import com.android.serujilituni.goodfood.model.RestaurantAdapter;
import com.android.serujilituni.goodfood.model.RestaurantItem;

import java.util.ArrayList;
import java.util.List;

public class RestaurantsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_restaurants);
        RecyclerView rv = findViewById(R.id.restaurants_layout);
        rv.setAdapter(new RestaurantAdapter(generateRestaurants(10)));
        rv.setLayoutManager(new LinearLayoutManager(this));
        rv.setHasFixedSize(true);
    }

    private List<RestaurantItem> generateRestaurants(int size) {
        List<RestaurantItem> items = new ArrayList<>();
        for(int i = 0; i < size; i++) {
            items.add(new RestaurantItem(R.drawable.restaurantdemo__1_, "RESTAURANT NAME"));
        }
        return items;
    }
}