package com.example.alarm

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.media.MediaPlayer
import android.provider.Settings
import android.widget.Toast

class MyBroadcastReceiver : BroadcastReceiver(){
    override fun onReceive(p0: Context?, p1: Intent?) {
        var mp= MediaPlayer.create(p0,Settings.System.DEFAULT_RINGTONE_URI);
        mp.start();
        Toast.makeText(p0,"Alarm...",Toast.LENGTH_LONG).show();
    }
}