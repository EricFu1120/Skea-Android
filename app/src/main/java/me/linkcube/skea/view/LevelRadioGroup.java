package me.linkcube.skea.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RadioGroup;
import android.widget.TextView;

import me.linkcube.skea.R;

/**
 * Created by Ervin on 14/11/13.
 */
public class LevelRadioGroup extends LinearLayout {

    private String level1, level2, level3, level4, level5;

    private TextView level1_tv, level2_tv, level3_tv, level4_tv, level5_tv;

    private RadioGroup radioGroup;

    private OnLevelSelectedListener listener;

    public LevelRadioGroup(Context context) {
        super(context);
        init(null, 0);
    }

    public LevelRadioGroup(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(attrs, 0);
    }

    private void init(AttributeSet attrs, int defStyle) {
        View view = inflate(this.getContext(), R.layout.level_radio_group, this);
        radioGroup = (RadioGroup) view.findViewById(R.id.radio_group);
        level1_tv = (TextView) view.findViewById(R.id.level1_tv);
        level2_tv = (TextView) view.findViewById(R.id.level2_tv);
        level3_tv = (TextView) view.findViewById(R.id.level3_tv);
        level4_tv = (TextView) view.findViewById(R.id.level4_tv);
        level5_tv = (TextView) view.findViewById(R.id.level5_tv);

        final TypedArray a = getContext().obtainStyledAttributes(
                attrs, R.styleable.LevelRadioGroup, defStyle, 0);
        level1 = a.getString(R.styleable.LevelRadioGroup_level1);
        level2 = a.getString(R.styleable.LevelRadioGroup_level2);
        level3 = a.getString(R.styleable.LevelRadioGroup_level3);
        level4 = a.getString(R.styleable.LevelRadioGroup_level4);
        level5 = a.getString(R.styleable.LevelRadioGroup_level5);

        level1_tv.setText(level1);
        level2_tv.setText(level2);
        level3_tv.setText(level3);
        level4_tv.setText(level4);
        level5_tv.setText(level5);

        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if (listener == null)
                    return;
                switch (checkedId) {
                    case R.id.level1_rb:
                        listener.onLevelSelected(0);
                        break;
                    case R.id.level2_rb:
                        listener.onLevelSelected(1);
                        break;
                    case R.id.level3_rb:
                        listener.onLevelSelected(2);
                        break;
                    case R.id.level4_rb:
                        listener.onLevelSelected(3);
                        break;
                    case R.id.level5_rb:
                        listener.onLevelSelected(4);
                        break;
                    default:
                        break;
                }
            }
        });
    }

    public void setOnOnLevelSelectedListener(OnLevelSelectedListener listener) {
        this.listener = listener;
    }



    public interface OnLevelSelectedListener {

        void onLevelSelected(int level);


    }
}
