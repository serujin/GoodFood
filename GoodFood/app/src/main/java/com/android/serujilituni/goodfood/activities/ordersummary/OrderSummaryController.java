package com.android.serujilituni.goodfood.activities.ordersummary;

import android.widget.Button;
import android.widget.TextView;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.serujilituni.goodfood.activities.ordercomplete.OrderCompleteActivity;
import com.android.serujilituni.goodfood.activities.restaurant.RestaurantsActivity;
import com.android.serujilituni.goodfood.adapters.TemporalPlateAdapter;
import com.android.serujilituni.goodfood.constants.Constants;
import com.android.serujilituni.goodfood.credentials.CredentialsManager;
import com.android.serujilituni.goodfood.items.TemporalPlateItem;
import com.android.serujilituni.goodfood.model.Order;
import com.android.serujilituni.goodfood.model.Plate;
import com.android.serujilituni.goodfood.store.AppCache;
import com.android.serujilituni.goodfood.store.DBManager;
import com.android.serujilituni.goodfood.utils.Utils;

import java.text.DecimalFormat;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class OrderSummaryController {
    private Button[] buttons;
    private RecyclerView orderRv;
    private TextView[] tvs;

    public OrderSummaryController(Button[] buttons, RecyclerView orderRv, TextView[] tvs) {
        this.buttons = buttons;
        this.orderRv = orderRv;
        this.tvs = tvs;
        initComponents();
        showInformation();
    }

    private void initComponents() {
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

    private void showInformation() {
        float distance = Utils.getOrderDistance();
        float platePrice = Utils.getTotalMoneyOfCurrentOrder();
        float deliverPrice = Utils.getDeliverPrice();
        float total = platePrice + deliverPrice;
        DecimalFormat formatter = AppCache.getInstance().getFormatter();
        this.tvs[Constants.ORDER_SUMMARY_ADDRESS_TV].setText(AppCache.getInstance().getUserAddress());
        this.tvs[Constants.ORDER_SUMMARY_DISTANCE_TV].setText(formatter.format(distance));
        this.tvs[Constants.ORDER_SUMMARY_PRICE_TV].setText(formatter.format(platePrice));
        this.tvs[Constants.ORDER_SUMMARY_DELIVER_TV].setText(formatter.format(deliverPrice));
        this.tvs[Constants.ORDER_SUMMARY_FINAL_PRICE_TV].setText(formatter.format(total));
    }

    private void initFinalizeAndPayButton() {
        this.buttons[Constants.ORDER_SUMMARY_FINALIZE_BTN_INDEX]
                .setOnClickListener(view -> {
                    DBManager.getInstance().storeOrder(CredentialsManager.getUserUid(), getOrder());
                    Utils.changeActivity(OrderCompleteActivity.class);
                });
    }

    private Order getOrder() {
        List<TemporalPlateItem> plates = AppCache.getInstance().getCurrentOrder();
        List<Plate> plateToSave = new ArrayList<>();
        for(TemporalPlateItem item : plates) {
            plateToSave.add(new Plate(item.getName(), item.getPrice()));
        }
        return new Order(
                this.tvs[Constants.ORDER_SUMMARY_ADDRESS_TV].getText().toString(),
                AppCache.getInstance().getRestaurants().get(AppCache.getInstance().getCurrentRestaurant()).getName(),
                plateToSave,
                LocalDateTime.now()
        );
    }

    private void initCancelButton() {
        this.buttons[Constants.ORDER_SUMMARY_CANCEL_BTN_INDEX].setOnClickListener(view -> {
            AppCache.getInstance().resetOrder();
            Utils.changeActivity(RestaurantsActivity.class);
        });
    }
}