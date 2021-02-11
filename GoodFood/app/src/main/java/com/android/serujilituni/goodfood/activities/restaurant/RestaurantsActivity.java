package com.android.serujilituni.goodfood.activities.restaurant;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.os.Bundle;

import com.android.serujilituni.goodfood.R;
import com.android.serujilituni.goodfood.store.AppCache;
import com.android.serujilituni.goodfood.utils.Utils;

public class RestaurantsActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_restaurants);
        AppCache.getInstance().setContext(this);
        new RestaurantsController(findViewById(R.id.restaurants_layout),
                findViewById(R.id.temporal_order_rv),
                findViewById(R.id.open_menu_btn),
                findViewById(R.id.drawerLayout),
                findViewById(R.id.finalize_order_btn),
                findViewById(R.id.total_price_temporal_tv)
        );
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = findViewById(R.id.drawerLayout);
        if(drawer.isDrawerOpen(GravityCompat.END)) {
            drawer.closeDrawers();
        } else {
            Utils.exitConfirmation(
                    getResources().getString(R.string.exit_confirmation),
                    getResources().getString(R.string.yes_confirmation),
                    getResources().getString(R.string.no_confirmation)
            );
        }
    }
}

