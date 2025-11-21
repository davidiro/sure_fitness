package com.example.surefitness;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class MainActivity extends AppCompatActivity {
 EditText UsernameText,PasswordText;
 ProgressBar progressBar;
 Button login, register;
 FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        //initialization
        auth = FirebaseAuth.getInstance();
        UsernameText = findViewById(R.id.editTextText);
        progressBar = findViewById(R.id.progressBar2);
        PasswordText = findViewById(R.id.editTextTextPassword);
        register = findViewById(R.id.button2);
        login = findViewById(R.id.button);
        progressBar.setVisibility(View.GONE);
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {//explicit intent to register activity
                progressBar.setVisibility(View.VISIBLE);
                Intent i = new Intent(MainActivity.this,Register.class);
                startActivity(i);
                progressBar.setVisibility(View.GONE);
            }
        });
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                progressBar.setVisibility(View.VISIBLE);
                String email = UsernameText.getText().toString().trim();
                String password = PasswordText.getText().toString().trim();
                if(email.isEmpty()||password.isEmpty()){
                    Toast.makeText(MainActivity.this, "please fill all credentials", Toast.LENGTH_SHORT).show();
                    progressBar.setVisibility(View.GONE);
                }
                else{
                    progressBar.setVisibility(View.VISIBLE);
                    auth.signInWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                          if(task.isSuccessful()){
                              Toast.makeText(MainActivity.this, "sign in succesful", Toast.LENGTH_SHORT).show();
                              Intent intent = new Intent (MainActivity.this, HomePage.class);
                              progressBar.setVisibility(View.GONE);
                              startActivity(intent);
                              finish();
                          }
                          else{
                              progressBar.setVisibility(View.GONE);
                              Toast.makeText(MainActivity.this, "registration failed please check credentials and try again", Toast.LENGTH_SHORT).show();
                          }
                        }

                    });
                }
            }
        });
    }
}