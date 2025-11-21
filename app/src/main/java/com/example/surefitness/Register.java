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

public class Register extends AppCompatActivity {
 EditText UsernameText,PasswordText;
 Button Next;
 ProgressBar progressBar;
 FirebaseAuth auth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_register);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        auth = FirebaseAuth.getInstance();
        UsernameText = findViewById(R.id.editTextText8);
        progressBar = findViewById(R.id.progressBar);
        PasswordText = findViewById(R.id.editTextTextPassword5);
        Next = findViewById(R.id.button3);
        progressBar.setVisibility(View.GONE);
        Next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                progressBar.setVisibility(View.VISIBLE);
                String email  = UsernameText.getText().toString();
                String password = PasswordText.getText().toString();
                if(email.isEmpty()||password.isEmpty()){
                    Toast.makeText(Register.this, "please fill all fields", Toast.LENGTH_SHORT).show();
                    progressBar.setVisibility(View.GONE);
                }
                else{ progressBar.setVisibility(View.VISIBLE);
                      auth.createUserWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                          @Override
                          public void onComplete(@NonNull Task<AuthResult> task) {
                              if(task.isSuccessful()){
                                  Toast.makeText(Register.this, "Registration succesful", Toast.LENGTH_SHORT).show();
                                  Intent intent = new Intent(Register.this, About.class);
                                  progressBar.setVisibility(View.GONE);
                                  startActivity(intent);
                                  //finish();
                              }
                                else{
                                  progressBar.setVisibility(View.GONE);
                                  Toast.makeText(Register.this, "Registration failed please try again", Toast.LENGTH_SHORT).show();
                              }
                          }
                      });

                }
            }
        });
    }
}