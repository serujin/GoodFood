package com.android.serujilituni.goodfood.activities.restaurant;

import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.serujilituni.goodfood.activities.ordersummary.OrderSummaryActivity;
import com.android.serujilituni.goodfood.adapters.RestaurantAdapter;
import com.android.serujilituni.goodfood.adapters.TemporalPlateAdapter;
import com.android.serujilituni.goodfood.store.AppCache;
import com.android.serujilituni.goodfood.utils.Utils;

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
        rv.setAdapter(new RestaurantAdapter(Utils.getRestaurantsFromCache()));
        rv.setLayoutManager(new LinearLayoutManager(AppCache.getInstance().getContext()));
        rv.setHasFixedSize(true);
    }

    private void initOrderNavigationView() {
         menu.setOnClickListener(view -> openDrawer());
         pay.setOnClickListener(view -> {
             if(AppCache.getInstance().getLocation()[0] != null && AppCache.getInstance().getLocation()[1] != null) {
                 if(AppCache.getInstance().getCurrentOrder().size() > 0) {
                     Utils.changeActivity(OrderSummaryActivity.class);
                 } else {
                     Utils.showText("You can't pay for nothing!!", Toast.LENGTH_LONG);
                 }
             } else {
                 Utils.showText("An error ocurred try again", Toast.LENGTH_LONG);
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
