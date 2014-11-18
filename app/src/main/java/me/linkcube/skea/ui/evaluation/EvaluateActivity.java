package me.linkcube.skea.ui.evaluation;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TextView;

import me.linkcube.skea.R;


import me.linkcube.skea.base.ui.BaseActivity;
import me.linkcube.skea.core.persistence.Evaluation;

public class EvaluateActivity extends BaseActivity {
    //声明控件
//    private LevelRadioGroup reproduct_history_lrg;
//    private LevelRadioGroup sex_activity_lrg;
//    private LevelRadioGroup urinary_incontinence_lrg;
//    private LevelRadioGroup mental_status_lrg;

    private TextView birthday_tv;
    private TextView height_tv;
    private TextView weight_tv;


    private Button submit_bt;

    private Evaluation mEvaluationBean;

    private static final int DATE_DIALOG_ID = 1;
    private static final int SHOW_DATAPICK = 0;
    private int mYear=1981;
    private int mMonth=0;
    private int mDay=1;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initializeViews();
        //setDateTime();
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
                .append("-").append((mDay < 10) ? "0" + mDay : mDay));
    }


    /**
     * 日期控件的事件
     */
    private DatePickerDialog.OnDateSetListener mDateSetListener = new DatePickerDialog.OnDateSetListener() {

        @Override
        public void onDateSet(DatePicker view, int year, int monthOfYear,
                              int dayOfMonth) {
            // TODO Auto-generated method stub
            mYear = year;
            mMonth = monthOfYear;
            mDay = dayOfMonth;
            updateDateDisplay();

        }
    };


    @Override
    protected Dialog onCreateDialog(int id) {
        switch (id) {
            case DATE_DIALOG_ID:
                return new DatePickerDialog(this, mDateSetListener, mYear, mMonth,
                        mDay);

        }

        return null;
    }

    protected void onPrepareDialog(int id, Dialog dialog) {
        switch (id) {
            case DATE_DIALOG_ID:
                ((DatePickerDialog) dialog).updateDate(mYear, mMonth, mDay);
                break;
        }
    }

    /**
     * 处理日期和时间控件的Handler
     */
    Handler dateandtimeHandler = new Handler() {

        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case EvaluateActivity.SHOW_DATAPICK:
                    showDialog(DATE_DIALOG_ID);
                    break;

            }
        }

    };

    /**
     * 得到测试数据 并进行评诂
     */
    private void getResult() {


    }

    View.OnClickListener evaluationClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Message msg=new Message();
            switch (v.getId()) {

                case R.id.birthday_tv:
                    //todo
                    msg.what = EvaluateActivity.SHOW_DATAPICK;
                    EvaluateActivity.this.dateandtimeHandler.sendMessage(msg);
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

    @Override
    public int getLayoutResourceId() {
        return R.layout.activity_evaluate;
    }

    

}
