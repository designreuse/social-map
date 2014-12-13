package com.winter.codefest.SocialMap.activity;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.widget.CompoundButton;
import android.widget.ToggleButton;

import com.winter.codefest.SocialMap.R;
import com.winter.codefest.SocialMap.daemon.UpdateLocation;

/**
 * Created by sadika on 12/13/14.
 */
public class TrackerActivity extends Activity {

    ToggleButton btnTracker;
    Context context;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tracker);

        context = this;
        btnTracker = (ToggleButton) findViewById(R.id.tbtnTracker);
        btnTracker.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if(btnTracker.isChecked()){
                    UpdateLocation.registerAlarm(context);
                    btnTracker.setText("Stop tracker");
                }else{
                    UpdateLocation.unregisterAlarm(context);
                    btnTracker.setText("Start tracker");
                }
            }
        });

        if(UpdateLocation.isAlarmRunning(context)){
            btnTracker.setText("Stop tracker");
            btnTracker.setChecked(true);
        }else{
            btnTracker.setText("Start tracker");
            btnTracker.setChecked(false);
        }
    }
}