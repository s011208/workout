package bj4.yhh.workout.utilities;

import android.content.Context;
import android.content.pm.PackageManager;

/**
 * Created by yenhsunhuang on 2016/5/4.
 */
public class Utility {
    public static final boolean DEBUG = true;

    private Utility() {
    }

    public static String getProcessName(Context context) {
        try {
            return context.getPackageManager().getApplicationInfo(context.getPackageName(), 0).processName;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }
}
