package me.linkcube.skea.view;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.RadioGroup;

import me.linkcube.skea.R;

/**
 * Created by Ervin on 14/11/13.
 */
public class LevelRadioGroup extends RadioGroup {

    private OnLevelSelectedListener listener;

    public LevelRadioGroup(Context context) {
        super(context);
        initViews(context);
    }

    public LevelRadioGroup(Context context, AttributeSet attrs) {
        super(context, attrs);
        initViews(context);
    }

    private void initViews(Context context) {
        inflate(context, R.layout.level_radio_group, this);
        setOnCheckedChangeListener(new OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if (listener == null)
                    return;
                switch (checkedId) {
                    case R.id.level1_rb:
                        listener.onLevelSelected(1);
                        break;
                    case R.id.level2_rb:
                        listener.onLevelSelected(2);
                        break;
                    case R.id.level3_rb:
                        listener.onLevelSelected(3);
                        break;
                    case R.id.level4_rb:
                        listener.onLevelSelected(4);
                        break;
                    case R.id.level5_rb:
                        listener.onLevelSelected(5);
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
