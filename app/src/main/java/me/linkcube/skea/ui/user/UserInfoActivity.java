package me.linkcube.skea.ui.user;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.net.Uri;

import me.linkcube.skea.R;
import me.linkcube.skea.ui.BaseActivity;
import me.linkcube.skea.ui.setting.ConfigSkeaActivity;
import me.linkcube.skea.ui.setting.SettingActivity;

public class UserInfoActivity extends BaseActivity implements View.OnClickListener {
    private final String urlString = "http://www.linkcube.me";
    private TextView test_pelvic_muscle;
    private TextView skea_settings;
    private TextView purchase_product;
    private TextView software_settings;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initViews();
    }

    @Override
    public int getLayoutResourceId() {
        return R.layout.activity_user_info;
    }

    /**
     * 初始化，并注册相应事件
     */
    private void initViews() {
        //得到控件
        test_pelvic_muscle = (TextView) findViewById(R.id.test_pelvic_muscle);
        skea_settings = (TextView) findViewById(R.id.skea_settings);
        purchase_product = (TextView) findViewById(R.id.purchase_product);
        software_settings = (TextView) findViewById(R.id.software_settings);

        //注册事件
        test_pelvic_muscle.setOnClickListener(this);
        skea_settings.setOnClickListener(this);
        purchase_product.setOnClickListener(this);
        software_settings.setOnClickListener(this);

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
                startActivity(new Intent().setClass(getApplicationContext(), TestPelvicMuscleResultActivity.class));
                break;
            case R.id.skea_settings:
                startActivity(new Intent().setClass(getApplicationContext(), ConfigSkeaActivity.class));
                break;
            case R.id.purchase_product:
                Uri uri = Uri.parse(urlString);//网址要加http
                Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                startActivity(intent);
                break;
            case R.id.software_settings:
                startActivity(new Intent().setClass(getApplicationContext(), SettingActivity.class));
                break;
            default:

        }
    }
}
