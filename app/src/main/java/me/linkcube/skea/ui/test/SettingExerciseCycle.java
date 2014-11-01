package me.linkcube.skea.ui.test;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import me.linkcube.skea.R;
import me.linkcube.skea.ui.user.TestPelvicMuscleResultActivity;

public class SettingExerciseCycle extends ActionBarActivity {


    private String exerciseCycleString="30 Days";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting_exercise_cycle);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.setting_exercise_cycle, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        //返回用户设置的训练周期
        Intent resultIntent=new Intent();
        resultIntent.putExtra(TestPelvicMuscleResultActivity.EXERCISE_CYCLE,exerciseCycleString);
        setResult(RESULT_OK,resultIntent);
    }
}
