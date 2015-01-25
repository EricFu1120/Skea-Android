package me.linkcube.skea.util;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;

/**
 * Created by CXC on 15-1-25.
 */
public class ActivityTranslateValues {
    public static void start_activity(Activity activity,Class<?> cls){
        Intent startIntent=new Intent();
        startIntent.setClass(activity,cls);
        activity.startActivity(startIntent);
    }


    public static void start_activity_for_result(Activity activity,Class<?> cls,int requestCode,int responseCode){
        Intent startIntent=new Intent();
        startIntent.setClass(activity,cls);
        activity.startActivityForResult(startIntent,requestCode);
    }
}
