package com.titan.baselibrary.util;

import android.content.Context;
import android.location.LocationManager;
import android.os.Build;
import android.provider.Settings;

/**
 * Created by li on 2017/12/21.
 * GpsUtil
 */

public class GpsUtil {

    //打开或者关闭gps
    public static void openGPS(Context context,boolean open) {
        if (Build.VERSION.SDK_INT <19) {
            Settings.Secure.setLocationProviderEnabled(context.getContentResolver(),
                    LocationManager.GPS_PROVIDER, open);
        }else{
            if(!open){
                Settings.Secure.putInt(context.getContentResolver(), Settings.Secure.LOCATION_MODE, android.provider.Settings.Secure.LOCATION_MODE_OFF);
            }else{
                Settings.Secure.putInt(context.getContentResolver(), Settings.Secure.LOCATION_MODE, android.provider.Settings.Secure.LOCATION_MODE_BATTERY_SAVING);
            }
        }
    }

    //判断gps是否处于打开状态
    public static boolean isOpen(Context context) {
        if (Build.VERSION.SDK_INT <19) {
            LocationManager myLocationManager = (LocationManager  )context.getSystemService(Context.LOCATION_SERVICE);
            return myLocationManager.isProviderEnabled(LocationManager.GPS_PROVIDER);
        }else{
            int state = Settings.Secure.getInt(context.getContentResolver(), Settings.Secure.LOCATION_MODE, Settings.Secure.LOCATION_MODE_OFF);
            if(state==Settings.Secure.LOCATION_MODE_OFF){
                return false;
            }else{
                return true;
            }
        }
    }

}
