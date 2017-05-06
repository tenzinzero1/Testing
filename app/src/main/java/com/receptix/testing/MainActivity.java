package com.receptix.testing;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.util.Calendar;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toast.makeText(this,"notific",Toast.LENGTH_LONG).show();
        Button btn = (Button) findViewById(R.id.btn);
        btn.setOnClickListener(this);
        //makeME();
        Button stopbtn = (Button) findViewById(R.id.stopbtn);
        stopbtn.setOnClickListener(this);
    }

    private void makeME() {

        AlarmManager alarmManager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
        Intent notificationIntent = new Intent("android.media.action.DISPLAY_NOTIFICATION");
        notificationIntent.addCategory("android.intent.category.DEFAULT");
        PendingIntent broadcast = PendingIntent.getBroadcast(this, 100, notificationIntent, PendingIntent.FLAG_UPDATE_CURRENT);
        Calendar c = Calendar.getInstance();
        c.add(Calendar.SECOND, 10);
        alarmManager.setExact(AlarmManager.RTC_WAKEUP,c.getTimeInMillis(),broadcast);
    }

    @Override
    public void onClick(View v) {

        if(v.getId()==R.id.btn) {

            Toast.makeText(MainActivity.this,"hello",Toast.LENGTH_LONG).show();
            Intent intent = new Intent(getApplicationContext(),NotificationService.class);
            getApplicationContext().startService(intent);
        }else {

            Toast.makeText(MainActivity.this,"hello",Toast.LENGTH_LONG).show();
            Intent intent = new Intent(getApplicationContext(),NotificationService.class);
            getApplicationContext().stopService(intent);
        }

    }
}
