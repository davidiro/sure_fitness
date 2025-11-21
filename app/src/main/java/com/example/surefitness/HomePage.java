package com.example.surefitness;

import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.fragment.app.Fragment;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class HomePage extends AppCompatActivity {
BottomNavigationView bottomNavigationView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_home_page);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        //initialize our bottom navigation view
        bottomNavigationView = findViewById(R.id.bottomNavigationView);
        //create instances of out fragment classes
        Profile prof_ile = new Profile();
        goals Goals = new goals();
        Workout work_out = new Workout();
        BMI b_mi = new BMI();

        setFragment(b_mi);
        //on navigation listener on our bottom navigation bar
        bottomNavigationView.setOnNavigationItemSelectedListener(item -> {

            if(R.id.profile == item.getItemId()){ //using the id of each icon
                setFragment(prof_ile);
            } else if (R.id.goals ==item.getItemId()) {
                setFragment(Goals);
            } else if (R.id.bmi == item.getItemId()) {
                setFragment(b_mi);
            } else if (R.id.workout == item.getItemId()) {
                setFragment(work_out);
            }
            return true;
        });
    }
//method to set out fragment on selection
    private void setFragment(Fragment fragment) {
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.frameLayout,fragment)
                .commit();

    }
}