package com.junie.debuglog.print;

/**
 * Created by niejun on 2017/2/24.
 */

public interface IPrinter {

    void d(Object object);
    void d(String message,Object... args);

    void v(Object object);
    void v(String message,Object... args);

    void i(Object object);
    void i(String message,Object... args);

    void w(Object object);
    void w(String message,Object... args);

    void e(Throwable throwable);
    void e(Throwable throwable,String msg);

}
