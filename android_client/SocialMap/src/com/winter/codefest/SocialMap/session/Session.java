package com.winter.codefest.SocialMap.session;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

/**
 * Created by sadika on 12/13/14.
 */
public class Session {
    private static void saveValue(Context context, String key, String value){
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(key, value);
        editor.commit();
    }
    private static String loadValue(Context context, String key){
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);
        return sharedPreferences.getString(key, null);
    }

    public static void setRegistered(Context context){
        saveValue(context, "registered", "registered");
    }

    public  static boolean isRegistered(Context context){
        return loadValue(context, "registered")!=null;
    }
}
