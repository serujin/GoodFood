package com.android.serujilituni.goodfood.activities.restaurant;

import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.SearchView;

import com.android.serujilituni.goodfood.R;
import com.android.serujilituni.goodfood.adapters.RestaurantAdapter;
import com.android.serujilituni.goodfood.items.RestaurantItem;

import java.util.ArrayList;
import java.util.List;

public class RestaurantsActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_restaurants);
        DrawerLayout drawer = findViewById(R.id)
    }
}
        /**RecyclerView rv = findViewById(R.id.restaurants_layout);
        rv.setAdapter(new RestaurantAdapter(generateRestaurants(10))); //TODO: CHANGE GENERATE RESTAURANTS FOR APP CACHE RESTAURANTS
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