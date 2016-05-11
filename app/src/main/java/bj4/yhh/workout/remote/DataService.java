package bj4.yhh.workout.remote;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.os.RemoteException;
import android.support.annotation.Nullable;
import android.util.Log;

import bj4.yhh.workout.data.IDataService;
import bj4.yhh.workout.data.TrainData;
import bj4.yhh.workout.utilities.Utility;

/**
 * Created by yenhsunhuang on 2016/5/4.
 */
public class DataService extends Service {
    private static final String TAG = "DataService";
    private static final boolean DEBUG = Utility.DEBUG;

    private IDataService.Stub mStub = new IDataService.Stub() {
        @Override
        public TrainData[] getAllTrainData() throws RemoteException {
            return new TrainData[0];
        }

        @Override
        public void addTrainData(TrainData data) throws RemoteException {
            if (DEBUG) {
                Log.d(TAG, "addTrainData, data: " + data);
            }
        }
    };

    @Override
    public void onCreate() {
        super.onCreate();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        return Service.START_STICKY_COMPATIBILITY;
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return mStub;
    }
}
