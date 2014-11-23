package me.linkcube.skea.base.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBar;

import java.util.Observable;
import java.util.Observer;

import custom.android.app.CustomActionBarActivity;
import me.linkcube.skea.core.UserManager;
import me.linkcube.skea.ui.user.LoginActivity;

/**
 * Created by Ervin on 14/11/11.
 */
public abstract class BaseActivity extends CustomActionBarActivity implements Observer {

    protected boolean isLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        configureActionBar();
        setContentView(getLayoutResourceId());
        UserManager.getInstance().getUserStateWatched().addObserver(this);
    }

    public abstract int getLayoutResourceId();

    public void configureActionBar() {
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setDisplayShowHomeEnabled(false);
        actionBar.setDisplayUseLogoEnabled(false);
//        actionBar.setHomeAsUpIndicator(R.drawable.layer_actionbar_up_indicator_white);
    }

    @Override
    public void update(Observable observable, Object data) {
        boolean isLogin = (Boolean) data;
        if (isLogin)
            this.isLogin = isLogin;
        else
            startLoginActivity();
    }

    //TODO 开始登录流程，并在该Activity上屏蔽返回键，返回键只是退出程序
    protected void startLoginActivity() {
        startActivity(new Intent(this, LoginActivity.class));
    }
}
