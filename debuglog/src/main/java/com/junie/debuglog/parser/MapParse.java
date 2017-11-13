package com.junie.debuglog.parser;

import com.junie.debuglog.setting.Constant;
import java.util.Map;
import java.util.Set;

/**
 * Created by niejun on 2017/4/13.
 */

public class MapParse implements IParse<Map>{
    @Override
    public Class<Map> parseClassType() {
        return Map.class;
    }

    @Override
    public String parseToString(Map map) {
        if(map == null) {
            return Constant.OBJECT_NULL;
        }
        String msg = map.getClass().getName() + " ["+ Constant.BR;
        Set<Object> keys = map.keySet();
        for(Object key : keys) {
            String formt = "%s : %s" + Constant.BR;
            Object value = map.get(key);
            if(value !=null) {
                msg += String.format(formt,ObjectParse.objectToString(key),ObjectParse.objectToString(value));
            }
        }
        return msg + "]";
    }
}
