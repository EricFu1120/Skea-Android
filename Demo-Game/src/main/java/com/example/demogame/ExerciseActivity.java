package com.example.demogame;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import com.example.demogame.core.ExerciseController;
import com.example.demogame.view.BarGroupManager;

import java.util.Timer;
import java.util.TimerTask;


public class ExerciseActivity extends ActionBarActivity {

    private ViewGroup frontParent, behindParent;

    private ExerciseController controller;

    private TextView scrollXView, scrollYView;

    private float scrollX, scrollY;

    private LinearLayout frontGroup;

    private LinearLayout behindGroup;

    private ScrollView frontScrollView;

    private ScrollView behindScrollView;

    private Timer timer;

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
        timer = new Timer();
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
                BarGroupManager.getInstance().getBarGroup(frontGroup, frontParent, false);
                BarGroupManager.getInstance().getBarGroup(behindGroup, behindParent, true);
                scroll = false;
            } else {
                Log.d("frontGroup height = ", "" + BarGroupManager.getInstance().getBarGroupHeight(this));
                frontScrollView.scrollBy(0, BarGroupManager.getInstance().getBarGroupHeight(this));
                behindScrollView.scrollBy(0, BarGroupManager.getInstance().getBarGroupHeight(this));
                timer.schedule(new TimerTask() {
                    @Override
                    public void run() {
                        frontScrollView.scrollBy(0, -30);
                        behindScrollView.scrollBy(0, -30);
                    }
                }, 1000, 100);
//                scroll = true;
            }

            return true;
        }
        return super.onOptionsItemSelected(item);
    }


}
