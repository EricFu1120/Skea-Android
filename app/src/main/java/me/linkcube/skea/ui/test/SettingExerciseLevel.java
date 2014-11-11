package me.linkcube.skea.ui.test;


import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.RadioGroup;
import  android.widget.Button;

import custom.android.app.CustomActionBarActivity;
import custom.android.widget.Toaster;
import me.linkcube.skea.R;
import me.linkcube.skea.ui.user.TestPelvicMuscleResultActivity;

<<<<<<< HEAD
public class SettingExerciseLevel extends ActionBarActivity {

=======
public class SettingExerciseLevel extends CustomActionBarActivity {
>>>>>>> bd59b32bd90bb0badf39c5ea2a6cf0f5d2ade9ce
    //训练强度

    private int exerciseLevelIndex = 4;
    //控件声明
    private RadioGroup exerciseLevel;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting_exercise_level);
        //ActionBar实现后退导航
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        init();


    }

    public void configureActionBar()
    {
        ActionBar actionBar = getSupportActionBar();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

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


    /**向父Activity返回消息*/
    private void returnMessage(){
        //返回用户设置的训练强度
        Intent resultIntent=new Intent();
        resultIntent.putExtra(TestPelvicMuscleResultActivity.EXERCISE_LEVEL,exerciseLevelIndex);
        Log.i("CXC","---level:"+ exerciseLevelIndex);
        setResult(RESULT_OK, resultIntent);
        this.finish();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.setting_exercise_level, menu);
        return true;
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
