package bj4.yhh.workout.recycler.adapters.addTrainData.holders;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;

import bj4.yhh.workout.R;

/**
 * Created by User on 2016/5/6.
 */
public class BaseHolder extends RecyclerView.ViewHolder {
    public EditText mIntensity, mTimes;
    public ImageView mRemoveButton;

    public BaseHolder(View itemView) {
        super(itemView);
        mIntensity = (EditText) itemView.findViewById(R.id.intensity);
        mTimes = (EditText) itemView.findViewById(R.id.times);
        mRemoveButton = (ImageView) itemView.findViewById(R.id.remove_button);
    }
}
