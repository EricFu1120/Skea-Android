package me.linkcube.skea.ui;

import android.os.Bundle;

import custom.android.app.CustomActionBarActivity;

/**
 * Created by Ervin on 14/11/11.
 */
public abstract class BaseActivity extends CustomActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayoutResourceId());
        configureActionBar();
    }

    public abstract int getLayoutResourceId();

    public abstract void configureActionBar();

}
