package me.linkcube.skea.ui.setting;

import android.app.Activity;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.SeekBar;

import custom.android.app.CustomActionBarActivity;
import me.linkcube.skea.R;

public class SkeaConfigActivity extends CustomActionBarActivity {
    /**Skea Config 本地持久化文件名*/
    private static final String  SKEA_CONFIG_XML_FILE="Skea_Config_XML_File";
    /**Feedback Sensitivity Key*/
    private static final String  SKEA_CONFIG_FEEDBACK_KEY="Feedback_Strength";
    /**Pressure Sensitivity key*/
    private static final String  SKEA_CONFIG_PRESSURE_KEY="Pressure_Sensitivity";

    private SharedPreferences mSharedPreferences=null;
    private SharedPreferences.Editor mEditor=null;

    //声明控件
    private SeekBar feedback_sensitive_sb;
    private SeekBar press_sensitive_sb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_skea_config);
        //返回导航
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        init();
    }
    /**初始化，并注册事件*/
    private void init(){



        //得到相关控件
        feedback_sensitive_sb=(SeekBar) findViewById(R.id.feedback_sensitive_sb);
        press_sensitive_sb=(SeekBar) findViewById(R.id.press_sensitive_sb);
        //得到之前存储值，并设置SeekBar
        mSharedPreferences=getSharedPreferences(SKEA_CONFIG_XML_FILE,Activity.MODE_PRIVATE);
        if(mSharedPreferences!=null){//不是第一次运行

            feedback_sensitive_sb.setProgress(mSharedPreferences.getInt(SKEA_CONFIG_FEEDBACK_KEY,50));
            press_sensitive_sb.setProgress(mSharedPreferences.getInt(SKEA_CONFIG_PRESSURE_KEY,50));

        }

        //注册事件
        feedback_sensitive_sb.setOnSeekBarChangeListener(onSkeaConfigSeekBarChangeListener);
        press_sensitive_sb.setOnSeekBarChangeListener(onSkeaConfigSeekBarChangeListener);
    }

    SeekBar.OnSeekBarChangeListener onSkeaConfigSeekBarChangeListener=new SeekBar.OnSeekBarChangeListener() {
        @Override
        public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {

        }

        @Override
        public void onStartTrackingTouch(SeekBar seekBar) {

        }

        @Override
        public void onStopTrackingTouch(SeekBar seekBar) {

            mSharedPreferences=getSharedPreferences(SKEA_CONFIG_XML_FILE,Activity.MODE_PRIVATE);
            mEditor=mSharedPreferences.edit();

            switch (seekBar.getId()){
                case R.id.feedback_sensitive_sb:
                    //Feedback Strength
                    //todo  ----XXXX----更改设备参数，保存设备参数
                    Log.i("CXC","---feedback_sensitive_sb:"+seekBar.getProgress());

                    mEditor.putInt(SKEA_CONFIG_FEEDBACK_KEY,seekBar.getProgress());
                    mEditor.commit();

                    break;
                case R.id.press_sensitive_sb:
                    //Pressure Sensitivity
                    //todo  ----XXXX----更改设备参数，保存设备参数

                    mEditor.putInt(SKEA_CONFIG_PRESSURE_KEY,seekBar.getProgress());
                    mEditor.commit();

                    Log.i("CXC","---press_sensitive_sb:"+seekBar.getProgress());
                    break;
                default:

            }

        }
    };

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.skea_config, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }



}
