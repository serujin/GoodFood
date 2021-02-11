package com.android.serujilituni.goodfood.utils;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.drawable.Drawable;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.util.Patterns;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import com.android.serujilituni.goodfood.R;
import com.android.serujilituni.goodfood.activities.login.LoginActivity;
import com.android.serujilituni.goodfood.activities.menu.MenuActivity;
import com.android.serujilituni.goodfood.activities.ordercomplete.OrderCompleteActivity;
import com.android.serujilituni.goodfood.activities.ordersummary.OrderSummaryActivity;
import com.android.serujilituni.goodfood.activities.restaurant.RestaurantsActivity;
import com.android.serujilituni.goodfood.constants.Constants;
import com.android.serujilituni.goodfood.items.PlateItem;
import com.android.serujilituni.goodfood.items.RestaurantItem;
import com.android.serujilituni.goodfood.items.TemporalPlateItem;
import com.android.serujilituni.goodfood.model.Plate;
import com.android.serujilituni.goodfood.model.Restaurant;
import com.android.serujilituni.goodfood.store.AppCache;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Random;

import static android.content.Context.LOCATION_SERVICE;

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

    public static void registrationAction(boolean wasSuccessful) {
        if (wasSuccessful) {
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
        if (wasSuccessful) {
            Utils.showText(Utils.getStringFromID(R.string.successfully_order), Toast.LENGTH_LONG);
            Utils.changeActivity(OrderCompleteActivity.class);
        } else {
            Utils.showText(Utils.getStringFromID(R.string.order_error), Toast.LENGTH_LONG);
        }
    }

    public static float getTotalMoneyOfCurrentOrder() {
        float total = 0f;
        for (TemporalPlateItem item : AppCache.getInstance().getCurrentOrder()) {
            total += item.getPrice() * ((float) item.getQuantity());
        }
        return total;
    }

    public static void changeToRestaurantMenu(int index, String msg, String positive, String negative) {
        if (AppCache.getInstance().getCurrentOrder().size() != 0 && index != AppCache.getInstance().getCurrentRestaurant()) {
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

    public static List<RestaurantItem> getRestaurantsFromCache() {
        List<Restaurant> restaurants = AppCache.getInstance().getRestaurants();
        List<RestaurantItem> items = new ArrayList<>();
        for (int i = 0; i < restaurants.size(); i++) {
            items.add(new RestaurantItem(Constants.RESTAURANTS_DRAWABLES[i], restaurants.get(i).getName()));
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

    private static void loadRestaurant(int index) {
        AppCache.getInstance().setCurrentRestaurant(index);
        Intent intent = new Intent(AppCache.getInstance().getContext(), MenuActivity.class);
        intent.putExtra(Constants.MENU_EXTRA_INTENT, index);
        AppCache.getInstance().getContext().startActivity(intent);
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

    public static void updateUserLocation() {
        Context context = AppCache.getInstance().getContext();
        if (ContextCompat.checkSelfPermission(context, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions((RestaurantsActivity) context, new String[]{
                    Manifest.permission.ACCESS_FINE_LOCATION
            }, 100);
        }
        LocationManager lm = (LocationManager) context.getSystemService(LOCATION_SERVICE);
        lm.requestLocationUpdates(LocationManager.GPS_PROVIDER, 5000, 5, location -> {
            Geocoder geocoder = new Geocoder(context, Locale.getDefault());
            try {
                AppCache.getInstance().setLocation(location.getLatitude(), location.getLongitude());
                AppCache.getInstance().setUserAddress(geocoder.getFromLocation(location.getLatitude(), location.getLongitude(), 1).get(0).getAddressLine(0));
                AppCache.getInstance().locationSetted = true;
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }

    public static float getOrderDistance() {
        Location restaurant = new Location("");
        Location destination = new Location("");
        List<Double> restaurantData = AppCache.getInstance().getRestaurants().get(AppCache.getInstance().getCurrentRestaurant()).getAddress();
        restaurant.setLatitude(restaurantData.get(0));
        restaurant.setLongitude(restaurantData.get(1));
        Double[] data = AppCache.getInstance().getLocation();
        destination.setLatitude(data[0]);
        destination.setLongitude(data[1]);
        return restaurant.distanceTo(destination) / 1000;
    }

    public static int getOrderTime() {
        int time = 0;
        int distance = (int) Math.ceil(Utils.getOrderDistance());
        Random r = new Random();
        List<TemporalPlateItem> plates = AppCache.getInstance().getCurrentOrder();
        for(TemporalPlateItem plate : plates) {
            time += plate.getQuantity() * (r.nextInt(9) + 1);
        }
        time += distance * (r.nextInt(5) + 1);
        return time;
    }

    public static float getDeliverPrice() {
        Random r = new Random();
        return ((float) r.nextInt(2)) + r.nextFloat();
    }
}
