package com.junie.debug;

import com.junie.debuglog.parser.ObjectParse;

/**
 * Created by niejun on 2017/4/12.
 */

public class ArrayUtil3 {
    // 基本数据类型
    private final static String[] TYPES = {"int", "java.lang.String", "boolean", "char",
            "float", "double", "long", "short", "byte"};


    public static String ArrayToString(Object array){
        StringBuilder result = new StringBuilder();
        parseArray(result, array);
        return result.toString();
    }

    public static void parseArray(StringBuilder builder, Object obj) {
        String classType = obj.getClass().getName();
        boolean flag;
        for (String type : TYPES) {
            if (classType.equalsIgnoreCase(type)) {
                builder.append(obj.toString());
            }
        }

        Object[] array = (Object []) obj;
        builder.append("[");
        for (int i = 0; i < array.length; ++i) {
            builder.append(ObjectParse.objectToString(array[i]));
            if (i != array.length - 1) {
                builder.append(",");
            }
        }
        builder.append("]");
    }

}
