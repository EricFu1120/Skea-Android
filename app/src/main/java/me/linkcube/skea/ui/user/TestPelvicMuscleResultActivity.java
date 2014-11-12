package me.linkcube.skea.ui.user;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Button;

import me.linkcube.skea.R;
import me.linkcube.skea.ui.BaseActivity;
import me.linkcube.skea.ui.test.ExerciseLevelSettingActivity;
import me.linkcube.skea.ui.test.ReEvaluationActivity;

public class TestPelvicMuscleResultActivity extends BaseActivity {
    public static final String EXERCISE_LEVEL = "me.linkcube.skea.ui.user.TestPelvicMuscleResultActivity.Exercise_level";
    private static final int SETTING_LEVEL_REQUEST_CODE = 1;


    //声明控件
    private TextView exerciseLevel;
    private Button reevaluate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initViews();
    }

    @Override
    public int getLayoutResourceId() {
        return R.layout.activity_test_pelvic_muscle_result;
    }

    /**
     * 得到相关控件，注册事件
     */
    private void initViews() {

        exerciseLevel = (TextView) findViewById(R.id.level);
        reevaluate = (Button) findViewById(R.id.reevaluate);



        exerciseLevel.setOnClickListener(testPelvicViewClickListener);
        reevaluate.setOnClickListener(testPelvicViewClickListener);
    }

    View.OnClickListener testPelvicViewClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.level:
                    startSettingExerciseLevel();
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
        intent.setClass(getApplicationContext(), ExerciseLevelSettingActivity.class);
        startActivityForResult(intent, SETTING_LEVEL_REQUEST_CODE);

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

            default:

        }
    }
}
