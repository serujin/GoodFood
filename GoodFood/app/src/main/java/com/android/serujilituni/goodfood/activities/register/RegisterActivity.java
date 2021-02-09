package com.android.serujilituni.goodfood.activities.register;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.android.serujilituni.goodfood.R;
import com.android.serujilituni.goodfood.activities.login.LoginActivity;
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
    private EditText confirmPasswordEt;
    private EditText fullNameEt;
    private Button registerBtn;

    private ProgressBar pb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        mAuth = FirebaseAuth.getInstance();
        emailEt = findViewById(R.id.register_user_email);
        passwordEt = findViewById(R.id.register_user_password);
        confirmPasswordEt = findViewById(R.id.register_user_pass_confirm);
        fullNameEt = findViewById(R.id.register_user_fullname);
        registerBtn = findViewById(R.id.register_btn);
        registerBtn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                registerUser();
            }
        });
        pb = findViewById(R.id.progress_bar);
        pb.setVisibility(View.GONE);

        /**
         * Logout implementation
         Button logout;
         logout = (Button) findViewById(R.id.singOut_btn);
         logout.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
        FirebaseAuth.getInstance().signOut();
        startActivity(new Intent(ProfileActivity.this,MainActivity.class));
        }
        });
         */

    }
    public void registerUser() {
        String email = emailEt.getText().toString().trim();
        String password = passwordEt.getText().toString().trim();
        String confirmedPassword = confirmPasswordEt.getText().toString().trim();
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

        if(!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            emailEt.setError("Please provide valid email!");
            emailEt.requestFocus();
            return;
        }

        if(password.isEmpty()) {
            passwordEt.setError("Password is required!");
            passwordEt.requestFocus();
            return;
        }

        if(password.length() < 8){
            passwordEt.setError("Min password length should be 8 characters!");
            passwordEt.requestFocus();
            return;
        }

        if(!password.equals(confirmedPassword)) {
            passwordEt.setError("Password and confirmed password must be equal");
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
                                startActivity(new Intent(getApplicationContext(), LoginActivity.class));
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