package com.android.serujilituni.goodfood.activities.menu;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.serujilituni.goodfood.R;
import com.android.serujilituni.goodfood.adapters.PlateAdapter;
import com.android.serujilituni.goodfood.items.PlateItem;
import com.android.serujilituni.goodfood.model.Plate;
import com.android.serujilituni.goodfood.store.AppCache;
import com.android.serujilituni.goodfood.utils.Utils;

import java.util.ArrayList;
import java.util.List;

public class MenuController {
    private RecyclerView rv;

    public MenuController(RecyclerView rv, int restaurantIndex) {
        this.rv = rv;
        initRecycleView(restaurantIndex);
    }

    private void initRecycleView(int restaurantIndex) {
        rv.setAdapter(new PlateAdapter(generatePlates(restaurantIndex)));
        rv.setLayoutManager(new LinearLayoutManager(AppCache.getInstance().getContext()));
        rv.setHasFixedSize(true);
    }

    private List<PlateItem> generatePlates(int restaurantIndex) {
        List<Plate> plates = AppCache.getInstance().getRestaurants().get(restaurantIndex).getPlates();
        List<PlateItem> items = new ArrayList<>();
        for (int i = 0; i < plates.size(); i++) {
            items.add(new PlateItem(Utils.getDrawableFromID(R.drawable.googleg_disabled_color_18), plates.get(i).getName(), plates.get(i).getPrice()));
        }
        return items;
    }
}
