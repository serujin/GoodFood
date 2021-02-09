package com.android.serujilituni.goodfood.activities.restaurant;

import androidx.appcompat.app.AppCompatActivity;
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
        RecyclerView rv = findViewById(R.id.restaurants_layout);
        rv.setAdapter(new RestaurantAdapter(generateRestaurants(10))); //TODO: CHANGE GENERATE RESTAURANTS FOR APP CACHE RESTAURANTS
        rv.setLayoutManager(new LinearLayoutManager(this));
        rv.setHasFixedSize(true);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.toolbar_restaurant_menu, menu);
        MenuItem menuItem = menu.findItem(R.id.app_bar_search);
        SearchView searchView = (SearchView) menuItem.getActionView();
        searchView.setQueryHint("Type here to search");
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {

            @Override
            public boolean onQueryTextSubmit(String s) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                return false;
            }
        });
        return super.onCreateOptionsMenu(menu);
    }

    private List<RestaurantItem> generateRestaurants(int size) {
        List<RestaurantItem> items = new ArrayList<>();
        for(int i = 0; i < size; i++) {
            items.add(new RestaurantItem(R.drawable.restaurantdemo__1_, "RESTAURANT NAME"));
        }
        return items;
    }
}