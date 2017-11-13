package com.junie.debuglog.print;

import android.text.TextUtils;
import com.junie.debuglog.DebugLog;
import com.junie.debuglog.util.DateUtils;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;

/**
 * Created by niejun on 17/3/1.
 */
public class FilePrinter {

    private static final String FILE_PREFIX = "DebugLog_";
    private static final String FILE_FORMAT = ".log";

    /**
     * 打印输出到文件
     * @param dic
     * @param msg
     * @return
     */
    public static void printFile(File dic, String msg) {
        String filePath = DebugLog.getConfig().getPrintFilePath();
        if(TextUtils.isEmpty(filePath)) {
            return ;
        }

        File dirs = new File(filePath);
        if (!dirs.exists()) {
            if (dirs.mkdirs()) {
                DebugLog.d("flushData Initialize the Log Path");
            } else {
                DebugLog.d("flushData Cannot Create Log Path");
                return ;
            }
        }

        String fileName = getFileName();
        File file = new File(dic, fileName);
        try {
            OutputStream outputStream = new FileOutputStream(file,true);//追加
            OutputStreamWriter outputStreamWriter = new OutputStreamWriter(outputStream, "UTF-8");
            outputStreamWriter.write(msg);
            outputStreamWriter.write("\n\r");
            outputStreamWriter.flush();
            outputStream.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    private static String getFileName() {
        return FILE_PREFIX + DateUtils.getDay() + FILE_FORMAT;
    }

}
