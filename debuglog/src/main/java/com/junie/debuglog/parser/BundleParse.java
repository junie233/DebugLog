package com.junie.debuglog.parser;

import android.os.Bundle;

import com.junie.debuglog.setting.Constant;

/**
 * Created by niejun on 2017/4/14.
 */

public class BundleParse implements IParse<Bundle>{
    @Override
    public Class<Bundle> parseClassType() {
        return Bundle.class;
    }

    @Override
    public String parseToString(Bundle bundle) {
        if(bundle == null) {
            return Constant.OBJECT_NULL;
        }
        StringBuilder builder = new StringBuilder(bundle.getClass().getName() + " [" + Constant.BR);
        String format = "'%s' : %s " + Constant.BR;
        for (String key : bundle.keySet()) {
            builder.append(String.format(format,
                    key, ObjectParse.objectToString(bundle.get(key))));
        }
        builder.append("]");
        return builder.toString();
    }
}
