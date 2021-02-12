package com.android.serujilituni.goodfood.activities.restaurant;

import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.serujilituni.goodfood.R;
import com.android.serujilituni.goodfood.activities.intermediate.IntermediateActivity;
import com.android.serujilituni.goodfood.activities.ordersummary.OrderSummaryActivity;
import com.android.serujilituni.goodfood.adapters.RestaurantAdapter;
import com.android.serujilituni.goodfood.adapters.TemporalPlateAdapter;
import com.android.serujilituni.goodfood.constants.Constants;
import com.android.serujilituni.goodfood.items.RestaurantItem;
import com.android.serujilituni.goodfood.managers.AppLocationManager;
import com.android.serujilituni.goodfood.model.Restaurant;
import com.android.serujilituni.goodfood.store.AppCache;
import com.android.serujilituni.goodfood.utils.Utils;

import java.util.ArrayList;
import java.util.List;

public class RestaurantsController {
    private RecyclerView rv;
    private RecyclerView orderRv;
    private DrawerLayout dl;
    private ImageButton menu;
    private Button pay;
    private TextView price;

    public RestaurantsController(RecyclerView rv, RecyclerView orderRv, ImageButton menu, DrawerLayout dl, Button pay, TextView price) {
        this.rv = rv;
        this.dl = dl;
        this.orderRv = orderRv;
        this.menu = menu;
        this.pay = pay;
        this.price = price;
        initViews();
    }

    private void initViews() {
        initRecycleView();
        initOrderNavigationView();
    }

    private void initRecycleView() {
        rv.setAdapter(new RestaurantAdapter(getRestaurantsFromCache()));
        rv.setLayoutManager(new LinearLayoutManager(AppCache.getInstance().getContext()));
        rv.setHasFixedSize(true);
    }

    private List<RestaurantItem> getRestaurantsFromCache() {
        List<Restaurant> restaurants = AppCache.getInstance().getRestaurants();
        List<RestaurantItem> items = new ArrayList<>();
        for (int i = 0; i < restaurants.size(); i++) {
            items.add(new RestaurantItem(Constants.RESTAURANTS_DRAWABLES[i], restaurants.get(i).getName()));
        }
        return items;
    }

    private void initOrderNavigationView() {
        menu.setOnClickListener(view -> openDrawer());
        pay.setOnClickListener(view -> {
            if(AppCache.getInstance().getLocation()[0] != null && AppCache.getInstance().getLocation()[1] != null) {
                if(AppCache.getInstance().getCurrentOrder().size() > 0) {
                    Utils.changeActivity(OrderSummaryActivity.class);
                } else {
                    Utils.showText(Utils.getStringFromID(R.string.cant_pay_for_nothing));
                }
            } else {
                AppLocationManager.getInstance().updateGps();
                Utils.showText(Utils.getStringFromID(R.string.finalize_order_error));
                Utils.changeActivity(IntermediateActivity.class);
                AppCache.getInstance().resetOrder();
            }
        });
    }

    private void openDrawer() {
        this.orderRv.setAdapter(new TemporalPlateAdapter(AppCache.getInstance().getCurrentOrder()));
        this.orderRv.setLayoutManager(new LinearLayoutManager(AppCache.getInstance().getContext()));
        this.orderRv.setHasFixedSize(true);
        this.price.setText(AppCache.getInstance().getFormatter().format(Utils.getTotalMoneyOfCurrentOrder()));
        this.dl.openDrawer(GravityCompat.END);
    }
}
