package com.android.serujilituni.goodfood.credentials;

import android.widget.Toast;

import androidx.annotation.NonNull;

import com.android.serujilituni.goodfood.R;
import com.android.serujilituni.goodfood.activities.intermediate.IntermediateActivity;
import com.android.serujilituni.goodfood.activities.restaurant.RestaurantsActivity;
import com.android.serujilituni.goodfood.model.User;
import com.android.serujilituni.goodfood.store.DBManager;
import com.android.serujilituni.goodfood.utils.Utils;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;

public class CredentialsManager {

    public static void login(String email, String password) {
        FirebaseAuth.getInstance().signInWithEmailAndPassword(email, password).addOnCompleteListener(task -> {
            if (task.isSuccessful()) {
                if (CredentialsManager.userEmailIsVerified()) {
                    Utils.changeActivity(IntermediateActivity.class);
                } else {
                    CredentialsManager.notifyRequiredVerification();
                }
            } else {
                Utils.showText(Utils.getStringFromID(R.string.credentials_error));
            }
        });
    }

    public static void register(String email, String password, String fullName) {
        FirebaseAuth.getInstance().createUserWithEmailAndPassword(email, password).addOnCompleteListener(task -> {
            if (task.isSuccessful()) {
                DBManager.getInstance().storeUser(getUserUid(), new User(fullName, email), false);
            } else {
                Utils.showText(Utils.getStringFromID(R.string.register_error));
            }
        });
    }

    public static String getUserEmail() {
        return FirebaseAuth.getInstance().getCurrentUser().getEmail();
    }

    public static void resetPassword(String email) {
        FirebaseAuth.getInstance().sendPasswordResetEmail(email).addOnCompleteListener((OnCompleteListener) task -> {
            String msg = Utils.getStringFromID(R.string.reset_error);
            if(task.isSuccessful()) {
                msg = Utils.getStringFromID(R.string.check_reset_email);
            }
            Utils.showText(msg);
        });
    }

    public static String getUserUid() {
        return FirebaseAuth.getInstance().getCurrentUser().getUid();
    }

    private static boolean userEmailIsVerified() {
        return FirebaseAuth.getInstance().getCurrentUser().isEmailVerified();
    }

    private static void notifyRequiredVerification() {
        FirebaseAuth.getInstance().getCurrentUser().sendEmailVerification();
        Utils.showText(Utils.getStringFromID(R.string.check_your_email));
    }
}
