package com.android.serujilituni.goodfood.activities.ordersummary;

import android.view.View;
import android.widget.Button;

import com.android.serujilituni.goodfood.activities.ordercomplete.OrderCompleteActivity;
import com.android.serujilituni.goodfood.activities.restaurant.RestaurantsActivity;
import com.android.serujilituni.goodfood.utils.Utils;

public class OrderSummaryController {
    private Button[] buttons;

    public OrderSummaryController(Button[] buttons) {
        this.buttons = buttons;
    }

    private void initButtons() {
        initFinalizeAndPayButton();
        initCancelButton();
    }

    private void initFinalizeAndPayButton() {
        this.buttons[0].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Utils.changeActivity(OrderCompleteActivity.class);
            }
        });
    }

    private void initCancelButton() {
        this.buttons[1].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //TODO: DELETE ORDER
                Utils.changeActivity(RestaurantsActivity.class);
            }
        });
    }
}
