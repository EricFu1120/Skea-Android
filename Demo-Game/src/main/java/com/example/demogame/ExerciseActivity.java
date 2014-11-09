package com.example.demogame;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import com.example.demogame.core.Bar;
import com.example.demogame.core.ExerciseController;
import com.example.demogame.core.ScoreCounter;


public class ExerciseActivity extends ActionBarActivity implements ExerciseController.ScoreCallback{

    private ViewGroup frontParent, behindParent;

    private TextView scrollXView, scrollYView;

    private LinearLayout frontGroup;

    private LinearLayout behindGroup;

    private ScrollView frontScrollView;

    private ScrollView behindScrollView;

    private ExerciseController controller;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exercise);
        frontScrollView = (ScrollView) findViewById(R.id.scrollView);
        behindScrollView = (ScrollView) findViewById(R.id.behind_scrollView);
        behindGroup = (LinearLayout) findViewById(R.id.behind_group);
        frontGroup = (LinearLayout) findViewById(R.id.exercise_group);
        frontParent = (ViewGroup) findViewById(R.id.frontParent);
        behindParent = (ViewGroup) findViewById(R.id.behindParent);
        controller = new ExerciseController(this);
        controller.registerShrinkCallback(this);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.exersise, menu);
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
        ScoreCounter.getInstance().startScore(bar);
    }

    @Override
    public void tickScore() {
        ScoreCounter.getInstance().tickScore();
    }

    @Override
    public void stopScore() {
        ScoreCounter.getInstance().stopScore();
    }
}
