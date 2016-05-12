package bj4.yhh.workout.data;

import android.os.Parcel;
import android.os.Parcelable;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by yenhsunhuang on 2016/5/12.
 */
public class ScheduleDate implements Parcelable {
    public static final String ID = "id";
    public static final String TRAIN_DATA_ID = "t_id";
    public static final String YEAR = "year";
    public static final String MONTH = "month";
    public static final String DAY = "day";

    private long mId = -1;
    private long mTrainDataId = -1;
    private int mYear, mMonth, mDay;

    public ScheduleDate(int y, int m, int d) {
        this(-1, -1, y, m, d);
    }

    public ScheduleDate(long id, long tId, int y, int m, int d) {
        init(id, tId, y, m, d);
    }

    private void init(long id, long tId, int y, int m, int d) {
        mId = id;
        mTrainDataId = tId;
        mYear = y;
        mMonth = m;
        mDay = d;
    }

    private ScheduleDate(Parcel source) {
        this(source.readString());
    }

    public ScheduleDate(String rawJson) {
        try {
            JSONObject jason = new JSONObject(rawJson);
            init(jason.getLong(ID), jason.getLong(TRAIN_DATA_ID), jason.getInt(YEAR), jason.getInt(MONTH), jason.getInt(DAY));
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public long getId() {
        return mId;
    }

    public long getTrainDataId() {
        return mTrainDataId;
    }

    public int getYear() {
        return mYear;
    }

    public int getMonth() {
        return mMonth;
    }

    public int getDay() {
        return mDay;
    }

    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        try {
            json.put(ID, mId);
            json.put(TRAIN_DATA_ID, mTrainDataId);
            json.put(YEAR, mYear);
            json.put(MONTH, mMonth);
            json.put(DAY, mDay);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return json;
    }

    public String toString() {
        return toJson().toString();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(toString());
    }

    public static final Parcelable.Creator<ScheduleDate> CREATOR = new Parcelable.Creator<ScheduleDate>() {

        @Override
        public ScheduleDate createFromParcel(Parcel source) {
            return new ScheduleDate(source);
        }

        @Override
        public ScheduleDate[] newArray(int size) {
            return new ScheduleDate[size];
        }
    };
}
