package me.linkcube.skea.ui.user;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Button;
import android.app.Dialog;

import custom.android.app.CustomActionBarActivity;
import me.linkcube.skea.R;
import me.linkcube.skea.ui.test.ReEvaluationActivity;
import me.linkcube.skea.ui.test.SettingExerciseCycle;
import me.linkcube.skea.ui.test.SettingExerciseLevel;

public class TestPelvicMuscleResultActivity extends CustomActionBarActivity {
    public static final String EXERCISE_LEVEL = "me.linkcube.skea.ui.user.TestPelvicMuscleResultActivity.Exercise_level";
    public static final String EXERCISE_CYCLE = "me.linkcube.skea.ui.user.TestPelvicMuscleResultActivity.Exercise_cycle";
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

    /**
     * 得到相关控件，注册事件
     */
    private void init() {

        exerciseLevel = (TextView) findViewById(R.id.level);
        exerciseCycle = (TextView) findViewById(R.id.exercise_cycle_tv);
        reevaluate = (Button) findViewById(R.id.reevaluate);

        exerciseLevel.setOnClickListener(testPelvicViewClickListener);
        exerciseCycle.setOnClickListener(testPelvicViewClickListener);
        reevaluate.setOnClickListener(testPelvicViewClickListener);
    }

    View.OnClickListener testPelvicViewClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.level:
                    startSettingExerciseLevel();
                    break;

                case R.id.exercise_cycle_tv:
                    startSettingExerciseCycle();
                    break;
                case R.id.reevaluate:

                    reEvaluateButtonClick();
                    break;

                default:

            }
        }
    };

    /**
     * 启动设置训练强度
     */
    private void startSettingExerciseLevel() {
        Intent intent = new Intent();
        intent.setClass(getApplicationContext(), SettingExerciseLevel.class);
        startActivityForResult(intent, SETTING_LEVEL_REQUEST_CODE);

    }


    /**
     * 启动设置训练周期
     */
    private void startSettingExerciseCycle() {
        Dialog alertDialog = new AlertDialog.Builder(this)
                .setTitle("Warning")
                .setMessage("重新计算锻练数据，还是把历史数据累加 ？")
                .setPositiveButton("重新计算", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        //Todo...重新计算在这里做处理ＸＸＸＸＸＸＸＸＸ

                        Intent intent = new Intent();
                        intent.setClass(getApplicationContext(), SettingExerciseCycle.class);
                        startActivityForResult(intent, SETTING_CYCLE_REQUEST_CODE);
                    }
                })
                .setNegativeButton("累加", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        //Todo...累加在这里做处理ＸＸＸＸＸＸＸＸＸＸＸＸ



                        Intent intent = new Intent();
                        intent.setClass(getApplicationContext(), SettingExerciseCycle.class);
                        startActivityForResult(intent, SETTING_CYCLE_REQUEST_CODE);

                    }
                })
                .show();


    }

    private void reEvaluateButtonClick() {

        startActivity(new Intent().setClass(getApplicationContext(), ReEvaluationActivity.class));
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
            case SETTING_LEVEL_REQUEST_CODE:
                //得到训练强度值
                if (resultCode == RESULT_OK) {

                    exerciseLevel.setText("Level "+data.getIntExtra(EXERCISE_LEVEL,4));
                    Log.i("CXC","++++:level:"+data.getIntExtra(EXERCISE_LEVEL,4));
                }
                break;
            case SETTING_CYCLE_REQUEST_CODE:
                //得到训练周期值
                if (resultCode == RESULT_OK) {

                    exerciseCycle.setText("Exercise Cycle "+data.getIntExtra(EXERCISE_CYCLE,20)+" Days");
                    Log.i("CXC","++++:cycle:"+data.getIntExtra(EXERCISE_CYCLE,20));
                }


                break;
            default:

        }
    }
}
