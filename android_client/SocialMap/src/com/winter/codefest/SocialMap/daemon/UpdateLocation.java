package com.winter.codefest.SocialMap.daemon;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.SystemClock;

/**
 * Created by Thilina on 12/12/2014.
 */
public class UpdateLocation {
    private static AlarmManager am;
    private static PendingIntent sender;


    public static void registerAlarm(Context context) {
        Intent i = new Intent(context, UpdateLocationBroadcastReceiver.class);

        sender = PendingIntent.getBroadcast(context, 200, i, 0);

        // We want the alarm to go off 3 seconds from now.
        long firstTime = SystemClock.elapsedRealtime();
        firstTime += 3 * 1000;//start 3 seconds after first register.

        // Schedule the alarm!
        am = (AlarmManager) context
                .getSystemService(context.ALARM_SERVICE);
        am.setRepeating(AlarmManager.ELAPSED_REALTIME_WAKEUP, firstTime,
                18000, sender);//2min interval
    }

    public static void unregisterAlarm(Context context){
        am.cancel(sender);
    }

    public static boolean isAlarmRunning(Context context){
        boolean alarmUp = (PendingIntent.getBroadcast(context, 1,
                new Intent(context, UpdateLocationBroadcastReceiver.class),
                PendingIntent.FLAG_NO_CREATE) != null);

        return alarmUp;
    }
}
