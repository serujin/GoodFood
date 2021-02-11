package com.android.serujilituni.goodfood.activities.ordersummary;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import com.android.serujilituni.goodfood.R;
import com.android.serujilituni.goodfood.store.AppCache;

public class OrderSummaryActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_summary);
        AppCache.getInstance().setContext(this);
        new OrderSummaryController(
                getButtons(),
                findViewById(R.id.address_info_et),
                findViewById(R.id.plates_rv_layout), 
                getTextviews()
        );
    }

    private Button[] getButtons() {
        return new Button[] {
                findViewById(R.id.pay_btn),
                findViewById(R.id.cancel_btn)
        };
    }

    private TextView[] getTextviews() {
        return new TextView[] {
                findViewById(R.id.distance_info_tv),
                findViewById(R.id.price_info_tv),
                findViewById(R.id.price_deliver_info_tv),
                findViewById(R.id.price_final_info_tv)
        };
    }
}