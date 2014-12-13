package com.winter.codefest.SocialMap.activity;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.winter.codefest.SocialMap.R;
import com.winter.codefest.SocialMap.daemon.UpdateLocation;
import com.winter.codefest.SocialMap.dialog.LoadingDialog;
import com.winter.codefest.SocialMap.model.Device;
import com.winter.codefest.SocialMap.session.Session;
import com.winter.codefest.SocialMap.util.AsyncHttpPost;
import com.winter.codefest.SocialMap.util.CheckNetwork;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Thilina on 12/12/2014.
 */
public class AddDeviceActivity extends Activity {

    private static final String TAG = AddDeviceActivity.class.getCanonicalName();

    private Button btnRegister;
    private EditText etCode;
    private EditText etGroupId;
    private Context context;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_device);
        context = AddDeviceActivity.this;

        btnRegister = (Button) findViewById(R.id.btnRegister);
        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                btnRegisterOnClick();
            }
        });
        etGroupId = (EditText) findViewById(R.id.etGroupId);
        etCode = (EditText) findViewById(R.id.etCode);

        Log.d(TAG, "initiate activity");

    }

    private void btnRegisterOnClick() {
        Map params = new HashMap();
        params.put("vehicle-id", Device.getDeviceId(this));
        params.put("code", etCode.getText().toString());
        params.put("group-id", etGroupId.getText().toString());
//        params.put("latitude", GPS.getGPSLocation(this).getAltitude());
//        params.put("longitude", GPS.getGPSLocation(this).getLongitude());

        if(CheckNetwork.isInternetAvailable(this)){
//            UpdateLocation.registerAlarm(context);
            new AsyncHttpPost(this, getString(R.string.server_base_url) + getString(R.string.path_reg)) {
                LoadingDialog loadingDialog = new LoadingDialog(context);
                @Override
                protected void onPreExecute() {
                    loadingDialog.show();
                }

                @Override
                public void onPostExecute(String result) {
                    loadingDialog.dismiss();
                    if(result!=null){
                        Log.d(TAG, "response: "+ result);
                        try {
                            JSONObject jsonObject = new JSONObject(result);
                            if(jsonObject.has("responseContext")){
                                if(jsonObject.getString("responseContext").equals("SUCCESS")){
                                Toast.makeText(context,
                                        "Vehicle registered successfully!",
                                        Toast.LENGTH_LONG).show();
                                    Session.setRegistered(context);

                                    UpdateLocation.registerAlarm(context);

                                }else if(jsonObject.getString("responseContext").equals("FAIL")){
                                    Toast.makeText(context,
                                            "Failed to register the vehicle!",
                                            Toast.LENGTH_LONG).show();
                                }
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }else{
                        Toast.makeText(context,
                                context.getString(R.string.err_connection_error),
                                Toast.LENGTH_LONG).show();
                    }
                }
            }.execute(params);
        }else{
            Toast.makeText(context,
                    context.getString(R.string.err_connection_error),
                    Toast.LENGTH_LONG).show();
        }
    }


}