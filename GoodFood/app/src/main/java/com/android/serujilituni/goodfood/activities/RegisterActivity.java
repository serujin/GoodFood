package com.android.serujilituni.goodfood.activities;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.android.serujilituni.goodfood.MainActivity;
import com.android.serujilituni.goodfood.R;
import com.android.serujilituni.goodfood.model.User;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;

public class RegisterActivity extends AppCompatActivity {

    private FirebaseAuth mAuth;

    private EditText emailEt;
    private EditText passwordEt;
    private EditText fullNameEt;

    private ProgressBar pb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        mAuth = FirebaseAuth.getInstance();

    }
    public void registerUser() {
        String email = emailEt.getText().toString().trim();
        String password = passwordEt.getText().toString().trim();
        String fullName = fullNameEt.getText().toString().trim();

        if(fullName.isEmpty()) {
            fullNameEt.setError("Full name is required!");
            fullNameEt.requestFocus();
            return;
        }

        if(email.isEmpty()) {
            emailEt.setError("Email is required!");
            emailEt.requestFocus();
            return;
        }

        if(Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            emailEt.setError("Please provide valid email!");
            emailEt.requestFocus();
            return;
        }

        if(password.isEmpty()) {
            passwordEt.setError("Password is required!");
            passwordEt.requestFocus();
        }

        if(password.length() < 8){
            passwordEt.setError("Min password length should be 8 characters!");
            passwordEt.requestFocus();
            return;
        }

        pb.setVisibility(View.VISIBLE);

        mAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>(){
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {

                if(task.isSuccessful()) {
                    User user = new User(fullName,email);
                    FirebaseDatabase.getInstance().getReference("Users").child(FirebaseAuth.getInstance().getCurrentUser().getUid()).setValue(user).addOnCompleteListener(new OnCompleteListener<Void>() {

                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            if(task.isSuccessful()) {
                                Toast.makeText(getApplicationContext(), "User has been registered successfully!", Toast.LENGTH_LONG).show();
                                pb.setVisibility(View.VISIBLE);

                                //redirect to login Layout
                                startActivity(new Intent(getApplicationContext(), MainActivity.class));
                            }else {
                                Toast.makeText(getApplicationContext(),"Failed to register, try again!",Toast.LENGTH_LONG).show();
                                pb.setVisibility(View.GONE);
                            }
                        }
                    });
                } else{
                    Toast.makeText(getApplicationContext(),"Failed to register!",Toast.LENGTH_LONG).show();
                    pb.setVisibility(View.GONE);
                }
            }
        });

    }


}
