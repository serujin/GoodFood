package com.android.serujilituni.goodfood.activities.register;

import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.android.serujilituni.goodfood.R;
import com.android.serujilituni.goodfood.constants.Constants;
import com.android.serujilituni.goodfood.credentials.CredentialsManager;
import com.android.serujilituni.goodfood.utils.Utils;

public class RegisterController {
    private EditText[] ets;
    private Button registerBtn;
    private ProgressBar pb;

    public RegisterController(EditText[] ets, Button registerBtn, ProgressBar pb) {
        this.ets = ets;
        this.registerBtn = registerBtn;
        this.pb = pb;
        initComponents();
    }

    private void initComponents() {
        initRegisterButton();
    }

    private void initRegisterButton() {
        this.registerBtn.setOnClickListener(view -> registerAction());
    }

    private void registerAction() {
        String email = Utils.getStringFromEditText(this.ets[Constants.REGISTER_EMAIL_ET_INDEX]).trim();
        String password = Utils.getStringFromEditText(this.ets[Constants.REGISTER_PASSWORD_ET_INDEX]).trim();
        String confirmed = Utils.getStringFromEditText(this.ets[Constants.REGISTER_CONFIRMED_PASSWORD_ET_INDEX]).trim();
        String name = Utils.getStringFromEditText(this.ets[Constants.REGISTER_NAME_ET_INDEX]).trim();
        if(validateUserData(email, password, confirmed, name)) {
            pb.setVisibility(View.VISIBLE);
            CredentialsManager.register(email, password, name);
            pb.setVisibility(View.GONE);
        }
    }

    private boolean validateUserData(String email, String password, String confirmed, String name) {
        int validationState = Utils.validateRegister(email, password, confirmed, name);
        if(Utils.isAnErrorState(validationState)) {
            Utils.showText(Utils.getStringFromID(validationState));
            showError(validationState);
            return false;
        }
        return true;
    }

    private void showError(int errorCode) {
        switch (errorCode) {
            case R.string.required_name_error:
                Utils.setErrorOnTextView(this.ets[Constants.REGISTER_NAME_ET_INDEX], R.string.required_name_error);
                this.ets[Constants.REGISTER_NAME_ET_INDEX].requestFocus();
                break;
            case R.string.required_email_error:
                Utils.setErrorOnTextView(this.ets[Constants.REGISTER_EMAIL_ET_INDEX], R.string.required_email_error);
                this.ets[Constants.REGISTER_EMAIL_ET_INDEX].requestFocus();
                break;
            case R.string.invalid_email_error:
                Utils.setErrorOnTextView(this.ets[Constants.REGISTER_EMAIL_ET_INDEX], R.string.invalid_email_error);
                this.ets[Constants.REGISTER_EMAIL_ET_INDEX].requestFocus();
                break;
            case R.string.required_password_error:
                Utils.setErrorOnTextView(this.ets[Constants.REGISTER_PASSWORD_ET_INDEX], R.string.required_password_error);
                this.ets[Constants.REGISTER_PASSWORD_ET_INDEX].requestFocus();
                break;
            case  R.string.incorrect_password_length_error:
                Utils.setErrorOnTextView(this.ets[Constants.REGISTER_PASSWORD_ET_INDEX],R.string.incorrect_password_length_error);
                this.ets[Constants.REGISTER_PASSWORD_ET_INDEX].requestFocus();
                break;
            case R.string.non_equals_password_error:
                Utils.setErrorOnTextView(this.ets[Constants.REGISTER_CONFIRMED_PASSWORD_ET_INDEX], R.string.non_equals_password_error);
                this.ets[Constants.REGISTER_CONFIRMED_PASSWORD_ET_INDEX].requestFocus();
                break;
        }
    }
}
