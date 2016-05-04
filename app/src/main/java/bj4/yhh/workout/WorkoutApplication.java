package bj4.yhh.workout;

import android.app.Application;
import android.content.Intent;
import android.util.Log;

import bj4.yhh.workout.remote.DataService;
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
        long start = System.currentTimeMillis();
        super.onCreate();
        final String processName = initApplicationProxy();
        if (mApplicationProxy != null) {
            mApplicationProxy.onCreate();
        }
        startRemoteService();
        if (DEBUG) {
            Log.v(TAG, "[onCreate] process: " + processName + ", takes: " + (System.currentTimeMillis() - start));
        }
    }

    @Override
    public void onTerminate() {
        super.onTerminate();
        if (mApplicationProxy != null) {
            mApplicationProxy.onTerminate();
        }
        if (DEBUG) {
            Log.v(TAG, "[onTerminate]");
        }
    }

    private String initApplicationProxy() {
        final String processName = Utility.getProcessName(getApplicationContext());
        if (processName != null) {
            if (processName.equals(getPackageName())) {
                mApplicationProxy = new WorkoutApplicationProxy(getApplicationContext());
            } else if (processName.endsWith(":remote")) {
                mApplicationProxy = new RemoteApplicationProxy(getApplicationContext());
            }
        }
        return processName;
    }

    private void startRemoteService() {
        getApplicationContext().startService(new Intent(getApplicationContext(), DataService.class));
    }
}
