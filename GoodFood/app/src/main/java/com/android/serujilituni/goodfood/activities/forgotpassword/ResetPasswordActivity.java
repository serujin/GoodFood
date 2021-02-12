package com.android.serujilituni.goodfood.activities.forgotpassword;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.android.serujilituni.goodfood.R;
import com.android.serujilituni.goodfood.activities.login.LoginActivity;
import com.android.serujilituni.goodfood.store.AppCache;
import com.android.serujilituni.goodfood.utils.Utils;

public class ResetPasswordActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reset_password);
        AppCache.getInstance().setContext(this);
        new ResetPasswordController(findViewById(R.id.forgot_pass_et),  findViewById(R.id.forgot_pass_btn));
    }

    @Override
    public void onBackPressed() {
        Utils.changeActivity(LoginActivity.class);
    }
}