package me.linkcube.skea.ui.test;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.RadioGroup;

import custom.android.app.CustomActionBarActivity;
import me.linkcube.skea.R;
import me.linkcube.skea.ui.user.TestPelvicMuscleResultActivity;

public class SettingExerciseCycle extends CustomActionBarActivity {

    //训练周期
    private int exerciseCycleInt = 20;

    //控件声明
    private RadioGroup exerciseCycle;
    private Button sumit_cycle_bt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting_exercise_cycle);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        init();
    }

    public void init() {
        //得到控件
        exerciseCycle = (RadioGroup) findViewById(R.id.exercise_cycle);
        sumit_cycle_bt = (Button) findViewById(R.id.sumit_cycle_bt);
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

        sumit_cycle_bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                returnMessage();
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
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.setting_exercise_cycle, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
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
        //返回用户设置的训练周期
        Intent resultIntent = new Intent();
        resultIntent.putExtra(TestPelvicMuscleResultActivity.EXERCISE_CYCLE, exerciseCycleInt);
        setResult(RESULT_OK, resultIntent);
    }
}
