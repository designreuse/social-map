package com.winter.codefest.SocialMap.daemon;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import com.winter.codefest.SocialMap.R;
import com.winter.codefest.SocialMap.model.Device;
import com.winter.codefest.SocialMap.util.AsyncHttpPost;
import com.winter.codefest.SocialMap.util.GPS;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Thilina on 12/12/2014.
 */
public class UpdateLocationBroadcastReceiver extends BroadcastReceiver {

    private static final String TAG = UpdateLocationBroadcastReceiver.class.getCanonicalName();

    @Override
    public void onReceive(Context context, Intent intent) {

        Map params = new HashMap();
        params.put("vehicle-id", Device.getDeviceId());
        params.put("longitude", GPS.getGPSLocation(context).getLongitude());
        params.put("latitude", GPS.getGPSLocation(context).getLatitude());
        params.put("time", GPS.getGPSLocation(context).getTime());

        new AsyncHttpPost(context, context.getString(R.string.server_base_url) +
                context.getString(R.string.path_location_send)) {

            @Override
            public void onPostExecute(String result) {
                Log.d(TAG, result);
                //TODO handle response
            }
        }.execute(params);
    }
}
