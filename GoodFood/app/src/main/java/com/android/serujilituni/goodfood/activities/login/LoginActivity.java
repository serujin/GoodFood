package com.android.serujilituni.goodfood.activities.login;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.android.serujilituni.goodfood.R;
import com.android.serujilituni.goodfood.activities.forgotpassword.ResetPasswordActivity;
import com.android.serujilituni.goodfood.activities.register.RegisterActivity;
import com.android.serujilituni.goodfood.activities.restaurant.RestaurantsActivity;
import com.android.serujilituni.goodfood.model.Restaurant;
import com.android.serujilituni.goodfood.store.AppCache;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {

    private TextView registerTv;
    private TextView forgotTv;
    private EditText emailEt;
    private EditText passwordEt;
    private Button loginBtn;
    private FirebaseAuth mAuth;
    private ProgressBar pb;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        //Test Data
        /**
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
        registerTv = (TextView) findViewById(R.id.register_tv);
        forgotTv = (TextView) findViewById(R.id.forgot_password_tv);
        mAuth = FirebaseAuth.getInstance();

        loginBtn = (Button) findViewById(R.id.login_btn);

        emailEt = (EditText) findViewById(R.id.login_user_email);
        passwordEt = (EditText) findViewById(R.id.login_user_pass);

        pb = (ProgressBar) findViewById(R.id.progress_bar);

        pb.setVisibility(View.GONE);
        loginBtn.setOnClickListener(this);
        registerTv.setOnClickListener(this);
        forgotTv.setOnClickListener(this);

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

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.register_tv:
                startActivity(new Intent(LoginActivity.this, RegisterActivity.class));
                break;
            case R.id.forgot_password_tv:
                startActivity(new Intent(LoginActivity.this, ResetPasswordActivity.class));
                break;
            case R.id.login_btn:
                userLogin();
                break;
        }
    }

    private void userLogin() {
        String email = emailEt.getText().toString().trim();
        String password = passwordEt.getText().toString().trim();

        if (email.isEmpty()) {
            emailEt.setError("Email is required!");
            emailEt.requestFocus();
            return;
        }

        if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            emailEt.setError("Enter a valid email!");
            emailEt.requestFocus();
            return;
        }

        if (password.isEmpty()) {
            passwordEt.setError("Password is required!");
            passwordEt.requestFocus();
            return;
        }

        if (password.length() < 8) {
            passwordEt.setError("Min password length should be 8 characters!");
            passwordEt.requestFocus();
            return;
        }

        pb.setVisibility(View.VISIBLE);

        mAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()) {
                    FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
                    if (user.isEmailVerified()) {
                        startActivity(new Intent(LoginActivity.this, RestaurantsActivity.class));
                    } else {
                        user.sendEmailVerification();
                        Toast.makeText(LoginActivity.this, "Check your email to verify account", Toast.LENGTH_LONG).show();
                    }
                } else {
                    Toast.makeText(LoginActivity.this, "Failed to login! Check credentials", Toast.LENGTH_LONG).show();
                }
                pb.setVisibility(View.GONE);
            }
        });


    }

}