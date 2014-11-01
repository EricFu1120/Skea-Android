package me.linkcube.skea.ui.user;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import me.linkcube.skea.R;
import me.linkcube.skea.ui.test.SettingExerciseLevel;

public class TestPelvicMuscleResultActivity extends Activity {
    public  static final String EXERCISE_LEVEL = "me.linkcube.skea.ui.user.TestPelvicMuscleResultActivity.Exercise_level";
    private static final int SETTING_LEVEL_REQUEST_CODE = 1;


    private TextView exerciseLevel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_pelvic_muscle_result);
    }

    private void init() {
        exerciseLevel = (TextView) findViewById(R.id.level);
    }

    /**
     * 启动设置训练强度
     */
    private void startSettingExerciseLevel() {
        Intent intent = new Intent();
        intent.setClass(getApplicationContext(), SettingExerciseLevel.class);
        startActivityForResult(intent, SETTING_LEVEL_REQUEST_CODE);

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.test_pelvic_muscle_result, menu);
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
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {
            case 1:
                //得到训练强度值
                if (resultCode == RESULT_OK) {

                    exerciseLevel.setText(data.getStringExtra(EXERCISE_LEVEL));


                }


                break;
            default:

        }
    }
}
