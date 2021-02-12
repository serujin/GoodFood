package com.android.serujilituni.goodfood.activities.changedata;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;

import com.android.serujilituni.goodfood.R;
import com.android.serujilituni.goodfood.activities.intermediate.IntermediateActivity;
import com.android.serujilituni.goodfood.store.AppCache;
import com.android.serujilituni.goodfood.utils.Utils;

public class ChangeDataActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_data);
        AppCache.getInstance().setContext(this);
        new ChangeDataController(getButtons(), findViewById(R.id.new_name_et));
    }

    @Override
    public void onBackPressed() {
        Utils.changeActivity(IntermediateActivity.class);
    }

    private Button[] getButtons() {
        return new Button[]{
                findViewById(R.id.change_password_btn),
                findViewById(R.id.change_fullname_btn)
        };
    }
}