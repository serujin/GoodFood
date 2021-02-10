package com.android.serujilituni.goodfood.activities.restaurant;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.serujilituni.goodfood.R;
import com.android.serujilituni.goodfood.adapters.RestaurantAdapter;
import com.android.serujilituni.goodfood.constants.Constants;
import com.android.serujilituni.goodfood.items.RestaurantItem;
import com.android.serujilituni.goodfood.model.Restaurant;
import com.android.serujilituni.goodfood.store.AppCache;
import com.android.serujilituni.goodfood.store.DBManager;
import com.android.serujilituni.goodfood.utils.Utils;

import java.util.ArrayList;
import java.util.List;

public class RestaurantController {
    private RecyclerView rv;

    public RestaurantController(RecyclerView rv) {
        this.rv = rv;
        initRecycleView();
    }

    private void initRecycleView() {
        rv.setAdapter(new RestaurantAdapter(generateRestaurants()));
        rv.setLayoutManager(new LinearLayoutManager(AppCache.getInstance().getContext()));
        rv.setHasFixedSize(true);
    }

    private List<RestaurantItem> generateRestaurants() {
        List<Restaurant> restaurants = AppCache.getInstance().getRestaurants();
        System.out.println(restaurants.size());
        List<RestaurantItem> items = new ArrayList<>();
        for(int i = 0; i < restaurants.size(); i++) {
            items.add(new RestaurantItem(Constants.RESTAURANTS_DRAWABLES[i], restaurants.get(i).getName()));
        }
        return items;
    }
}
