package com.android.serujilituni.goodfood;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Toast;

import com.android.serujilituni.goodfood.model.Order;
import com.android.serujilituni.goodfood.model.Plate;
import com.android.serujilituni.goodfood.model.Restaurant;
import com.android.serujilituni.goodfood.store.AppCache;
import com.android.serujilituni.goodfood.store.DBManager;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class MainActivity extends AppCompatActivity {

    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

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

        mAuth = FirebaseAuth.getInstance();

        DBManager.getInstance().storeOrder(mAuth,order);


    }

    protected ValueEventListener sendEvent(){

        return new ValueEventListener() {

            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.getValue()!=null) {

                    for(DataSnapshot child : snapshot.getChildren()) {
                        Restaurant r = child.getValue(Restaurant.class);
                        AppCache.getInstance().addRestaurant(r);
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error){

            }
        };
    }
    @Override
    public void onStart() {
        super.onStart();
        // Check if user is signed in (non-null) and update UI accordingly.
        FirebaseUser currentUser = mAuth.getCurrentUser();
        updateUI(currentUser);
    }

    private void updateUI(FirebaseUser user) {
        if (user != null) {
            Toast.makeText(this, "User ID: "+ user.getUid(), Toast.LENGTH_LONG).show();
        } else {
            Toast.makeText(this, "Error: sign in failed.", Toast.LENGTH_LONG).show();
        }
    }

}
