package me.linkcube.skea.ui.evaluation;


import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.util.Log;
import android.view.MenuItem;
import android.widget.RadioGroup;

import me.linkcube.skea.R;
import me.linkcube.skea.base.ui.BaseActivity;


public class ExerciseLevelSettingActivity extends BaseActivity {
    /**
     * Exercise Level  File 本地持久化文件名
     */
    private static final String SKEA_EXERCISE_LEVEL_FILE = "Setting_Exercise_Level_File";
    /**
     * Exercise Level Key
     */
    private static final String SKEA_EXERCISE_LEVEL_KEY = "Setting_Exercise_Level_Key";

    private SharedPreferences mSharedPreferences = null;
    private SharedPreferences.Editor mEditor = null;


    //训练强度
    private int exerciseLevelIndex = 4;
    //控件声明
    private RadioGroup exerciseLevel;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exercise_level_setting);
        initViews();
    }

    @Override
    public int getLayoutResourceId() {
        return R.layout.activity_exercise_level_setting;
    }

    public void configureActionBar() {
        ActionBar actionBar = getSupportActionBar();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mSharedPreferences = getSharedPreferences(SKEA_EXERCISE_LEVEL_FILE, Activity.MODE_PRIVATE);
        mEditor = mSharedPreferences.edit();
        mEditor.putInt(SKEA_EXERCISE_LEVEL_KEY, exerciseLevelIndex);
        mEditor.commit();


    }

    public void initViews() {


        //得到控件
        exerciseLevel = (RadioGroup) findViewById(R.id.exercise_level);
        //得到之前存储值，并设置
        mSharedPreferences = getSharedPreferences(SKEA_EXERCISE_LEVEL_FILE, Activity.MODE_PRIVATE);
        if (mSharedPreferences != null) {//不是第一次运行
            int id;
            switch (mSharedPreferences.getInt(SKEA_EXERCISE_LEVEL_KEY, 4)) {
                case 1:
                    id = R.id.level1_rb;
                    break;
                case 2:
                    id = R.id.level2_rb;
                    break;
                case 3:
                    id = R.id.level3_rb;
                    break;
                case 4:
                    id = R.id.level4_rb;
                    break;
                case 5:
                    id = R.id.level5_rb;
                    break;
                default:
                    id = R.id.level4_rb;
            }
            exerciseLevel.check(id);

        }


        //注册事件
        exerciseLevel.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId) {
                    case R.id.level1_rb:
                        exerciseLevelIndex = 1;
                        break;
                    case R.id.level2_rb:
                        exerciseLevelIndex = 2;

                        break;
                    case R.id.level3_rb:

                        exerciseLevelIndex = 3;
                        break;
                    case R.id.level4_rb:

                        exerciseLevelIndex = 4;
                        break;
                    case R.id.level5_rb:

                        exerciseLevelIndex = 5;
                        break;
                    default:


                }
            }
        });
    }


    /**
     * 向父Activity返回消息
     */
    private void returnMessage() {
        //返回用户设置的训练强度
        Intent resultIntent = new Intent();
        resultIntent.putExtra(EvaluateResultActivity.EXERCISE_LEVEL, exerciseLevelIndex);
        Log.i("CXC", "---level:" + exerciseLevelIndex);
        setResult(RESULT_OK, resultIntent);
        this.finish();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == android.R.id.home) {
            //TODO 尝试setResult();
            returnMessage();
            //Toaster.showLong(this,"click home");
            return true;
        }
        return super.onOptionsItemSelected(item);

    }

}
