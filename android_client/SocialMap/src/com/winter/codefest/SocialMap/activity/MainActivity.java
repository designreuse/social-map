package com.winter.codefest.SocialMap.activity;

import android.app.Activity;
import android.os.Bundle;

import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.winter.codefest.SocialMap.R;


public class MainActivity extends Activity {
    GoogleMap googleMap;
    final LatLng hms = new LatLng(6.923543, 79.860793);
    /**
     * Called when the activity is first created.
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        try {
            if (googleMap == null) {
                googleMap = ((MapFragment) getFragmentManager().
                        findFragmentById(R.id.map)).getMap();
            }
            googleMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);
//            googleMap.setMyLocationEnabled(true);
            CameraPosition camPos = new CameraPosition.Builder()
                    .target(new LatLng(7, 81))
                    .zoom(7)
                    .tilt(70)
                    .build();

            CameraUpdate camUpd3 =
                    CameraUpdateFactory.newCameraPosition(camPos);

            googleMap.animateCamera(camUpd3);
//            googleMap.animateCamera(CameraUpdateFactory.zoomTo(10.0f));
//            Marker TP = googleMap.addMarker(new MarkerOptions().
//                    position(hms).title("hms"));

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

}
