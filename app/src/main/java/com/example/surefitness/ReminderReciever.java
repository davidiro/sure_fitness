package com.example.surefitness;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

public class ReminderReciever extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        NotificationHelper.createNotification(context);
    }
}
