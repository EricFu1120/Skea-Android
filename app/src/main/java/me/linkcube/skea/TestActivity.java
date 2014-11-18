package me.linkcube.skea;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;

import custom.android.app.CustomActionBarActivity;
import me.linkcube.skea.ui.evaluation.ReEvaluationActivity;


public class TestActivity extends CustomActionBarActivity implements View.OnClickListener {

    private static final int TEST_REQUEST_CODE = 0x1;

    private EditText emailEditText;

    private EditText passwordEditText;

    private String email;

    private String password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);
        init();
        findViewById(R.id.add).setOnClickListener(this);
        findViewById(R.id.delete).setOnClickListener(this);
        findViewById(R.id.find).setOnClickListener(this);
        findViewById(R.id.evaluateBtn).setOnClickListener(this);
    }

    private void init() {
        emailEditText = (EditText) findViewById(R.id.email);
        passwordEditText = (EditText) findViewById(R.id.password);
        loadUser();
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.add:
                addUser();
                break;
            case R.id.delete:
                deleteUser();
                break;
            case R.id.find:
                findUser();
                break;
            case R.id.evaluateBtn:
                evaluate();
                break;
            default:
                break;
        }
    }

    private void addUser() {

    }

    private void deleteUser() {

    }

    private void findUser() {

    }

    private void loadUser() {

    }

    private void loadEvaluation() {

    }

    private void evaluate() {
        startActivityForResult(new Intent(this, ReEvaluationActivity.class), TEST_REQUEST_CODE);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {
            case TEST_REQUEST_CODE:
                loadEvaluation();
                break;
            default:
                break;
        }
    }
}
