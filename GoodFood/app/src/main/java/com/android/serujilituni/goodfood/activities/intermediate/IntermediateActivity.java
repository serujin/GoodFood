package com.android.serujilituni.goodfood.activities.intermediate;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.android.serujilituni.goodfood.R;
import com.android.serujilituni.goodfood.activities.changedata.ChangeDataActivity;
import com.android.serujilituni.goodfood.activities.login.LoginActivity;
import com.android.serujilituni.goodfood.activities.restaurant.RestaurantsActivity;
import com.android.serujilituni.goodfood.store.AppCache;
import com.android.serujilituni.goodfood.store.DBManager;
import com.android.serujilituni.goodfood.utils.Utils;

public class IntermediateActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_intermediate);
        AppCache.getInstance().setContext(this);
        DBManager.getInstance().updateRestaurants();
        initButtons();
        Utils.updateUserLocation();
    }

    private void initButtons() {
        findViewById(R.id.change_user_data_btn).setOnClickListener(v -> Utils.changeActivity(ChangeDataActivity.class));
        findViewById(R.id.go_to_restaurant_btn).setOnClickListener(v -> Utils.changeActivity(RestaurantsActivity.class));
    }

    @Override
    public void onBackPressed() {
        exitConfirmation(
                getResources().getString(R.string.exit_confirmation),
                getResources().getString(R.string.yes_confirmation),
                getResources().getString(R.string.no_confirmation)
        );
    }

    private void exitConfirmation(String msg, String positive, String negative) {
        new AlertDialog.Builder(AppCache.getInstance().getContext()).setMessage(msg).
                setPositiveButton(positive, (dialogInterface, i) -> {
                    AppCache.getInstance().resetOrder();
                    Utils.changeActivity(LoginActivity.class);
                })
                .setNegativeButton(negative, null).create().show();
    }
}