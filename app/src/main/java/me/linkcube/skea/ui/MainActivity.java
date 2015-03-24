package me.linkcube.skea.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

import custom.android.widget.Toaster;
import me.linkcube.skea.R;
import me.linkcube.skea.SkeaConfig;
import me.linkcube.skea.base.ui.BaseActivity;
import me.linkcube.skea.core.UserManager;
import me.linkcube.skea.core.excercise.BarConst;
import me.linkcube.skea.db.DayRecord;
import me.linkcube.skea.ui.bluetooth.EasyBluetoothActivity;
import me.linkcube.skea.ui.evaluation.RecordActivity;
import me.linkcube.skea.ui.exercise.ExerciseActivity;
import me.linkcube.skea.ui.info.InformationActivity;
import me.linkcube.skea.ui.user.LoginActivity;
import me.linkcube.skea.ui.user.MeActivity;

public class MainActivity extends BaseActivity implements View.OnClickListener {


    public static final int LOGIN_REQUEST_CODE = 1;
    public static final int LOGOUT_REQUEST_CODE = 2;
    private int userState;
    private TextView lastResultTextView;
    private TextView levelTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initViews();
        userState = UserManager.getInstance().getUserState();
    }


    @Override
    public void onResume() {
        super.onResume();
        queryLastRecord();
    }

    @Override
    public int getLayoutResourceId() {
        return R.layout.activity_main;
    }

    /**
     * 配置ActionBar
     */
    @Override
    public void configureActionBar() {
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayUseLogoEnabled(false);
        actionBar.setDisplayHomeAsUpEnabled(false);
        actionBar.setDisplayShowHomeEnabled(false);
    }

    /**
     * 初始化控件，注册相应事件
     */
    private void initViews() {
        findViewById(R.id.infoTextView).setOnClickListener(this);
        findViewById(R.id.recordsTextView).setOnClickListener(this);
        findViewById(R.id.meTextView).setOnClickListener(this);
        findViewById(R.id.startButton).setOnClickListener(this);
        lastResultTextView = (TextView) findViewById(R.id.lastResultTextView);
        levelTextView = (TextView) findViewById(R.id.levelTextView);
    }


    @Override
    public void onClick(View v) {

        if (userState == UserManager.STATE_LOGOUT) {
            startActivityForResult(new Intent(this, LoginActivity.class), LOGIN_REQUEST_CODE);
            return;
        }
        switch (v.getId()) {
            case R.id.infoTextView:
                startActivityForResult(new Intent(this, InformationActivity.class), LOGOUT_REQUEST_CODE);
                break;
            case R.id.recordsTextView:
                startActivity(new Intent(this, RecordActivity.class));
                break;
            case R.id.startButton:
                startActivity(new Intent(this, ExerciseActivity.class));
                break;
            case R.id.meTextView:
                startActivity(new Intent(this, MeActivity.class));
                break;
            default:
        }
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {
            case LOGIN_REQUEST_CODE:
                if (resultCode == RESULT_OK) {
                    userState = UserManager.STATE_LOGIN;
                    UserManager.getInstance().setLogin(this, userState);
                }
                break;
            case LOGOUT_REQUEST_CODE:
                if (resultCode == RESULT_OK) {
                    userState = UserManager.STATE_LOGOUT;
                    UserManager.getInstance().setLogin(this, userState);
                }
                break;
            default:
                break;
        }

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_connect_bluetooth) {
            startActivity(new Intent(this, EasyBluetoothActivity.class));
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void switchLanguage() {
        super.switchLanguage();
        SkeaConfig.IS_LANGUAGE_CHANGED = false;
    }


    private void queryLastRecord() {
        List<DayRecord> dayRecords = DayRecord.listAll(DayRecord.class);
        if (dayRecords == null || dayRecords.size() <= 0) {
            Toaster.showShort(this, getResources().getString(R.string.main_note_no_data));
            lastResultTextView.setText("0%");
            levelTextView.setText("");
            return;
        }
        DayRecord dayRecord = dayRecords.get(dayRecords.size() - 1);

        //设置Level
        levelTextView.setText("Level "+dayRecord.getmLevel()+"");
        JSONArray jsonBarArray;
        try{
            jsonBarArray=new JSONObject(dayRecord.getmBarsJSONInfo()).getJSONArray(BarConst.JSONConst.KEY_INFO);
            double full_total_score=0;
            double current_total_score=0;
            for(int i=0;i<jsonBarArray.length();i++){
                JSONObject jsonBar=((JSONObject)jsonBarArray.opt(i));
                full_total_score+=getFullScore(jsonBar.getInt(BarConst.JSONConst.KEY_TYPE));
                current_total_score+=jsonBar.getDouble(BarConst.JSONConst.KEY_SCORE);
            }
            //Last Record
            lastResultTextView.setText((int)Math.rint(100*current_total_score/full_total_score)+"%");

        }catch (JSONException e){
            Toaster.showShort(this,getResources().getString(R.string.main_data_error));
        }

    }

    /**
     * @return 根据type得到该类型Bar的满分
     * @param type :Bar Type
     * */
    private double getFullScore(int type){
        switch (type) {
            case BarConst.TYPE.SHORT:
                return BarConst.SCORE.SHORT_FULL_SCORE;
//                break;
            case BarConst.TYPE.MEDIUM:
                return BarConst.SCORE.MEDIUM_FULL_SCORE;
//                break;
            case BarConst.TYPE.LONG:
                return BarConst.SCORE.LONG_FULL_SCORE;
//                break;
            default:
                return 0.0;
//                break;

        }
    }

}
