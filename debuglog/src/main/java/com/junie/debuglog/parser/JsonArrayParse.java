package com.junie.debuglog.parser;

import android.util.Log;

import com.junie.debuglog.setting.Constant;

import org.json.JSONArray;
import org.json.JSONException;

/**
 * Created by niejun on 2017/4/12.
 */

public class JsonArrayParse implements IParse<JSONArray>{

    @Override
    public Class<JSONArray> parseClassType() {
        return JSONArray.class;
    }

    @Override
    public String parseToString(JSONArray jsonArray) {
        if(jsonArray == null) {
            return Constant.OBJECT_NULL;
        }
        try {
            return jsonArray.toString(Constant.JSON_SHIFT);
        } catch (JSONException e) {
            return Log.getStackTraceString(e);
        }
    }
}
