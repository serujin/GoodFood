package com.android.serujilituni.goodfood.activities.ordercomplete;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.widget.ImageView;

import com.android.serujilituni.goodfood.R;
import com.bumptech.glide.Glide;

public class OrderCompleteActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_complete);
        showGif((ImageView) findViewById(R.id.deliver_guy_gif), getDrawable(R.drawable.deliver_guy));
    }

    private void showGif(ImageView image, Drawable gif) {
        Glide.with(this).load(gif).into(image);
    }
}