package me.linkcube.skea.ui.test;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;

import custom.android.app.dialog.DatePickerDialogFragment;
import custom.android.app.dialog.SimpleDialogFragment;
import me.linkcube.skea.R;
import me.linkcube.skea.base.ui.BaseActivity;
import me.linkcube.skea.core.persistence.EvaluationBean;
import me.linkcube.skea.view.LevelRadioGroup;

public class ReEvaluationActivity extends BaseActivity {
    //声明控件
    private LevelRadioGroup reproduct_history_lrg;
    private LevelRadioGroup sex_activity_lrg;
    private LevelRadioGroup urinary_incontinence_lrg;
    private LevelRadioGroup mental_status_lrg;


    private RelativeLayout birthday_layout;
    private RelativeLayout height_layout;
    private RelativeLayout weight_layout;

    private Button submit_bt;

    private EvaluationBean mEvaluationBean;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        init();


    }
    /**初始化控件及变量，注册事件*/
    private void init(){
        //得到控件
        reproduct_history_lrg=(LevelRadioGroup) findViewById(R.id.reproduct_history_lrg);
        sex_activity_lrg=(LevelRadioGroup) findViewById(R.id.sex_activity_lrg);
        urinary_incontinence_lrg=(LevelRadioGroup) findViewById(R.id.urinary_incontinence_lrg);
        mental_status_lrg=(LevelRadioGroup) findViewById(R.id.mental_status_lrg);
        submit_bt=(Button)findViewById(R.id.submit_bt);

        birthday_layout=(RelativeLayout) findViewById(R.id.birthday_layout);
        height_layout=(RelativeLayout) findViewById(R.id.height_layout);
        weight_layout=(RelativeLayout) findViewById(R.id.weight_layout);



        //注册事件
        reproduct_history_lrg.setOnOnLevelSelectedListener(new LevelRadioGroup.OnLevelSelectedListener() {
            @Override
            public void onLevelSelected(int level) {
                Log.i("CXC", "reproduct_history_lrg---" + level);
                //得到相应的Level
                mEvaluationBean.setReproductive_level(level);

            }
        });

        sex_activity_lrg.setOnOnLevelSelectedListener(new LevelRadioGroup.OnLevelSelectedListener() {
            @Override
            public void onLevelSelected(int level) {
                Log.i("CXC", "sex_activity_lrg---" + level);
                mEvaluationBean.setSexual_level(level);
            }
        });
        urinary_incontinence_lrg.setOnOnLevelSelectedListener(new LevelRadioGroup.OnLevelSelectedListener() {
            @Override
            public void onLevelSelected(int level) {
                Log.i("CXC", "urinary_incontinence_lrg---" + level);
                mEvaluationBean.setUrinary_level(level);
            }
        });
        mental_status_lrg.setOnOnLevelSelectedListener(new LevelRadioGroup.OnLevelSelectedListener() {
            @Override
            public void onLevelSelected(int level) {
                Log.i("CXC", "mental_status_lrg---" + level);
                mEvaluationBean.setMental_level(level);
            }
        });

        submit_bt.setOnClickListener(evaluationClickListener);
        birthday_layout.setOnClickListener(evaluationClickListener);
        height_layout.setOnClickListener(evaluationClickListener);
        weight_layout.setOnClickListener(evaluationClickListener);



    }

    /**
     * 得到测试数据 并进行评诂
     * */
    private void getResult(){


    }

    View.OnClickListener evaluationClickListener=new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            switch (v.getId()){
                case R.id.birthday_layout:
                    //todo

                    break;
                case R.id.height_layout:

                    break;
                case R.id.weight_layout:

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
        return R.layout.activity_re_evaluation;
    }


}
