package com.winter.codefest.SocialMap.activity;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.*;
import com.winter.codefest.SocialMap.R;
import com.winter.codefest.SocialMap.dialog.LoadingDialog;
import com.winter.codefest.SocialMap.model.Device;
import com.winter.codefest.SocialMap.util.AsyncHttpPost;
import com.winter.codefest.SocialMap.util.CheckNetwork;
import com.winter.codefest.SocialMap.util.GPS;
import com.winter.codefest.SocialMap.util.HTTPRequest;
import org.json.JSONException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Thilina on 12/12/2014.
 */
public class AddDeviceActivity extends Activity {

    private static final String TAG = AddDeviceActivity.class.getCanonicalName();

    private Button btnRegister;
    private EditText etCode;
    private Context context;
    private Spinner spGroups;

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
        etCode = (EditText) findViewById(R.id.etCode);
        spGroups = (Spinner) findViewById(R.id.spGroups);

        Log.d(TAG, "initiate activity");

        loadGroups();

    }

    private void loadGroups() {
        if(CheckNetwork.isInternetAvailable(this)){
            new AsyncHttpPost(this, getString(R.string.server_base_url) + getString(R.string.path_get_groups)) {
                LoadingDialog loadingDialog = new LoadingDialog(context);
                @Override
                protected void onPreExecute() {
                    loadingDialog.show();
                }

                @Override
                public void onPostExecute(String result) {
                    loadingDialog.dismiss();
                    if(result!=null){
                        showGroups(result);
                    }else{
                        Toast.makeText(context,
                                context.getString(R.string.err_connection_error),
                                Toast.LENGTH_LONG).show();
                    }
                }
            }.execute(new HashMap<String, String>());
        }else{
            Toast.makeText(context,
                    context.getString(R.string.err_connection_error),
                    Toast.LENGTH_LONG).show();
        }
    }

    private void btnRegisterOnClick() {
        Map params = new HashMap();
        params.put("vehicle-id", Device.getDeviceId());
        params.put("code", etCode.getText().toString());
        params.put("latitude", GPS.getGPSLocation(this).getAltitude());
        params.put("longitude", GPS.getGPSLocation(this).getLongitude());

        if(CheckNetwork.isInternetAvailable(this)){
            new AsyncHttpPost(this, getString(R.string.server_base_url) + getString(R.string.path_get_groups)) {
                LoadingDialog loadingDialog = new LoadingDialog(context);
                @Override
                protected void onPreExecute() {
                    loadingDialog.show();
                }

                @Override
                public void onPostExecute(String result) {
                    loadingDialog.dismiss();
                    if(result!=null){
                        //TODO show success/fail
                        //TODO run daemon if success
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

    private void showGroups(String result) {
        try {
            Map<String, Object> response = HTTPRequest.prepareResult(result);
            if(response!=null){
                List<String> list = getGroupList(response);
                ArrayAdapter<String> adapter = new ArrayAdapter<String>(getApplicationContext(),
                        android.R.layout.simple_spinner_item, list);
                adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                spGroups.setAdapter(adapter);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    private List<String> getGroupList(Map<String, Object> response) {
        List<String> groups = new ArrayList<String>();
        //TODO make the list
        return groups;
    }

}