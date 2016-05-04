package bj4.yhh.workout.remote;

import android.content.Context;

import bj4.yhh.workout.utilities.ApplicationProxy;

/**
 * Created by yenhsunhuang on 2016/5/4.
 */
public class RemoteApplication extends ApplicationProxy {
    public RemoteApplication(Context context) {
        super(context);
    }

    @Override
    public void onCreate() {

    }

    @Override
    public void onTerminate() {

    }
}
