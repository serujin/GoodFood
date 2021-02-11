package com.android.serujilituni.goodfood.activities.menu;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.os.Bundle;

import com.android.serujilituni.goodfood.R;
import com.android.serujilituni.goodfood.activities.restaurant.RestaurantsActivity;
import com.android.serujilituni.goodfood.constants.Constants;
import com.android.serujilituni.goodfood.store.AppCache;
import com.android.serujilituni.goodfood.utils.Utils;

public class MenuActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
        AppCache.getInstance().setContext(this);
        new MenuController(findViewById(R.id.plates_layout),
                getIntent().getIntExtra(Constants.MENU_EXTRA_INTENT, 0),
                findViewById(R.id.temporal_order_rv),
                findViewById(R.id.open_menu_btn),
                findViewById(R.id.drawerLayout),
                findViewById(R.id.finalize_order_btn)
        );
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = findViewById(R.id.drawerLayout);
        if(drawer.isDrawerOpen(GravityCompat.END)) {
            drawer.closeDrawers();
        } else {
            Utils.changeActivity(RestaurantsActivity.class);
        }
    }
}