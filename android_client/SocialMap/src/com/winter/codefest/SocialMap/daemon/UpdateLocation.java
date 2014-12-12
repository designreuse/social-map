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
    public static void registerAlarm(Context context) {
        Intent i = new Intent(context, UpdateLocationBroadcastReceiver.class);

        PendingIntent sender = PendingIntent.getBroadcast(context, 200, i, 0);

        // We want the alarm to go off 3 seconds from now.
        long firstTime = SystemClock.elapsedRealtime();
        firstTime += 3 * 1000;//start 3 seconds after first register.

        // Schedule the alarm!
        AlarmManager am = (AlarmManager) context
                .getSystemService(context.ALARM_SERVICE);
        am.setRepeating(AlarmManager.ELAPSED_REALTIME_WAKEUP, firstTime,
                18000, sender);//2min interval
    }
}
