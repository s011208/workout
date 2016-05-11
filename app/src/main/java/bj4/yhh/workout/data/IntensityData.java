package bj4.yhh.workout.data;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by yenhsunhuang on 2016/5/4.
 */
public class IntensityData {
    private static final String INTENSITY = "intensity";
    private static final String TIMES = "times";

    private int mIntensity;
    private int mTimes;

    public IntensityData(int intensity, int times) {
        mIntensity = intensity;
        mTimes = times;
    }

    public IntensityData(JSONObject json) throws JSONException {
        this(json.getInt(INTENSITY), json.getInt(TIMES));
    }

    public int getIntensity() {
        return mIntensity;
    }

    public void setIntensity(int mIntensity) {
        this.mIntensity = mIntensity;
    }

    public int getTimes() {
        return mTimes;
    }

    public void setTimes(int mTimes) {
        this.mTimes = mTimes;
    }

    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        try {
            json.put(INTENSITY, mIntensity);
            json.put(TIMES, mTimes);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return json;
    }

    @Override
    public String toString() {
        return toJson().toString();
    }

    public static IntensityData fromJson(String raw) {
        IntensityData rtn = null;
        try {
            rtn = new IntensityData(new JSONObject(raw));
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return rtn;
    }
}
