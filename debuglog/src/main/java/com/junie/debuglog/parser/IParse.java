package com.junie.debuglog.parser;

/**
 * Created by niejun on 2017/4/12.
 * 解析器接口
 */

public interface IParse<T> {

    Class<T> parseClassType();

    String parseToString(T t);

}
