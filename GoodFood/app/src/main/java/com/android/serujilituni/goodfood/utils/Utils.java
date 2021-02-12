package com.android.serujilituni.goodfood.utils;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.serujilituni.goodfood.R;
import com.android.serujilituni.goodfood.constants.Constants;
import com.android.serujilituni.goodfood.items.PlateItem;
import com.android.serujilituni.goodfood.items.TemporalPlateItem;
import com.android.serujilituni.goodfood.managers.ValidationManager;
import com.android.serujilituni.goodfood.model.Plate;
import com.android.serujilituni.goodfood.store.AppCache;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;


public class Utils {
    public static int validateLogin(String email, String password) {
        return ValidationManager.validateLogin(email, password);
    }

    public static int validateRegister(String email, String password, String confirmed, String name) {
        return ValidationManager.validateRegister(email, password, confirmed, name);
    }

    public static int validateResetPassword(String email) {
        return ValidationManager.validateResetPassword(email);
    }

    public static float getTotalMoneyOfCurrentOrder() {
        float total = 0f;
        for (TemporalPlateItem item : AppCache.getInstance().getCurrentOrder()) {
            total += item.getPrice() * ((float) item.getQuantity());
        }
        return total;
    }

    public static List<PlateItem> getPlatesForMenuActivity(int restaurant) {
        List<Plate> plates = AppCache.getInstance().getRestaurants().get(restaurant).getPlates();
        List<PlateItem> items = new ArrayList<>();
        for (int i = 0; i < plates.size(); i++) {
            if (restaurant == AppCache.getInstance().getCurrentRestaurant()) {
                int quantity = getPlateInOrderQuantity(plates.get(i));
                if (quantity != -1) {
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

    private static int getPlateInOrderQuantity(Plate plate) {
        for (TemporalPlateItem item : AppCache.getInstance().getCurrentOrder()) {
            if (item.getName().equals(plate.getName())) {
                return item.getQuantity();
            }
        }
        return -1;
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

    public static void showText(String text) {
        Toast.makeText(AppCache.getInstance().getContext(), text, Toast.LENGTH_LONG).show();
    }

    public static String getStringFromID(int id) {
        return AppCache.getInstance().getContext().getResources().getString(id);
    }

    public static String getStringFromEditText(EditText et) {
        return et.getText().toString();
    }

    public static float getDeliverPrice() {
        Random r = new Random();
        return ((float) r.nextInt(2)) + r.nextFloat();
    }

}
