package me.linkcube.skea.ui.exercise;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ScrollView;

import me.linkcube.skea.R;
import me.linkcube.skea.core.excercise.Bar;
import me.linkcube.skea.core.excercise.ExerciseController;
import me.linkcube.skea.core.excercise.ExerciseScoreCounter;


public class ExerciseActivity extends ActionBarActivity implements ExerciseController.ScoreCallback {

    private LinearLayout frontGroup;

    private LinearLayout behindGroup;

    private ScrollView frontScrollView;

    private ScrollView behindScrollView;

    private ExerciseController controller;

    private Button shrink;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exercise);
        frontScrollView = (ScrollView) findViewById(R.id.scrollView);
        behindScrollView = (ScrollView) findViewById(R.id.behind_scrollView);
        behindGroup = (LinearLayout) findViewById(R.id.behind_group);
        frontGroup = (LinearLayout) findViewById(R.id.exercise_group);
        controller = new ExerciseController(this);
        controller.registerShrinkCallback(this);
        shrink = (Button) findViewById(R.id.shrink_button);
        shrink.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
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
        ExerciseScoreCounter.getInstance().startScore(bar);
    }

    @Override
    public void tickScore() {
        ExerciseScoreCounter.getInstance().tickScore();
    }

    @Override
    public void tickSecond() {
        //TODO 更新UI倒计时
    }

    @Override
    public void stopScore() {
        int score = ExerciseScoreCounter.getInstance().stopScore();
        //TODO 更新UI分数
    }

}
