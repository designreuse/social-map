package com.winter.codefest.SocialMap.activity;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import com.winter.codefest.SocialMap.R;
import com.winter.codefest.SocialMap.model.Device;
import com.winter.codefest.SocialMap.util.GPS;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Thilina on 12/12/2014.
 */
public class AddDeviceActivity extends Activity {

    private static final String TAG = AddDeviceActivity.class.getCanonicalName();

    private Button btnRegister;
    private EditText etCode;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_device);

        btnRegister = (Button) findViewById(R.id.btnRegister);
        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                btnRegisterOnClick();
            }
        });
        etCode = (EditText) findViewById(R.id.etCode);

        Log.d(TAG, "initiate activity");

    }

    private void btnRegisterOnClick() {
        Map params = new HashMap();
        params.put("deviceId", Device.getDeviceId());
        params.put("code", etCode.getText().toString());
        params.put("latitude", GPS.getGPSLocation(this).getAltitude());

    }
}