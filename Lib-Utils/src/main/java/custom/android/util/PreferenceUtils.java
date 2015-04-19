package custom.android.util;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.util.Log;

/**
 * @author Ervin
 */
public class PreferenceUtils {

    private final static String PREFS_NAME = "custom.android.util.PreferenceUtils";

    private static SharedPreferences getSharedPreference(Context context) {
        return context.getSharedPreferences(PREFS_NAME, 0);
    }

    public static void clearData(Context context) {
        SharedPreferences sharedPreferences = getSharedPreference(context);
        if (null != sharedPreferences) {
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.clear();
            editor.commit();
        }
    }

    public static void removeData(Context context, String key) {
        SharedPreferences sharedPreferences = getSharedPreference(context);
        if (null != sharedPreferences) {
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.remove(key);
            editor.commit();
        }
    }

    public static boolean contains(Context context, String key) {
        return getSharedPreference(context).contains(key);
    }

    public static int getInt(Context context, String key, int def) {
        if (key == null || key.equals("")) {
            return def;
        }
        return getSharedPreference(context).getInt(key, def);
    }

    public static long getLong(Context context, String key, long def) {
        if (key == null || key.equals("")) {
            return def;
        }
        return getSharedPreference(context).getLong(key, def);
    }


    public static boolean getBoolean(Context context, String key, boolean def) {
        if (key == null || key.equals("")) {
            return def;
        }
        return getSharedPreference(context).getBoolean(key, def);
    }

    public static String getString(Context context, String key, String def) {
        if (key == null || key.equals("")) {
            return def;

        }
        return getSharedPreference(context).getString(key, def);
    }

    public static void setInt(Context context, String key, int value) {
        Editor editor = getSharedPreference(context).edit();
        editor.putInt(key, value);
        editor.commit();
    }

    public static void setLong(Context context, String key, long value) {
        Editor editor = getSharedPreference(context).edit();
        editor.putLong(key, value);
        editor.commit();
    }

    public static void setString(Context context, String key, String value) {
        Editor editor = getSharedPreference(context).edit();
        editor.putString(key, value);
        editor.commit();
    }

    public static void setBoolean(Context context, String key, boolean value) {
        Editor editor = getSharedPreference(context).edit();
        editor.putBoolean(key, value);
        editor.commit();
    }
}