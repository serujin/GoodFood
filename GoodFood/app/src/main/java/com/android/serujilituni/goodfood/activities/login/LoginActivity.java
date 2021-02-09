package com.android.serujilituni.goodfood.activities.login;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.TextView;

import com.android.serujilituni.goodfood.R;
import com.android.serujilituni.goodfood.activities.restaurant.RestaurantsActivity;
import com.android.serujilituni.goodfood.model.Restaurant;
import com.android.serujilituni.goodfood.store.AppCache;
import com.android.serujilituni.goodfood.utils.Utils;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;

public class LoginActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        AppCache.getInstance().setContext(this);
        Utils.changeActivity(RestaurantsActivity.class);
        /**
        new LoginController(getTextViews(), getEditTexts(), findViewById(R.id.login_btn), findViewById(R.id.progress_bar));
         /**
         * Test Data
         List<Restaurant> restaurants = new ArrayList<>();
         List<Plate> plates = new ArrayList<>();
         List<Plate> orderPlates = new ArrayList<>();


         plates.add(new Plate("Plato1","Descripción1",10));
         plates.add(new Plate("Plato2","Descripción2",10.50f));
         plates.add(new Plate("Plato3","Descripción3",20.6f));
         plates.add(new Plate("Plato4","Descripción4",15));
         plates.add(new Plate("Plato5","Descripción5",13));

         restaurants.add(new Restaurant("Restaurante1","direccion1", plates));
         restaurants.add(new Restaurant("Restaurante2", "direccion2", plates));
         restaurants.add(new Restaurant("Restaurante5","direccion1", plates));

         DBManager.getInstance().setRestaurants(restaurants);

         DBManager.getInstance().getRestaurants(sendEvent());

         orderPlates.add(new Plate("Pollo al limón","Pollo,Limón,Harina",6.8f));
         orderPlates.add(new Plate("Ternera en salsa de ostras","Ternera,Salsa de ostras, Salsa de soja",10.8f));
         orderPlates.add(new Plate("Onigiri de salmón","Arroz, salmón",4.8f));

         Order order = new Order("Direccón1",new Restaurant("Restaurante1","direccion1", plates),orderPlates,LocalDateTime.now());

         DBManager.getInstance().storeOrder(mAuth,order);
         **/
    }

    private TextView[] getTextViews() {
        return new TextView[] {
                findViewById(R.id.register_tv),
                findViewById(R.id.forgot_password_tv)
        };
    }

    private EditText[] getEditTexts() {
        return new EditText[] {
                findViewById(R.id.login_user_email),
                findViewById(R.id.login_user_pass)
        };
    }

    protected ValueEventListener sendEvent() {
        return new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.getValue() != null) {

                    for (DataSnapshot child : snapshot.getChildren()) {
                        Restaurant r = child.getValue(Restaurant.class);
                        AppCache.getInstance().addRestaurant(r);
                    }
                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {}
        };
    }

}