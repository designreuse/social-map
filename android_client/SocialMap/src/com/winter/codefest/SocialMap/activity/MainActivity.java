package com.winter.codefest.SocialMap.activity;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.winter.codefest.SocialMap.R;
import com.winter.codefest.SocialMap.dialog.LoadingDialog;
import com.winter.codefest.SocialMap.session.Session;
import com.winter.codefest.SocialMap.util.AsyncHttpPost;
import com.winter.codefest.SocialMap.util.CheckNetwork;
import com.winter.codefest.SocialMap.util.HTTPRequest;

import org.json.JSONException;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


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

        setGoogleMap(null);

        Button btnSearch = (Button) findViewById(R.id.btnSearch);

        btnSearch.setOnClickListener(new View.OnClickListener()
        {
            public void onClick(View v)
            {
                loadGroups();
            }
        });

    }

    private void setGoogleMap(List<Map> mapList){
        try {
            if (googleMap == null) {
                googleMap = ((MapFragment) getFragmentManager().
                        findFragmentById(R.id.map)).getMap();
            }
            googleMap.clear();
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
            if (mapList != null){
                for (int i = 0; i < mapList.size(); i++){
                    String lat = String.valueOf(mapList.get(i).get("latitude"));
                    String lon = String.valueOf(mapList.get(i).get("longitude"));
                    lat = lat.indexOf(".") < 0 ? lat : lat.replaceAll("0*$", "").replaceAll("\\.$", "");
                    lon = lon.indexOf(".") < 0 ? lon : lon.replaceAll("0*$", "").replaceAll("\\.$", "");


                    LatLng latLng = new LatLng(Double.valueOf(lat), Double.valueOf(lon));
                    CameraUpdate center= CameraUpdateFactory.newLatLng(latLng);
                    CameraUpdate zoom=CameraUpdateFactory.zoomTo(10f);
                    googleMap.moveCamera(center);
                    googleMap.animateCamera(zoom);

            Marker TP = googleMap.addMarker(new MarkerOptions().
                    position(latLng).title((String) mapList.get(i).get("name")));
                    TP.setIcon(BitmapDescriptorFactory.fromResource(R.drawable.bus));
                }
            }
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
        if(!Session.isRegistered(this)){
            MenuItem menuItem = menu.findItem(R.id.tracker);
            menuItem.setVisible(false);
        }
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
            case R.id.tracker:
                showTrackerPage();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void showTrackerPage() {
        Intent intent = new Intent(this, TrackerActivity.class);
        startActivity(intent);
    }

    private void startRegisteractivity(){
        Intent intent = new Intent(this, AddDeviceActivity.class);
        startActivity(intent);
    }

    private void loadGroups() {
        if(CheckNetwork.isInternetAvailable(this)){
            new AsyncHttpPost(this, getString(R.string.server_base_url) + getString(R.string.path_get_groups)) {
                LoadingDialog loadingDialog = new LoadingDialog(MainActivity.this);
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
                        Toast.makeText(MainActivity.this,
                                MainActivity.this.getString(R.string.err_connection_error),
                                Toast.LENGTH_LONG).show();
                    }
                }
            }.execute(new HashMap<String, String>());
        }else{
            Toast.makeText(MainActivity.this,
                    MainActivity.this.getString(R.string.err_connection_error),
                    Toast.LENGTH_LONG).show();
        }
    }

    private void loadVehicles(Integer id) {
        if(CheckNetwork.isInternetAvailable(this)){
            Map<String, String> post = new HashMap<String, String>();
            System.out.println("### "+id);
            post.put("group-id", String.valueOf(id));
            new AsyncHttpPost(this, getString(R.string.server_base_url) + getString(R.string.path_group_location)) {
                LoadingDialog loadingDialog = new LoadingDialog(MainActivity.this);
                @Override
                protected void onPreExecute() {
                    loadingDialog.show();
                }

                @Override
                public void onPostExecute(String result) {
                    loadingDialog.dismiss();
                    if(result!=null){
                        Log.d("===============================", result);
                        createVehiclesList(result);
                    }else{
                        Toast.makeText(MainActivity.this,
                                MainActivity.this.getString(R.string.err_connection_error),
                                 Toast.LENGTH_LONG).show();
                    }
                }
            }.execute(post);
        }else{
            Toast.makeText(MainActivity.this,
                    MainActivity.this.getString(R.string.err_connection_error),
                    Toast.LENGTH_LONG).show();
        }
    }

    private void createVehiclesList(String result){
        try {
            Map<String, Object> response = HTTPRequest.prepareResult(result);
            if(response!=null){
                List<Map> list = (List<Map>) response.get("responseContext");
                Log.d("createVehicleList", list.toString());
                setGoogleMap(list);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public String tempGroupList(){
        return "{\"groups\":\n" +
                "\t\t\t\t[\n" +
                "\t\t\t\t\t{ \"id\": \"1001\", \"name\": \"C-K\" },\n" +
                "\t\t\t\t\t{ \"id\": \"1002\", \"name\": \"C-G\" },\n" +
                "\t\t\t\t\t{ \"id\": \"1003\", \"name\": \"C-N\" },\n" +
                "\t\t\t\t\t{ \"id\": \"1004\", \"name\": \"K-J\" }\n" +
                "\t\t\t\t]}";
    }

    public String tempVehicles(){
        return "{\n" +
                "\t\t\t\"vehicles\":\n" +
                "\t\t\t\t[\n" +
                "\t\t\t\t\t{ \"id\": \"1001\", \"name\": \"Regular\", \"details\": \"1001\", \"latitude\": \"6.952852\",\"longitude\": \"79.921608\" },\n" +
                "\t\t\t\t\t{ \"id\": \"1001\", \"name\": \"Regular\", \"details\": \"1001\", \"latitude\": \"7.324173\",\"longitude\": \"80.396424\" }\n" +
                "\t\t\t\t]\n" +
                "\t\t}";
    }

    private void showGroups(String result) {
        try {
            Map<String, Object> response = HTTPRequest.prepareResult(result);
            if(response!=null){
                List<Map> list = (List<Map>) response.get("responseContext");
//                ArrayAdapter<String> adapter = new ArrayAdapter<String>(getApplicationContext(),
//                        android.R.layout.simple_spinner_item, list);
//                adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
//                spGroups.setAdapter(adapter);
                Log.d("List============",list.toString());
                Dialog dialog = onCreateDialogSingleChoice(list);
                dialog.show();
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public Dialog onCreateDialogSingleChoice(List<Map> list) {
        final List<Integer> ids = new ArrayList<Integer>();
        //Initialize the Alert Dialog
                AlertDialog.Builder builder = new AlertDialog.Builder(this);
        //Source of the data in the DIalog
        String[] arr = new String[list.size()];
        for (int i=0;i<list.size();i++){
            arr[i] = (String)list.get(i).get("name");
            ids.add((Integer) list.get(i).get("id"));
        }
                builder.setTitle("Select Route")
                        .setSingleChoiceItems(arr, 1, new DialogInterface.OnClickListener() {

                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                            }
                        })

                        .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int id) {
                                int selectedPosition = ((AlertDialog)dialog).getListView().getCheckedItemPosition();
                                loadVehicles(ids.get(selectedPosition));
                            }
                        })
                        .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int id) {

                            }
                        });

                return builder.create();
    }

    private List<String> getGroupList(Map<String, Object> response) {
        List<String> groups = new ArrayList<String>();
        //TODO make the list
        return groups;
    }

}
