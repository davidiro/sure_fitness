package com.example.surefitness;

import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.IBinder;

import androidx.annotation.Nullable;

public class Myservice extends Service {
    MediaPlayer myPlayer;
    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        myPlayer = MediaPlayer.create( this,R.raw.celebration );
        myPlayer.start();

        // Return START_NOT_STICKY to indicate that the service should not be restarted if it's terminated by the system
        return START_STICKY;

    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }
    public void onDestroy() {

        if (myPlayer != null) {

            myPlayer.stop();
            myPlayer.release();


            myPlayer = null;
        }
    }
}
