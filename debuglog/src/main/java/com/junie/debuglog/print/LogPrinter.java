package com.junie.debuglog.print;

import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Looper;
import android.os.Message;
import android.text.TextUtils;
import com.junie.debuglog.DebugLog;
import com.junie.debuglog.parser.ObjectParse;
import com.junie.debuglog.setting.Constant;
import com.junie.debuglog.util.DateUtils;
import com.junie.debuglog.util.TraceUtils;

/**
 * Created by niejun on 2017/2/24.
 */

public class LogPrinter implements IPrinter{

    private static final int MSG_HANDLE_PRINT = 1001;
    /**
     * Android's max limit for a log entry is ~4076 bytes,
     */
    private static final int MAX_SIZE = 4000;

    private HandlerThread mPrintHandlerDebugThread;
    private PrintHandler mPrintHandler;

    @Override
    public void d(Object object) {
        if(!isShowLog(Constant.DEBUG)) {
            return ;
        }
        String msg = ObjectParse.objectToString(object);
        log(Constant.DEBUG,msg);
    }

    @Override
    public void d(String message, Object... args) {
        if(!isShowLog(Constant.DEBUG)) {
            return ;
        }
        //msg格式化合并
        String msg = ObjectParse.StringParse(message,args);
        log(Constant.DEBUG,msg);
    }

    @Override
    public void v(Object object) {
        if(!isShowLog(Constant.VERBOSE)) {
            return ;
        }
        String msg = ObjectParse.objectToString(object);
        log(Constant.VERBOSE,msg);
    }

    @Override
    public void v(String message, Object... args) {
        if(!isShowLog(Constant.DEBUG)) {
            return ;
        }
        //msg格式化合并
        String msg = ObjectParse.StringParse(message,args);
        log(Constant.VERBOSE,msg);
    }

    @Override
    public void i(Object object) {
        if(!isShowLog(Constant.INFO)) {
            return ;
        }
        String msg = ObjectParse.objectToString(object);
        log(Constant.INFO,msg);
    }

    @Override
    public void i(String message, Object... args) {
        if(!isShowLog(Constant.INFO)) {
            return ;
        }
        //msg格式化合并
        String msg = ObjectParse.StringParse(message,args);
        log(Constant.INFO,msg);
    }

    @Override
    public void w(Object object) {
        if(!isShowLog(Constant.WARING)) {
            return ;
        }
        String msg = ObjectParse.objectToString(object);
        log(Constant.WARING,msg);
    }

    @Override
    public void w(String message, Object... args) {
        if(!isShowLog(Constant.WARING)) {
            return ;
        }
        //msg格式化合并
        String msg = ObjectParse.StringParse(message,args);
        log(Constant.WARING,msg);
    }

    @Override
    public void e(Throwable throwable) {
        if(!isShowLog(Constant.ERROR)) {
            return ;
        }
        String msg = ObjectParse.objectToString(throwable);
        log(Constant.ERROR,msg);
    }

    @Override
    public void e(Throwable throwable, String message) {
        if(!isShowLog(Constant.ERROR)) {
            return ;
        }
        String errorMsg = ObjectParse.objectToString(throwable);
        if(!TextUtils.isEmpty(message)) {
            errorMsg = message +" : " + errorMsg;
        }
        log(Constant.ERROR,errorMsg);
    }


    /**
     * 打印内容及堆栈
     * @param logType
     * @param contentMsg
     */
    public void log(int logType, String contentMsg) {
        logContent(logType,contentMsg);
        if(DebugLog.getConfig().getIsPrintFile() && !TextUtils.isEmpty(DebugLog.getConfig().getPrintFilePath())) {
            printFile(contentMsg);
        }
    }


    /**
     * 判断logType是否在LogLevel中
     * @param logType
     * @return
     */
    public boolean isShowLog(int logType) {
        if((logType & DebugLog.getConfig().getLogLevel()) > 0) {
            return true;
        }
        return false;
    }


    /**
     * 调用Adapter去打印
     * @param logType
     * @param msg
     */
    private void logBase(int logType,String msg) {
        String tag = getTag()+" "+getTrace();
        switch(logType) {
            case Constant.VERBOSE:
                    DebugLog.getConfig().getLogAdapter().v(tag,msg);
                    break;
            case Constant.DEBUG:
                    DebugLog.getConfig().getLogAdapter().d(tag,msg);
                    break;
            case Constant.INFO:
                    DebugLog.getConfig().getLogAdapter().i(tag,msg);
                    break;
            case Constant.WARING:
                    DebugLog.getConfig().getLogAdapter().w(tag,msg);
                    break;
            case Constant.ERROR:
                    DebugLog.getConfig().getLogAdapter().e(tag,msg);
                    break;
            default:
                    DebugLog.getConfig().getLogAdapter().i(tag,msg);
                    break;
        }
    }

    /**
     * 获取Tag
     * @return
     */
    private String getTag(){
        return DebugLog.getConfig().getTag();
    }


    /**
     * 打印堆栈，class，方法，行号
     */
    private String getTrace() {
        StringBuilder msgBuilder = new StringBuilder("");
        StackTraceElement[] trace = Thread.currentThread().getStackTrace();
        if (DebugLog.getConfig().getIsShowTradeInfo()) {
            msgBuilder.append(" Thread: " + Thread.currentThread().getName());
        }
        int stackOffset = TraceUtils.getStackOffset(trace) ;
        int methodCount = DebugLog.getConfig().getMethodCount();
        if (methodCount + stackOffset > trace.length) {
            methodCount = trace.length - stackOffset - 1;
        }
        for (int i = methodCount; i > 0; i--) {
            int stackIndex = stackOffset + i;
            if (stackIndex >= trace.length) {
                continue;
            }
        msgBuilder.append("(")
                .append(trace[stackIndex].getFileName())
                .append(":")
                .append(trace[stackIndex].getLineNumber())
                .append(")")
                .append(trace[stackIndex].getMethodName());
        }
        return msgBuilder.toString();
    }

    /**
     * 切割换行后打印log具体内容
     */
    private void logContent(int logTyppe,String msg) {
        byte[] bytes = msg.getBytes();
        int length = bytes.length;
        int strLins =  length / MAX_SIZE;

        if(strLins == 0) {
            logBase(logTyppe,msg);
        } else {
            int index = 0;
            for (int i = 0; i <= strLins; i++) {
                int offset = Math.min(index + MAX_SIZE, length);
                String strLine = new String(bytes, index, offset - index);
                logBase(logTyppe, strLine);
                index += MAX_SIZE;
            }
        }
    }

    /**
     * 整合字符串并通知Handler打印log到文件
     * @param content 打印信息
     */
    private void printFile(String content) {
        cheakHandler();//校验是否初始化mPrintHandler
        String traceMsg = getTrace();
        String printMsg = DateUtils.getStringDate()+" : "+traceMsg+" ："+content;
        Message msg = mPrintHandler.obtainMessage(MSG_HANDLE_PRINT);
        Bundle bundle = new Bundle();
        bundle.putString("printMsg",printMsg); //fixme 去掉字符串中的空格
        msg.setData(bundle);
        mPrintHandler.sendMessage(msg);
    }


    /**
     * 校验并初始化handler
     * @param
     */
    private void cheakHandler() {
        if (null == mPrintHandlerDebugThread) {
            mPrintHandlerDebugThread = new HandlerThread("PrintHandlerThread");
            mPrintHandlerDebugThread.start();
        }
        mPrintHandler = new PrintHandler(mPrintHandlerDebugThread.getLooper());
    }
    /**
     * 处理打印Handler
     */
    private class PrintHandler extends Handler {
        public PrintHandler(Looper looper) {
            super(looper);
        }

        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case MSG_HANDLE_PRINT:
                    if (null == msg.getData()) {
                        break;
                    }
                    String printMsg = msg.getData().getString("printMsg");
                    if (TextUtils.isEmpty(printMsg)) {
                        break;
                    }
                    FilePrinter.printFile(Environment.getExternalStorageDirectory(),printMsg);
                    break;
                default:
                    break;
            }
        }
    }
}
