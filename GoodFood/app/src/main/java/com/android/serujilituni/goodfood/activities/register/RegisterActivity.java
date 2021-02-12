package com.android.serujilituni.goodfood.activities.register;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.EditText;

import com.android.serujilituni.goodfood.R;
import com.android.serujilituni.goodfood.activities.login.LoginActivity;
import com.android.serujilituni.goodfood.store.AppCache;
import com.android.serujilituni.goodfood.utils.Utils;

public class RegisterActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        AppCache.getInstance().setContext(this);
        new RegisterController(getEditTexts(), findViewById(R.id.register_btn), findViewById(R.id.progress_bar));
    }

    @Override
    public void onBackPressed() {
        Utils.changeActivity(LoginActivity.class);
    }

    private EditText[] getEditTexts() {
        return new EditText[] {
                findViewById(R.id.register_user_email),
                findViewById(R.id.register_user_password),
                findViewById(R.id.register_user_pass_confirm),
                findViewById(R.id.register_user_fullname)
        };
    }

}