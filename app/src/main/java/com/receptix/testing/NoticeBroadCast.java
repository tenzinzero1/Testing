package com.receptix.testing;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.TaskStackBuilder;
import android.widget.Toast;

/**
 * Created by zero1 on 5/5/2017.
 */

public class NoticeBroadCast extends BroadcastReceiver {
    NotificationManager mManager;
    NotificationCompat.Builder notificationBuilder;
    @Override
    public void onReceive(Context context, Intent intent) {

        Toast.makeText(context,"hello i m working here",Toast.LENGTH_LONG).show();
//        Intent notificationIntent = new Intent(context, NotificationActivity.class);
//
//        TaskStackBuilder stackBuilder = TaskStackBuilder.create(context);
//        stackBuilder.addParentStack(NotificationActivity.class);
//        stackBuilder.addNextIntent(notificationIntent);
//
//        PendingIntent pendingIntent = stackBuilder.getPendingIntent(0, PendingIntent.FLAG_UPDATE_CURRENT);
//
//        NotificationCompat.Builder builder = new NotificationCompat.Builder(context);
//
//        Notification notification = builder.setContentTitle("Demo App Notification")
//                .setContentText("New Notification From Demo App..")
//                .setTicker("New Message Alert!")
//                .setSmallIcon(R.mipmap.ic_launcher)
//                .setContentIntent(pendingIntent).build();
//
//        NotificationManager notificationManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
//        notificationManager.notify(0, notification);

// System.out.println("-------------------->Notification send");
    }
}
