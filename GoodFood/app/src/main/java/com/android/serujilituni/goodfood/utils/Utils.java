package com.android.serujilituni.goodfood.utils;

import android.content.Context;
import android.content.Intent;
import android.util.Patterns;
import android.widget.EditText;
import android.widget.Toast;

import com.android.serujilituni.goodfood.R;
import com.android.serujilituni.goodfood.activities.login.LoginActivity;
import com.android.serujilituni.goodfood.store.AppCache;

public class Utils {
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
        if(name.isEmpty()) {
            return R.string.required_name_error;
        }
        if(email.isEmpty()) {
            return R.string.required_email_error;
        }

        if(!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            return R.string.invalid_email_error;
        }

        if(password.isEmpty()) {
            return R.string.required_password_error;
        }

        if(password.length() < 8){
            return R.string.incorrect_password_length_error;
        }

        if(!password.equals(confirmed)) {
            return R.string.non_equals_password_error;
        }
        return R.string.no_error;
    }

    public static void registrationAction(boolean wasSuccessful) {
        if(wasSuccessful) {
            Utils.showText(Utils.getStringFromID(R.string.successfully_registered), Toast.LENGTH_LONG);
            Utils.changeActivity(LoginActivity.class);
        } else {
            Utils.showText(Utils.getStringFromID(R.string.register_error), Toast.LENGTH_LONG);
        }
    }

    public static boolean isAnErrorState(int state) {
        return state != R.string.no_error;
    }

    public static void changeActivity(Class toChange) {
        Context context = AppCache.getInstance().getContext();
        context.startActivity(new Intent(context, toChange));
    }

    public static void showText(String text, int duration) {
        Toast.makeText(AppCache.getInstance().getContext(), text, duration).show();
    }

    public static String getStringFromID(int id) {
        return AppCache.getInstance().getContext().getResources().getString(id);
    }

    public static String getStringFromEditText(EditText et) {
        return et.getText().toString();
    }
}
