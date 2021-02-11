package com.android.serujilituni.goodfood.utils;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.util.Patterns;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;

import com.android.serujilituni.goodfood.R;
import com.android.serujilituni.goodfood.activities.login.LoginActivity;
import com.android.serujilituni.goodfood.activities.menu.MenuActivity;
import com.android.serujilituni.goodfood.activities.ordercomplete.OrderCompleteActivity;
import com.android.serujilituni.goodfood.constants.Constants;
import com.android.serujilituni.goodfood.credentials.CredentialsManager;
import com.android.serujilituni.goodfood.items.PlateItem;
import com.android.serujilituni.goodfood.items.RestaurantItem;
import com.android.serujilituni.goodfood.items.TemporalPlateItem;
import com.android.serujilituni.goodfood.model.Plate;
import com.android.serujilituni.goodfood.model.Restaurant;
import com.android.serujilituni.goodfood.store.AppCache;

import java.util.ArrayList;
import java.util.List;

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

    public static int validateResetPassword(String email) {
        if (email.isEmpty()) {
            return R.string.required_email_error;
        }
        if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            return R.string.invalid_email_error;
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

    public static void exitConfirmation(String msg, String positive, String negative) {
        new AlertDialog.Builder(AppCache.getInstance().getContext()).setMessage(msg).
                setPositiveButton(positive, (dialogInterface, i) -> {
                    AppCache.getInstance().resetOrder();
                    Utils.changeActivity(LoginActivity.class);
                })
        .setNegativeButton(negative, null).create().show();
    }

    public static void storeOrderAction(boolean wasSuccessful) {
        if(wasSuccessful) {
            Utils.showText(Utils.getStringFromID(R.string.successfully_order), Toast.LENGTH_LONG);
            Utils.changeActivity(OrderCompleteActivity.class);
        } else {
            Utils.showText(Utils.getStringFromID(R.string.order_error), Toast.LENGTH_LONG);
        }
    }

    public static float getTotalMoneyOfCurrentOrder() {
        float total = 0f;
        for(TemporalPlateItem item : AppCache.getInstance().getCurrentOrder()) {
            total += item.getPrice() * ((float) item.getQuantity());
        }
        return total;
    }

    public static void changeToRestaurantMenu(int index, String msg, String positive, String negative) {
        if(AppCache.getInstance().getCurrentOrder().size() != 0 && index != AppCache.getInstance().getCurrentRestaurant()) {
            new AlertDialog.Builder(AppCache.getInstance().getContext()).setMessage(msg).
                    setPositiveButton(positive, (dialogInterface, i) -> {
                        AppCache.getInstance().resetOrder();
                        Utils.loadRestaurant(index);
                    })
                    .setNegativeButton(negative, null).create().show();
        } else {
            Utils.loadRestaurant(index);
        }
    }

    public static List<PlateItem> getPlatesForMenuActivity(int restaurant) {
        List<Plate> plates = AppCache.getInstance().getRestaurants().get(restaurant).getPlates();
        List<PlateItem> items = new ArrayList<>();
        for (int i = 0; i < plates.size(); i++) {
            if(restaurant == AppCache.getInstance().getCurrentRestaurant()) {
                int quantity = getPlateInOrderQuantity(plates.get(i));
                if(quantity != -1) {
                    items.add(new PlateItem(Utils.getDrawableFromID(Constants.PLATES_DRAWABLES[restaurant][i]), plates.get(i).getName(), plates.get(i).getPrice(), quantity));
                } else {
                    items.add(new PlateItem(Utils.getDrawableFromID(Constants.PLATES_DRAWABLES[restaurant][i]), plates.get(i).getName(), plates.get(i).getPrice()));
                }
            } else {
                items.add(new PlateItem(Utils.getDrawableFromID(Constants.PLATES_DRAWABLES[restaurant][i]), plates.get(i).getName(), plates.get(i).getPrice()));
            }
        }
        return items;
    }

    public static List<RestaurantItem> getRestaurantsFromCache() {
        List<Restaurant> restaurants = AppCache.getInstance().getRestaurants();
        List<RestaurantItem> items = new ArrayList<>();
        for (int i = 0; i < restaurants.size(); i++) {
            items.add(new RestaurantItem(Constants.RESTAURANTS_DRAWABLES[i], restaurants.get(i).getName()));
        }
        return items;
    }

    private static int getPlateInOrderQuantity(Plate plate) {
        for(TemporalPlateItem item : AppCache.getInstance().getCurrentOrder()) {
            if(item.getName().equals(plate.getName())) {
                return item.getQuantity();
            }
        }
        return -1;
    }

    private static void loadRestaurant(int index) {
        AppCache.getInstance().setCurrentRestaurant(index);
        Intent intent = new Intent(AppCache.getInstance().getContext(), MenuActivity.class);
        intent.putExtra(Constants.MENU_EXTRA_INTENT, index);
        AppCache.getInstance().getContext().startActivity(intent);
    }

    public static int[] getIntArrayFromID(int id) {
        return AppCache.getInstance().getContext().getResources().getIntArray(id);
    }

    public static String[] getStringArrayFromID(int id) {
        return AppCache.getInstance().getContext().getResources().getStringArray(id);
    }

    public static Drawable getDrawableFromID(int id) {
        return AppCache.getInstance().getContext().getResources().getDrawable(id);
    }

    public static void setErrorOnTextView(TextView tv, int errorcode) {
        tv.setError(Utils.getStringFromID(errorcode));
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
