package com.example.surefitness;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class About extends AppCompatActivity {
EditText nameText,AgeText,HeightText,WeightText;
Button Next;
String size ="";//variable for the users size;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_about);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        nameText = findViewById(R.id.editTextText2);
        AgeText = findViewById(R.id.editTextText3);
        HeightText = findViewById(R.id.editTextText5);
        WeightText = findViewById(R.id.editTextText4);
        Next = findViewById(R.id.button4);
        Next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = nameText.getText().toString().trim();
                String age  = AgeText.getText().toString().trim();
                String height = HeightText.getText().toString().trim();
                String weight = WeightText.getText().toString().trim();
                if(name.isEmpty()||age.isEmpty()|| height.isEmpty()||weight.isEmpty()){
                    Toast.makeText(About.this, "please fill all credentials", Toast.LENGTH_SHORT).show();
                }
                else {
                    try {
                        double Bmi = 0.0;
                        //parse the string variables to primitives
                        double Weight = Double.parseDouble(weight);
                        double Height = Double.parseDouble(height);
                        int Age = Integer.parseInt(age);
                        Bmi = Weight/(Math.pow(Height,2));//calculation for the bmi based in weight and height
                        if(Bmi <= 18.5){//size of the user
                            size = "underweight";
                        } else if (Bmi <= 24.99) {
                            size = "regular size";
                        } else if (Bmi > 25) {
                            size = "overweight";
                        }
                        SharedPreferences sharedPreferences = getSharedPreferences("USER_BODY",MODE_PRIVATE);
                        SharedPreferences.Editor editor = sharedPreferences.edit();
                        editor.putString("Name",name);
                        editor.putInt("age",Age);
                        editor.putString("size",size);
                        editor.putFloat("Weight", (float) Weight);
                        editor.putFloat("Height",(float)Height);
                        editor.putFloat("Bmi",(float)Bmi);
                        editor.apply();
                        Intent i = new Intent(About.this,HomePage.class);
                        startActivity(i);
                        Toast.makeText(About.this, "your information is safe :)", Toast.LENGTH_SHORT).show();
                    } catch (NumberFormatException e) {
                        Toast.makeText(About.this, "Invalid inputs ", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
    }
}