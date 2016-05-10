package bj4.yhh.workout.recycler.adapters.addTrainData;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

import bj4.yhh.workout.R;
import bj4.yhh.workout.data.IntensityData;
import bj4.yhh.workout.recycler.adapters.addTrainData.holders.BaseHolder;

/**
 * Created by User on 2016/5/6.
 */
public class AddTrainDataAdapter extends RecyclerView.Adapter<BaseHolder> {
    private final Context mContext;
    private final LayoutInflater mInflater;
    private final ArrayList<IntensityData> mIntensityData = new ArrayList<>();

    public AddTrainDataAdapter(Context context) {
        mContext = context;
        mInflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        mIntensityData.add(new IntensityData(-1, -1, ""));
    }

    @Override
    public BaseHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new BaseHolder(mInflater.inflate(R.layout.content_add_train_data_intensity, parent, false));
    }

    @Override
    public void onBindViewHolder(BaseHolder holder, final int position) {
        final IntensityData item = getItem(position);
        String intensity = "";
        String times = "";
        String unit = "";
        if (item.getIntensity() > 0) {
            intensity = String.valueOf(item.getIntensity());
        }
        if (item.getTimes() >= 0) {
            times = String.valueOf(item.getTimes());
        }
        if (!TextUtils.isEmpty(item.getUnit())) {
            unit = item.getUnit();
        }
        holder.mIntensity.setText(intensity);
        holder.mTimes.setText(times);
        holder.mUnit.setText(unit);
        holder.mRemoveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final int indexOfItem = mIntensityData.indexOf(item);
                mIntensityData.remove(indexOfItem);
                notifyItemRemoved(indexOfItem);
            }
        });
    }

    public IntensityData getItem(int position) {
        return mIntensityData.get(position);
    }

    @Override
    public int getItemCount() {
        return mIntensityData.size();
    }

    public void addNewItem() {
        mIntensityData.add(new IntensityData(-1, -1, ""));
        notifyItemInserted(mIntensityData.size());
    }

    public boolean validData() {
        return false;
    }

    public void collectData() {

    }
}
