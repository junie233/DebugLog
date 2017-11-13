package com.junie.debuglog.parser;

import android.util.Log;

import com.junie.debuglog.setting.Constant;

/**
 * Created by niejun on 2017/4/17.
 */

public class ThrowableParse implements IParse<Throwable>{

    @Override
    public Class<Throwable> parseClassType() {
        return Throwable.class;
    }

    @Override
    public String parseToString(Throwable throwable) {
        if(throwable == null) {
            return Constant.OBJECT_NULL;
        }
        return Log.getStackTraceString(throwable);
    }
}
