package me.linkcube.skea.ui.setting;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import me.linkcube.skea.R;
import me.linkcube.skea.base.ui.BaseActivity;

public class AboutUsActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about_us);
    }

    @Override
    public int getLayoutResourceId() {
        return R.layout.activity_about_us;
    }
}
