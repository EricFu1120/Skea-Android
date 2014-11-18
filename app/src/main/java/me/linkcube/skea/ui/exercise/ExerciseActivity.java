package me.linkcube.skea.ui.exercise;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.CompoundButton;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.ToggleButton;

import org.w3c.dom.Text;

import java.util.Timer;
import java.util.TimerTask;

import custom.android.util.Timber;
import me.linkcube.skea.R;
import me.linkcube.skea.base.ui.BaseActivity;
import me.linkcube.skea.core.excercise.Bar;
import me.linkcube.skea.core.excercise.ExerciseController;
import me.linkcube.skea.core.excercise.ExerciseScoreCounter;


public class ExerciseActivity extends BaseActivity implements ExerciseController.ExerciseScoreCallback {

    private LinearLayout frontGroup;

    private LinearLayout behindGroup;

    private ScrollView frontScrollView;

    private ScrollView behindScrollView;

    private ExerciseController controller;

    private ToggleButton shrinkButton;

    private TextView leftTimeTextView;

    private TextView scoreTextView;

    private boolean shrink;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exercise);
        leftTimeTextView = (TextView) findViewById(R.id.time_left);
        scoreTextView = (TextView) findViewById(R.id.score);
        frontScrollView = (ScrollView) findViewById(R.id.scrollView);
        behindScrollView = (ScrollView) findViewById(R.id.behind_scrollView);
        behindGroup = (LinearLayout) findViewById(R.id.behind_group);
        frontGroup = (LinearLayout) findViewById(R.id.exercise_group);
        controller = new ExerciseController(this);
        controller.registerShrinkCallback(this);
        shrinkButton = (ToggleButton) findViewById(R.id.shrink_button);
        shrinkButton.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                shrink = isChecked;
            }
        });
        new Timer().schedule(new TimerTask() {
            @Override
            public void run() {
                if (shrink)
                    ExerciseScoreCounter.getInstance().receiveSignal();
            }
        }, 1000, 15);
    }

    @Override
    public int getLayoutResourceId() {
        return R.layout.activity_exercise;
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.exercise, menu);
        return true;
    }

    public boolean scroll = true;

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_pause) {
            if (scroll) {
                controller.init(this, frontGroup, behindGroup);
                scroll = false;
            } else {
                controller.prepare(this, frontScrollView, behindScrollView);
                controller.start();
//                controller.test();
            }

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
