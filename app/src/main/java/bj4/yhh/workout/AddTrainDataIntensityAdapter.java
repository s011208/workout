package bj4.yhh.workout;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.EditText;

import java.util.ArrayList;

import bj4.yhh.workout.data.IntensityData;

/**
 * Created by yenhsunhuang on 2016/5/5.
 */
public class AddTrainDataIntensityAdapter extends BaseAdapter {
    private final Context mContext;
    private final LayoutInflater mInflater;

    private final ArrayList<IntensityData> mIntensityData = new ArrayList<>();

    public AddTrainDataIntensityAdapter(Context context) {
        mContext = context;
        mInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        mIntensityData.add(new IntensityData(0, 0, "kg"));
        mIntensityData.add(new IntensityData(-1, -1, "lb"));
        mIntensityData.add(new IntensityData(-1, -1, "kb"));
        mIntensityData.add(new IntensityData(-1, -1, "km"));
        mIntensityData.add(new IntensityData(0, 0, "kg"));
        mIntensityData.add(new IntensityData(-1, -1, "lb"));
        mIntensityData.add(new IntensityData(-1, -1, "kb"));
        mIntensityData.add(new IntensityData(-1, -1, "km"));
        mIntensityData.add(new IntensityData(0, 0, "kg"));
        mIntensityData.add(new IntensityData(-1, -1, "lb"));
        mIntensityData.add(new IntensityData(-1, -1, "kb"));
        mIntensityData.add(new IntensityData(-1, -1, "km"));
        mIntensityData.add(new IntensityData(0, 0, "kg"));
        mIntensityData.add(new IntensityData(-1, -1, "lb"));
        mIntensityData.add(new IntensityData(-1, -1, "kb"));
        mIntensityData.add(new IntensityData(-1, -1, "km"));
    }

    @Override
    public int getCount() {
        return mIntensityData.size();
    }

    @Override
    public IntensityData getItem(int position) {
        return mIntensityData.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if (convertView == null) {
            holder = new ViewHolder();
            convertView = mInflater.inflate(R.layout.content_add_train_data_intensity, null);
            holder.mIntensity = (EditText) convertView.findViewById(R.id.intensity);
            holder.mUnit = (EditText) convertView.findViewById(R.id.unit);
            holder.mTimes = (EditText) convertView.findViewById(R.id.times);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        IntensityData item = getItem(position);
        holder.mIntensity.setText(String.valueOf(item.getIntensity()));
        holder.mUnit.setText(item.getUnit());
        holder.mTimes.setText(String.valueOf(item.getTimes()));
        return convertView;
    }

    private static class ViewHolder {
        EditText mIntensity, mTimes, mUnit;
    }
}
