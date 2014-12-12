package com.winter.codefest.SocialMap.util;

import android.content.Context;
import android.location.Location;
import android.location.LocationManager;

/**
 * Created by Thilina on 12/12/2014.
 */
public class GPS {
    public static Location getGPSLocation(Context context) {
        LocationManager lm = (LocationManager) context.getSystemService(Context.LOCATION_SERVICE);
        Location location = lm.getLastKnownLocation(LocationManager.GPS_PROVIDER);
        return location;
    }
}
