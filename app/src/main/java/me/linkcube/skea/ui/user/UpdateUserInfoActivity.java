package me.linkcube.skea.ui.user;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import me.linkcube.skea.R;
import me.linkcube.skea.ui.BaseActivity;

public class UpdateUserInfoActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public int getLayoutResourceId() {
        return R.layout.activity_update_user_info;
    }

}
