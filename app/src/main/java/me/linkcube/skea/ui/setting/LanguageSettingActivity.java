package me.linkcube.skea.ui.setting;

import android.os.Bundle;
import android.widget.RadioGroup;

import custom.android.util.PreferenceUtils;
import me.linkcube.skea.R;
import me.linkcube.skea.SkeaConfig;
import me.linkcube.skea.base.ui.BaseActivity;
import me.linkcube.skea.core.KeyConst;

public class LanguageSettingActivity extends BaseActivity implements RadioGroup.OnCheckedChangeListener {

    private RadioGroup radioGroup;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initViews();
    }

    @Override
    public int getLayoutResourceId() {
        return R.layout.activity_language_setting;
    }

    public void initViews() {
        String language  = PreferenceUtils.getString(this,KeyConst.KEY_LANGUAGE,KeyConst.Language.English);
        radioGroup = (RadioGroup) findViewById(R.id.language_radioGroup);
        if (language.equals(KeyConst.Language.English)) {
            radioGroup.check(R.id.english);
        }else {
            radioGroup.check(R.id.chinese);
        }
        radioGroup.setOnCheckedChangeListener(this);
    }

    @Override
    public void onCheckedChanged(RadioGroup group, int checkedId) {
        switch (checkedId) {
            case R.id.english:
                SkeaConfig.IS_LANGUAGE_CHANGED = true;
                PreferenceUtils.setString(LanguageSettingActivity.this, KeyConst.KEY_LANGUAGE, KeyConst.Language.English);
                break;
            case R.id.chinese:
                SkeaConfig.IS_LANGUAGE_CHANGED = true;
                PreferenceUtils.setString(LanguageSettingActivity.this, KeyConst.KEY_LANGUAGE, KeyConst.Language.Chinese);
                break;
            default:
                break;
        }
    }


}
