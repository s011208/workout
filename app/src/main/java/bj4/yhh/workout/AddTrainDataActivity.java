package bj4.yhh.workout;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import bj4.yhh.workout.recycler.adapters.addTrainData.AddTrainDataAdapter;
import bj4.yhh.workout.utilities.Utility;
import bj4.yhh.workout.views.VerticalSpaceItemDecoration;

/**
 * Created by yenhsunhuang on 2016/5/5.
 */
public class AddTrainDataActivity extends Activity {
    private static final String TAG = "AddTrainDataActivity";
    private static final boolean DEBUG = Utility.DEBUG;

    private EditText mWorkoutName;
    private TextView mDatePicker, mConfirmOk, mConfirmCancel;
    private RecyclerView mIntensityDataContainer;
    private AddTrainDataAdapter mAddTrainDataAdapter;
    private TextView mAddMoreIntensity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_train_data);
        initComponents();
    }

    private void initComponents() {
        mWorkoutName = (EditText) findViewById(R.id.workout_name);
        mDatePicker = (TextView) findViewById(R.id.date_picker);
        mDatePicker.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        initIntensityDataContainer();

        mConfirmOk = (TextView) findViewById(R.id.confirm_ok);
        mConfirmOk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mAddTrainDataAdapter.validData()) {
                    mAddTrainDataAdapter.collectData();
                    setResult(Activity.RESULT_OK);
                    finish();
                } else {

                }
            }
        });
        mConfirmCancel = (TextView) findViewById(R.id.confirm_cancel);
        mConfirmCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setResult(Activity.RESULT_CANCELED);
                finish();
            }
        });
    }

    private void initIntensityDataContainer() {
        mIntensityDataContainer = (RecyclerView) findViewById(R.id.intensity_container);
        mAddTrainDataAdapter = new AddTrainDataAdapter(AddTrainDataActivity.this);
        mIntensityDataContainer.setAdapter(mAddTrainDataAdapter);
        mIntensityDataContainer.addItemDecoration(new VerticalSpaceItemDecoration(getResources().getDimensionPixelOffset(R.dimen.activity_add_train_data_item_divider_height)));
        mIntensityDataContainer.setLayoutManager(new LinearLayoutManager(AddTrainDataActivity.this));
        mIntensityDataContainer.setItemAnimator(new DefaultItemAnimator());
        mIntensityDataContainer.setHasFixedSize(true);

        mAddMoreIntensity = (TextView) findViewById(R.id.intensity_add_more);
        mAddMoreIntensity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mAddTrainDataAdapter.addNewItem();
            }
        });
    }
}
