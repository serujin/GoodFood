package com.android.serujilituni.goodfood.activities.ordersummary;

import android.widget.Button;
import android.widget.TextView;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.serujilituni.goodfood.activities.ordercomplete.OrderCompleteActivity;
import com.android.serujilituni.goodfood.activities.restaurant.RestaurantsActivity;
import com.android.serujilituni.goodfood.adapters.TemporalPlateAdapter;
import com.android.serujilituni.goodfood.constants.Constants;
import com.android.serujilituni.goodfood.store.AppCache;
import com.android.serujilituni.goodfood.utils.Utils;

public class OrderSummaryController {
    private Button[] buttons;
    private RecyclerView orderRv;
    private TextView[] tvs;

    public OrderSummaryController(Button[] buttons, RecyclerView orderRv, TextView[] tvs) {
        this.buttons = buttons;
        this.orderRv = orderRv;
        this.tvs = tvs;
        initButtons();
        initOrderRV();
    }

    private void initButtons() {
        initFinalizeAndPayButton();
        initCancelButton();
    }

    private void initOrderRV() {
        this.orderRv.setAdapter(new TemporalPlateAdapter(AppCache.getInstance().getCurrentOrder()));
        this.orderRv.setLayoutManager(new LinearLayoutManager(AppCache.getInstance().getContext()));
        this.orderRv.setHasFixedSize(true);
    }

    private void initTextViews() {
        this.tvs[Constants.ORDER_SUMMARY_DISTANCE_TV].setText(String.valueOf(Utils.getOrderDistance()));
        this.tvs[Constants.ORDER_SUMMARY_PRICE_TV].setText(String.valueOf(Utils.getTotalMoneyOfCurrentOrder()));
        this.tvs[Constants.ORDER_SUMMARY_DELIVER_TV].setText(String.valueOf(Utils.getDeliverPrice()));
        this.tvs[Constants.ORDER_SUMMARY_FINAL_PRICE_TV].setText(String.valueOf(Utils.getTotalPrice()));
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
