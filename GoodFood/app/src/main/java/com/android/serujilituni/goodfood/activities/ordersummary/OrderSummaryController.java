package com.android.serujilituni.goodfood.activities.ordersummary;

import android.view.View;
import android.widget.Button;

import com.android.serujilituni.goodfood.activities.ordercomplete.OrderCompleteActivity;
import com.android.serujilituni.goodfood.activities.restaurant.RestaurantsActivity;
import com.android.serujilituni.goodfood.constants.Constants;
import com.android.serujilituni.goodfood.store.AppCache;
import com.android.serujilituni.goodfood.utils.Utils;

public class OrderSummaryController {
    private Button[] buttons;

    public OrderSummaryController(Button[] buttons) {
        this.buttons = buttons;
        initButtons();
    }

    private void initButtons() {
        initFinalizeAndPayButton();
        initCancelButton();
    }

    private void initFinalizeAndPayButton() {
        this.buttons[Constants.ORDER_SUMMARY_FINALIZE_BTN_INDEX]
                .setOnClickListener(view -> Utils.changeActivity(OrderCompleteActivity.class));
    }

    private void initCancelButton() {
        this.buttons[Constants.ORDER_SUMMARY_CANCEL_BTN_INDEX].setOnClickListener(view -> {
            AppCache.getInstance().resetOrder();
            Utils.changeActivity(RestaurantsActivity.class);
        });
    }
}
