package me.linkcube.skea.ui.user;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;

import me.linkcube.skea.R;
import me.linkcube.skea.base.ui.BaseActivity;
import me.linkcube.skea.ui.evaluation.EvaluateResultActivity;
import me.linkcube.skea.ui.setting.SettingActivity;
import me.linkcube.skea.ui.setting.SkeaSettingActivity;

public class MeActivity extends BaseActivity implements View.OnClickListener {

    private final String urlString = "http://www.linkcube.me";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initViews();
    }

    @Override
    public int getLayoutResourceId() {
        return R.layout.activity_me;
    }

    /**
     * 初始化，并注册相应事件
     */
    private void initViews() {
        findViewById(R.id.test_pelvic_muscle).setOnClickListener(this);
        findViewById(R.id.skea_settings).setOnClickListener(this);
        findViewById(R.id.purchase_product).setOnClickListener(this);
        findViewById(R.id.software_settings).setOnClickListener(this);
        findViewById(R.id.logout_button).setOnClickListener(this);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == android.R.id.home) {
            finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.test_pelvic_muscle:
                startActivity(new Intent().setClass(getApplicationContext(), EvaluateResultActivity.class));
                break;
            case R.id.skea_settings:
                startActivity(new Intent().setClass(getApplicationContext(), SkeaSettingActivity.class));
                break;
            case R.id.purchase_product:
                Uri uri = Uri.parse(urlString);//网址要加http
                Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                startActivity(intent);
                break;
            case R.id.software_settings:
                startActivity(new Intent().setClass(getApplicationContext(), SettingActivity.class));
                break;
            case R.id.logout_button:
                setResult(RESULT_OK);
                finish();
                break;
            default:

        }
    }
}
