package me.linkcube.skea.ui.init;

import android.app.Activity;
import android.os.Bundle;

import me.linkcube.skea.R;
import me.linkcube.skea.base.ui.BaseActivity;

public class GuideActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public int getLayoutResourceId() {
        return R.layout.activity_guide;
    }

}
