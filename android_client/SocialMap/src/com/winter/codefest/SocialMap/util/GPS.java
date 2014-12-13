package com.winter.codefest.SocialMap.util;

import android.content.Context;
import android.location.Location;

/**
 * Created by Thilina on 12/12/2014.
 */
public class GPS {
    public static Location getGPSLocation(Context context) {
        GPSTracker gpsTracker = new GPSTracker(context);
        Location location = gpsTracker.getLocation();
        return location;
    }
}
