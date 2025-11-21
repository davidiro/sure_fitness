package com.example.surefitness;

import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class SuggestedMealPlan extends AppCompatActivity {
RecyclerView recyclerView;
ArrayList<SuggestedModel> suggested = new ArrayList<>();//arrayList for the suggested meals
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_suggested_meal_plan);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        SharedPreferences sharedPreferences = getSharedPreferences("USER_BODY",MODE_PRIVATE);

       // String size = sharedPreferences.getString("size","");
        float bmi = sharedPreferences.getFloat("Bmi",0.0F);
        if (bmi  >= 26){
            suggested.add(new SuggestedModel("High Protein Bowl",getString(R.string.highProteinBowl),R.drawable.highprotein));
            suggested.add(new SuggestedModel("oats and berries",getString(R.string.Oatsandberries),R.drawable.oats));
            suggested.add(new SuggestedModel("Grilled Chicken Salad",getString(R.string.Grilledchickensalad),R.drawable.grilledchickensalad));
            suggested.add(new SuggestedModel("salmon and sweetPotato",getString(R.string.salmonandsweetpotato),R.drawable.salmonandsweetpotato));
            suggested.add(new SuggestedModel("lean Turkey and brown rice",getString(R.string.LeanTurkeyAndBrownRice),R.drawable.leanturkeyandbrownrice));
        }
        suggested.add(new SuggestedModel("Protein Smoothie",getString(R.string.Proteinsmoothie),R.drawable.proteinsmoothie));
        suggested.add(new SuggestedModel("Egg and avacado",getString(R.string.EggandAvacado),R.drawable.eggsandavacado));
        suggested.add(new SuggestedModel("Beef and Roasted Vegitables",getString(R.string.BeefandroastedPotato), R.drawable.beefandroatestedvegitables));
        suggested.add(new SuggestedModel("Tofu and stirFry",getString(R.string.Tofustirfry),R.drawable.tofustirfry));
        suggested.add(new SuggestedModel("Cottage and cheese",getString(R.string.cottagecheese),R.drawable.cottagecheese));

        SuggestedAdapter adapter = new SuggestedAdapter(suggested,this);
        recyclerView = findViewById(R.id.mRecycler);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(this,1);
        recyclerView.setLayoutManager(gridLayoutManager);
        recyclerView.setAdapter(adapter);
    }
}