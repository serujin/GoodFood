package com.android.serujilituni.goodfood.activities.login;

import android.os.Handler;
import android.os.Looper;
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

    public LoginController(TextView[] tvs, EditText[] ets, Button loginBtn, ProgressBar pb) {
        this.tvs = tvs;
        this.ets = ets;
        this.loginBtn = loginBtn;
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
        if(validateUserData(email, password)) {
            new Handler(Looper.getMainLooper()).postDelayed(new Runnable() {
                @Override
                public void run() {
                    CredentialsManager.login(email, password);
                }
            }, 500);
        }
    }


    private boolean validateUserData(String email, String password) {
        int validationState = Utils.validateLogin(email, password);
        if(Utils.isAnErrorState(validationState)) {
            Utils.showText(Utils.getStringFromID(validationState), Toast.LENGTH_LONG);
            showError(validationState);
            return false;
        }
        return true;
    }

    private void showError(int errorCode) {
        switch (errorCode) {
            case R.string.required_email_error:
                Utils.setErrorOnTextView(this.ets[Constants.LOGIN_EMAIL_ET_INDEX], R.string.required_email_error);
                this.ets[Constants.LOGIN_EMAIL_ET_INDEX].requestFocus();
                break;
            case R.string.invalid_email_error:
                Utils.setErrorOnTextView(this.ets[Constants.LOGIN_EMAIL_ET_INDEX], R.string.invalid_email_error);
                this.ets[Constants.LOGIN_EMAIL_ET_INDEX].requestFocus();
                break;
            case R.string.required_password_error:
                Utils.setErrorOnTextView(this.ets[Constants.LOGIN_PASSWORD_ET_INDEX], R.string.required_password_error);
                this.ets[Constants.LOGIN_PASSWORD_ET_INDEX].requestFocus();
                break;
            case  R.string.incorrect_password_length_error:
                Utils.setErrorOnTextView(this.ets[Constants.LOGIN_PASSWORD_ET_INDEX],R.string.incorrect_password_length_error);
                this.ets[Constants.LOGIN_PASSWORD_ET_INDEX].requestFocus();
                break;
        }
    }
}
