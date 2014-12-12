/**
 * Create HTTP requests
 * */
package com.winter.codefest.SocialMap.util;

import android.content.Context;
import android.util.Log;
import com.winter.codefest.SocialMap.model.Response;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONException;
import org.json.JSONObject;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Iterator;
import java.util.Map;

public class HTTPRequest {

    private static final String TAG = "HTTPRequest";// for log
    public static final String PARAMS = "data";

    /**
     * @param data post data
     * @param uri            URL
     * @return String response from the server
     */
    public static String post(Context context,String uri, Map data) throws Exception {
        try {
            HttpClient client = new DefaultHttpClient();
            HttpPost request = new HttpPost(uri);
            request.setHeader("Content-type", "application/json");
            JSONObject holder = prepareJsonObject(data);
            Log.d(TAG, "json request: "+holder.toString());
            request.setEntity(new StringEntity(holder.toString()));
            HttpResponse response = client.execute(request);

            InputStream ips = response.getEntity().getContent();
            BufferedReader buf = new BufferedReader(new InputStreamReader(ips, "UTF-8"));
            if (response.getStatusLine().getStatusCode() != HttpStatus.SC_OK) {
                Log.d(TAG, response.getStatusLine().getReasonPhrase());
            }
            StringBuilder sb = new StringBuilder();
            String s;
            while (true) {
                s = buf.readLine();
                if (s == null || s.length() == 0)
                    break;
                sb.append(s);
            }

            if (s != null)
                Log.d(TAG, s);

            buf.close();
            ips.close();
            return sb.toString();
        }catch (Exception e){
            Log.d(TAG, e.toString());
            return null;
        }
    }

    private static JSONObject getJsonObjectFromMap(Map params) throws JSONException {
        Iterator iter = params.entrySet().iterator();
        JSONObject holder = new JSONObject();
        while (iter.hasNext()) {
            Map.Entry pairs = (Map.Entry) iter.next();
            holder.put((String) pairs.getKey(), pairs.getValue());
        }
        return holder;
    }

    private static JSONObject prepareJsonObject(Map params){
        JSONObject jsonObject = null;
        try {
            jsonObject = new JSONObject();
            jsonObject.put(PARAMS, getJsonObjectFromMap(params));
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return jsonObject;
    }

    public static Map prepareResult(String jsonString) throws JSONException {
        if(jsonString!=null)
            return Json2Map.convertJ2M(new JSONObject(jsonString));
        else
            return null;
    }

    @Deprecated
    private static Response prepareResultMap(Map map){
        Response response = new Response();

        //TODO generate response from map

        Log.d(TAG, "response: "+response.toString());

        return response;
    }
}
