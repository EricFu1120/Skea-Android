package me.linkcube.skea.ui.setting;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;

import me.linkcube.skea.R;
import me.linkcube.skea.base.ui.BaseActivity;

public class SettingNicknameActivity extends BaseActivity {

    public static final String NICKNAME_KEY="me.linkcube.skea.ui.setting.SettingNicknameActivity.nickname_key";
    /**
     * Nickname 本地持久化文件名
     */
    public static final String NICKNAME_CONFIG_XML_FILE = "Nickname_Config_XML_File";

    /**
     * Nickname 本地持久化Key
     */
    public static final String SHARED_PREFERENCE_NICKNAME_KEY="me.linkcube.skea.ui.setting.SettingNicknameActivity.shared_preference_nickname_key";
    private EditText username_et;

    private SharedPreferences msharedPreferences=null;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting_nickname);
        initViews();

    }
    private void initViews(){
        username_et=(EditText)findViewById(R.id.username_et);
        msharedPreferences=getSharedPreferences(NICKNAME_CONFIG_XML_FILE,Activity.MODE_PRIVATE);
        if(msharedPreferences!=null){

            this.username_et.setText(msharedPreferences.getString(SHARED_PREFERENCE_NICKNAME_KEY,"Nickname"));
        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_setting_nickname, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_store_username) {
            //保存用户设置的Nickname

            finishWithMessage();

            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public int getLayoutResourceId() {
        return R.layout.activity_setting_nickname;
    }
    private void finishWithMessage(){
        Intent resultIntent=new Intent();
        String nickname=username_et.getText().toString();
        username_et.setText("");
        if(nickname==null || "".equals(nickname)){//Nickname为空
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setTitle(getResources().getString(R.string.nickname_notice_title));
            builder.setMessage(getResources().getString(R.string.nickname_notice_message))
                    .setCancelable(false)
                    .setPositiveButton(getResources().getString(R.string.evaluate_notice_yes), new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {

                            dialog.cancel();
                        }
                    });
            //显示Dialog
            builder.create().show();
        }else {
            resultIntent.putExtra(NICKNAME_KEY,nickname);
            //本地化
            msharedPreferences.edit().putString(SHARED_PREFERENCE_NICKNAME_KEY,nickname).commit();

            setResult(RESULT_OK,resultIntent);
            this.finish();
        }

    }
}
