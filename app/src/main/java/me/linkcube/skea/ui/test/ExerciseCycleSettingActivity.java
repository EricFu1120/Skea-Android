package me.linkcube.skea.ui.test;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.RadioGroup;

import me.linkcube.skea.R;
import me.linkcube.skea.ui.BaseActivity;
import me.linkcube.skea.ui.user.TestPelvicMuscleResultActivity;

public class ExerciseCycleSettingActivity extends BaseActivity {
    /**Exercise Cycle  File 本地持久化文件名*/
    private static final String  SKEA_EXERCISE_CYCLE_FILE="Setting_Exercise_Cycle_File";
    /**Exercise Cycle Key*/
    private static final String  SKEA_EXERCISE_CYCLE_KEY="Setting_Exercise_Cycle_Key";

    private int exerciseCycleNum=20;

    private SharedPreferences mSharedPreferences=null;
    private SharedPreferences.Editor mEditor=null;

    //训练周期
    private int exerciseCycleInt = 20;

    //控件声明
    private RadioGroup exerciseCycle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initViews();
    }

    @Override
    public int getLayoutResourceId() {
        return R.layout.activity_exercise_cycle_setting;
    }

    public void initViews() {
        //得到控件
        exerciseCycle = (RadioGroup) findViewById(R.id.exercise_cycle);

        //得到之前存储值，并设置
        mSharedPreferences=getSharedPreferences(SKEA_EXERCISE_CYCLE_FILE, Activity.MODE_PRIVATE);
        if(mSharedPreferences!=null){//不是第一次运行
            int id;
            switch (mSharedPreferences.getInt(SKEA_EXERCISE_CYCLE_KEY,20)){
                case 5:
                    id=R.id.cycle1_rb;
                    break;
                case 10:
                    id=R.id.cycle2_rb;
                    break;
                case 15:
                    id=R.id.cycle3_rb;
                    break;
                case 20:
                    id=R.id.cycle4_rb;
                    break;
                case 25:
                    id=R.id.cycle5_rb;
                    break;
                case 30:
                    id=R.id.cycle6_rb;
                    break;
                default:
                    id=R.id.cycle4_rb;
            }
            exerciseCycle.check(id);

        }
        //注册事件
        exerciseCycle.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId) {
                    case R.id.cycle1_rb:
                        exerciseCycleInt = 5;


                        break;
                    case R.id.cycle2_rb:
                        exerciseCycleInt = 10;

                        break;
                    case R.id.cycle3_rb:

                        exerciseCycleInt = 15;
                        break;
                    case R.id.cycle4_rb:

                        exerciseCycleInt= 20;
                        break;
                    case R.id.cycle5_rb:

                        exerciseCycleInt = 25;
                        break;

                    case R.id.cycle6_rb:

                        exerciseCycleInt = 30;
                        break;
                    default:


                }
            }
        });


    }


    private void returnMessage() {
        //返回用户设置的训练强度
        Intent resultIntent = new Intent();
        resultIntent.putExtra(TestPelvicMuscleResultActivity.EXERCISE_CYCLE, exerciseCycleInt);
        setResult(RESULT_OK, resultIntent);
        this.finish();


    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == android.R.id.home) {
            returnMessage();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        //写入本地文件
        mSharedPreferences = getSharedPreferences(SKEA_EXERCISE_CYCLE_FILE, Activity.MODE_PRIVATE);
        mEditor = mSharedPreferences.edit();
        mEditor.putInt(SKEA_EXERCISE_CYCLE_KEY, exerciseCycleInt);
        mEditor.commit();
    }
}
