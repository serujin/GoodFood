package com.android.serujilituni.goodfood.activities.ordersummary;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import com.android.serujilituni.goodfood.R;
import com.android.serujilituni.goodfood.activities.restaurant.RestaurantsActivity;
import com.android.serujilituni.goodfood.store.AppCache;
import com.android.serujilituni.goodfood.utils.Utils;

public class OrderSummaryActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_summary);
        AppCache.getInstance().setContext(this);
        new OrderSummaryController(
                getButtons(),
                findViewById(R.id.plates_rv_layout),
                getTextViews()
        );
    }

    @Override
    public void onBackPressed() {
        Utils.changeActivity(RestaurantsActivity.class);
    }

    private Button[] getButtons() {
        return new Button[]{
                findViewById(R.id.pay_btn),
                findViewById(R.id.cancel_btn)
        };
    }

    private TextView[] getTextViews() {
        return new TextView[]{
                findViewById(R.id.address_info_tv),
                findViewById(R.id.distance_info_tv),
                findViewById(R.id.price_info_tv),
                findViewById(R.id.price_deliver_info_tv),
                findViewById(R.id.price_final_info_tv)
        };
    }
}