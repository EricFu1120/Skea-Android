package com.example.demogame;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.ViewGroup;
import android.widget.ListView;

import com.example.demogame.core.ExerciseManager;
import com.example.demogame.view.BarViewGroup;

import java.util.List;


public class ExerciseActivity extends ActionBarActivity {

//    private BarView barView, barViewGlow;

//    private BarViewFrontAnimator barViewFrontAnimator;
//
//    private BarViewBehindAnimator barViewBehindAnimator;

    private BarViewGroup group;

    private ViewGroup frontParent, behindParent;

    private ExerciseManager manager;

    private ListView listView;

    private BarViewAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exercise);
        listView = (ListView) findViewById(R.id.listView);
        frontParent = (ViewGroup) findViewById(R.id.frontParent);
        behindParent = (ViewGroup) findViewById(R.id.behindParent);
//        listView = new ListViewWrapper().getListView(listView,frontParent);
//        adapter = new BarViewAdapter(this, false);
//        listView.setAdapter(adapter);
//        listView.setSelection(1);

//        group = new BarViewGroup(this, BarViewGroup.BAR_VIEW_LONG, frontParent, behindParent);
//        manager = new ExerciseManager(this, frontParent, behindParent);
//        barView = (BarView) findViewById(R.id.bar_view);
//        barViewGlow = (BarView) findViewById(R.id.bar_view_glow);
//        barViewFrontAnimator = new BarViewFrontAnimator();
//        barViewBehindAnimator = new BarViewBehindAnimator();

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.exersise, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_pause) {
            listView = new ListViewWrapper().getListView(listView,frontParent);
            adapter = new BarViewAdapter(this, false);
            listView.setAdapter(adapter);
            listView.setSelection(1);
            listView.smoothScrollBy(-300,7000);
//            if (manager == null)
//                manager = new ExerciseManager(this, frontParent, behindParent);
//            group.animate();
//            barViewFrontAnimator.setDuration(7000).animate(barView);
//            barViewBehindAnimator.setDuration(7000).animate(barViewGlow);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
