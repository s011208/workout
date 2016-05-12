package bj4.yhh.workout.remote;

import android.app.Service;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.Intent;
import android.net.Uri;
import android.os.Handler;
import android.os.IBinder;
import android.os.RemoteException;
import android.support.annotation.Nullable;
import android.util.Log;

import java.util.ArrayList;

import bj4.yhh.workout.data.IDataService;
import bj4.yhh.workout.data.ScheduleDate;
import bj4.yhh.workout.data.TrainData;
import bj4.yhh.workout.remote.provider.DataProvider;
import bj4.yhh.workout.utilities.Utility;

/**
 * Created by yenhsunhuang on 2016/5/4.
 */
public class DataService extends Service {
    private static final String TAG = "DataService";
    private static final boolean DEBUG = Utility.DEBUG;

    private final ArrayList<TrainData> mDataLock = new ArrayList<>();
    private final Handler mHandler = new Handler();

    private IDataService.Stub mStub = new IDataService.Stub() {
        @Override
        public TrainData[] getAllTrainData() throws RemoteException {
            synchronized (mDataLock) {
                return mDataLock.toArray(new TrainData[mDataLock.size()]);
            }
        }

        @Override
        public ScheduleDate addTrainData(final TrainData data, int y, int m, int d) throws RemoteException {
            if (DEBUG) {
                Log.d(TAG, "addTrainData, data: " + data);
            }
            if (data == null) return null;
            Uri rtn = getContentResolver().insert(DataProvider.URI_TRAIN_DATA, data.toContentValues());
            data.setId(ContentUris.parseId(rtn));
            if (DEBUG) {
                Log.d(TAG, "add data: " + data.getId());
            }
            synchronized (mDataLock) {
                mDataLock.add(data);
            }
            ContentValues cv = new ContentValues();
            cv.put(ScheduleDate.YEAR, y);
            cv.put(ScheduleDate.MONTH, m);
            cv.put(ScheduleDate.DAY, d);
            cv.put(ScheduleDate.TRAIN_DATA_ID, data.getId());
            rtn = getContentResolver().insert(DataProvider.URI_SCHEDULE_DATE, cv);
            final long scheduleDateId = ContentUris.parseId(rtn);
            return new ScheduleDate(scheduleDateId, data.getId(), y, m, d);
        }
    };

    @Override
    public void onCreate() {
        super.onCreate();
        reloadAllTrainData();
    }

    private void reloadAllTrainData() {
        synchronized (mDataLock) {
            mDataLock.clear();
            mDataLock.addAll(TrainData.getFromCursor(getContentResolver().query(DataProvider.URI_TRAIN_DATA, null, null, null, null)));
            if (DEBUG) {
                for (TrainData data : mDataLock)
                    Log.d(TAG, "data: " + data.toString());
            }
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
