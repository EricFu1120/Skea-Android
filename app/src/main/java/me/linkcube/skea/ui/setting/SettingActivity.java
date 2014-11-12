package me.linkcube.skea.ui.setting;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import custom.android.app.CustomActionBarActivity;
import me.linkcube.skea.R;
import me.linkcube.skea.ui.BaseActivity;

public class SettingActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public int getLayoutResourceId() {
        return R.layout.activity_setting;
    }

}
