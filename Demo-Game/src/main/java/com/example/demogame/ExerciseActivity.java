package com.example.demogame;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import com.example.demogame.view.BarView;
import com.example.demogame.view.BarViewAnimator;
import com.example.demogame.view.BarViewGlowAnimator;
import com.example.demogame.view.BaseViewAnimator;


public class ExerciseActivity extends ActionBarActivity {

    private BarView barView, barViewGlow;

    private BarViewAnimator barViewAnimator;

    private BarViewGlowAnimator barViewGlowAnimator;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exercise);
        barView = (BarView)findViewById(R.id.bar_view);
        barViewGlow = (BarView) findViewById(R.id.bar_view_glow);
        barViewAnimator = new BarViewAnimator();
        barViewGlowAnimator = new BarViewGlowAnimator();
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.exersise, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_pause) {
            barViewAnimator.setDuration(7000).animate(barView);
            barViewGlowAnimator.setDuration(7000).animate(barViewGlow);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
