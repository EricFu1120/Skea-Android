package me.linkcube.skea.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import me.linkcube.skea.R;
import me.linkcube.skea.core.persistence.UserManager;
import me.linkcube.skea.ui.bluetooth.BTSettingActivity;
import me.linkcube.skea.ui.exercise.ExerciseActivity;
import me.linkcube.skea.ui.info.InformationActivity;
import me.linkcube.skea.ui.record.RecordActivity;
import me.linkcube.skea.ui.user.UserInfoActivity;

public class MainActivity extends BaseActivity implements View.OnClickListener {


    private boolean login;

    public static final int LOGIN_REQUEST_CODE = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initViews();
    }

    @Override
    public void onResume() {
        super.onResume();
        login = UserManager.getInstance().isLogin();
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
        findViewById(R.id.infoTextView).setOnClickListener(this);
        findViewById(R.id.recordsTextView).setOnClickListener(this);
        findViewById(R.id.meTextView).setOnClickListener(this);
        findViewById(R.id.startButton).setOnClickListener(this);
    }


    @Override
    public void onClick(View v) {

        if (!login) {
            startActivityForResult(new Intent(this, LoginActivity.class), LOGIN_REQUEST_CODE);
            return;
        }
        switch (v.getId()) {
            case R.id.infoTextView:
                startActivity(new Intent(this, InformationActivity.class));
                break;
            case R.id.recordsTextView:
                startActivity(new Intent(this, RecordActivity.class));
                break;
            case R.id.startButton:
                startActivity(new Intent(this, ExerciseActivity.class));
                break;
            case R.id.meTextView:
                startActivity(new Intent(this, UserInfoActivity.class));
                break;
            default:
        }
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {
            case LOGIN_REQUEST_CODE:
                if (resultCode == RESULT_OK) {
                    login = true;
                    UserManager.getInstance().setLogin(true);
                }
                break;
            default:
                break;
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
