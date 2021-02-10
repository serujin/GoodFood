package com.android.serujilituni.goodfood.activities.forgotpassword;

import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.serujilituni.goodfood.R;
import com.android.serujilituni.goodfood.credentials.CredentialsManager;
import com.android.serujilituni.goodfood.utils.Utils;

public class ResetPasswordController {
    private EditText mailEt;
    private Button resetBtn;

    public ResetPasswordController(EditText mailEt, Button resetBtn) {
        this.mailEt = mailEt;
        this.resetBtn = resetBtn;
        initResetPasswordButton();
    }

    private void initResetPasswordButton() {
        this.resetBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                resetAction();
            }
        });
    }

    private void resetAction() {
        String email = Utils.getStringFromEditText(this.mailEt);
        if(validateUserData(email)) {
            CredentialsManager.resetPassword(email);
        }
    }

    private boolean validateUserData(String email) {
        int validationState = Utils.validateResetPassword(email);
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
                Utils.setErrorOnTextView(this.mailEt, R.string.required_email_error);
                this.mailEt.requestFocus();
                break;
            case R.string.invalid_email_error:
                Utils.setErrorOnTextView(this.mailEt, R.string.invalid_email_error);
                this.mailEt.requestFocus();
                break;
        }
    }
}
