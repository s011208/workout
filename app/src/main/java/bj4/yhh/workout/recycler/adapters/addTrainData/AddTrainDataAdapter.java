package bj4.yhh.workout.recycler.adapters.addTrainData;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
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
    public BaseHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new BaseHolder(mInflater.inflate(R.layout.content_add_train_data_intensity, parent, false));
    }

    @Override
    public void onBindViewHolder(BaseHolder holder, final int position) {
        final IntensityData item = getItem(position);
        holder.mIntensity.setText(String.valueOf(item.getIntensity()));
        holder.mTimes.setText(String.valueOf(item.getTimes()));
        holder.mUnit.setText(String.valueOf(item.getUnit()));
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
        mIntensityData.add(new IntensityData(0, 0, "kg"));
        notifyItemInserted(mIntensityData.size());
    }
}
