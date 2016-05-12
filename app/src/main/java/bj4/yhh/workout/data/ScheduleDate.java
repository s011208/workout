package bj4.yhh.workout.data;

/**
 * Created by yenhsunhuang on 2016/5/12.
 */
public class ScheduleDate {
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
        mId = id;
        mTrainDataId = tId;
        mYear = y;
        mMonth = m;
        mDay = d;
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
}
