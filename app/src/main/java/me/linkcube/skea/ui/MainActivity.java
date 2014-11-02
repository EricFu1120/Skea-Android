package me.linkcube.skea.ui;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import org.w3c.dom.Text;

import me.linkcube.skea.R;
import me.linkcube.skea.ui.info.InformationActivity;
import me.linkcube.skea.ui.record.RecordActivity;
import me.linkcube.skea.ui.user.UserInfoActivity;

public class MainActivity extends Activity {


    //声明控件
    /**
     * Info
     */
    private TextView infoTextView;
    /**
     * Records
     */
    private TextView recordsTextView;
    /**
     * Me
     */
    private TextView meTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();


    }

    /**
     * 初始化控件，注册相应事件
     */
    private void init() {
        infoTextView = (TextView) findViewById(R.id.infoTextView);
        recordsTextView = (TextView) findViewById(R.id.recordsTextView);
        meTextView = (TextView) findViewById(R.id.meTextView);

        infoTextView.setOnClickListener(mainTextViewOnClickListener);
        recordsTextView.setOnClickListener(mainTextViewOnClickListener);
        meTextView.setOnClickListener(mainTextViewOnClickListener);


    }

    View.OnClickListener mainTextViewOnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.infoTextView:
                    startActivity(new Intent().setClass(getApplicationContext(), InformationActivity.class));

                    break;

                case R.id.recordsTextView:
                    startActivity(new Intent().setClass(getApplicationContext(), RecordActivity.class));
                    break;
                case R.id.meTextView:
                    startActivity(new Intent().setClass(getApplicationContext(), UserInfoActivity.class));
                    break;

                default:


            }

        }
    };


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_connect_bluetooth) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
