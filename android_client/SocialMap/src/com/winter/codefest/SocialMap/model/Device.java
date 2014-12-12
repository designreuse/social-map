/**
 * Settings.Secure.ANDROID_ID is used as the unique device ID for android device.
 * Note that this may likely to be changed at factory reset. After that device have to be re registered with the system
 * */
package com.winter.codefest.SocialMap.model;

import android.provider.Settings;

/**
 * Created by Thilina on 12/12/2014.
 */
public class Device {
    private String group;
    private String code;

    public static String getDeviceId() {
        return Settings.Secure.ANDROID_ID;
    }

    public String getGroup() {
        return group;
    }

    public void setGroup(String group) {
        this.group = group;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
}
