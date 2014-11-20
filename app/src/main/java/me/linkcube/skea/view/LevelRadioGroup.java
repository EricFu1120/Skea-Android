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
public class LevelRadioGroup extends LinearLayout {

    private String level1, level2, level3, level4;

    private String title, introduction;

    private TextView title_tv, introduction_tv;

    private TextView level1_tv, level2_tv, level3_tv, level4_tv;

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
        View view = inflate(this.getContext(), R.layout.radio_group_level, this);
        radioGroup = (RadioGroup) view.findViewById(R.id.radio_group);
        level1_tv = (TextView) view.findViewById(R.id.level1_tv);
        level2_tv = (TextView) view.findViewById(R.id.level2_tv);
        level3_tv = (TextView) view.findViewById(R.id.level3_tv);
        level4_tv = (TextView) view.findViewById(R.id.level4_tv);
        title_tv = (TextView) view.findViewById(R.id.title_tv);
        introduction_tv = (TextView) view.findViewById(R.id.introduction_tv);

        final TypedArray a = getContext().obtainStyledAttributes(
                attrs, R.styleable.CustomRadioGroup, defStyle, 0);
        level1 = a.getString(R.styleable.CustomRadioGroup_level1);
        level2 = a.getString(R.styleable.CustomRadioGroup_level2);
        level3 = a.getString(R.styleable.CustomRadioGroup_level3);
        level4 = a.getString(R.styleable.CustomRadioGroup_level4);
        title = a.getString(R.styleable.CustomRadioGroup_radio_title);
        introduction = a.getString(R.styleable.CustomRadioGroup_radio_introduction);

        if (introduction != null && !introduction.equals("")) {
            introduction_tv
                    .setVisibility(VISIBLE);
            introduction_tv.setText(introduction);
        } else {
            introduction_tv.setVisibility(GONE);
        }

        level1_tv.setText(level1);
        level2_tv.setText(level2);
        level3_tv.setText(level3);
        level4_tv.setText(level4);
        title_tv.setText(title);

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
