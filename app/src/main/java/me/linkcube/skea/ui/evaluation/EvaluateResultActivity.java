package me.linkcube.skea.ui.evaluation;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import me.linkcube.skea.R;
import me.linkcube.skea.base.ui.BaseActivity;

public class EvaluateResultActivity extends BaseActivity implements View.OnClickListener {

    private static final String TAG = "EvaluateResultActivity";

    private static final int[] RISK_FACTOR_IMG_RES = new int[]{R.drawable.icon_risk_factor_low, R.drawable.icon_risk_factor_low, R.drawable.icon_risk_factor_medium, R.drawable.icon_risk_factor_high, R.drawable.icon_risk_factor_high};

    public static final String KEY_EXERCISE_LEVEL = "KEY_EXERCISE_LEVEL";

    public static final String KEY_RISK_FACTOR = "KEY_RISK_FACTOR";

    private static final int REQUEST_CODE_SETTING_LEVEL = 1;

    private static final int REQUEST_CODE_EVALUATE = 2;

    private TextView exerciseLevel;

    private Button evaluateBtn;

    private ImageView riskRactorView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initViews();
    }

    @Override
    public int getLayoutResourceId() {
        return R.layout.activity_evaluate_result;
    }

    /**
     * 得到相关控件，注册事件
     */
    private void initViews() {
        riskRactorView = (ImageView) findViewById(R.id.risk_factor);
        exerciseLevel = (TextView) findViewById(R.id.level);
        evaluateBtn = (Button) findViewById(R.id.reevaluate);
        exerciseLevel.setOnClickListener(this);
        evaluateBtn.setOnClickListener(this);
    }

    private void updateRiskRactorView() {

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {
            case REQUEST_CODE_SETTING_LEVEL:
                //得到训练强度值
                if (resultCode == RESULT_OK) {
                    exerciseLevel.setText("Level " + data.getIntExtra(KEY_EXERCISE_LEVEL, 4));
                    Log.i(TAG, "onActivityResult - exercise level = " + data.getIntExtra(KEY_EXERCISE_LEVEL, 4));
                }
                break;
            case REQUEST_CODE_EVALUATE:
                if (resultCode == RESULT_OK) {
                    int riskFactor = data.getIntExtra(KEY_RISK_FACTOR, 0);
                    riskRactorView.setImageResource(RISK_FACTOR_IMG_RES[riskFactor]);
                    Log.i(TAG, "onActivityResult - risk factor = " + data.getIntExtra(KEY_RISK_FACTOR, 4));
                }
                break;
            default:
                break;

        }
    }

    @Override
    public void onClick(View v) {
        Intent intent = null;
        switch (v.getId()) {
            case R.id.level:
                intent = new Intent();
                intent.setClass(getApplicationContext(), ExerciseLevelSettingActivity.class);
                startActivityForResult(intent, REQUEST_CODE_SETTING_LEVEL);
                break;
            case R.id.reevaluate:
                intent = new Intent();
                intent.setClass(getApplicationContext(), EvaluateActivity.class);
                startActivityForResult(intent, REQUEST_CODE_EVALUATE);
                break;
            default:
                break;
        }
    }
}
