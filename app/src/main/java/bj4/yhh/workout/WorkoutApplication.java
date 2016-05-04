package bj4.yhh.workout;

import android.app.Application;
import android.content.pm.PackageManager;
import android.util.Log;

import bj4.yhh.workout.remote.RemoteApplication;
import bj4.yhh.workout.utilities.ApplicationProxy;
import bj4.yhh.workout.utilities.Utility;

/**
 * Created by yenhsunhuang on 2016/5/4.
 */
public class WorkoutApplication extends Application {
    private static final String TAG = "WorkoutApplication";
    private static final boolean DEBUG = Utility.DEBUG;

    private ApplicationProxy mApplicationProxy;

    @Override
    public void onCreate() {
        super.onCreate();
        final String processName = getProcessName();
        if (DEBUG) {
            Log.v(TAG, "[onCreate] process: " + processName);
        }
        if (processName != null) {
            if (processName.endsWith(":remote")) {
                mApplicationProxy = new RemoteApplication(getApplicationContext());
            }
        }

        if (mApplicationProxy != null) {
            mApplicationProxy.onCreate();
        }
    }

    @Override
    public void onTerminate() {
        super.onTerminate();
        if (mApplicationProxy != null) {
            mApplicationProxy.onTerminate();
        }
    }

    private String getProcessName() {
        try {
            return getPackageManager().getApplicationInfo(getPackageName(), 0).processName;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }
}
