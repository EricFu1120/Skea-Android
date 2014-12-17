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
import me.linkcube.skea.core.persistence.Evaluation;
import me.linkcube.skea.view.LevelRadioGroup;

public class EvaluateActivity extends BaseActivity {
    //声明控件

    private LevelRadioGroup reproduct_history_lrg;

    private LevelRadioGroup sex_activity_lrg;

    private LevelRadioGroup urinary_incontinence_lrg;

    private LevelRadioGroup mental_status_lrg;


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
    private TextView birthday_tv;
    private TextView height_tv;
    private TextView weight_tv;
    private Button submit_bt;
    private Evaluation mEvaluationBean;
    private int mYear = 1981;
    private int mMonth = 0;
    private int mDays = 1;

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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initializeViews();
        getDate();
        updateDateDisplay();
    }

    /**
     * 初始化控件及变量，注册事件
     */
    private void initializeViews() {
        //得到控件
//        reproduct_history_lrg = (LevelRadioGroup) findViewById(R.id.reproduct_history_lrg);
//        sex_activity_lrg = (LevelRadioGroup) findViewById(R.id.sex_activity_lrg);
//        urinary_incontinence_lrg = (LevelRadioGroup) findViewById(R.id.urinary_incontinence_lrg);
//        mental_status_lrg = (LevelRadioGroup) findViewById(R.id.mental_status_lrg);
        submit_bt = (Button) findViewById(R.id.submit_bt);

        birthday_tv = (TextView) findViewById(R.id.birthday_tv);
        height_tv = (TextView) findViewById(R.id.height_tv);
        weight_tv = (TextView) findViewById(R.id.weight_tv);

        birthday_tv.setOnClickListener(evaluationClickListener);
        height_tv.setOnClickListener(evaluationClickListener);
        weight_tv.setOnClickListener(evaluationClickListener);


//        //注册事件
//        reproduct_history_lrg.setOnOnLevelSelectedListener(new LevelRadioGroup.OnLevelSelectedListener() {
//            @Override
//            public void onLevelSelected(int level) {
//                Log.i("CXC", "reproduct_history_lrg---" + level);
//                //得到相应的Level
//                mEvaluationItemsClass.setReproductive_level(level);
//
//            }
//        });
//
//        sex_activity_lrg.setOnOnLevelSelectedListener(new LevelRadioGroup.OnLevelSelectedListener() {
//            @Override
//            public void onLevelSelected(int level) {
//                Log.i("CXC", "sex_activity_lrg---" + level);
//                mEvaluationItemsClass.setSexual_level(level);
//            }
//        });
//        urinary_incontinence_lrg.setOnOnLevelSelectedListener(new LevelRadioGroup.OnLevelSelectedListener() {
//            @Override
//            public void onLevelSelected(int level) {
//                Log.i("CXC", "urinary_incontinence_lrg---" + level);
//                mEvaluationItemsClass.setUrinary_level(level);
//            }
//        });
//        mental_status_lrg.setOnOnLevelSelectedListener(new LevelRadioGroup.OnLevelSelectedListener() {
//            @Override
//            public void onLevelSelected(int level) {
//                Log.i("CXC", "mental_status_lrg---" + level);
//                mEvaluationItemsClass.setMental_level(level);
//            }
//        });

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
    public int getLayoutResourceId() {
        return R.layout.activity_evaluate;
    }


}
