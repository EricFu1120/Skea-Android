package me.linkcube.skea.ui.test;


import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.RadioGroup;

import me.linkcube.skea.R;
import me.linkcube.skea.ui.user.TestPelvicMuscleResultActivity;

public class SettingExerciseLevel extends ActionBarActivity {
    //训练强度

    private String exerciseLevelString = "Level 4";
    //控件声明
    private RadioGroup exerciseLevel;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting_exercise_level);
        //ActionBar实现后退导航
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        //返回用户设置的训练强度
        Intent resultIntent=new Intent();
        resultIntent.putExtra(TestPelvicMuscleResultActivity.EXERCISE_LEVEL,exerciseLevelString);
        setResult(RESULT_OK,resultIntent);
    }

    public void init() {
        //得到控件
        exerciseLevel = (RadioGroup) findViewById(R.id.exercise_level);
        //注册事件
        exerciseLevel.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId) {
                    case R.id.level1_rb:
                        exerciseLevelString = "Level 1";


                        break;
                    case R.id.level2_rb:
                        exerciseLevelString = "Level 2";

                        break;
                    case R.id.level3_rb:

                        exerciseLevelString = "Level 3";
                        break;
                    case R.id.level4_rb:

                        exerciseLevelString = "Level 4";
                        break;
                    case R.id.level5_rb:

                        exerciseLevelString = "Level 5";
                        break;
                    default:


                }
            }
        });
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.setting_exercise_level, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);

    }


}
