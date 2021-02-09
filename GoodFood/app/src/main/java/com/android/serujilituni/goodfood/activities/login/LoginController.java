package com.android.serujilituni.goodfood.activities.login;

import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.android.serujilituni.goodfood.R;
import com.android.serujilituni.goodfood.activities.forgotpassword.ResetPasswordActivity;
import com.android.serujilituni.goodfood.activities.register.RegisterActivity;
import com.android.serujilituni.goodfood.constants.Constants;
import com.android.serujilituni.goodfood.credentials.CredentialsManager;
import com.android.serujilituni.goodfood.utils.Utils;

public class LoginController {
    private TextView[] tvs;
    private EditText[] ets;
    private Button loginBtn;
    private ProgressBar pb;

    public LoginController(TextView[] tvs, EditText[] ets, Button loginBtn, ProgressBar pb) {
        this.tvs = tvs;
        this.ets = ets;
        this.loginBtn = loginBtn;
        this.pb = pb;
        initComponents();
    }

    private void initComponents() {
        initRegister();
        initForgotPassword();
        initLoginButton();
    }

    private void initRegister() {
        this.tvs[Constants.LOGIN_REGISTER_TV_INDEX].
                setOnClickListener(view -> Utils.changeActivity(RegisterActivity.class));
    }

    private void initForgotPassword() {
        this.tvs[Constants.LOGIN_PASSWORD_TV_INDEX].
                setOnClickListener(view -> Utils.changeActivity(ResetPasswordActivity.class));
    }

    private void initLoginButton() {
        this.loginBtn.setOnClickListener(view -> loginAction());
    }

    private void loginAction() {
        String email = Utils.getStringFromEditText(this.ets[Constants.LOGIN_EMAIL_ET_INDEX]).trim();
        String password = Utils.getStringFromEditText(this.ets[Constants.LOGIN_PASSWORD_ET_INDEX]).trim();
        validateUserData(email, password);
        this.ets[Constants.LOGIN_EMAIL_ET_INDEX].requestFocus();
        pb.setVisibility(View.VISIBLE);
        CredentialsManager.login(email, password);
        pb.setVisibility(View.GONE);
    }

    private void validateUserData(String email, String password) {
        int validationState = Utils.validateLogin(email, password);
        if(Utils.isAnErrorState(validationState)) {
            Utils.showText(Utils.getStringFromID(validationState), Toast.LENGTH_LONG);
        }
    }


}
