package me.linkcube.skea.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import custom.android.app.CustomActionBarActivity;
import me.linkcube.skea.R;
import me.linkcube.skea.ui.bt.BTSettingActivity;
import me.linkcube.skea.ui.info.InformationActivity;
import me.linkcube.skea.ui.record.RecordActivity;
import me.linkcube.skea.ui.user.UserInfoActivity;

public class MainActivity extends BaseActivity implements View.OnClickListener {


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
        initViews();
    }

    @Override
    public int getLayoutResourceId() {
        return R.layout.activity_main;
    }

    /**
     * 配置ActionBar
     */
    @Override
    public void configureActionBar() {
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayUseLogoEnabled(false);
        actionBar.setDisplayHomeAsUpEnabled(false);
        actionBar.setDisplayShowHomeEnabled(false);
    }

    /**
     * 初始化控件，注册相应事件
     */
    private void initViews() {
        infoTextView = (TextView) findViewById(R.id.infoTextView);
        recordsTextView = (TextView) findViewById(R.id.recordsTextView);
        meTextView = (TextView) findViewById(R.id.meTextView);
        infoTextView.setOnClickListener(this);
        recordsTextView.setOnClickListener(this);
        meTextView.setOnClickListener(this);
        findViewById(R.id.login).setOnClickListener(this);
        findViewById(R.id.register).setOnClickListener(this);
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.infoTextView:
                startActivity(new Intent(this, InformationActivity.class));
                break;
            case R.id.recordsTextView:
                startActivity(new Intent(this, RecordActivity.class));
                break;
            case R.id.meTextView:
                startActivity(new Intent(this, UserInfoActivity.class));
                break;
            case R.id.login:
                startActivity(new Intent(this, LoginActivity.class));
                break;
            case R.id.register:
                startActivity(new Intent(this, RegisterActivity.class));
                break;
            default:
        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_connect_bluetooth) {
            //TODO 进入蓝牙连接页面
            startActivity(new Intent(this, BTSettingActivity.class));
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

}
