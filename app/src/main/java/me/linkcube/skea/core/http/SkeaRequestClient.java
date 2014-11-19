package me.linkcube.skea.core.http;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;

/**
 * Created by Ervin on 14/10/23.
 */
public class SkeaRequestClient {


    private final static String BASE_URL = "";

    private static String getAbsoluteUrl(String relativeUrl) {
        return BASE_URL + relativeUrl;
    }

    private static AsyncHttpClient client = new AsyncHttpClient();

    public static void get(String url, RequestParams params, AsyncHttpResponseHandler responseHandler) {
        client.get(getAbsoluteUrl(url), params, responseHandler);
    }

    public static void post(String url, RequestParams params, AsyncHttpResponseHandler responseHandler) {
        client.post(getAbsoluteUrl(url), params, responseHandler);
    }

    public static class URL {
        static String Register = "register";
        static String Login = "login";
        static String EditNickname = "editNickname";
        static String GetInfo = "getInfo";
        static String SaveQuestionResult = "saveQuestionResult";
        static String GetLastQuestionResult = "getLastQuestionResult";
        static String SaveRecord = "saveRecord";
        static String GetRecords = "getRecords";
    }

}
