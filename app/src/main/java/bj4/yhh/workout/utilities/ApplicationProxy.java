package bj4.yhh.workout.utilities;

import android.content.Context;

/**
 * Created by yenhsunhuang on 2016/5/4.
 */
public abstract class ApplicationProxy {
    public ApplicationProxy(Context context) {
    }

    public abstract void onCreate();

    public abstract void onTerminate();
}
