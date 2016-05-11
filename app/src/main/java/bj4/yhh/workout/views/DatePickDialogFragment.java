package bj4.yhh.workout.views;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.os.Bundle;
import android.widget.DatePicker;

import java.util.Calendar;

/**
 * Created by yenhsunhuang on 2016/5/11.
 */
public class DatePickDialogFragment extends DialogFragment {

    public static final String YEAR = "y";
    public static final String MONTH = "m";
    public static final String DAY = "d";

    public interface Callback {
        void onDateSet(int y, int m, int d);
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        int y, m, d;
        Bundle b = getArguments();
        if (b != null) {
            y = b.getInt(YEAR);
            m = b.getInt(MONTH);
            d = b.getInt(DAY);
        } else {
            Calendar calendar = Calendar.getInstance();
            calendar.setTimeInMillis(System.currentTimeMillis());
            y = calendar.get(Calendar.YEAR);
            m = calendar.get(Calendar.MONTH);
            d = calendar.get(Calendar.DAY_OF_MONTH);
        }
        return new DatePickerDialog(getActivity(), new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                if (getActivity() != null && getActivity() instanceof Callback) {
                    ((Callback) getActivity()).onDateSet(year, monthOfYear, dayOfMonth);
                } else if (getTargetFragment() != null) {
                    getTargetFragment().onActivityResult(getTargetRequestCode(), Activity.RESULT_OK, getActivity().getIntent());
                }
            }
        }, y, m, d);
    }
}
