package com.android.serujilituni.goodfood.activities.ordersummary;

import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

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
    private EditText address;
    private ImageButton location;
    private RecyclerView orderRv;
    private TextView[] tvs;

    public OrderSummaryController(Button[] buttons, EditText address, ImageButton location, RecyclerView orderRv, TextView[] tvs) {
        this.buttons = buttons;
        this.address = address;
        this.location = location;
        this.orderRv = orderRv;
        this.tvs = tvs;
        initComponents();
    }

    private void initComponents() {
        initButtons();
        initOrderRV();
    }

    private void initButtons() {
        initLocationButton();
        initRefreshButton();
        initFinalizeAndPayButton();
        initCancelButton();
    }

    private void initOrderRV() {
        this.orderRv.setAdapter(new TemporalPlateAdapter(AppCache.getInstance().getCurrentOrder()));
        this.orderRv.setLayoutManager(new LinearLayoutManager(AppCache.getInstance().getContext()));
        this.orderRv.setHasFixedSize(true);
    }

    private void showInformation() {
        this.tvs[Constants.ORDER_SUMMARY_DISTANCE_TV].setText(String.valueOf(Utils.getOrderDistance()));
        this.tvs[Constants.ORDER_SUMMARY_PRICE_TV].setText(String.valueOf(Utils.getTotalMoneyOfCurrentOrder()));
        this.tvs[Constants.ORDER_SUMMARY_DELIVER_TV].setText(String.valueOf(Utils.getDeliverPrice()));
        this.tvs[Constants.ORDER_SUMMARY_FINAL_PRICE_TV].setText(String.valueOf(Utils.getTotalPrice()));
    }

    private void showUserLocation() {
        this.address.setText(Utils.getUserLocation());
    }

    private void initLocationButton() {
        this.location.setOnClickListener(view -> {showUserLocation(); showInformation();});
    }

    private void initRefreshButton() {
        this.buttons[Constants.ORDER_SUMMARY_REFRESH_BTN_INDEX]
                .setOnClickListener(view -> showInformation());
    }

    private void initFinalizeAndPayButton() {
        this.buttons[Constants.ORDER_SUMMARY_FINALIZE_BTN_INDEX]
                .setOnClickListener(view -> finalizeAndPayAction());
    }

    private void finalizeAndPayAction() {
        if(Utils.isAddressUpdated(address)) {
            Utils.changeActivity(OrderCompleteActivity.class);
        } else {
            Utils.showText("You need to refresh the address with the button", Toast.LENGTH_LONG);
        }
    }

    private void initCancelButton() {
        this.buttons[Constants.ORDER_SUMMARY_CANCEL_BTN_INDEX].setOnClickListener(view -> {
            AppCache.getInstance().resetOrder();
            Utils.changeActivity(RestaurantsActivity.class);
        });
    }
}
