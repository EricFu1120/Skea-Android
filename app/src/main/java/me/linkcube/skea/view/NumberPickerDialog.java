package me.linkcube.skea.view;

import android.content.Context;
import android.view.Gravity;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import custom.android.widget.nubmerpicker.NumberPicker;

/**
 * Created by ervinwang on 2014/12/18.
 */
public class NumberPickerDialog {

    private Context mContext;

    private PopupWindow popupWindow;

    private View parent;

    private OnValueChangedListener listener;

    private int mOldValue, mNewValue;

    public void setOnValueChangedListener(OnValueChangedListener listener) {
        this.listener = listener;
    }

    public NumberPickerDialog(Context context, View parent) {
        this.mContext = context;
        this.parent = parent;
    }

    public NumberPickerDialog configurePicker(int maxValue, int minValue) {

        NumberPicker np = new NumberPicker(mContext);
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
        popupWindow = new PopupWindow(np, LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT, true);
        popupWindow.setAnimationStyle(android.R.style.Animation_Dialog);
        popupWindow.setOutsideTouchable(false);
        return this;
    }

    public void show() {
        if (popupWindow != null)
            popupWindow.showAtLocation(parent, Gravity.CENTER, 0, 0);
    }


    public void dismiss() {
        if (popupWindow != null && popupWindow.isShowing()) {
            popupWindow.dismiss();
            popupWindow = null;
        }

    }


    public interface OnValueChangedListener {
        public void onValueChange(View parent, int oldVal, int newVal);

    }
}
