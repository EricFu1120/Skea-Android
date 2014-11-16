package me.linkcube.skea.ui.test;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import me.linkcube.skea.R;
import me.linkcube.skea.core.persistence.EvaluationBean;
import me.linkcube.skea.ui.BaseActivity;
import me.linkcube.skea.view.LevelRadioGroup;

public class ReEvaluationActivity extends BaseActivity  {
    //声明控件
    private LevelRadioGroup reproduct_history_lrg;
    private LevelRadioGroup sex_activity_lrg;
    private LevelRadioGroup urinary_incontinence_lrg;
    private LevelRadioGroup mental_status_lrg;

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

        submit_bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //TODO
                /**
                 *
                 * 在这里得出测试结果
                 *
                 * **/


                getResult();
             }
        });


    }

    /**
     * 得到测试数据 并进行评诂
     * */
    private void getResult(){


    }


    @Override
    public int getLayoutResourceId() {
        return R.layout.activity_re_evaluation;
    }


}
