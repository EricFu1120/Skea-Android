package me.linkcube.skea.ui.exercise;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import me.linkcube.skea.R;
import me.linkcube.skea.ui.BaseActivity;

public class ExerciseActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
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

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_connect_bluetooth) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
