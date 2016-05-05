package bj4.yhh.workout;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.ListView;
import android.widget.TextView;

import bj4.yhh.workout.utilities.Utility;

/**
 * Created by yenhsunhuang on 2016/5/5.
 */
public class AddTrainDataActivity extends Activity {
    private TextView mDatePicker;
    private ListView mIntensityDataContainer;
    private AddTrainDataIntensityAdapter mAddTrainDataIntensityAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_train_data);
        initComponents();
    }

    private void initComponents() {
        mDatePicker = (TextView) findViewById(R.id.date_picker);
        mDatePicker.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        initIntensityDataContainer();
    }

    private void initIntensityDataContainer() {
        mIntensityDataContainer = (ListView) findViewById(R.id.intensity_container);
        mAddTrainDataIntensityAdapter = new AddTrainDataIntensityAdapter(AddTrainDataActivity.this);
        mIntensityDataContainer.setAdapter(mAddTrainDataIntensityAdapter);
        mIntensityDataContainer.getViewTreeObserver().addOnPreDrawListener(new ViewTreeObserver.OnPreDrawListener() {
            @Override
            public boolean onPreDraw() {
                if (mIntensityDataContainer.getViewTreeObserver().isAlive()) {
                    mIntensityDataContainer.getViewTreeObserver().removeOnPreDrawListener(this);
                }
                Utility.setListViewHeightBasedOnChildren(mIntensityDataContainer);
                return false;
            }
        });
    }
}
