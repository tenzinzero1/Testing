package com.receptix.testing;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.graphics.PixelFormat;
import android.os.BatteryManager;
import android.os.Bundle;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.TaskStackBuilder;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.Toast;

import static android.content.Context.LAYOUT_INFLATER_SERVICE;
import static android.content.Context.WINDOW_SERVICE;

/**
 * Created by zero1 on 5/5/2017.
 */

public class NoticeBroadCast extends BroadcastReceiver {

    NotificationManager mManager;
    NotificationCompat.Builder notificationBuilder;
    int plugged;
    @Override
    public void onReceive(Context context, Intent intent) {

        plugged = intent.getIntExtra("plugged",-1);
//        zero(0) show it is on charging
        Log.d("Plugg", String.valueOf(plugged));
        if(plugged==BatteryManager.BATTERY_PLUGGED_USB || plugged==BatteryManager.BATTERY_PLUGGED_AC || plugged==1){

         Notification(context);
        }else {
            Notificatio(context);

            StartServiceMethod(context);

        }


    }

    private void StartServiceMethod(Context context) {

       // Toast.makeText(this,"hello",Toast.LENGTH_LONG).show();
        Intent intent = new Intent(context,NotificationService.class);
        context.startService(intent);
    }


    private void Notificatio(Context context) {
        Intent notificationIntent = new Intent(context, NotificationActivity.class);

        TaskStackBuilder stackBuilder = TaskStackBuilder.create(context);
        stackBuilder.addParentStack(NotificationActivity.class);
        stackBuilder.addNextIntent(notificationIntent);

        PendingIntent pendingIntent = stackBuilder.getPendingIntent(0, PendingIntent.FLAG_UPDATE_CURRENT);

        NotificationCompat.Builder builder = new NotificationCompat.Builder(context);

        Notification notification = builder.setContentTitle(" Second Demo+"+plugged+"+ App Notification")
                .setContentText("New Notification From Demo App..")
                .setTicker("New Message Alert!")
                .setSmallIcon(R.mipmap.ic_launcher)
                .setOngoing(true)
                .setContentIntent(pendingIntent).build();

        NotificationManager notificationManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
        notificationManager.notify(0, notification);

    }

    public  void Notification(Context context){


        Intent notificationIntent = new Intent(context, NotificationActivity.class);

        TaskStackBuilder stackBuilder = TaskStackBuilder.create(context);
        stackBuilder.addParentStack(NotificationActivity.class);
        stackBuilder.addNextIntent(notificationIntent);

        PendingIntent pendingIntent = stackBuilder.getPendingIntent(0, PendingIntent.FLAG_UPDATE_CURRENT);

        NotificationCompat.Builder builder = new NotificationCompat.Builder(context);

        Notification notification = builder.setContentTitle("Demo +"+plugged+"+App Notification")
                .setContentText("New Notification From Demo App..")
                .setTicker("New Message Alert!")
                .setSmallIcon(R.mipmap.ic_launcher)
                .setContentIntent(pendingIntent).build();

        NotificationManager notificationManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
        notificationManager.notify(0, notification);


    }

}
