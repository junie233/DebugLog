package com.junie.debuglog.adapter;

import android.util.Log;

/**
 * Created by niejun on 2017/2/24.
 */

public class AndroidLogAdapter implements LogAdapter {
    @Override
    public void d(String TAG, String msg) {
        Log.d(TAG,msg);
    }

    @Override
    public void i(String TAG, String msg) {
        Log.i(TAG,msg);
    }

    @Override
    public void v(String TAG, String msg) {
        Log.v(TAG,msg);
    }

    @Override
    public void w(String TAG, String msg) {
        Log.w(TAG,msg);
    }

    @Override
    public void e(String TAG, String msg) {
        Log.e(TAG,msg);
    }
}
