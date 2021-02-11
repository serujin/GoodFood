package com.android.serujilituni.goodfood.activities.login;

import androidx.appcompat.app.AppCompatActivity;

import android.location.LocationManager;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.TextView;

import com.android.serujilituni.goodfood.R;

import com.android.serujilituni.goodfood.store.AppCache;
import com.android.serujilituni.goodfood.store.DBManager;

public class LoginActivity extends AppCompatActivity {

    private LocationManager lm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        AppCache.getInstance().setContext(this);
        DBManager.getInstance().updateRestaurants();
        new LoginController(getTextViews(), getEditTexts(), findViewById(R.id.login_btn), findViewById(R.id.progress_bar));
        /*** Test Data

         /**

         List<Restaurant> restaurants = new ArrayList<>();

         restaurants.add(new Restaurant("McDonald's", new ArrayList<Double>(Arrays.asList(40.417740,-3.70918)), new ArrayList<Plate>(Arrays.asList(
         new Plate("CBO", 5.80f),
         new Plate("Grand McExtreme Double Bacon", 7.05F),
         new Plate("Big Mac", 4.95f),
         new Plate("McPollo", 4.65f),
         new Plate("Chicken & Cheese", 1.55f)
         ))));
         restaurants.add(new Restaurant("Doner Kebab", new ArrayList<Double>(Arrays.asList(40.402860,-3.69607)), new ArrayList<Plate>(Arrays.asList(
         new Plate("Dürüm Kebab", 5.50f),
         new Plate("Ensalada Kebab", 5.50f),
         new Plate("Döner Kebab", 4.00f),
         new Plate("Patatas fritas", 4.50f),
         new Plate("Fingers de pollo", 5.15f)
         ))));
         restaurants.add(new Restaurant("Goiko Grill", new ArrayList<Double>(Arrays.asList(40.443500,-3.71400)), new ArrayList<Plate>(Arrays.asList(
         new Plate("La Kiki", 12.40f),
         new Plate("Yankee", 15.90f),
         new Plate("Pigma", 12.40f),
         new Plate("Chipotle", 11.90f),
         new Plate("Teques", 8.90f)
         ))));
         restaurants.add(new Restaurant("Taco Bell", new ArrayList<Double>(Arrays.asList(40.444520,-3.58282)), new ArrayList<Plate>(Arrays.asList(
         new Plate("Burrito GSB", 5.00f),
         new Plate("Chicken burrito", 5.00f),
         new Plate("Quesarito", 5.00f),
         new Plate("Quesadilla", 4.80f),
         new Plate("Crunchywrap Supreme", 4.90f)
         ))));
         restaurants.add(new Restaurant("100 montaditos", new ArrayList<Double>(Arrays.asList(40.395563,-3.66371)), new ArrayList<Plate>(Arrays.asList(
         new Plate("Chori jam", 1.30f),
         new Plate("Pulled pork", 1.30f),
         new Plate("Pollo con ali-oli", 1.30f),
         new Plate("Queso de cabra y pesto", 1.30f),
         new Plate("Atún, lechuga y mayonesa", 1.60f)
         ))));
         restaurants.add(new Restaurant("Telepizza", new ArrayList<Double>(Arrays.asList(40.436026,-3.64481)), new ArrayList<Plate>(Arrays.asList(
         new Plate("Barbacoa con cuarto de libra", 16.95f),
         new Plate("Carnívora Gourmet", 16.95f),
         new Plate("Vulcano nachos", 16.95f),
         new Plate("Carbonara", 14.95f),
         new Plate("Crispy", 14.95f)
         ))));
         restaurants.add(new Restaurant("Foster's Hollywood", new ArrayList<Double>(Arrays.asList(40.445225,-3.67725)), new ArrayList<Plate>(Arrays.asList(
         new Plate("Foster's Caesar salad", 12.25f),
         new Plate("Director's choice", 10.95f),
         new Plate("Pulled pork burguer", 12.95f),
         new Plate("Smoked burguer", 15.15f),
         new Plate("Black Label Ribs", 20.15f)
         ))));
         restaurants.add(new Restaurant("Udon", new ArrayList<Double>(Arrays.asList(40.340145,-3.73318)), new ArrayList<Plate>(Arrays.asList(
         new Plate("Osaka gyozas", 5.90f),
         new Plate("Curry Soup", 11.45f),
         new Plate("Spicy thai Udon", 11.90f),
         new Plate("Chicken yaki Udon", 9.55f),
         new Plate("Nabeyaki Udon", 11.45f)
         ))));
         restaurants.add(new Restaurant("La Tagliatella", new ArrayList<Double>(Arrays.asList(40.393873,-3.79677)), new ArrayList<Plate>(Arrays.asList(
         new Plate("Foie e arugula", 13.25f),
         new Plate("Canneloni", 14.95f),
         new Plate("Insalata Affumicatta", 13.90f),
         new Plate("Insalata Tagliatella", 13.90f),
         new Plate("Pepperoni piccante", 12.30f)
         ))));
         restaurants.add(new Restaurant("KFC", new ArrayList<Double>(Arrays.asList(40.453317,-3.70282)), new ArrayList<Plate>(Arrays.asList(
         new Plate("La infame", 6.99f),
         new Plate("BBC Wrap", 5.19f),
         new Plate("Twister Wrap", 4.79f),
         new Plate("Double Krunch", 2.99f),
         new Plate("Menú Bucket 6 piezas para 2", 16.99f)
         ))));
         DBManager.getInstance().setRestaurants(restaurants);
         */
    }

    private TextView[] getTextViews() {
        return new TextView[]{
                findViewById(R.id.register_tv),
                findViewById(R.id.forgot_password_tv)
        };
    }

    private EditText[] getEditTexts() {
        return new EditText[]{
                findViewById(R.id.login_user_email),
                findViewById(R.id.login_user_pass)
        };
    }
}