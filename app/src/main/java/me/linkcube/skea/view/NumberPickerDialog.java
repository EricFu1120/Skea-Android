package me.linkcube.skea.view;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;

import custom.android.widget.nubmerpicker.NumberPicker;
import me.linkcube.skea.R;

/**
 * Created by ervinwang on 2014/12/18.
 */
public class NumberPickerDialog {

    private Context mContext;

    private Dialog dialog;

    private View parent;

    private OnValueChangedListener listener;

    private int mOldValue, mNewValue;

    public void setOnValueChangedListener(OnValueChangedListener listener) {
        this.listener = listener;
    }

    public NumberPickerDialog(Context context) {
        this.mContext = context;


    }

    public NumberPickerDialog configurePicker(View parent, int maxValue, int minValue) {
        this.parent = parent;
        LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(
                Context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.dialog_number_picker, null);
        NumberPicker np = (NumberPicker) view.findViewById(R.id.number_picker);
        np.setMaxValue(maxValue);
        np.setMinValue(minValue);
        np.setFocusable(true);
        np.setFocusableInTouchMode(true);
        np.setOnValueChangedListener(new NumberPicker.OnValueChangeListener() {
            @Override
            public void onValueChange(NumberPicker picker, int oldVal, int newVal) {
                mOldValue = oldVal;
                mNewValue = newVal;
            }
        });
        dialog = new AlertDialog.Builder(mContext).setView(view).create();
//        popupWindow = new PopupWindow(view, LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT, true);
//        popupWindow.setAnimationStyle(android.R.style.Animation_Dialog);
//        popupWindow.setOutsideTouchable(false);
        return this;
    }

    public void show() {
        if (dialog != null && !dialog.isShowing())
            dialog.show();
    }


    public void dismiss() {
        if (dialog != null && dialog.isShowing()) {
            dialog.dismiss();
        }

    }


    public interface OnValueChangedListener {
        public void onValueChange(View parent, int oldVal, int newVal);

    }
}
