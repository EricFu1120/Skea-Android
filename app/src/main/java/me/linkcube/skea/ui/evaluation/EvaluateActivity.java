package me.linkcube.skea.ui.evaluation;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;

import java.util.Calendar;
import java.util.Date;

import custom.android.widget.Toaster;
import me.linkcube.skea.R;
import me.linkcube.skea.base.ui.BaseActivity;
import me.linkcube.skea.core.UserManager;
import me.linkcube.skea.core.evaluation.EvaluationScore;
import me.linkcube.skea.core.persistence.Evaluation;
import me.linkcube.skea.core.persistence.User;
import me.linkcube.skea.util.TimeUtils;
import me.linkcube.skea.view.LevelRadioGroup;
import me.linkcube.skea.view.NumberPickerDialog;
import me.linkcube.skea.view.TwoWayRadioGroup;

public class EvaluateActivity extends BaseActivity implements TwoWayRadioGroup.OnTwoWaySelectedListener, LevelRadioGroup.OnLevelSelectedListener, NumberPickerDialog.OnValueChangedListener, View.OnClickListener {

    private static final String TAG = "EvaluateActivity";

    private TwoWayRadioGroup menopausalRadioGroup;
    private LevelRadioGroup childrenRadioGroup;
    private TwoWayRadioGroup smokingRadioGroup;
    private TwoWayRadioGroup surgeryRadioGroup;
    private TwoWayRadioGroup workRadioGroup;
    private TwoWayRadioGroup problemsRadioGroup;
    private TwoWayRadioGroup popRadioGroup;
    private TwoWayRadioGroup bulgeRadioGroup;
    private TextView birthday_tv;
    private EditText height_tv;
    private EditText weight_tv;

    private int scoreMenopausal;
    private int scoreChildren;
    private int scoreSmoking;
    private int scoreSurgery;
    private int scoreWork;
    private int scoreProblems;
    private int scorePop;
    private int scoreBulge;
    private int scoreAge;
    private int scoreMeanBMI;

    private int height;
    private int weight;

    private String birthday;

    private int riskLevel;

    private int mYear = 1981;

    private int mMonth = 0;

    private int mDays = 1;

    private boolean isEvaluateDone;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initializeViews();
        getDate();
        updateDateDisplay();
    }

    @Override
    public int getLayoutResourceId() {
        return R.layout.activity_evaluate;
    }


    /**
     * 初始化控件及变量，注册事件
     */
    private void initializeViews() {
        menopausalRadioGroup = (TwoWayRadioGroup) findViewById(R.id.menopausal_radioGroup);
        childrenRadioGroup = (LevelRadioGroup) findViewById(R.id.children_radioGroup);
        smokingRadioGroup = (TwoWayRadioGroup) findViewById(R.id.smoking_radioGroup);
        surgeryRadioGroup = (TwoWayRadioGroup) findViewById(R.id.surgery_radioGroup);
        workRadioGroup = (TwoWayRadioGroup) findViewById(R.id.work_radioGroup);
        problemsRadioGroup = (TwoWayRadioGroup) findViewById(R.id.problems_radioGroup);
        popRadioGroup = (TwoWayRadioGroup) findViewById(R.id.pop_radioGroup);
        bulgeRadioGroup = (TwoWayRadioGroup) findViewById(R.id.bulge_radioGroup);
        menopausalRadioGroup.setOnTwoWaySelectedListener(this);
        childrenRadioGroup.setOnOnLevelSelectedListener(this);
        smokingRadioGroup.setOnTwoWaySelectedListener(this);
        surgeryRadioGroup.setOnTwoWaySelectedListener(this);
        workRadioGroup.setOnTwoWaySelectedListener(this);
        problemsRadioGroup.setOnTwoWaySelectedListener(this);
        popRadioGroup.setOnTwoWaySelectedListener(this);
        bulgeRadioGroup.setOnTwoWaySelectedListener(this);

        findViewById(R.id.submit).setOnClickListener(this);

        birthday_tv = (TextView) findViewById(R.id.birthday_tv);
        height_tv = (EditText) findViewById(R.id.height_tv);
        weight_tv = (EditText) findViewById(R.id.weight_tv);

        birthday_tv.setOnClickListener(this);
    }

    DatePickerDialog.OnDateSetListener myDateSetListener = new DatePickerDialog.OnDateSetListener() {

        @Override
        public void onDateSet(DatePicker view, int year, int monthOfYear,
                              int dayOfMonth) {
            // 更新年月日，以便下次启动DatePickerDialog时，显示的是上一次设置的值
            mYear = year;
            mMonth = monthOfYear;
            mDays = dayOfMonth;
            Log.i(TAG, "year-month-day:" + year + "-" + monthOfYear + "-" + dayOfMonth);
            updateDateDisplay();
        }
    };

    private boolean getWeight() {
        weight = Integer.parseInt(weight_tv.getText().toString());
        if (weight > 30 && weight < 150)
            return true;
        return false;
    }

    private boolean getHeight() {
        height = Integer.parseInt(height_tv.getText().toString());
        if (height > 100 && height < 260)
            return true;
        return false;
    }


    /**
     * 得当前的年月日,以便初始化日历
     */
    private void getDate() {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date(System.currentTimeMillis()));
        mYear = calendar.get(Calendar.YEAR);
        mMonth = calendar.get(Calendar.MONTH);
        mDays = calendar.get(Calendar.DAY_OF_MONTH);
    }

    /**
     * 更新日期显示
     */
    private void updateDateDisplay() {
        birthday = new StringBuilder().append(mYear).append("-")
                .append((mMonth + 1) < 10 ? "0" + (mMonth + 1) : (mMonth + 1))
                .append("-").append((mDays < 10) ? "0" + mDays : mDays).toString();
        birthday_tv.setText(birthday);
    }

    private void saveUser() {
        User user = UserManager.getInstance().getUser(this);
        if (user != null) {
            Log.d(TAG, user.toString());
            int age = TimeUtils.getAgeByBirthday(birthday);
            user.setAge(age);
            user.setBirthday(birthday);
            user.setHeight(height);
            user.setWeight(weight);
            user.save();
        }
    }

    private void saveEvaluations() {
        Evaluation evaluation = new Evaluation();
        int age = TimeUtils.getAgeByBirthday(birthday);
        evaluation.setAge(age);
        evaluation.setBirthday(birthday);
        scoreAge = EvaluationScore.getAgeScore(age);
        evaluation.setScoreAge(scoreAge);
        scoreMeanBMI = EvaluationScore.getMeanBMI(weight, height);
        evaluation.setScoreMeanBMI(scoreMeanBMI);
        evaluation.setHeight(height);
        evaluation.setWeight(weight);
        evaluation.setScoreMenopausal(scoreMenopausal);
        evaluation.setScoreChildren(scoreChildren);
        evaluation.setScoreSmoking(scoreSmoking);
        evaluation.setScoreSurgery(scoreSurgery);
        evaluation.setScoreWork(scoreWork);
        evaluation.setScoreProblems(scoreProblems);
        evaluation.setScorePOP(scorePop);
        evaluation.setScoreBulge(scoreBulge);
        int scoreTotal = scoreAge + scoreMeanBMI + scoreMenopausal + scoreChildren + scoreSmoking + scoreSurgery + scoreWork + scoreProblems + scorePop + scoreBulge;
        evaluation.setScoreTotal(scoreTotal);
        riskLevel = EvaluationScore.getRiskLevel(scoreTotal);
        evaluation.setLevel(riskLevel);
        evaluation.save();
    }


    /**
     * 得到测试数据 并进行评诂
     */
    private void evaluate() {
        if (!getWeight()) {
            Toaster.showShort(this, "不符合条件");
            return;
        }
        if (!getHeight()) {
            Toaster.showShort(this, "不符合条件");
            return;
        }
        saveUser();
        saveEvaluations();
        isEvaluateDone = true;
        finishWithMessage();
    }


    @Override
    public void onLevelSelected(View view, int level) {
        switch (view.getId()) {
            case R.id.children_radioGroup:
                scoreChildren = EvaluationScore.getChildrenScore(level);
                break;
            default:
                break;
        }
    }

    @Override
    public void onTwoWaySelected(View view, boolean yes) {
        switch (view.getId()) {
            case R.id.menopausal_radioGroup:
                scoreMenopausal = EvaluationScore.getMenopauseScore(yes);
                break;
            case R.id.smoking_radioGroup:
                scoreSmoking = EvaluationScore.getSmokingScore(yes);
                break;
            case R.id.surgery_radioGroup:
                scoreSurgery = EvaluationScore.getPelvicFloorSurgeryScore(yes);
                break;
            case R.id.work_radioGroup:
                scoreWork = EvaluationScore.getCurrentHeavyWorkScore(yes);
                break;
            case R.id.problems_radioGroup:
                scoreProblems = EvaluationScore.getPelvicFloorProblemsScore(yes);
                break;
            case R.id.pop_radioGroup:
                scorePop = EvaluationScore.getMotherWithPOPScore(yes);
                break;
            case R.id.bulge_radioGroup:
                scoreBulge = EvaluationScore.getSeeingBulgeScore(yes);
                break;
            default:
                break;
        }
    }

    @Override
    public void onValueChange(View parent, int oldVal, int newVal) {
        switch (parent.getId()) {
            case R.id.height_tv:
                height = newVal;
                break;
            case R.id.weight_tv:
                weight = newVal;
                break;
            default:
                break;
        }
    }

    private void finishWithMessage() {
        Intent resultIntent = new Intent();
        resultIntent.putExtra(EvaluateResultActivity.KEY_RISK_FACTOR, riskLevel);
        Log.i(TAG, "level:" + riskLevel);
        setResult(RESULT_OK, resultIntent);
        this.finish();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == android.R.id.home) {
            if (isEvaluateDone) {
                finishWithMessage();
                return true;
            }
        }
        return super.onOptionsItemSelected(item);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.birthday_tv:
                //Todo
                DatePickerDialog datePickerDialog = new DatePickerDialog(
                        EvaluateActivity.this, myDateSetListener, mYear, mMonth,
                        mDays);
                datePickerDialog.show();
                break;
            case R.id.height_tv:
                //TODO numberPickerDialog.configurePicker(height_tv, 260, 100).show();
                break;
            case R.id.weight_tv:
                //TODO
                break;
            case R.id.submit:
                evaluate();
                break;
            default:
                break;
        }
    }
}
