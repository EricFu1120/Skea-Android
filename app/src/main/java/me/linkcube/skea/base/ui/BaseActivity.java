package me.linkcube.skea.base.ui;

import android.os.Bundle;
import android.support.v7.app.ActionBar;

import custom.android.app.CustomActionBarActivity;
import me.linkcube.skea.R;

/**
 * Created by Ervin on 14/11/11.
 */
public abstract class BaseActivity extends CustomActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        configureActionBar();
        setContentView(getLayoutResourceId());
    }

    public abstract int getLayoutResourceId();

    public void configureActionBar() {
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setDisplayShowHomeEnabled(false);
        actionBar.setDisplayUseLogoEnabled(false);
        actionBar.setHomeAsUpIndicator(R.drawable.layer_actionbar_up_indicator_white);
    }

}
