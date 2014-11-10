package me.linkcube.skea.ui.user;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Button;

import me.linkcube.skea.R;
import me.linkcube.skea.ui.test.ReEvaluationActivity;
import me.linkcube.skea.ui.test.SettingExerciseCycle;
import me.linkcube.skea.ui.test.SettingExerciseLevel;

public class TestPelvicMuscleResultActivity extends ActionBarActivity{
    public  static final String EXERCISE_LEVEL = "me.linkcube.skea.ui.user.TestPelvicMuscleResultActivity.Exercise_level";
    public  static final String EXERCISE_CYCLE = "me.linkcube.skea.ui.user.TestPelvicMuscleResultActivity.Exercise_level";
    private static final int SETTING_LEVEL_REQUEST_CODE = 1;
    private static final int SETTING_CYCLE_REQUEST_CODE = 2;



    //声明控件
    private TextView exerciseLevel;
    private TextView exerciseCycle;
    private Button reevaluate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_pelvic_muscle_result);
        //ActionBar实现后退导航
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        init();
    }

    /**得到相关控件，注册事件*/
    private void init() {

        exerciseLevel = (TextView) findViewById(R.id.level);
        exerciseCycle = (TextView) findViewById(R.id.exercise_cycle_tv);
        reevaluate=(Button) findViewById(R.id.reevaluate);

        exerciseLevel.setOnClickListener(testPelvicViewClickListener);
        exerciseCycle.setOnClickListener(testPelvicViewClickListener);
        reevaluate.setOnClickListener(testPelvicViewClickListener);
    }
    View.OnClickListener testPelvicViewClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            switch (v.getId()){
                case R.id.level:
                    startSettingExerciseLevel();
                    break;

                case R.id.exercise_cycle_tv:
                    startSettingExerciseCycle();
                    break;
                case R.id.reevaluate:
                    startActivity(new Intent().setClass(getApplicationContext(), ReEvaluationActivity.class));
                    break;

                default:

            }
        }
    };

    /**
     * 启动设置训练强度
     */
    private  void startSettingExerciseLevel() {
        Intent intent = new Intent();
        intent.setClass(getApplicationContext(), SettingExerciseLevel.class);
        startActivityForResult(intent, SETTING_LEVEL_REQUEST_CODE);

    }


    /**
     * 启动设置训练周期
     */
    private void startSettingExerciseCycle() {
        Intent intent = new Intent();
        intent.setClass(getApplicationContext(), SettingExerciseCycle.class);
        startActivityForResult(intent, SETTING_CYCLE_REQUEST_CODE);

    }




    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.test_pelvic_muscle_result, menu);
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

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {
            case 1:
                //得到训练强度值
                if (resultCode == RESULT_OK) {

                    exerciseLevel.setText(data.getStringExtra(EXERCISE_LEVEL));
                }

            case 2:
                //得到训练强度值
                if (resultCode == RESULT_OK) {

                    exerciseCycle.setText(data.getStringExtra(EXERCISE_CYCLE));
                }



                break;
            default:

        }
    }
}
