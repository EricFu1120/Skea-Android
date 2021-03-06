package me.linkcube.skea.ui.init;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import me.linkcube.skea.R;
import me.linkcube.skea.TestHttpActivity;
import me.linkcube.skea.TestSugarActivity;
import me.linkcube.skea.base.ui.BaseActivity;
import me.linkcube.skea.core.UserManager;
import me.linkcube.skea.ui.MainActivity;
import me.linkcube.skea.ui.user.LoginActivity;

public class SplashActivity extends BaseActivity implements Runnable, View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initViews();
        UserManager.getInstance().startAutoLogin(this);
        new Thread(this).start();
    }

    @Override
    public int getLayoutResourceId() {
        return R.layout.activity_splash;
    }

    private void initViews() {
        findViewById(R.id.testDb).setOnClickListener(this);
        findViewById(R.id.testHttp).setOnClickListener(this);
        findViewById(R.id.skip).setOnClickListener(this);
    }

    @Override
    public void run() {
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        startActivity(new Intent(this, MainActivity.class));
        finish();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.testDb:
                startActivity(new Intent(this, TestSugarActivity.class));
                break;
            case R.id.testHttp:
                startActivity(new Intent(this, TestHttpActivity.class));
                break;
            case R.id.skip:
                startActivity(new Intent(this, LoginActivity.class));
                finish();
                break;
            default:
                break;
        }
    }
}
