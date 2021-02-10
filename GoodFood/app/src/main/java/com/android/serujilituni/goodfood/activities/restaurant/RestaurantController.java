package com.android.serujilituni.goodfood.activities.restaurant;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.serujilituni.goodfood.R;
import com.android.serujilituni.goodfood.adapters.RestaurantAdapter;
import com.android.serujilituni.goodfood.constants.Constants;
import com.android.serujilituni.goodfood.items.RestaurantItem;
import com.android.serujilituni.goodfood.store.AppCache;
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
        String[] restaurantsNames = Utils.getStringArrayFromID(R.array.restaurants_names);
        List<RestaurantItem> items = new ArrayList<>();
        for(int i = 0; i < restaurantsNames.length; i++) {
            items.add(new RestaurantItem(i, Constants.RESTAURANTS_DRAWABLES[i], restaurantsNames[i]));
        }
        return items;
    }
}
