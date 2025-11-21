package com.example.surefitness;

import static android.content.Context.MODE_PRIVATE;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Workout extends Fragment {
    EditText pushUpText,sitUpText,SquatsText,kmText;
    Button contBtn;

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
       View view =inflater.inflate(R.layout.fragment_workout, container, false);
       pushUpText = view.findViewById(R.id.editTextText11);
       sitUpText = view.findViewById(R.id.editTextText12);
       SquatsText = view.findViewById(R.id.editTextText13);
       kmText = view.findViewById(R.id.editTextText14);
       contBtn = view.findViewById(R.id.button6);
       contBtn.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               String pushups = pushUpText.getText().toString();
               String sitUp = sitUpText.getText().toString();
               String squats = SquatsText.getText().toString();
               String km = kmText.getText().toString();

               if(pushups.isEmpty()||sitUp.isEmpty()||squats.isEmpty()||km.isEmpty()){
                   Toast.makeText(getContext(), "please fill all credentials", Toast.LENGTH_SHORT).show();
               }

               else{
                   try {

                       int Pushups = Integer.parseInt(pushups);
                       int Situps = Integer.parseInt(sitUp);
                       int Squats = Integer.parseInt(squats);
                       int Km = Integer.parseInt(km);


                       //retrieve goals from shared preferences file;
                       SharedPreferences sharedPreferences = requireContext().getSharedPreferences("USER_GOALS",MODE_PRIVATE);
                       int pushUpGoal = sharedPreferences.getInt("pushups",0);
                       int sitUpGoals = sharedPreferences.getInt("situps",0);
                       int SquatsGoals = sharedPreferences.getInt("squats",0);
                       int kmGoals = sharedPreferences.getInt("KM",0);

                       //calculate accomplishments
                       double PushUpAccomplishemnt = calculatePercentage(pushUpGoal,Pushups);
                       double sitUpAccomplishment = calculatePercentage(sitUpGoals,Situps);
                       double SquatsAccomplishment = calculatePercentage(SquatsGoals,Squats);
                       double KmAccomplishment = calculatePercentage(kmGoals,Km);

                       //total accomplishment percentage
                       double totalAccomplishment = (PushUpAccomplishemnt+SquatsAccomplishment+sitUpAccomplishment+KmAccomplishment)/4;

                       //determine medal gained
                       String medal = determineMedal(totalAccomplishment);

                       //add medal to user body
                      SharedPreferences sharedPreferences1 = requireContext().getSharedPreferences("USER_GOALS",MODE_PRIVATE);
                      SharedPreferences.Editor editor = sharedPreferences1.edit();
                      editor.putString("Medal",medal);
                      editor.apply();



                       //display medal gained
                       AlertDialog.Builder alertdialog = new AlertDialog.Builder(getContext());
                       if(medal.equals("Gold")){
                           alertdialog.setTitle("Congratulations!!");
                           alertdialog.setMessage("You received a gold medal for your achievements!!");
                           alertdialog.setCancelable(true);
                           //play music service
                           Intent intent = new Intent(requireContext(), Myservice.class);
                           requireContext().startService(intent);
                           alertdialog.setOnCancelListener(new DialogInterface.OnCancelListener() {
                               @Override
                               public void onCancel(DialogInterface dialog) {
                                   requireContext().stopService(intent);
                               }
                           });
                       } else if (medal.equals( "silver")) {
                           alertdialog.setTitle("Congratulations!");
                           alertdialog.setMessage("you received a silver medal for your accomplishments");
                           alertdialog.setCancelable(true);

                           //play music service
                           Intent intent = new Intent(requireContext(), Myservice.class);
                           requireContext().startService(intent);
                           alertdialog.setOnCancelListener(new DialogInterface.OnCancelListener() {
                             @Override
                             public void onCancel(DialogInterface dialog) {
                                    requireContext().stopService(intent);
                             }
                         });

                       } else if (medal.equals("Bronze")) {
                           alertdialog.setTitle("great work");
                           alertdialog.setMessage("you received a bronze medal for your accomplishments");
                           alertdialog.setCancelable(true);
                           //play music service
                           Intent intent = new Intent(requireContext(), Myservice.class);
                           requireContext().startService(intent);
                           alertdialog.setOnCancelListener(new DialogInterface.OnCancelListener() {
                               @Override
                               public void onCancel(DialogInterface dialog) {
                                   requireContext().stopService(intent);
                               }
                           });

                       } else if (medal.equals("no medal")) {
                           alertdialog.setTitle("oops...");
                           alertdialog.setMessage("You didn't receive any medal try harder next time");
                           alertdialog.setCancelable(true);
                           //play music service
                           Intent intent = new Intent(requireContext(), Myservice.class);
                           requireContext().startService(intent);
                           alertdialog.setOnCancelListener(new DialogInterface.OnCancelListener() {
                               @Override
                               public void onCancel(DialogInterface dialog) {
                                   requireContext().stopService(intent);
                               }
                           });
                       }
                       AlertDialog builder = alertdialog.create();
                       builder.show();


                   } catch (NumberFormatException e) { //catch number format exception
                       Toast.makeText(getContext(), "please enter valid  inputs", Toast.LENGTH_SHORT).show();
                   }
               }


           }

       });


       return view;
    }

    //method to calculate the percentage of goals completed
    public  double calculatePercentage(int goal, int accomplished){
       if (goal == 0){//cannot divide by 0
            return 0;
        }
       else{
           return(accomplished/(double)goal)*100;
       }
    }
        //method to determine medal
    public String determineMedal(double overallAccomplishment){
        if(overallAccomplishment >= 90){
            return "Gold";
        } else if (overallAccomplishment  >= 70) {
            return "silver";
        } else if (overallAccomplishment >= 50) {
            return "Bronze";
        }
        else{
            return "no medal";
        }
    }


}