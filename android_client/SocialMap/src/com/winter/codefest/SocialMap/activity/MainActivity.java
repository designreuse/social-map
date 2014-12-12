package com.winter.codefest.SocialMap.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

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
                    .zoom(8)
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

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle item selection
        switch (item.getItemId()) {
            case R.id.register:
                startRegisteractivity();
                return true;
            case R.id.help:
//                showHelp();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void startRegisteractivity(){
        Intent intent = new Intent(this, AddDeviceActivity.class);
        startActivity(intent);
    }

}
