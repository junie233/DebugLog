package com.junie.debuglog.parser;

import com.junie.debuglog.setting.Constant;

import java.util.Collection;
import java.util.Iterator;

/**
 * Created by niejun on 2017/4/14.
 * Collection 类解析
 */

public class CollectionParse implements IParse<Collection>{
    @Override
    public Class<Collection> parseClassType() {
        return Collection.class;
    }

    @Override
    public String parseToString(Collection collection) {
        if(collection == null) {
            return Constant.OBJECT_NULL;
        }
        String titleFormat = "%s size = %d [" + Constant.BR;
        String msg = String.format(titleFormat,collection.getClass().getName(),collection.size());
        if(!collection.isEmpty()) {
            Iterator<Object> iterator = collection.iterator();
            String itemFormat = "[%d]:%s"+ Constant.BR;
            int flag = 0;
            while(iterator.hasNext()) {
                Object item = iterator.next();
                msg += String.format(itemFormat,flag++,ObjectParse.objectToString(item));
            }
        }
        return msg + "]";
    }
}
