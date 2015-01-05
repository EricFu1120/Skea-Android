package me.linkcube.skea.ui.user;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.EditText;

import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import org.apache.http.Header;
import org.json.JSONObject;

import custom.android.widget.Toaster;
import me.linkcube.skea.R;
import me.linkcube.skea.base.ui.BaseActivity;
import me.linkcube.skea.core.UserManager;
import me.linkcube.skea.core.http.SkeaRequestClient;
import me.linkcube.skea.core.persistence.User;
import me.linkcube.skea.ui.MainActivity;
import me.linkcube.skea.util.RegularExpression;

import static me.linkcube.skea.core.http.SkeaRequestClient.URL.LOGIN;


/**
 * A login screen that offers login via email/password.
 */
public class LoginActivity extends BaseActivity implements OnClickListener {

    private static final String TAG = "LoginActivity";

    public static final int REQUEST_CODE_LOGIN = 0;

    private EditText emailEditText;
    private EditText passwordEditText;

    private String email;
    private String password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initViews();
    }

    private void initViews() {
        emailEditText = (EditText) findViewById(R.id.email_editText);
        passwordEditText = (EditText) findViewById(R.id.password_editText);
        findViewById(R.id.login_button).setOnClickListener(this);
        findViewById(R.id.register_button).setOnClickListener(this);
    }

    @Override
    public int getLayoutResourceId() {
        return R.layout.activity_login;
    }

    public void configureActionBar() {
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(false);
        actionBar.setDisplayShowHomeEnabled(false);
        actionBar.setDisplayUseLogoEnabled(false);
        actionBar.setHomeAsUpIndicator(R.drawable.layer_actionbar_up_indicator_black);
    }


    /**
     * Attempts to sign in or register the account specified by the login form.
     * If there are form errors (invalid email, missing fields, etc.), the
     * errors are presented and no actual login attempt is made.
     */
    public void attemptLogin() {
        emailEditText.setError(null);
        passwordEditText.setError(null);

        // Store values at the time of the login attempt.
        email = emailEditText.getText().toString();
        password = passwordEditText.getText().toString();

        boolean cancel = false;
        View focusView = null;


        // Check for a valid email address.
        if (TextUtils.isEmpty(email)) {
            emailEditText.setError(getString(R.string.error_field_required));
            focusView = emailEditText;
            cancel = true;
        } else if (!RegularExpression.isEmailValid(email)) {
            emailEditText.setError(getString(R.string.error_invalid_email));
            focusView = emailEditText;
            cancel = true;
        }

        if (cancel) {
            focusView.requestFocus();
            return;
        }

        // Check for a valid password, if the user entered one.
        if (TextUtils.isEmpty(password) || !RegularExpression.isPasswordValid(password)) {
            passwordEditText.setError(getString(R.string.error_invalid_password));
            focusView = passwordEditText;
            cancel = true;
        } else {
            focusView = passwordEditText;
            cancel = false;
        }

        if (cancel) {
            focusView.requestFocus();
            return;
        } else {
            executeLoginTask();
        }
    }

    private void executeLoginTask() {
        RequestParams params = new RequestParams();
        params.add("email", email);
        params.add("password", password);
        SkeaRequestClient.post(this, LOGIN, params, new JsonHttpResponseHandler() {

            @Override
            public void onStart() {
                super.onStart();
                showProgress(true);
                Log.d(TAG, "Start Login");

            }

            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                super.onSuccess(statusCode, headers, response);
                if (UserManager.getInstance().loginCallback(LoginActivity.this, response)) {
                    Log.d(TAG, "Login Success");
                    User user = UserManager.getInstance().getUser(LoginActivity.this);
                    //TODO 更新用户信息
                    startActivity(new Intent(LoginActivity.this, MainActivity.class));
                    finish();
                } else {
                    Toaster.showShort(LoginActivity.this, "用户名或者密码不正确");
                }

            }

            @Override
            public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONObject errorResponse) {
                super.onFailure(statusCode, headers, throwable, errorResponse);
                //TODO 提示用户
                Log.d(TAG, "Login Failure");
            }

            @Override
            public void onFinish() {
                super.onFinish();
                showProgress(false);
                Log.d(TAG, "Finish Login");

            }
        });

    }

    ProgressDialog progressDialog;

    private void showProgress(boolean show) {
        if (show) {
            if (progressDialog == null)
                progressDialog = new ProgressDialog(this);
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
                //直接进入主界面，以便测试
                startActivity(new Intent(this, MainActivity.class));
//                attemptLogin();
                break;
            case R.id.register_button:
                startActivityForResult(new Intent(this, RegisterActivity.class), REQUEST_CODE_LOGIN);
                break;
            default:
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {
            case REQUEST_CODE_LOGIN:
                if (resultCode == RESULT_OK) {
                    User user = UserManager.getInstance().getUser(LoginActivity.this);
                    if (user != null) {
                        emailEditText.setText(user.getEmail());
                        passwordEditText.setText(user.getPassword());
                        attemptLogin();
                    }
                }
                break;
            default:
                break;
        }
    }
}



