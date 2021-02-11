package com.android.serujilituni.goodfood.activities.ordercomplete;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.serujilituni.goodfood.R;
import com.android.serujilituni.goodfood.store.AppCache;
import com.android.serujilituni.goodfood.utils.Utils;
import com.bumptech.glide.Glide;

public class OrderCompleteActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_complete);
        AppCache.getInstance().setContext(this);
        showGif(findViewById(R.id.deliver_guy_gif), getDrawable(R.drawable.deliver_guy));
        ((TextView) findViewById(R.id.time_tv)).setText(String.valueOf(Utils.getOrderTime()));
    }

    private void showGif(ImageView image, Drawable gif) {
        Glide.with(this).load(gif).into(image);
    }

    @Override
    public void onBackPressed() {
        Utils.exitConfirmation(
                getResources().getString(R.string.exit_confirmation),
                getResources().getString(R.string.yes_confirmation),
                getResources().getString(R.string.no_confirmation)
        );
    }
}