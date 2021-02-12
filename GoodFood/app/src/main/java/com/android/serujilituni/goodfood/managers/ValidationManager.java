package com.android.serujilituni.goodfood.managers;

import android.util.Patterns;

import com.android.serujilituni.goodfood.R;

public class ValidationManager {
    public static int validateLogin(String email, String password) {
        if (email.isEmpty()) {
            return R.string.required_email_error;
        }
        if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            return R.string.invalid_email_error;
        }
        if (password.isEmpty()) {
            return R.string.required_password_error;
        }
        if (password.length() < 8) {
            return R.string.incorrect_password_length_error;
        }
        return R.string.no_error;
    }

    public static int validateRegister(String email, String password, String confirmed, String name) {
        if (name.isEmpty()) {
            return R.string.required_name_error;
        }
        if (email.isEmpty()) {
            return R.string.required_email_error;
        }

        if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            return R.string.invalid_email_error;
        }

        if (password.isEmpty()) {
            return R.string.required_password_error;
        }

        if (password.length() < 8) {
            return R.string.incorrect_password_length_error;
        }

        if (!password.equals(confirmed)) {
            return R.string.non_equals_password_error;
        }
        return R.string.no_error;
    }

    public static int validateResetPassword(String email) {
        if (email.isEmpty()) {
            return R.string.required_email_error;
        }
        if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            return R.string.invalid_email_error;
        }
        return R.string.no_error;
    }
}
