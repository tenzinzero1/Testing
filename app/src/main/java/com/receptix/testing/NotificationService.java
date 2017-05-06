package com.receptix.testing;

import android.app.AlarmManager;
import android.app.Notification;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.graphics.PixelFormat;
import android.media.MediaPlayer;
import android.media.audiofx.BassBoost;
import android.os.Handler;
import android.os.IBinder;
import android.os.SystemClock;
import android.provider.Settings;
import android.support.annotation.IntDef;
import android.support.annotation.Nullable;
import android.support.v4.app.NotificationCompat;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;

import java.util.Calendar;

/**
 * Created by zero1 on 5/5/2017.
 */

public class NotificationService extends Service  {

    NotificationCompat.Builder notificationBuilder;
    long delay =5000;
    MediaPlayer player;
    private static final String TAG = "TashiDelek";
    @Nullable
    @Override
    public IBinder onBind(Intent intent) {

        return null;


    }

    @Override
    public int onStartCommand(Intent intent,int flags, int startId) {
        super.onStartCommand(intent, flags, startId);

        try{
            if(intent!=null){

                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        startJob();
                       // ShowHomeScreen();
                    }
                },3000);


            }


        }catch (Throwable e){

            Log.d("TTT",e.fillInStackTrace().toString());
        }


     //  StartLocalNotification();
        return Service.START_STICKY;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        player.stop();
    }

    private void startJob() {
        player = MediaPlayer.create(this,Settings.System.DEFAULT_RINGTONE_URI);
        player.setLooping(true);
        player.start();
    }

    private void StartLocalNotification() {

        AlarmManager alarmManager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
        Intent notificationIntent = new Intent("android.media.action.DISPLAY_NOTIFICATION");
        notificationIntent.addCategory("android.intent.category.DEFAULT");
        PendingIntent broadcast = PendingIntent.getBroadcast(this, 100, notificationIntent, PendingIntent.FLAG_UPDATE_CURRENT);
        Calendar c = Calendar.getInstance();
        c.add(Calendar.SECOND, 10);
        alarmManager.setExact(AlarmManager.RTC_WAKEUP,c.getTimeInMillis(),broadcast);

    }

    private void ShowHomeScreen() {
//        WindowManager.LayoutParams.TYPE_SYSTEM_ALERT,
//        WindowManager.LayoutParams params = new WindowManager.LayoutParams(
//                WindowManager.LayoutParams.MATCH_PARENT,
//                WindowManager.LayoutParams.MATCH_PARENT,
//                WindowManager.LayoutParams.TYPE_PHONE,
//                WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE
//                        | WindowManager.LayoutParams.FLAG_NOT_TOUCH_MODAL
//                        | WindowManager.LayoutParams.FLAG_WATCH_OUTSIDE_TOUCH,
//                PixelFormat.TRANSLUCENT);
        //Works above code....



                WindowManager.LayoutParams params = new WindowManager.LayoutParams(
                WindowManager.LayoutParams.MATCH_PARENT,
                WindowManager.LayoutParams.MATCH_PARENT,
                WindowManager.LayoutParams.TYPE_SYSTEM_OVERLAY,  // have to think here.. what type can be used for better interactive....
                WindowManager.LayoutParams.FLAG_FULLSCREEN
                        | WindowManager.LayoutParams.FLAG_SHOW_WHEN_LOCKED
                        | WindowManager.LayoutParams.FLAG_DISMISS_KEYGUARD,
                PixelFormat.TRANSLUCENT);

        // WindowManager wm = (WindowManager) getSystemService(WINDOW_SERVICE);
        WindowManager wm = (WindowManager) getSystemService(WINDOW_SERVICE);

        LayoutInflater inflater = (LayoutInflater) getSystemService(LAYOUT_INFLATER_SERVICE);
        View myView = inflater.inflate(R.layout.my_view, null);

        final ImageView img = (ImageView) myView.findViewById(R.id.image);

        myView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                Log.d(TAG, "touch me");
                if(event.getAction()==MotionEvent.ACTION_SCROLL || event.getAction()==MotionEvent.ACTION_UP){
                    stopSelf();

                }
                return true;
            }
        });

        // Add layout to window manager
        wm.addView(myView, params);
    }


}
