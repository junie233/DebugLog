package com.junie.debuglog.setting;

import com.junie.debuglog.adapter.AndroidLogAdapter;
import com.junie.debuglog.adapter.LogAdapter;
import com.junie.debuglog.parser.IParse;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by niejun on 2017/2/24.
 */

public class Config {

    //log打印等级
    private int logLevel = Constant.All;
    //Tag
    private String tag = "DebugLog";
    //自定义Adapter
    private LogAdapter logAdapter;
    //方法数
    private int methodCount = 1;
    //是否打印进程信息
    private boolean isShowTradeInfo = false;
    //是否输出到文件
    private boolean isPrintFile = false;
    //输出到文件的路径
    private String printFilePath;
    //解析器列表
    private List<IParse> parsers = new ArrayList<>();

    public List<IParse> getParsers() {
        return parsers;
    }

    public boolean getIsPrintFile() {
        return isPrintFile;
    }

    public boolean getIsShowTradeInfo() {
        return isShowTradeInfo;
    }

    public int getMethodCount() {
        return methodCount;
    }

    public String getPrintFilePath() {
        return printFilePath;
    }

    public String getTag() {
        return tag;
    }

    public int getLogLevel() {
        return logLevel;
    }

    public LogAdapter getLogAdapter() {
        if(null == logAdapter) {
            logAdapter = new AndroidLogAdapter();
        }
        return logAdapter;
    }


    public Config enabelePrintFile(String filePath) {
        isPrintFile = true;
        this.printFilePath = filePath;
        return this;
    }

    public Config setShowTradeInfo(boolean showTradeInfo) {
        isShowTradeInfo = showTradeInfo;
        return this;
    }

    public Config setMethodCount(int methodCount) {
        this.methodCount = methodCount;
        return this;
    }

    public Config setLogAdapter(LogAdapter logAdapter) {
        this.logAdapter = logAdapter;
        return this;
    }

    public Config setTag(String tag) {
        this.tag = tag;
        return this;
    }

    public Config setLogLevel(int logLevel) {
        this.logLevel = logLevel;
        return this;
    }

    public Config addParse(Class<? extends IParse>... classes) {
        for (Class<? extends IParse> cla : classes) {
            try {
                parsers.add(cla.newInstance());
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return this;
    }

}
