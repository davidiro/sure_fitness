package com.example.surefitness;

import static android.content.Context.MODE_PRIVATE;

import android.app.AlertDialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.widget.Toast;

public class GoalResetReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        //clear all the goals from the user goals shared preference file
        SharedPreferences sharedPreferences = context.getSharedPreferences("USER_GOALS",MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putInt("pushups",0);
        editor.putInt("situps",0);
        editor.putInt("KM",0);
        editor.putInt("squats",0);
        editor.apply();

        //diplay alert dialog when goals have been reset
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle("Alert!!");
        builder.setMessage("Your goals have been reset for this week");
        builder.setCancelable(true);

        //create and show alert dialog
        AlertDialog  alertdialog = builder.create();
        alertdialog.show();

    }
}
