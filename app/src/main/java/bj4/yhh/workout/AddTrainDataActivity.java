package bj4.yhh.workout;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Calendar;

import bj4.yhh.workout.data.IntensityData;
import bj4.yhh.workout.data.TrainData;
import bj4.yhh.workout.recycler.adapters.addTrainData.AddTrainDataAdapter;
import bj4.yhh.workout.remote.provider.DataProvider;
import bj4.yhh.workout.utilities.Utility;
import bj4.yhh.workout.views.DatePickDialogFragment;
import bj4.yhh.workout.views.VerticalSpaceItemDecoration;

/**
 * Created by yenhsunhuang on 2016/5/5.
 */
public class AddTrainDataActivity extends Activity implements DatePickDialogFragment.Callback {
    private static final String TAG = "AddTrainDataActivity";
    private static final boolean DEBUG = Utility.DEBUG;

    private long mDate = System.currentTimeMillis();
    private AutoCompleteTextView mWorkoutName, mUnit;
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
        mWorkoutName = (AutoCompleteTextView) findViewById(R.id.workout_name);
        mUnit = (AutoCompleteTextView) findViewById(R.id.unit);
        mUnit.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                loadUnitAdapterAsync(s.toString());
            }
        });
        mDatePicker = (TextView) findViewById(R.id.date_picker);
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(mDate);
        mDatePicker.setText(calendar.get(Calendar.YEAR) + "." + (calendar.get(Calendar.MONTH) + 1) + "." + calendar.get(Calendar.DAY_OF_MONTH));
        mDatePicker.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatePickDialogFragment dialog = new DatePickDialogFragment();
                dialog.show(getFragmentManager(), dialog.getClass().getName());
            }
        });
        initIntensityDataContainer();

        mConfirmOk = (TextView) findViewById(R.id.confirm_ok);
        mConfirmOk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isDataValid()) {
                    TrainData data = collectData();
                    if (data == null) {
                        setResult(Activity.RESULT_CANCELED);
                        finish();
                    } else {
                        Intent intent = new Intent();
                        intent.putExtra(WorkoutActivity.INTENT_TRAIN_DATA, data.toString());
                        intent.putExtra(WorkoutActivity.INTENT_DATE, mDate);
                        setResult(Activity.RESULT_OK, intent);
                        finish();
                    }
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

    private boolean isDataValid() {
        if (TextUtils.isEmpty(mWorkoutName.getText())) {
            mWorkoutName.setPressed(true);
            Toast.makeText(AddTrainDataActivity.this, R.string.activity_add_train_data_toast_fill_up_workout_name, Toast.LENGTH_LONG).show();
            return false;
        }
        if (TextUtils.isEmpty(mUnit.getText())) {
            mUnit.setPressed(true);
            Toast.makeText(AddTrainDataActivity.this, R.string.activity_add_train_data_toast_fill_up_intensity_unit, Toast.LENGTH_LONG).show();
            return false;
        }
        if (mDate == 0) {
            mDatePicker.setPressed(true);
            Toast.makeText(AddTrainDataActivity.this, R.string.activity_add_train_data_toast_pick_up_a_date, Toast.LENGTH_LONG).show();
            return false;
        }
        if (!mAddTrainDataAdapter.validData()) {
            if (DEBUG) {
                Log.d(TAG, "mAddTrainDataAdapter invalid");
            }
            return false;
        }
        return true;
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

    private TrainData collectData() {
        ArrayList<IntensityData> dataList = new ArrayList<>();
        for (int i = 0; i < mAddTrainDataAdapter.getItemCount(); ++i) {
            IntensityData data = mAddTrainDataAdapter.getItem(i);
            dataList.add(data);
        }
        TrainData data = new TrainData(mWorkoutName.getText().toString(), dataList, mUnit.getText().toString());
        if (DEBUG) {
            Log.d(TAG, "data: " + data.toString());
        }
        return data;
    }

    @Override
    public void onDateSet(int y, int m, int d) {
        Calendar calendar = Calendar.getInstance();
        calendar.set(y, m, d);
        mDate = calendar.getTimeInMillis();
        mDatePicker.setText(y + "." + (m + 1) + "." + d);

        if (DEBUG) {
            Log.d(TAG, "mDate: " + mDate);
        }
    }

    private void loadUnitAdapterAsync(final String txt) {
        new AsyncTask<Void, Void, ArrayAdapter>() {
            @Override
            protected ArrayAdapter doInBackground(Void... params) {
                final ArrayList<String> data = new ArrayList<>();
                ArrayAdapter mAdapter = null;
                Cursor raw = getContentResolver().query(DataProvider.URI_TRAIN_DATA_DISTINCT_UNIT, null, null, null, null);
                if (raw != null) {
                    try {
                        while (raw.moveToNext()) {
                            String item = raw.getString(0);
                            if (item.contains(txt)) {
                                data.add(item);
                            }
                        }
                    } finally {
                        raw.close();
                    }
                }
                if (!data.isEmpty()) {
                    mAdapter = new ArrayAdapter(AddTrainDataActivity.this, android.R.layout.simple_dropdown_item_1line, data);
                }
                return mAdapter;
            }

            @Override
            protected void onPostExecute(ArrayAdapter adapter) {
                if (DEBUG) {
                    if (adapter != null) {
                        Log.v(TAG, "adapter item count: " + adapter.getCount());
                    } else {
                        Log.v(TAG, "adapter is null");
                    }
                }
                mUnit.setAdapter(adapter);
            }
        }.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
    }
}
