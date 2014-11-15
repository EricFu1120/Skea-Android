package me.linkcube.skea.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RadioGroup;
import android.widget.TextView;

import me.linkcube.skea.R;

/**
 * Created by Ervin on 14/11/13.
 */
public class TwoWayRadioGroup extends LinearLayout {

    private String yes, no;

    private TextView yes_tv, no_tv;

    private RadioGroup radioGroup;

    private String title, introduction;

    private TextView title_tv, introduction_tv;

    private OnTwoWaySelectedListener listener;

    public TwoWayRadioGroup(Context context) {
        super(context);
        init(null, 0);
    }

    public TwoWayRadioGroup(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(attrs, 0);
    }

    private void init(AttributeSet attrs, int defStyle) {
        View view = inflate(this.getContext(), R.layout.radio_group_two_way, this);
        radioGroup = (RadioGroup) view.findViewById(R.id.radio_group);
        no_tv = (TextView) view.findViewById(R.id.no_tv);
        yes_tv = (TextView) view.findViewById(R.id.yes_tv);
        title_tv = (TextView) view.findViewById(R.id.title_tv);
        introduction_tv = (TextView) view.findViewById(R.id.introduction_tv);

        final TypedArray a = getContext().obtainStyledAttributes(
                attrs, R.styleable.CustomRadioGroup, defStyle, 0);
        no = a.getString(R.styleable.CustomRadioGroup_no);
        yes = a.getString(R.styleable.CustomRadioGroup_yes);
        title = a.getString(R.styleable.CustomRadioGroup_radio_title);
        introduction = a.getString(R.styleable.CustomRadioGroup_radio_introduction);

        if (introduction != null && !introduction.equals("")) {
            introduction_tv
                    .setVisibility(VISIBLE);
            introduction_tv.setText(introduction);
        } else {
            introduction_tv.setVisibility(GONE);
        }

        no_tv.setText(no);
        yes_tv.setText(yes);
        title_tv.setText(title);

        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if (listener == null)
                    return;
                switch (checkedId) {
                    case R.id.yes_rb:
                        listener.onTwoWaySelected(true);
                        break;
                    case R.id.no_rb:
                        listener.onTwoWaySelected(false);
                        break;
                    default:
                        break;
                }
            }
        });
    }

    public void setOnTwoWaySelectedListener(OnTwoWaySelectedListener listener) {
        this.listener = listener;
    }

    public interface OnTwoWaySelectedListener {

        void onTwoWaySelected(boolean yes);

    }
}
