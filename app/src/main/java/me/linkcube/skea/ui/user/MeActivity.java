package me.linkcube.skea.ui.user;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import org.w3c.dom.Text;

import me.linkcube.skea.R;
import me.linkcube.skea.SkeaConfig;
import me.linkcube.skea.base.ui.BaseActivity;
import me.linkcube.skea.ui.evaluation.EvaluateResultActivity;
import me.linkcube.skea.ui.setting.SettingActivity;
import me.linkcube.skea.ui.setting.SettingNicknameActivity;
import me.linkcube.skea.ui.setting.SkeaSettingActivity;

public class MeActivity extends BaseActivity implements View.OnClickListener {

    private final String urlString = "http://www.linkcube.me";
    private static final int SETTING_NICKNAME_REQUEST_CODE=50001;

    private TextView username_tv;

    public static final String ME_NICKNAME_KEY="me.linkcube.skea.ui.setting.MeActivity.nickname_key";

    private SharedPreferences msharedPreferences=null;
    /**
     * Nickname 本地持久化文件名
     */
    private static final String ME_NICKNAME_CONFIG_XML_FILE = "Me_Nickname_Config_XML_File";

    /**
     * Nickname 本地持久化Key
     */
    public static final String ME_SHARED_PREFERENCE_NICKNAME_KEY="me.linkcube.skea.ui.setting.MeActivity.shared_preference_nickname_key";

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
        username_tv=(TextView)findViewById(R.id.user_name);
        username_tv.setOnClickListener(this);

        findViewById(R.id.test_pelvic_muscle).setOnClickListener(this);
        findViewById(R.id.skea_settings).setOnClickListener(this);
        findViewById(R.id.purchase_product).setOnClickListener(this);
        findViewById(R.id.software_settings).setOnClickListener(this);
        findViewById(R.id.logout_button).setOnClickListener(this);

        //读取本地持久化的信息
        msharedPreferences=getSharedPreferences(ME_NICKNAME_CONFIG_XML_FILE, Activity.MODE_PRIVATE);
        if(msharedPreferences!=null){
            this.username_tv.setText(msharedPreferences.getString(ME_NICKNAME_KEY,getResources().getString(R.string.me_nickname)));
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.user_name://昵称
                Intent settingNicknameIntent=new Intent();
                settingNicknameIntent.setClass(getApplicationContext(),SettingNicknameActivity.class);
                startActivityForResult(settingNicknameIntent,SETTING_NICKNAME_REQUEST_CODE);
                break;
            case R.id.test_pelvic_muscle://盆底肌健康测试
                startActivity(new Intent().setClass(getApplicationContext(), EvaluateResultActivity.class));
                break;
            case R.id.skea_settings://Skea参数设置
                startActivity(new Intent().setClass(getApplicationContext(), SkeaSettingActivity.class));
                break;
            case R.id.purchase_product://购买Skea产品
                Uri uri = Uri.parse(urlString);//网址要加http
                Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                startActivity(intent);
                break;
            case R.id.software_settings: //设置
                startActivity(new Intent().setClass(getApplicationContext(), SettingActivity.class));
                break;
            case R.id.logout_button: //退出登录
                setResult(RESULT_OK);
                finish();
                break;
            default:

        }
    }

    @Override
    protected void switchLanguage() {
        super.switchLanguage();
        //此处不能将标志置为false,否则MeActivity的上一级Activity感觉不到变化，也就不会进行中英文切换了
//        SkeaConfig.IS_LANGUAGE_CHANGED = false;

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode){
            case SETTING_NICKNAME_REQUEST_CODE:
                if(resultCode==RESULT_OK){//得到用户设置的NickName
                    String tempNickname=data.getStringExtra(SettingNicknameActivity.NICKNAME_KEY);
                    username_tv.setText(tempNickname);
                    //本地化
                    msharedPreferences.edit().putString(ME_NICKNAME_KEY,tempNickname).commit();
                }
                break;
            default:
                break;
        }
    }
}
