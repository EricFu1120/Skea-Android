package me.linkcube.skea.ui.setting;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.SeekBar;

import me.linkcube.skea.R;

public class SkeaConfigActivity extends ActionBarActivity {
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
    /**初始化相关控件，并注册事件*/
    private void init(){
        //得到相关控件
        feedback_sensitive_sb=(SeekBar) findViewById(R.id.feedback_sensitive_sb);
        press_sensitive_sb=(SeekBar) findViewById(R.id.press_sensitive_sb);

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
            switch (seekBar.getId()){
                case R.id.feedback_sensitive_sb:
                    //Feedback Strength
                    //todo  ----XXXX----更改设备参数，保存设备参数
                    Log.i("CXC","---feedback_sensitive_sb:"+seekBar.getProgress());

                    break;
                case R.id.press_sensitive_sb:
                    //Pressure Sensitivity
                    //todo  ----XXXX----更改设备参数，保存设备参数

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
