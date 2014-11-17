package me.linkcube.skea.ui.evaluation;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import me.linkcube.skea.R;
import me.linkcube.skea.base.ui.BaseActivity;

public class TestPelvicMuscleResultActivity extends BaseActivity {
    public static final String EXERCISE_LEVEL = "me.linkcube.skea.ui.test.TestPelvicMuscleResultActivity.Exercise_level";
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
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {
            case SETTING_LEVEL_REQUEST_CODE:
                //得到训练强度值
                if (resultCode == RESULT_OK) {

                    exerciseLevel.setText("Level " + data.getIntExtra(EXERCISE_LEVEL, 4));
                    Log.i("CXC", "++++:level:" + data.getIntExtra(EXERCISE_LEVEL, 4));
                }
                break;

            default:

        }
    }
}
