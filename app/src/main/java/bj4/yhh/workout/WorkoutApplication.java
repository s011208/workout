package bj4.yhh.workout;

import android.app.Application;
import android.util.Log;

import bj4.yhh.workout.remote.RemoteApplicationProxy;
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
        initApplicationProxy();
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

    private void initApplicationProxy() {
        final String processName = Utility.getProcessName(getApplicationContext());
        if (DEBUG) {
            Log.v(TAG, "[onCreate] process: " + processName);
        }
        if (processName != null) {
            if (processName.equals(getPackageName())) {
                mApplicationProxy = new WorkoutApplicationProxy(getApplicationContext());
            } else if (processName.endsWith(":remote")) {
                mApplicationProxy = new RemoteApplicationProxy(getApplicationContext());
            }
        }
    }
}
