package com.android.serujilituni.goodfood.activities.ordersummary;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;

import com.android.serujilituni.goodfood.R;
import com.android.serujilituni.goodfood.store.AppCache;

public class OrderSummaryActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_summary);
        AppCache.getInstance().setContext(this);
        new OrderSummaryController(getButtons());
    }

    private Button[] getButtons() {
        return new Button[] {
                findViewById(R.id.pay_btn),
                findViewById(R.id.cancel_btn)
        };
    }
}