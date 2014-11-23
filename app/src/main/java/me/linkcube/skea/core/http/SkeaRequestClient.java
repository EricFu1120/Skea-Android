package me.linkcube.skea.core.http;

import android.content.Context;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;

/**
 * Created by Ervin on 14/10/23.
 */
public class SkeaRequestClient {


    private final static String BASE_URL = "http://112.124.22.252:8002/";

    private static String getAbsoluteUrl(String relativeUrl) {
        return BASE_URL + relativeUrl;
    }

    private static AsyncHttpClient client = new AsyncHttpClient();

    public static void get(Context context, String url, RequestParams params, AsyncHttpResponseHandler responseHandler) {
        client.get(context, getAbsoluteUrl(url), params, responseHandler);
    }

    public static void post(Context context, String url, RequestParams params, AsyncHttpResponseHandler responseHandler) {
        client.post(context, getAbsoluteUrl(url), params, responseHandler);
    }

    public static void cancel(Context context, boolean mayInterruptIfRunning) {
        client.cancelRequests(context, mayInterruptIfRunning);
    }

    public static class URL {

        public static String REGISTER = "register";

        public static String LOGIN = "login";

        public static String EDIT_NICKNAME = "editNickname";

        public static String GET_INFO = "getInfo";

        public static String SAVE_QUESTION_RESULT = "saveQuestionResult";

        public static String GET_LAST_QUESTION_RESULT = "getLastQuestionResult";

        public static String SAVE_RECORD = "saveRecord";

        public static String GET_RECORDS = "getRecords";
    }

}
