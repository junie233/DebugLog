package com.junie.debuglog.adapter;

/**
 * Created by niejun on 2017/2/24.
 */

public interface LogAdapter {

    void d(String TAG,String msg);

    void i(String TAG,String msg);

    void v(String TAG,String msg);

    void w(String TAG,String msg);

    void e(String TAG,String msg);

}
