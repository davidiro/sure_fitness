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
import android.widget.ProgressBar;
import android.widget.TextView;

import java.util.Objects;


public class BMI extends Fragment {
ProgressBar progressBar;
TextView bmiText,medalText;
Button suggested;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_b_m_i, container, false);

        progressBar = view.findViewById(R.id.progressbar);
        bmiText = view.findViewById(R.id.textView9);

        suggested = view.findViewById(R.id.button9);
        suggested.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(),SuggestedMealPlan.class);
                startActivity(intent);
            }
        });
        SharedPreferences sharedPreferences = requireContext().getSharedPreferences("USER_BODY",MODE_PRIVATE);
        float Bmi = sharedPreferences.getFloat("Bmi",0.0F);
        String size = sharedPreferences.getString("size","");



        progressBar.setProgress((int)Bmi);
        progressBar.setMax(40);
        String formatted = String.format("%.1f", Bmi);
        bmiText.setText( "Current BMI: " + formatted + " you are currently " + size );


        return view;

    }
}