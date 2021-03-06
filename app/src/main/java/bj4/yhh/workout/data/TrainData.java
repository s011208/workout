package bj4.yhh.workout.data;

import android.content.ContentValues;
import android.database.Cursor;
import android.os.Parcel;
import android.os.Parcelable;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by yenhsunhuang on 2016/5/4.
 */
public class TrainData implements Parcelable {
    public static final int STATUS_NONE = 0;
    public static final int STATUS_STARTING = 1;
    public static final int STATUS_PAUSE = 2;
    public static final int STATUS_FINISH = 3;
    public static final int STATUS_BREAK_TIME = 4;

    public static final String ID = "id";
    public static final String TRAIN_TITLE = "train_title";
    public static final String TRAIN_IMAGE_SOURCE = "train_image_source";
    public static final String TOTAL_TIME = "total_time";
    public static final String LAP_TIME = "lap_time";
    public static final String STATUS = "status";
    public static final String INTENSITY_DATA = "intensity_data";
    public static final String UNIT = "unit";

    private long mId = -1;

    private String mTrainTitle = "";
    private String mTrainImageSource = "";
    private String mTotalTime = "";
    private String mLapTime = "";
    private String mUnit = "";
    private int mStatus = STATUS_NONE;
    private final ArrayList<IntensityData> mIntensityData = new ArrayList<>();

    public TrainData(String trainTitle, ArrayList<IntensityData> data, String unit) {
        mTrainTitle = trainTitle;
        mIntensityData.addAll(data);
        mUnit = unit;
    }

    public TrainData(String rawJson) {
        fromJson(rawJson);
    }

    public ContentValues toContentValues() {
        ContentValues cv = new ContentValues();
        if (getId() != -1) {
            cv.put(ID, getId());
        }
        cv.put(TRAIN_TITLE, getTrainTitle());
        cv.put(TRAIN_IMAGE_SOURCE, getTrainImageSource());
        cv.put(TOTAL_TIME, getTotalTime());
        cv.put(LAP_TIME, getLapTime());
        cv.put(STATUS, getStatus());
        cv.put(UNIT, getUnit());
        JSONArray jArray = new JSONArray();
        for (IntensityData data : getIntensityData()) {
            jArray.put(data.toJson());
        }
        cv.put(INTENSITY_DATA, jArray.toString());
        return cv;
    }

    private void fromJson(String rawJson) {
        try {
            JSONObject json = new JSONObject(rawJson);
            setId(json.getLong(ID));
            setLapTime(json.getString(LAP_TIME));
            setStatus(json.getInt(STATUS));
            setTotalTime(json.getString(TOTAL_TIME));
            setTrainImageSource(json.getString(TRAIN_IMAGE_SOURCE));
            setTrainTitle(json.getString(TRAIN_TITLE));
            setUnit(json.getString(UNIT));
            JSONArray jArray = json.getJSONArray(INTENSITY_DATA);
            for (int i = 0; i < jArray.length(); ++i) {
                mIntensityData.add(new IntensityData(jArray.getJSONObject(i)));
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    private TrainData(Parcel source) {
        readFromParcel(source);
    }

    public String getLapTime() {
        return mLapTime;
    }

    public void setLapTime(String time) {
        mLapTime = time;
    }

    public String getTotalTime() {
        return mTotalTime;
    }

    public void setTotalTime(String time) {
        mTotalTime = time;
    }

    public String getTrainImageSource() {
        return mTrainImageSource;
    }

    public void setTrainImageSource(String source) {
        mTrainImageSource = source;
    }

    public String getTrainTitle() {
        return mTrainTitle;
    }

    public void setTrainTitle(String title) {
        mTrainTitle = title;
    }

    public long getId() {
        return mId;
    }

    public void setId(long id) {
        mId = id;
    }

    public int getStatus() {
        return mStatus;
    }

    public void setStatus(int mStatus) {
        this.mStatus = mStatus;
    }


    public String getUnit() {
        return mUnit;
    }

    public void setUnit(String mUnit) {
        this.mUnit = mUnit;
    }


    public ArrayList<IntensityData> getIntensityData() {
        return mIntensityData;
    }


    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        try {
            json.put(ID, getId());
            json.put(TRAIN_TITLE, getTrainTitle());
            json.put(TRAIN_IMAGE_SOURCE, getTrainImageSource());
            json.put(TOTAL_TIME, getTotalTime());
            json.put(LAP_TIME, getLapTime());
            json.put(STATUS, getStatus());
            json.put(UNIT, getUnit());
            JSONArray jArray = new JSONArray();
            for (IntensityData data : getIntensityData()) {
                jArray.put(data.toJson());
            }
            json.put(INTENSITY_DATA, jArray);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return json;
    }

    @Override
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

    public void readFromParcel(Parcel source) {
        fromJson(source.readString());
    }

    public static final Parcelable.Creator<TrainData> CREATOR = new Parcelable.Creator<TrainData>() {

        @Override
        public TrainData createFromParcel(Parcel source) {
            return new TrainData(source);
        }

        @Override
        public TrainData[] newArray(int size) {
            return new TrainData[size];
        }
    };

    public static ArrayList<TrainData> getFromCursor(Cursor raw) {
        ArrayList<TrainData> rtn = new ArrayList<>();
        if (raw == null) return rtn;
        try {
            final int indexOfId = raw.getColumnIndex(TrainData.ID);
            final int indexOfTrainTitle = raw.getColumnIndex(TrainData.TRAIN_TITLE);
            final int indexOfTrainImageSource = raw.getColumnIndex(TrainData.TRAIN_IMAGE_SOURCE);
            final int indexOfTotalTime = raw.getColumnIndex(TrainData.TOTAL_TIME);
            final int indexOfLapTime = raw.getColumnIndex(TrainData.LAP_TIME);
            final int indexOfStatus = raw.getColumnIndex(TrainData.STATUS);
            final int indexOfIntensityData = raw.getColumnIndex(TrainData.INTENSITY_DATA);
            final int indexOfUnit = raw.getColumnIndex(TrainData.UNIT);
            while (raw.moveToNext()) {
                long id = raw.getLong(indexOfId);
                String trainTitle = raw.getString(indexOfTrainTitle);
                String trainTrainImageSource = raw.getString(indexOfTrainImageSource);
                String trainTotalTime = raw.getString(indexOfTotalTime);
                String trainLapTime = raw.getString(indexOfLapTime);
                String trainIntensityData = raw.getString(indexOfIntensityData);
                String trainUnit = raw.getString(indexOfUnit);
                int status = raw.getInt(indexOfStatus);
                ArrayList<IntensityData> intensity = IntensityData.fromJsonArray(trainIntensityData);
                TrainData trainData = new TrainData(trainTitle, intensity, trainUnit);
                trainData.setId(id);
                trainData.setTrainImageSource(trainTrainImageSource);
                trainData.setTotalTime(trainTotalTime);
                trainData.setLapTime(trainLapTime);
                trainData.setStatus(status);
                rtn.add(trainData);
            }
        } finally {
            raw.close();
        }
        return rtn;
    }
}
