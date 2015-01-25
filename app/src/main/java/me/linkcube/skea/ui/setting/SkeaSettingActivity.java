package me.linkcube.skea.ui.setting;

import android.app.Activity;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.widget.SeekBar;

import custom.android.util.PreferenceUtils;
import me.linkcube.skea.R;
import me.linkcube.skea.base.ui.BaseActivity;

public class SkeaSettingActivity extends BaseActivity {
    /**
     * Feedback Sensitivity Key
     */
    private static final String SKEA_CONFIG_FEEDBACK_KEY = "Feedback_Strength";
    private int feedback_value=50;
    /**
     * Pressure Sensitivity key
     */
    private static final String SKEA_CONFIG_PRESSURE_KEY = "Pressure_Sensitivity";
    private int pressure_value=50;


    SeekBar.OnSeekBarChangeListener onSkeaConfigSeekBarChangeListener = new SeekBar.OnSeekBarChangeListener() {
        @Override
        public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {

        }

        @Override
        public void onStartTrackingTouch(SeekBar seekBar) {

        }

        @Override
        public void onStopTrackingTouch(SeekBar seekBar) {
            switch (seekBar.getId()) {
                case R.id.feedback_sensitive_sb:
                    //Feedback Strength
                    //todo  ----XXXX----更改设备参数，保存设备参数

                    feedback_value=seekBar.getProgress();
                    break;
                case R.id.press_sensitive_sb:
                    //Pressure Sensitivity
                    //todo  ----XXXX----更改设备参数，保存设备参数

                    pressure_value=seekBar.getProgress();
                    break;
                default:

            }

        }
    };
    //声明控件
    private SeekBar feedback_sensitive_sb;
    private SeekBar press_sensitive_sb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initViews();
    }

    @Override
    public int getLayoutResourceId() {
        return R.layout.activity_skea_setting;
    }

    /**
     * 初始化，并注册事件
     */
    private void initViews() {


        //得到相关控件
        feedback_sensitive_sb = (SeekBar) findViewById(R.id.feedback_sensitive_sb);
        press_sensitive_sb = (SeekBar) findViewById(R.id.press_sensitive_sb);

        //得到之前存储值，并设置SeekBar
        feedback_sensitive_sb.setProgress(PreferenceUtils.getInt(this,SKEA_CONFIG_FEEDBACK_KEY, feedback_value));
        press_sensitive_sb.setProgress(PreferenceUtils.getInt(this,SKEA_CONFIG_PRESSURE_KEY, pressure_value));

        //注册事件
        feedback_sensitive_sb.setOnSeekBarChangeListener(onSkeaConfigSeekBarChangeListener);
        press_sensitive_sb.setOnSeekBarChangeListener(onSkeaConfigSeekBarChangeListener);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        PreferenceUtils.setInt(getApplicationContext(),SKEA_CONFIG_FEEDBACK_KEY,feedback_value);
        PreferenceUtils.setInt(getApplicationContext(),SKEA_CONFIG_PRESSURE_KEY,pressure_value);

    }
}
