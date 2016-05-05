package bj4.yhh.workout;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

/**
 * Created by yenhsunhuang on 2016/5/5.
 */
public class AddTrainDataActivity extends Activity {
    private TextView mDatePicker;

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
    }
}
