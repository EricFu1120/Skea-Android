package me.linkcube.skea.core.http;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by Ervin on 14/11/23.
 */
public class SkeaResponseHelper {

    public static int getStatus(JSONObject jsonObject) {
        int status = -1;
        try {
            status = jsonObject.getInt("status");
        } catch (JSONException e) {
            //TODO 网络解析错误
            e.printStackTrace();
            return status;
        }
        return status;

    }


}
