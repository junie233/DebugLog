package com.junie.debuglog;

import com.junie.debuglog.setting.Config;
import com.junie.debuglog.print.IPrinter;
import com.junie.debuglog.print.LogPrinter;
import com.junie.debuglog.setting.Constant;

/**
 * Created by niejun on 2017/2/24.
 * 对外类
 */

public class DebugLog {

    private static IPrinter logPrinter = new LogPrinter();
    private static Config config ;

    public static Config getConfig() {
        if(config == null ) {
            synchronized (Config.class) {
                if (config == null) {
                    config = new Config();
                    config.addParse(Constant.DEFAULT_PARSE_CLASS);
                }
            }
        }
        return config;
    }

    public static void d(Object object) {
        logPrinter.d(object);
    }

    public static void d(String message, Object... args) {
        logPrinter.d(message,args);
    }

    public static void v(Object object) {
        logPrinter.v(object);
    }

    public static void v(String message, Object... args) {
        logPrinter.v(message,args);
    }

    public static void i(Object object) {
        logPrinter.i(object);
    }

    public static void i(String message, Object... args) {
        logPrinter.i(message,args);
    }

    public static void w(Object object) {
        logPrinter.w(object);
    }

    public static void w(String message, Object... args) {
        logPrinter.w(message,args);
    }

    public static void e(Throwable throwable) {
        logPrinter.e(throwable);
    }

    public static void e(Throwable throwable, String msg) {
        logPrinter.e(throwable,msg);
    }


}
