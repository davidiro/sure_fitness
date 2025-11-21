package com.example.surefitness;

import static android.content.Context.MODE_PRIVATE;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import java.util.Objects;


public class Profile extends Fragment {
TextView NameText,AgeText,WeightText,HeightText;
Button edit;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_profile, container, false);
        NameText = view.findViewById(R.id.textView2);
        edit =  view.findViewById(R.id.button7);
        AgeText = view.findViewById(R.id.textView4);
        WeightText = view.findViewById(R.id.textView5);
        HeightText = view.findViewById(R.id.textView6);

        SharedPreferences sharedPreferences = requireContext().getSharedPreferences("USER_BODY",MODE_PRIVATE);
        float weight = sharedPreferences.getFloat("Weight", 0.0F);
        float Height = sharedPreferences.getFloat("Height",0.0F);
        int Age = sharedPreferences.getInt("age",0);
        String name = sharedPreferences.getString("Name","");

        NameText.setText("Hello " +name);
        AgeText.setText("You are currently " + Age + " "+"years old ");
        HeightText.setText("Your current Height is " + Height +"M");
        WeightText.setText("Your current weight is " + weight +"KG");
        //return to about page to edit profile
        edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getActivity(), About.class);
                startActivity(i);
            }
        });

       return view;
    }
}