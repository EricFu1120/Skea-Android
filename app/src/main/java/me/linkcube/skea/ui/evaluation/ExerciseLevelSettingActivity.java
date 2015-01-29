package me.linkcube.skea.ui.evaluation;


import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.widget.RadioGroup;

import custom.android.util.PreferenceUtils;
import me.linkcube.skea.R;
import me.linkcube.skea.base.ui.BaseActivity;
import me.linkcube.skea.core.KeyConst;


public class ExerciseLevelSettingActivity extends BaseActivity {

    private static final String TAG = "ExerciseLevelSettingActivity";
    /**
     * Exercise Level Key
     */

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

    @Override
    public void onPause() {
        super.onPause();
        PreferenceUtils.setInt(this, KeyConst.SKEA_EXERCISE_LEVEL_KEY, exerciseLevelIndex);
    }


    private void initViews() {
        exerciseLevel = (RadioGroup) findViewById(R.id.exercise_level);
        int level = PreferenceUtils.getInt(this, KeyConst.SKEA_EXERCISE_LEVEL_KEY, 4);
        int id;
        switch (level+1) {
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

        exerciseLevel.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId) {
                    case R.id.level1_rb:
                        exerciseLevelIndex = 0;
                        break;
                    case R.id.level2_rb:
                        exerciseLevelIndex = 1;
                        break;
                    case R.id.level3_rb:
                        exerciseLevelIndex = 2;
                        break;
                    case R.id.level4_rb:
                        exerciseLevelIndex = 3;
                        break;
                    case R.id.level5_rb:
                        exerciseLevelIndex = 4;
                        break;
                    default:


                }
            }
        });
    }

    private void finishWithMessage() {
        //返回用户设置的训练强度
        Intent resultIntent = new Intent();
        resultIntent.putExtra(EvaluateResultActivity.KEY_EXERCISE_LEVEL, exerciseLevelIndex);
        Log.i(TAG, "---level:" + exerciseLevelIndex);
        setResult(RESULT_OK, resultIntent);
        this.finish();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == android.R.id.home) {
            finishWithMessage();
//            return true;
        }
        return super.onOptionsItemSelected(item);

    }

}
