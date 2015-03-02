package me.linkcube.skea.base.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.util.Log;
import android.view.MenuItem;

import com.umeng.analytics.MobclickAgent;

import java.util.Observable;
import java.util.Observer;

import custom.android.app.CustomActionBarActivity;
import custom.android.app.util.ActivityUtils;
import custom.android.util.PreferenceUtils;
import me.linkcube.skea.SkeaConfig;
import me.linkcube.skea.core.KeyConst;
import me.linkcube.skea.core.UserManager;
import me.linkcube.skea.ui.user.LoginActivity;

/**
 * Created by Ervin on 14/11/11.
 */
public abstract class BaseActivity extends CustomActionBarActivity implements Observer {

    protected boolean isLogin;

    protected boolean isFirstStart = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        configureActionBar();
        loadLanguage();
        setContentView(getLayoutResourceId());
        UserManager.getInstance().getUserStateWatched().addObserver(this);
    }

    @Override
    public void onResume() {
        super.onResume();
        MobclickAgent.onResume(this);
        if (isFirstStart) {
            isFirstStart = false;
        } else {
            if (SkeaConfig.IS_LANGUAGE_CHANGED) {
                switchLanguage();
            }
        }
    }

    @Override
    public void onPause() {
        super.onPause();
        MobclickAgent.onPause(this);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId()==android.R.id.home){
            finish();
        }
        return super.onOptionsItemSelected(item);
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

    protected void loadLanguage() {
        Log.d(this.getClass().toString(), "loadLanguage");
        String language = PreferenceUtils.getString(this, KeyConst.KEY_LANGUAGE, KeyConst.Language.English);
        ActivityUtils.switchLanguage(this, language);
    }

    protected void switchLanguage() {
        Log.d(this.getClass().toString(), "switchLanguage");
        String language = PreferenceUtils.getString(this, KeyConst.KEY_LANGUAGE, KeyConst.Language.English);
        ActivityUtils.switchLanguage(this, language);
        startActivity(new Intent(this, this.getClass()));
        finish();
    }
}
