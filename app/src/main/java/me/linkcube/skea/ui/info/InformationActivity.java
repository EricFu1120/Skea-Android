package me.linkcube.skea.ui.info;

import android.os.Bundle;

import me.linkcube.skea.R;
import me.linkcube.skea.base.ui.BaseActivity;

public class InformationActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_information);
    }

    @Override
    public int getLayoutResourceId() {
        return R.layout.activity_information;
    }


}
