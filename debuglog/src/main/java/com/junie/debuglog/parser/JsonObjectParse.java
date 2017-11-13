package com.junie.debuglog.parser;

import android.util.Log;
import com.junie.debuglog.setting.Constant;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by niejun on 2017/4/12.
 */

public class JsonObjectParse implements IParse<JSONObject> {
    @Override
    public Class<JSONObject> parseClassType() {
        return JSONObject.class;
    }

    @Override
    public String parseToString(JSONObject jsonObject) {
        if(jsonObject == null) {
            return Constant.OBJECT_NULL;
        }
        try {
            return jsonObject.toString(Constant.JSON_SHIFT);
        } catch (JSONException e) {
            return Log.getStackTraceString(e);
        }
    }
}
