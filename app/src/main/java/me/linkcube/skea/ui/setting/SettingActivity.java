package me.linkcube.skea.ui.setting;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import me.linkcube.skea.R;
import me.linkcube.skea.base.ui.BaseActivity;

public class SettingActivity extends BaseActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        findViewById(R.id.language).setOnClickListener(this);
    }

    @Override
    public int getLayoutResourceId() {
        return R.layout.activity_setting;
    }

    @Override
    public void onClick(View v) {

        switch (v.getId()) {
            case R.id.language:
                startActivity(new Intent(this, LanguageSettingActivity.class));
                break;
            case R.id.update:
                break;

            case R.id.feedback:
                break;
            case R.id.aboutus:
                break;
            default:
                break;
        }
    }
}
