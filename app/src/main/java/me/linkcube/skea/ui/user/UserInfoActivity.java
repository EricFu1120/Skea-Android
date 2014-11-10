package me.linkcube.skea.ui.user;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.net.Uri;

import org.w3c.dom.Text;

import me.linkcube.skea.R;
import me.linkcube.skea.ui.setting.SettingActivity;
import me.linkcube.skea.ui.setting.SkeaConfigActivity;

public class UserInfoActivity extends ActionBarActivity {
    private final String urlString="http://www.linkcube.me";
    private TextView test_pelvic_muscle;
    private TextView skea_settings;
    private TextView purchase_product;
    private TextView software_settings;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_info);
        //ActionBar实现后退导航
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        init();

    }

    /**初始化，并注册相应事件*/
    private void init(){
        //得到控件
        test_pelvic_muscle=(TextView) findViewById(R.id.test_pelvic_muscle);
        skea_settings=(TextView) findViewById(R.id.skea_settings);

        purchase_product=(TextView) findViewById(R.id.purchase_product);

        software_settings=(TextView) findViewById(R.id.software_settings);




        //注册事件
        test_pelvic_muscle.setOnClickListener(onUserInfoTextViewClickListener);
        skea_settings.setOnClickListener(onUserInfoTextViewClickListener);

        purchase_product.setOnClickListener(onUserInfoTextViewClickListener);

        software_settings.setOnClickListener(onUserInfoTextViewClickListener);



    }

    View.OnClickListener onUserInfoTextViewClickListener=new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            switch (v.getId()){
                case R.id.test_pelvic_muscle:

                    break;
                case R.id.skea_settings:
                    startActivity(new Intent().setClass(getApplicationContext(), SkeaConfigActivity.class));
                    break;
                case R.id.purchase_product:
                    Uri uri=Uri.parse(urlString);//网址要加http
                    Intent intent=new Intent(Intent.ACTION_VIEW,uri);
                    startActivity(intent);
                    break;
                case R.id.software_settings:
                    startActivity(new Intent().setClass(getApplicationContext(), SettingActivity.class));
                    break;
                default:

            }
        }
    };

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.setting, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_connect_bluetooth) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
