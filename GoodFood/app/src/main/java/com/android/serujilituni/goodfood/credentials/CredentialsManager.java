package com.android.serujilituni.goodfood.credentials;

import android.widget.Toast;

import androidx.annotation.NonNull;

import com.android.serujilituni.goodfood.R;
import com.android.serujilituni.goodfood.activities.restaurant.RestaurantsActivity;
import com.android.serujilituni.goodfood.model.User;
import com.android.serujilituni.goodfood.store.DBManager;
import com.android.serujilituni.goodfood.utils.Utils;
import com.google.firebase.auth.FirebaseAuth;

public class CredentialsManager {

    public static void login(String email, String password) {
        FirebaseAuth.getInstance().signInWithEmailAndPassword(email, password).addOnCompleteListener(task -> {
            if (task.isSuccessful()) {
                if (CredentialsManager.userEmailIsVerified()) {
                    Utils.changeActivity(RestaurantsActivity.class);
                } else {
                    CredentialsManager.notifyRequiredVerification();
                }
            } else {
                Utils.showText(Utils.getStringFromID(R.string.credentials_error), Toast.LENGTH_LONG);
            }
        });
    }

    public static void register(String email, String password, String fullName) {
        FirebaseAuth.getInstance().createUserWithEmailAndPassword(email, password).addOnCompleteListener(task -> {
            if (task.isSuccessful()) {
                DBManager.getInstance().storeUser(getUserUid(), new User(fullName, email));
            } else {
                Utils.showText(Utils.getStringFromID(R.string.register_error), Toast.LENGTH_LONG);
            }
        });
    }

    private static String getUserUid() {
        return FirebaseAuth.getInstance().getCurrentUser().getUid();
    }

    private static boolean userEmailIsVerified() {
        return FirebaseAuth.getInstance().getCurrentUser().isEmailVerified();
    }

    private static void notifyRequiredVerification() {
        FirebaseAuth.getInstance().getCurrentUser().sendEmailVerification();
        Utils.showText(Utils.getStringFromID(R.string.check_your_email), Toast.LENGTH_LONG);
    }
}
