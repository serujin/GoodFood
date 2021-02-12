package com.android.serujilituni.goodfood.activities.login;

import androidx.appcompat.app.AppCompatActivity;

import android.location.LocationManager;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.TextView;

import com.android.serujilituni.goodfood.R;

import com.android.serujilituni.goodfood.store.AppCache;
import com.android.serujilituni.goodfood.store.DBManager;

public class LoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        AppCache.getInstance().setContext(this);
        new LoginController(getTextViews(), getEditTexts(), findViewById(R.id.login_btn));
    }

    private TextView[] getTextViews() {
        return new TextView[]{
                findViewById(R.id.register_tv),
                findViewById(R.id.forgot_password_tv)
        };
    }

    private EditText[] getEditTexts() {
        return new EditText[]{
                findViewById(R.id.login_user_email),
                findViewById(R.id.login_user_pass)
        };
    }
}