package com.junie.debuglog.setting;

import com.junie.debuglog.parser.BundleParse;
import com.junie.debuglog.parser.CollectionParse;
import com.junie.debuglog.parser.IParse;
import com.junie.debuglog.parser.IntentParse;
import com.junie.debuglog.parser.JsonArrayParse;
import com.junie.debuglog.parser.JsonObjectParse;
import com.junie.debuglog.parser.MapParse;
import com.junie.debuglog.parser.ThrowableParse;

/**
 * Created by niejun on 2017/4/12.
 */

public class Constant {

    public static final int All = 0b11111;
    public static final int VERBOSE = 0b10000;
    public static final int DEBUG = 0b01000;
    public static final int INFO = 0b00100;
    public static final int WARING = 0b00010;
    public static final int ERROR = 0b00001;
    public static final int NONE = 0b00000;

    // Object为空
    public static final String OBJECT_NULL = "null";
    // 换行符
    public static final String BR = System.getProperty("line.separator");
    // 解析Object属性最大层级
    public static final int MAX_CHILD_LEVEL = 2;
    //Json解析空格偏移量
    public static final int JSON_SHIFT = 3;
    // 默认支持解析库
    public static final Class<? extends IParse>[] DEFAULT_PARSE_CLASS = new Class[]{
            BundleParse.class, IntentParse.class, CollectionParse.class,
            MapParse.class, ThrowableParse.class, JsonObjectParse.class,
            JsonArrayParse.class
    };


}
