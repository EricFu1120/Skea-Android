package me.linkcube.skea;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;

import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import org.apache.http.Header;
import org.json.JSONObject;

import custom.android.app.CustomActionBarActivity;
import me.linkcube.skea.core.http.SkeaRequestClient;

import static me.linkcube.skea.core.http.SkeaRequestClient.URL.EDIT_NICKNAME;
import static me.linkcube.skea.core.http.SkeaRequestClient.URL.GET_INFO;
import static me.linkcube.skea.core.http.SkeaRequestClient.URL.GET_LAST_QUESTION_RESULT;
import static me.linkcube.skea.core.http.SkeaRequestClient.URL.GET_RECORDS;
import static me.linkcube.skea.core.http.SkeaRequestClient.URL.LOGIN;
import static me.linkcube.skea.core.http.SkeaRequestClient.URL.REGISTER;
import static me.linkcube.skea.core.http.SkeaRequestClient.URL.SAVE_QUESTION_RESULT;
import static me.linkcube.skea.core.http.SkeaRequestClient.URL.SAVE_RECORD;

public class TestHttpActivity extends CustomActionBarActivity implements View.OnClickListener {

    //登录和注册
    private EditText emailEditText;
    private EditText passwordEditText;
    private TextView loginOrRegisterResultTextView;

    private String defaultNickname = "Skea Girl";

    //修改Nickname
    private EditText editNickNameEditText;
    private TextView editNickNameResultTextView;

    //GetInfo
    private TextView getInfoResultTextView;

    //GetLastQuestion
    private TextView getLastQuestionResultTextView;

    //GetRecords
    private TextView datePickerRecordsTextView;
    private TextView getRecordsResultTextView;

    //Save Question
    private EditText heightEditText;
    private EditText weightEditText;
    private EditText evaluationsEditText;
    private TextView datePickerBirthdayTextView;
    private TextView datePickerQuestionTextView;
    private TextView saveQuestion_result_textView;

    //Save Record
    private EditText highScoreEditText;
    private EditText actualScoreEditText;
    private EditText levelEditText;
    private EditText powerEditText;
    private EditText staminaEditText;
    private EditText exerciseDataEditText;
    private EditText exerciseTimeEditText;
    private EditText scoreDataEditText;
    private TextView datePickerSaveRecordTextView;
    private TextView saveRecordResultTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_http);
        init();
    }

    private void init() {
        //登录和注册
        emailEditText = (EditText) findViewById(R.id.email_editText);
        passwordEditText = (EditText) findViewById(R.id.password_editText);
        loginOrRegisterResultTextView = (TextView) findViewById(R.id.login_or_register_result_textView);
        findViewById(R.id.register_button).setOnClickListener(this);
        findViewById(R.id.login_button).setOnClickListener(this);
        //修改昵称
        editNickNameEditText = (EditText) findViewById(R.id.editNickName_editText);
        editNickNameResultTextView = (TextView) findViewById(R.id.editNickName_result_textView);
        findViewById(R.id.editNickName_button).setOnClickListener(this);
        //GetInfo
        getInfoResultTextView = (TextView) findViewById(R.id.getInfo_result_textView);
        findViewById(R.id.getInfo_button).setOnClickListener(this);
        //GetLastQuestion
        getLastQuestionResultTextView = (TextView) findViewById(R.id.getLastQuestion_result_textView);
        findViewById(R.id.getLastQuestion_button).setOnClickListener(this);
        //GetRecords
        datePickerRecordsTextView = (TextView) findViewById(R.id.datePicker_records);
        getRecordsResultTextView = (TextView) findViewById(R.id.getRecords_result_textView);
        findViewById(R.id.datePicker_records).setOnClickListener(this);
        findViewById(R.id.getRecords_button).setOnClickListener(this);
        //saveQuestion
        heightEditText = (EditText) findViewById(R.id.height_editText);
        weightEditText = (EditText) findViewById(R.id.weight_editText);
        evaluationsEditText = (EditText) findViewById(R.id.evaluations_editText);
        datePickerQuestionTextView = (TextView) findViewById(R.id.datePciker_question);
        datePickerBirthdayTextView = (TextView) findViewById(R.id.datePicker_birthday);
        findViewById(R.id.datePicker_birthday).setOnClickListener(this);
        findViewById(R.id.datePciker_question).setOnClickListener(this);
        findViewById(R.id.saveQuestion_button).setOnClickListener(this);
        //saveRecord
        highScoreEditText = (EditText) findViewById(R.id.highScore_editText);
        actualScoreEditText = (EditText) findViewById(R.id.actualScore_editText);
        levelEditText = (EditText) findViewById(R.id.level_editText);
        powerEditText = (EditText) findViewById(R.id.power_editText);
        staminaEditText = (EditText) findViewById(R.id.stamina_editText);
        exerciseDataEditText = (EditText) findViewById(R.id.exerciseData_editText);
        exerciseTimeEditText = (EditText) findViewById(R.id.exerciseTime_editText);
        scoreDataEditText = (EditText) findViewById(R.id.scoreData_editText);
        datePickerSaveRecordTextView = (TextView) findViewById(R.id.datePicker_saveRecord);
        saveRecordResultTextView = (TextView) findViewById(R.id.saveRecord_result_textView);
        findViewById(R.id.datePicker_saveRecord).setOnClickListener(this);
        findViewById(R.id.saveRecord_button).setOnClickListener(this);

    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.register_button:
                register();
                break;
            case R.id.login_button:
                login();
                break;
            case R.id.editNickName_button:
                editNickname();
                break;
            case R.id.getInfo_button:
                getInfo();
                break;
            case R.id.getLastQuestion_button:
                getLastQuestion();
                break;
            case R.id.datePicker_records:
                selectDate(datePickerRecordsTextView);
                break;
            case R.id.getRecords_button:
                getRecords();
                break;
            case R.id.datePciker_question:
                selectDate(datePickerQuestionTextView);
                break;
            case R.id.datePicker_birthday:
                selectDate(datePickerBirthdayTextView);
                break;
            case R.id.saveQuestion_button:
                saveQuestion();
                break;
            case R.id.datePicker_saveRecord:
                selectDate(datePickerSaveRecordTextView);
                break;
            case R.id.saveRecord_button:
                saveRecord();
                break;
            default:
                break;
        }
    }

    private void register() {
        String email = emailEditText.getText().toString();
        String password = passwordEditText.getText().toString();
        RequestParams params = new RequestParams();
        params.add("email", email);
        params.add("password", password);
        params.add("nickname", defaultNickname);
        SkeaRequestClient.post(this, REGISTER, params, new JsonHttpResponseHandler() {

            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                super.onSuccess(statusCode, headers, response);
                loginOrRegisterResultTextView.setText(response.toString());
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONObject errorResponse) {
                super.onFailure(statusCode, headers, throwable, errorResponse);
            }
        });

    }

    private void login() {
        String email = emailEditText.getText().toString();
        String password = passwordEditText.getText().toString();
        RequestParams params = new RequestParams();
        params.add("email", email);
        params.add("password", password);
        SkeaRequestClient.post(this, LOGIN, params, new JsonHttpResponseHandler() {

            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                super.onSuccess(statusCode, headers, response);
                loginOrRegisterResultTextView.setText(response.toString());
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONObject errorResponse) {
                super.onFailure(statusCode, headers, throwable, errorResponse);
            }
        });
    }

    private void editNickname() {
        String nickname = editNickNameEditText.getText().toString();
        String email = emailEditText.getText().toString();
        RequestParams params = new RequestParams();
        params.add("nickname", nickname);
        params.add("email", email);
        SkeaRequestClient.post(this, EDIT_NICKNAME, params, new JsonHttpResponseHandler() {

            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                super.onSuccess(statusCode, headers, response);
                editNickNameResultTextView.setText(response.toString());
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONObject errorResponse) {
                super.onFailure(statusCode, headers, throwable, errorResponse);
            }
        });
    }

    private void getInfo() {
        String email = emailEditText.getText().toString();
        RequestParams params = new RequestParams();
        params.add("email", email);
        SkeaRequestClient.post(this, GET_INFO, params, new JsonHttpResponseHandler() {

            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                super.onSuccess(statusCode, headers, response);
                getInfoResultTextView.setText(response.toString());
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONObject errorResponse) {
                super.onFailure(statusCode, headers, throwable, errorResponse);
            }
        });
    }

    private void getLastQuestion() {
        String email = emailEditText.getText().toString();
        RequestParams params = new RequestParams();
        params.add("email", email);
        SkeaRequestClient.post(this, GET_LAST_QUESTION_RESULT, params, new JsonHttpResponseHandler() {

            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                super.onSuccess(statusCode, headers, response);
                getLastQuestionResultTextView.setText(response.toString());
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONObject errorResponse) {
                super.onFailure(statusCode, headers, throwable, errorResponse);
            }
        });
    }

    private void getRecords() {
        String email = emailEditText.getText().toString();
        String date = datePickerRecordsTextView.getText().toString();
        RequestParams params = new RequestParams();
        params.add("email", email);
        params.add("begin", date);
        SkeaRequestClient.post(this, GET_RECORDS, params, new JsonHttpResponseHandler() {

            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                super.onSuccess(statusCode, headers, response);
                getRecordsResultTextView.setText(response.toString());
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONObject errorResponse) {
                super.onFailure(statusCode, headers, throwable, errorResponse);
            }
        });
    }

    private void saveQuestion() {
        String email = emailEditText.getText().toString();
        String dateQuestion = datePickerQuestionTextView.getText().toString();
        String dateBirthday = datePickerBirthdayTextView.getText().toString();
        String height = heightEditText.getText().toString();
        String weight = weightEditText.getText().toString();
        String evaluations = evaluationsEditText.getText().toString();
        RequestParams params = new RequestParams();
        params.add("email", email);
        params.add("date", dateQuestion);
        params.add("birthday", dateBirthday);
        params.add("height", height);
        params.add("weight", weight);
        params.add("result", evaluations);
        SkeaRequestClient.post(this, SAVE_QUESTION_RESULT, params, new JsonHttpResponseHandler() {

            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                super.onSuccess(statusCode, headers, response);
                saveQuestion_result_textView.setText(response.toString());
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONObject errorResponse) {
                super.onFailure(statusCode, headers, throwable, errorResponse);
            }
        });
    }

    private void saveRecord() {
        String email = emailEditText.getText().toString();
        String highScore = highScoreEditText.getText().toString();
        String actualScore = actualScoreEditText.getText().toString();
        String level = levelEditText.getText().toString();
        String power = powerEditText.getText().toString();
        String stamina = staminaEditText.getText().toString();
        String exerciseData = exerciseDataEditText.getText().toString();
        String exerciseTime = exerciseTimeEditText.getText().toString();
        String scoreData = scoreDataEditText.getText().toString();
        RequestParams params = new RequestParams();
        params.add("email", email);
        params.add("date", datePickerSaveRecordTextView.getText().toString());
        params.add("highScore", highScore);
        params.add("factScore", actualScore);
        params.add("exerciseTime", exerciseTime);
        params.add("level", level);
        params.add("scoreData", scoreData);
        params.add("exerciseData", exerciseData);
        params.add("explosive", power);
        params.add("endurance", stamina);
        SkeaRequestClient.post(this, SAVE_RECORD, params, new JsonHttpResponseHandler() {

            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                super.onSuccess(statusCode, headers, response);
                saveRecordResultTextView.setText(response.toString());
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONObject errorResponse) {
                super.onFailure(statusCode, headers, throwable, errorResponse);
            }
        });

    }

    private void selectDate(final TextView dateTextView) {
        DatePickerDialog dpd = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int myyear, int monthOfYear, int dayOfMonth) {
                dateTextView.setText(myyear + "-" + (monthOfYear + 1) + "-" + dayOfMonth);

            }
        }, 1988, 9, 5);
        dpd.show();//显示DatePickerDialog组件
    }

}
