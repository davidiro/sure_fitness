package com.example.surefitness;

import static android.content.Context.MODE_PRIVATE;

import static androidx.core.content.ContextCompat.startForegroundService;

import android.app.AlarmManager;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;

import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Calendar;
import java.util.Objects;


public class goals extends Fragment {
EditText pushUpText,sitUpText,KmText,squatsText;
Button saveBnt,notifyButton;
ImageView trophie;
GoalResetReceiver goalResetReceiver;

private final  String channelId = "channelid";
private  final String Description = "Daily checkup";
private final int notificationId = 123;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =inflater.inflate(R.layout.fragment_goals, container, false);
        pushUpText = view.findViewById(R.id.editTextText6);
        sitUpText = view.findViewById(R.id.editTextText7);
        notifyButton = view.findViewById(R.id.button8);
        trophie = view.findViewById(R.id.imageView11);
        KmText = view.findViewById(R.id.editTextText9);
        squatsText = view.findViewById(R.id.editTextText10);
        saveBnt = view.findViewById(R.id.button5);
        notifyButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                NotificationHelper.createNotification(requireContext());

                //scheduleDailyReminder();
            }
        });


        SharedPreferences sharedPreferences = getContext().getSharedPreferences("USER_GOALS",MODE_PRIVATE);
        String medal = sharedPreferences.getString("Medal","");
        if(medal.equals("Gold")){
            trophie.setImageResource(R.drawable.gold);
        } else if (medal.equals("silver")) {
            trophie.setImageResource(R.drawable.silver);
        } else if (medal.equals("Bronze")) {
            trophie.setImageResource(R.drawable.bronze);
        }
        else{
            trophie.setImageResource(R.drawable.none);
        }
        saveBnt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String pushup = pushUpText.getText().toString().trim();
                String sitUp = sitUpText.getText().toString().trim();
                String Km = KmText.getText().toString().trim();
                String squats = squatsText.getText().toString().trim();

                //validation
                if(pushup.isEmpty()||sitUp.isEmpty()||Km.isEmpty()|| squats.isEmpty()){
                    Toast.makeText(getContext(), "Please fill all credentials", Toast.LENGTH_SHORT).show();
                }
                else{
                    try {
                        int PushUps = Integer.parseInt(pushup);
                        int SitUp = Integer.parseInt(sitUp);
                        int KM = Integer.parseInt(Km);
                        int Squats = Integer.parseInt(squats);
                        //save user goals
                        SharedPreferences sharedPreferences = requireContext().getSharedPreferences("USER_GOALS",MODE_PRIVATE);
                        SharedPreferences.Editor editor = sharedPreferences.edit();
                        editor.putInt("pushups",PushUps);
                        editor.putInt("situps",SitUp);
                        editor.putInt("KM", KM);
                        editor.putInt("squats",Squats);
                        editor.apply();

                        Toast.makeText(getContext(), "Good luck with your goals", Toast.LENGTH_SHORT).show();
                    }
                    catch (NumberFormatException e) {
                        Toast.makeText(getContext(), "please input valid values", Toast.LENGTH_SHORT).show();
                    }

                }
            }
        });
            //schedule weekly reset
          ScheduleWeeklyReset(requireContext());

        return view;
    }
    public void ScheduleWeeklyReset(Context context){
        //get instance of out alarm service
        AlarmManager alarmManager = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);

        //create an intent that will be fired weekly
        Intent intent =  new Intent(context,GoalResetReceiver.class);

        //wrap our intent so it can be executed at a later time
        PendingIntent pendingIntent = PendingIntent.getBroadcast(
                context, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT | PendingIntent.FLAG_IMMUTABLE
        );

        //set current day and time to sunday midnight
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.DAY_OF_WEEK,Calendar.SUNDAY);
        calendar.set(Calendar.HOUR_OF_DAY,0);
        calendar.set(Calendar.MINUTE,0);
        calendar.set(Calendar.SECOND,0);
        calendar.set(Calendar.MILLISECOND,0);

        //if the current time is already past this weeks reset time set the listening time for next week
        if(calendar.getTimeInMillis()  <=  System.currentTimeMillis()){
            //set the Week of the year to first
            calendar.add(Calendar.WEEK_OF_YEAR,1);
        }
        //set Alarm to repeat every seven days.Rtc is to wake the alarm manager uo when system is in sleep mode
        alarmManager.setRepeating(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(),AlarmManager.INTERVAL_DAY*7,pendingIntent);
    }
    private void scheduleDailyReminder() {
        Context context = requireContext(); // Get context in Fragment
        AlarmManager alarmManager = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);

        Intent intent = new Intent(context, ReminderReciever.class);
        PendingIntent pendingIntent = PendingIntent.getBroadcast(context, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT | PendingIntent.FLAG_IMMUTABLE);

        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.HOUR_OF_DAY, 9); // Schedule for 9 AM
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);

        // Set to repeat every day
        if (alarmManager != null) {
            alarmManager.setRepeating(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(),
                    AlarmManager.INTERVAL_DAY, pendingIntent);
        }
    }

    }





