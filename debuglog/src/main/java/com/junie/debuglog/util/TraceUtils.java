package com.junie.debuglog.util;

import com.junie.debuglog.DebugLog;

/**
 * Created by niejun on 2017/2/24.
 *
 */

public class TraceUtils {
    private static final int MIN_STACK_OFFSET = 1;


    /**
     * 获取堆栈起始位置
     * @param trace
     * @return
     */
    public static int getStackOffset(StackTraceElement[] trace) {
        for (int i = MIN_STACK_OFFSET; i < trace.length; i++) {
            StackTraceElement e = trace[i];
            String name = e.getClassName();
            if (name.equals(DebugLog.class.getName())) {  //FIXME nj
                return i;
            }
        }
        return -1;   //FIXME nj
    }



    /**
     * 去掉文件名后缀
     * @param name
     * @return
     */
    public static String getSimpleClassName(String name) {
        int lastIndex = name.lastIndexOf(".");
        return name.substring(lastIndex + 1);
    }


}
