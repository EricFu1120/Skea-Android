package me.linkcube.skea.core;

import android.content.Context;
import android.util.Log;

import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import org.json.JSONException;
import org.json.JSONObject;

import custom.android.util.PreferenceUtils;
import me.linkcube.skea.core.http.SkeaRequestClient;
import me.linkcube.skea.core.http.SkeaRequestStatus;
import me.linkcube.skea.core.persistence.User;

/**
 * Created by Ervin on 14/11/15.
 */
public class UserManager {

    private final static String TAG = "UserManager";

    private static UserManager instance;

    public static final int STATE_LOGIN = 0;

    public static final int STATE_LOGINING = 1;

    public static final int STATE_LOGOUT = 2;

    private int userState;

    private UserStateWatched userStateWatched;

    private UserManager() {
        userStateWatched = new UserStateWatched();
    }

    public static UserManager getInstance() {
        if (instance == null)
            instance = new UserManager();
        return instance;
    }

    public int getUserState() {
        return userState;
    }

    public void setLogin(Context context, int state) {
        userState = state;
//        PreferenceUtils.setBoolean(context, KeyConst.AUTO_LOGIN, true);
    }

    public void startAutoLogin(final Context context, JsonHttpResponseHandler handler) {
        long id = PreferenceUtils.getLong(context, KeyConst.USER_ID, 0);
        if (id == 0) {
            //TODO 无法自动登录
        } else {
            User user = User.findById(User.class, id);
            String email = user.getEmail();
            String password = user.getPassword();
            RequestParams params = new RequestParams();
            params.add("email", email);
            params.add("password", password);
            SkeaRequestClient.post(SkeaRequestClient.URL.LOGIN, params, handler);
        }
    }

    public User getUser(Context context) {
        long id = PreferenceUtils.getLong(context, KeyConst.USER_ID, 0);
        if (id > 0) {
            User user = User.findById(User.class, id);
            if (user != null)
                return user;
        }
        return null;
    }

    public boolean loginCallback(Context context, JSONObject response) {
        int status = -1;
        try {
            status = response.getInt("status");
        } catch (JSONException e) {
            e.printStackTrace();
            return false;
        }
        if (status < 0) {
            //TODO 服务器解析位置错误
            Log.d(TAG, "服务器解析位置错误");
            userState = STATE_LOGOUT;
            return false;
        } else if (status == SkeaRequestStatus.SUCC) {
            //TODO 讲用户信息解析之后更新用户信息
            userState = STATE_LOGIN;
            setLogin(context, userState);
            userStateWatched.notifyObservers(true);
            Log.d(TAG, "Auto Login Success");
            return true;
        } else if (status == SkeaRequestStatus.UNKNOWN_USER_OR_BAD_PWD) {
            //TODO 推测用户在其他机器上更改了用户密码
            Log.d(TAG, "User exists");
            userState = STATE_LOGOUT;
            return false;
        } else {
            Log.d(TAG, "其他错误");
            userState = STATE_LOGOUT;
            return false;
        }

    }

    public UserStateWatched getUserStateWatched() {
        return userStateWatched;
    }


}
