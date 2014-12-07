package me.linkcube.skea.ui.exercise;

import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.ToggleButton;

import java.util.Timer;
import java.util.TimerTask;

import me.linkcube.skea.R;
import me.linkcube.skea.base.ui.BaseActivity;
import me.linkcube.skea.core.excercise.Bar;
import me.linkcube.skea.core.excercise.ExerciseController;
import me.linkcube.skea.core.excercise.ExerciseScoreCounter;


public class ExerciseActivity extends BaseActivity implements ExerciseController.ExerciseScoreCallback {

    public boolean scroll = true;
    private LinearLayout frontGroup;
    private LinearLayout behindGroup;
    private ScrollView frontScrollView;
    private ScrollView behindScrollView;
    private ExerciseController controller;
    private ToggleButton shrinkButton;
    private TextView leftTimeTextView;
    private TextView scoreTextView;
    private boolean shrink;

    /**
     * 用于标记ScorllView 是否可以滑动
     * 此处是不允许滑动的
     */
    private boolean mScrollable = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exercise);

        initViews();

        new Timer().schedule(new TimerTask() {
            @Override
            public void run() {
                if (shrink)
                    ExerciseScoreCounter.getInstance().receiveSignal();
            }
        }, 1000, 15);
    }

    /**
     * 初始化控件及变量，注册事件
     */
    private void initViews() {
        leftTimeTextView = (TextView) findViewById(R.id.time_left);
        scoreTextView = (TextView) findViewById(R.id.score);
        frontScrollView = (ScrollView) findViewById(R.id.front_scrollView);
        behindScrollView = (ScrollView) findViewById(R.id.behind_scrollView);
        behindGroup = (LinearLayout) findViewById(R.id.behind_group);
        frontGroup = (LinearLayout) findViewById(R.id.exercise_group);
        shrinkButton = (ToggleButton) findViewById(R.id.shrink_button);


        controller = new ExerciseController(this);
        controller.registerShrinkCallback(this);

        frontScrollView.setOnTouchListener(mScorllViewOnTouchListener);
        behindScrollView.setOnTouchListener(mScorllViewOnTouchListener);


        shrinkButton.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                shrink = isChecked;
            }
        });

    }

    /**
     * ScorllView 的OnTouchListener事件，
     * 以便禁用其滑动事件
     */
    View.OnTouchListener mScorllViewOnTouchListener = new View.OnTouchListener() {
        @Override
        public boolean onTouch(View view, MotionEvent motionEvent) {
            if (mScrollable)
                return false;
            else {
                switch (motionEvent.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        break;
                    case MotionEvent.ACTION_UP:
                        break;
                }
                return true;
            }
        }
    };

    @Override
    public int getLayoutResourceId() {
        return R.layout.activity_exercise;
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.exercise, menu);
        return true;
    }

    private boolean isPasueable = false;

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_pause) {
            if (scroll) {
                controller.init(this, frontGroup, behindGroup);
                scroll = false;
            }else {

                    controller.prepare(this,frontScrollView,behindScrollView);
                    controller.start();

            }

            Log.i("CXC", "scorll:--" + scroll);
            Log.i("CXC", "isPauseable:--" + isPasueable);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }


    @Override
    public void startScore(Bar bar) {
        Log.d("startScore", "bar type = " + bar.getType());
        ExerciseScoreCounter.getInstance().startScore(bar);
    }

    @Override
    public void tickScore() {
        ExerciseScoreCounter.getInstance().tickScore();
    }

    @Override
    public void tickSecond(final int leftTime) {
        Log.d("tickSecond ", "" + leftTime);
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                leftTimeTextView.setText(leftTime + "");
            }
        });

        //TODO 更新UI倒计时
    }

    @Override
    public void stopScore() {
        final int score = ExerciseScoreCounter.getInstance().stopScore();
        Log.d("stopScore ", "" + score);
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                scoreTextView.setText(score + "");
            }
        });
        //TODO 更新UI分数
    }

}
