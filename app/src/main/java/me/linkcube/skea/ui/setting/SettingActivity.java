package me.linkcube.skea.ui.setting;

import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import java.util.Locale;

import custom.android.widget.Toaster;
import me.linkcube.skea.R;
import me.linkcube.skea.base.ui.BaseActivity;

public class SettingActivity extends BaseActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        findViewById(R.id.language).setOnClickListener(this);
        findViewById(R.id.aboutus).setOnClickListener(this);
    }

    @Override
    public int getLayoutResourceId() {
        return R.layout.activity_setting;
    }

    @Override
    public void onClick(View v) {

        switch (v.getId()) {
//            case R.id.language:
//                startActivity(new Intent(this, LanguageSettingActivity.class));
//                break;
            case R.id.aboutus:
                startActivity(new Intent(this, AboutUsActivity.class));
                break;
            default:
                break;
        }
    }

//    @Override
//    public void onConfigurationChanged(Configuration newConfig) {
//        super.onConfigurationChanged(newConfig);
////        setTitle(getResources().getString(R.string.title_activity_setting));
//        Toaster.showLong(this,"****onConfigurationChanged****å");
//        Log.i("CXC","&&&&&&&&&&&&---onConfigurationChanged");
//        if(newConfig.locale== Locale.CHINA){
//            switchLanguage();
//        }else if(newConfig.locale==Locale.ENGLISH){
//            switchLanguage();
//
//        }else{
//            //to-do nothing
//        }
//
//
//    }
}
