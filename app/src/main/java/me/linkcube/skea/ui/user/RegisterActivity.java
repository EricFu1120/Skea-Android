package me.linkcube.skea.ui.user;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import org.apache.http.Header;
import org.json.JSONException;
import org.json.JSONObject;

import custom.android.widget.Toaster;
import me.linkcube.skea.R;
import me.linkcube.skea.base.ui.BaseActivity;
import me.linkcube.skea.core.UserManager;
import me.linkcube.skea.core.http.SkeaRequestClient;
import me.linkcube.skea.core.http.SkeaRequestStatus;
import me.linkcube.skea.core.persistence.User;
import me.linkcube.skea.ui.MainActivity;
import me.linkcube.skea.util.RegularExpression;

import static me.linkcube.skea.core.http.SkeaRequestClient.URL.REGISTER;

public class RegisterActivity extends BaseActivity implements View.OnClickListener {

    private static final String TAG = "RegisterActivity";

    private EditText emailEditText;
    private EditText passwordEditText;
    private EditText confirmPasswordEditText;

    private String email;
    private String password;
    private String confirmPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initViews();
    }

    private void initViews() {
        emailEditText = (EditText) findViewById(R.id.email_editText);
        passwordEditText = (EditText) findViewById(R.id.password_editText);
        confirmPasswordEditText = (EditText) findViewById(R.id.confirm_password_editText);
        findViewById(R.id.login_button).setOnClickListener(this);
        findViewById(R.id.register_button).setOnClickListener(this);
        findViewById(R.id.clause_textView).setOnClickListener(this);

    }

    @Override
    public int getLayoutResourceId() {
        return R.layout.activity_register;
    }

    public void configureActionBar() {
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setDisplayShowHomeEnabled(false);
        actionBar.setDisplayUseLogoEnabled(false);
        actionBar.setHomeAsUpIndicator(R.drawable.layer_actionbar_up_indicator_black);
    }


    /**
     * Attempts to sign in or register the account specified by the login form.
     * If there are form errors (invalid email, missing fields, etc.), the
     * errors are presented and no actual login attempt is made.
     */
    public void attemptRegister() {
        email = emailEditText.getText().toString();
        password = passwordEditText.getText().toString();
        confirmPassword = confirmPasswordEditText.getText().toString();

        boolean cancel = false;
        View focusView = null;


        // Check for a valid email address.
        if (TextUtils.isEmpty(email)) {
            Toaster.showShort(this, R.string.error_field_required);
            focusView = emailEditText;
            cancel = true;
        } else if (!RegularExpression.isEmailValid(email)) {
            Toaster.showShort(this, R.string.error_invalid_password);
            focusView = emailEditText;
            cancel = true;
        }

        // Check for a valid password, if the user entered one.
        if (TextUtils.isEmpty(password) || !RegularExpression.isPasswordValid(password)) {
            Toaster.showShort(this, getString(R.string.error_invalid_password));
            focusView = passwordEditText;
            cancel = true;
        }

        if (!password.equals(confirmPassword)) {
            //TODO 两次密码不一致
            focusView = confirmPasswordEditText;
            Toaster.showShort(this, "两次密码不一致");
            cancel = true;
        }

        if (cancel) {
            // There was an error; don't attempt login and focus the first
            // form field with an error.
            focusView.requestFocus();
        } else {
            executeRegisterTask();
            // Show a progress spinner, and kick off a background task to
            // perform the user login attempt.
//            mAuthTask = new UserLoginTask(email, password);
//            mAuthTask.execute((Void) null);
        }
    }

    private void executeRegisterTask() {
        RequestParams params = new RequestParams();
        params.add("email", email);
        params.add("password", password);
        params.add("nickname", "Skea Girl");
        SkeaRequestClient.post(this, REGISTER, params, new JsonHttpResponseHandler() {

            @Override
            public void onStart() {
                super.onStart();
                showProgress(true);
                Log.d(TAG, "Start Register");

            }

            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                super.onSuccess(statusCode, headers, response);
                registerCallback(response);
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONObject errorResponse) {
                super.onFailure(statusCode, headers, throwable, errorResponse);
                //TODO 提示用户
            }

            @Override
            public void onFinish() {
                super.onFinish();
                showProgress(false);
                Log.d(TAG, "Finish Register");

            }
        });

    }

    private void registerCallback(JSONObject response) {
        int status = -1;
        try {
            status = response.getInt("status");
        } catch (JSONException e) {
            e.printStackTrace();
        }
        if (status < 0) {
            //TODO 服务器解析位置错误
            Log.d(TAG, "服务器解析位置错误");
        } else if (status == SkeaRequestStatus.SUCC) {
            Log.d(TAG, "Register Success");
            //TODO 自动登录
            User user = new User(email, password);
            user.save();
            UserManager.getInstance().setLogin(this, UserManager.STATE_LOGIN);
            startActivity(new Intent(this, MainActivity.class));
            finish();
        } else if (status == SkeaRequestStatus.USER_EXIST) {
            Log.d(TAG, "User exists");
            Toaster.showShort(this, "该用户名已经被注册");
        } else {
            Log.d(TAG, "其他错误");
        }

    }

    ProgressDialog progressDialog;

    private void showProgress(boolean show) {
        if (show) {
            if (progressDialog == null)
                progressDialog = new ProgressDialog(this);
            progressDialog.setMessage("正在向服务器发送注册请求");
            progressDialog.show();
        } else {
            if (progressDialog != null)
                progressDialog.dismiss();
        }

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.login_button:
                startActivity(new Intent(this, LoginActivity.class));
                finish();
                break;
            case R.id.register_button:
                attemptRegister();
                break;
            case R.id.clause_textView:
                break;
            default:
                break;
        }
    }
}
