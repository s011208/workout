package bj4.yhh.workout.remote;

import android.app.Service;
import android.content.ContentUris;
import android.content.Intent;
import android.net.Uri;
import android.os.Handler;
import android.os.IBinder;
import android.os.RemoteException;
import android.support.annotation.Nullable;
import android.util.Log;

import java.util.ArrayList;

import bj4.yhh.workout.data.IDataService;
import bj4.yhh.workout.data.TrainData;
import bj4.yhh.workout.remote.provider.DataProvider;
import bj4.yhh.workout.utilities.Utility;

/**
 * Created by yenhsunhuang on 2016/5/4.
 */
public class DataService extends Service {
    private static final String TAG = "DataService";
    private static final boolean DEBUG = Utility.DEBUG;

    private final ArrayList<TrainData> mData = new ArrayList<>();
    private final Handler mHandler = new Handler();

    private IDataService.Stub mStub = new IDataService.Stub() {
        @Override
        public TrainData[] getAllTrainData() throws RemoteException {
            return new TrainData[0];
        }

        @Override
        public void addTrainData(final TrainData data) throws RemoteException {
            if (DEBUG) {
                Log.d(TAG, "addTrainData, data: " + data);
            }
            if (data == null) return;
            Uri rtn = getContentResolver().insert(DataProvider.URI_TRAIN_DATA, data.toContentValues());
            data.setId(ContentUris.parseId(rtn));
            mHandler.post(new Runnable() {
                @Override
                public void run() {
                    if (DEBUG) {
                        Log.d(TAG, "add data: " + data.getId());
                    }
                    mData.add(data);
                }
            });
        }
    };

    @Override
    public void onCreate() {
        super.onCreate();
        reloadAllTrainData();
    }

    private void reloadAllTrainData() {
        mData.clear();
        mData.addAll(TrainData.getFromCursor(getContentResolver().query(DataProvider.URI_TRAIN_DATA, null, null, null, null)));
        if (DEBUG) {
            for (TrainData data : mData)
                Log.d(TAG, "data: " + data.toString());
        }
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
