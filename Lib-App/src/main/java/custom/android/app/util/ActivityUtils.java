package custom.android.app.util;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.util.DisplayMetrics;

import java.util.Locale;

import static android.content.Intent.FLAG_ACTIVITY_CLEAR_TOP;
import static android.content.Intent.FLAG_ACTIVITY_SINGLE_TOP;

/**
 * Utilities for an {@link Activity}
 */
public class ActivityUtils {

    /**
     * Finish the given activity and start a home activity class.
     * <p/>
     * This mirror the behavior of the home action bar button that clears the
     * current activity and starts or brings another activity to the top.
     *
     * @param activity
     * @param homeActivityClass
     */
    public static void goHome(Activity activity, Class<?> homeActivityClass) {
        activity.finish();
        Intent intent = new Intent(activity, homeActivityClass);
        intent.addFlags(FLAG_ACTIVITY_CLEAR_TOP | FLAG_ACTIVITY_SINGLE_TOP);
        activity.startActivity(intent);
    }

    public static void switchLanguage(Context context, String language) {
        Configuration config = context.getResources().getConfiguration();// 获得设置对象
        Resources resources = context.getResources();// 获得res资源对象
        DisplayMetrics dm = resources.getDisplayMetrics();// 获得屏幕参数：主要是分辨率，像素等。
        if (language.equals("Chinese")) {
            config.locale = Locale.ENGLISH;
        } else if (language.equals("English")) {
            config.locale = Locale.CHINA;
        }
        resources.updateConfiguration(config, dm);
    }
}
