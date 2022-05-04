package com.example.alarm

import android.app.AlarmManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.SystemClock
import android.widget.Button
import android.widget.EditText

class MainActivity : AppCompatActivity() {

    lateinit var state:Button
    lateinit var stop:Button
    lateinit var editText: EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        state = findViewById(R.id.button)
        stop = findViewById(R.id.buttons)
        editText = findViewById(R.id.time)
    }

    override fun onStart() {
        super.onStart()

        state.setOnClickListener {
            startAlarm()
        }
        stop.setOnClickListener {
            stopAlarm()
        }
    }

    private fun startAlarm(){
        var alarmMgr: AlarmManager? = null
        lateinit var alarmIntent: PendingIntent

        alarmMgr = this.getSystemService(Context.ALARM_SERVICE) as AlarmManager
        alarmIntent = Intent(this, MyBroadcastReceiver::class.java).let { intent ->
            PendingIntent.getBroadcast(this,0,intent,0)
        }

        alarmMgr?.set(
            AlarmManager.RTC_WAKEUP,
            SystemClock.elapsedRealtime() + 60 * 1000,
            alarmIntent
        )
    }
    private fun stopAlarm() {
        val alarmManager =
            this.getSystemService(Context.ALARM_SERVICE) as? AlarmManager
        val pendingIntent =
            PendingIntent.getService(this, 0, intent,
                PendingIntent.FLAG_NO_CREATE)
        if (pendingIntent != null && alarmManager != null) {
            alarmManager.cancel(pendingIntent)
        }
    }
}