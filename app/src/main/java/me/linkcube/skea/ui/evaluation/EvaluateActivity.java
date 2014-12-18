package me.linkcube.skea.ui.evaluation;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TextView;

import java.util.Calendar;
import java.util.Date;

import me.linkcube.skea.R;
import me.linkcube.skea.base.ui.BaseActivity;
import me.linkcube.skea.core.evaluation.EvaluationScore;
import me.linkcube.skea.core.persistence.Evaluation;
import me.linkcube.skea.view.LevelRadioGroup;
import me.linkcube.skea.view.NumberPickerDialog;
import me.linkcube.skea.view.TwoWayRadioGroup;

public class EvaluateActivity extends BaseActivity implements TwoWayRadioGroup.OnTwoWaySelectedListener, LevelRadioGroup.OnLevelSelectedListener, NumberPickerDialog.OnValueChangedListener {

    private TwoWayRadioGroup menopausalRadioGroup;
    private LevelRadioGroup childrenRadioGroup;
    private TwoWayRadioGroup smokingRadioGroup;
    private TwoWayRadioGroup surgeryRadioGroup;
    private TwoWayRadioGroup workRadioGroup;
    private TwoWayRadioGroup problemsRadioGroup;
    private TwoWayRadioGroup popRadioGroup;
    private TwoWayRadioGroup bulgeRadioGroup;
    private TextView birthday_tv;
    private TextView height_tv;
    private TextView weight_tv;
    private Button submit_bt;

    private int menopausalScore;
    private int childrenScore;
    private int smokingScore;
    private int surgeryScore;
    private int workScore;
    private int problemsScore;
    private int popScore;
    private int bulgeScore;

    private int height;
    private int weight;

    private NumberPickerDialog numberPickerDialog;

    private Evaluation mEvaluationBean;

    private int mYear = 1981;

    private int mMonth = 0;

    private int mDays = 1;

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

        submit_bt = (Button) findViewById(R.id.submit_bt);

        birthday_tv = (TextView) findViewById(R.id.birthday_tv);
        height_tv = (TextView) findViewById(R.id.height_tv);
        weight_tv = (TextView) findViewById(R.id.weight_tv);

        birthday_tv.setOnClickListener(evaluationClickListener);
        height_tv.setOnClickListener(evaluationClickListener);
        weight_tv.setOnClickListener(evaluationClickListener);


    }

    DatePickerDialog.OnDateSetListener myDateSetListener = new DatePickerDialog.OnDateSetListener() {

        @Override
        public void onDateSet(DatePicker view, int year, int monthOfYear,
                              int dayOfMonth) {
            // TODO Auto-generated method stub

            // 更新年月日，以便下次启动DatePickerDialog时，显示的是上一次设置的值
            mYear = year;
            mMonth = monthOfYear;
            mDays = dayOfMonth;
            Log.i("CXC", "year-month-day:" + year + "-" + monthOfYear + "-" + dayOfMonth);

            updateDateDisplay();
        }
    };
    View.OnClickListener evaluationClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Message msg = new Message();
            switch (v.getId()) {

                case R.id.birthday_tv:
                    //todo
                    DatePickerDialog datePickerDialog = new DatePickerDialog(
                            EvaluateActivity.this, myDateSetListener, mYear, mMonth,
                            mDays);
                    datePickerDialog.show();

                    break;
                case R.id.height_tv:

                    break;
                case R.id.weight_tv:

                    break;
                case R.id.submit_bt:
                    //todo
                    getResult();

                    break;
                default:

            }

        }
    };


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
        birthday_tv.setText(new StringBuilder().append(mYear).append("-")
                .append((mMonth + 1) < 10 ? "0" + (mMonth + 1) : (mMonth + 1))
                .append("-").append((mDays < 10) ? "0" + mDays : mDays));
    }


    /**
     * 得到测试数据 并进行评诂
     */
    private void getResult() {

    }


    @Override
    public void onLevelSelected(View view, int level) {
        switch (view.getId()) {
            case R.id.children_radioGroup:
                childrenScore = EvaluationScore.getChildrenScore(level);
                break;
            default:
                break;
        }
    }

    @Override
    public void onTwoWaySelected(View view, boolean yes) {
        switch (view.getId()) {
            case R.id.menopausal_radioGroup:
                menopausalScore = EvaluationScore.getMenopauseScore(yes);
                break;
            case R.id.smoking_radioGroup:
                smokingScore = EvaluationScore.getSmokingScore(yes);
                break;
            case R.id.surgery_radioGroup:
                surgeryScore = EvaluationScore.getPelvicFloorSurgeryScore(yes);
                break;
            case R.id.work_radioGroup:
                workScore = EvaluationScore.getCurrentHeavyWorkScore(yes);
                break;
            case R.id.problems_radioGroup:
                problemsScore = EvaluationScore.getPelvicFloorProblemsScore(yes);
                break;
            case R.id.pop_radioGroup:
                popScore = EvaluationScore.getMotherWithPOPScore(yes);
                break;
            case R.id.bulge_radioGroup:
                bulgeScore = EvaluationScore.getSeeingBulgeScore(yes);
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
}
