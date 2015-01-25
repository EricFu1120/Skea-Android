package me.linkcube.skea.ui.setting;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;

import custom.android.util.PreferenceUtils;
import me.linkcube.skea.R;
import me.linkcube.skea.base.ui.BaseActivity;

public class SettingNicknameActivity extends BaseActivity {

    public static final String NICKNAME_KEY="me.linkcube.skea.ui.setting.SettingNicknameActivity.nickname_key";

    public static final String SHARED_PREFERENCE_NICKNAME_KEY="me.linkcube.skea.ui.setting.SettingNicknameActivity.shared_preference_nickname_key";
    private EditText username_et;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting_nickname);
        initViews();

    }
    private void initViews(){
        username_et=(EditText)findViewById(R.id.username_et);
        //得到本地化的数据
        String str =PreferenceUtils.getString(this,SHARED_PREFERENCE_NICKNAME_KEY,getResources().getString(R.string.nickname_nickname));

        this.username_et.setText(str);

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
            setResult(RESULT_OK, resultIntent);
            this.finish();
        }

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        //本地化
        PreferenceUtils.setString(this,SHARED_PREFERENCE_NICKNAME_KEY,username_et.getText().toString());
    }
}

