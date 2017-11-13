package com.junie.debuglog.parser;

import android.content.Intent;

import com.junie.debuglog.setting.Constant;

/**
 * Created by niejun on 2017/4/14.
 */

public class IntentParse implements IParse<Intent>{

    @Override
    public Class<Intent> parseClassType() {
        return Intent.class;
    }

    @Override
    public String parseToString(Intent intent) {
        if(intent == null) {
            return Constant.OBJECT_NULL;
        }
        StringBuilder builder = new StringBuilder(parseClassType().getSimpleName() + " [" + Constant.BR);
//        builder.append(String.format("%s = %s" + Constant.BR, "Scheme", intent.getScheme()));
//        builder.append(String.format("%s = %s" + Constant.BR, "DataString", intent.getDataString()));
//        builder.append(String.format("%s = %s" + Constant.BR, "Type", intent.getType()));
//        builder.append(String.format("%s = %s" + Constant.BR, "Package", intent.getPackage()));
//        builder.append(String.format("%s = %s" + Constant.BR, "Categories", intent.getCategories()));
//        builder.append(String.format("%s = %s" + Constant.BR, "Flags", getFlags(intent.getFlags())));
//        builder.append(String.format("%s = %s" + Constant.BR, "ComponentInfo", intent.getComponent()));

        builder.append(String.format("%s = %s" + Constant.BR, "ComponentInfo", intent.getComponent()));
        builder.append(String.format("%s : %s" + Constant.BR, "Action", intent.getAction()));
        builder.append(String.format("%s : %s" + Constant.BR, "Extras", new BundleParse().parseToString(intent.getExtras())));
        return builder.toString() + "]";
    }
}
