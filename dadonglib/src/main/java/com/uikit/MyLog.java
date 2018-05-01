package com.uikit;

import android.content.Context;
import android.text.TextUtils;
import android.util.Log;

import com.orhanobut.logger.AndroidLogAdapter;
import com.orhanobut.logger.CsvFormatStrategy;
import com.orhanobut.logger.DiskLogAdapter;
import com.orhanobut.logger.FormatStrategy;
import com.orhanobut.logger.Logger;
import com.orhanobut.logger.PrettyFormatStrategy;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import me.chunsheng.logboy.BuildConfig;
import me.chunsheng.logboy.LogBoy;


public class MyLog {
    private static final String LINE_SEPARATOR = System.getProperty("line.separator");
    private static final int JSON_INDENT = 4;
    private static final int d = 0;
    private static final int i = 1;
    private static final int e = 2;
    private static final int w = 3;

    public static void init(String defaultTag, final boolean debug) {
        FormatStrategy formatStrategy = PrettyFormatStrategy.newBuilder()
                .methodCount(1)
                .methodOffset(1)
                .tag(defaultTag)   // (Optional) Global tag for every log. Default PRETTY_LOGGER
                .build();
        Logger.addLogAdapter(new AndroidLogAdapter(formatStrategy) {
            @Override
            public boolean isLoggable(int priority, String tag) {
                return debug;
            }
        });

        CsvFormatStrategy csvFormatStrategy = CsvFormatStrategy.newBuilder().
                folderPath(getLoggerPath()).build();
        Logger.addLogAdapter(new DiskLogAdapter(csvFormatStrategy) {
            @Override
            public boolean isLoggable(int priority, String tag) {
                //DEBUG级别以上，日志输出到文件
                return priority > Logger.DEBUG;
            }
        });
    }

    /**
     * 日志绝对路径
     *
     * @return
     */
    private static String getLoggerPath() {
//        return ExternalStorage.getInstance().getDirectory() + File.separatorChar + "logger";
        return ExternalStorage.getInstance().getDirectoryByDirType(StorageType.TYPE_LOG);
    }

//    public static File[] getLogFiles() {
////        String dirPath = Environment.getExternalStorageDirectory().getAbsolutePath() + File.separatorChar + "logger";
//        String dirPath = getLoggerPath();
//        File dir = new File(dirPath);
//        return dir.listFiles();
//    }


    public static void enableRemoteLog(Context context, String remoteLogUrl, boolean enableServer) {
//        LogBoy.setRemoteUrl(remoteLogUrl);
//        LogBoy.setEnable(enableServer);
//        LogBoy.setLocalEnable(false);
//        LogBoy.init(context);
    }


    public static void d(String msg) {
        Logger.d(msg);
//        printServerLog(d, "LogBoy", msg);
    }


    public static void d(String tag, String msg) {
        Logger.t(tag).d(msg);
//        printServerLog(d, tag, msg);
    }

    public static void i(String msg) {
        Logger.i(msg);
//        printServerLog(i, "LogBoy", msg);
    }

    public static void i(String tag, String msg) {
        Logger.t(tag).i(msg);
//        printServerLog(i, tag, msg);
    }


    public static void e(String msg) {
        Logger.e(msg);
//        printServerLog(e, "LogBoy", msg);
    }

    public static void e(String tag, String msg) {
        Logger.t(tag).e(msg);
//        printServerLog(e, tag, msg);
    }

    public static void w(String msg) {
        Logger.w(msg);
//        printServerLog(w, "log", msg);
    }

    public static void w(String tag, String msg) {
        Logger.t(tag).w(msg);
//        printServerLog(w, tag, msg);
    }


    private static void printServerLog(int priority, String tag, String msg) {
        if (BuildConfig.DEBUG) {
            StackTraceElement[] stackTrace = Thread.currentThread().getStackTrace();

            int index = 4;
            String className = stackTrace[index].getFileName();
            String methodName = stackTrace[index].getMethodName();
            int lineNumber = stackTrace[index].getLineNumber();

            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("[ (").append(className).append(":").append(lineNumber).append(")#").append(methodName).append(" ] ");
            String logStr = stringBuilder.toString();

            if (TextUtils.isEmpty(msg)) {
                printLine(priority, tag, "null string or zero length");
                return;
            }

            boolean isJson = false;
            try {
                if (msg.startsWith("{")) {
                    JSONObject jsonObject = new JSONObject(msg);
                    msg = jsonObject.toString(JSON_INDENT);
                    isJson = true;
                } else if (msg.startsWith("[")) {
                    JSONArray jsonArray = new JSONArray(msg);
                    msg = jsonArray.toString(JSON_INDENT);
                    isJson = true;
                }

                if (isJson) {
                    printLine(priority, tag, true);
                    msg = logStr + LINE_SEPARATOR + msg;
                    String[] lines = msg.split(LINE_SEPARATOR);
                    for (String line : lines) {
                        printLine(priority, tag, "║ " + line);
                    }
                    printLine(priority, tag, false);
                    return;
                }
            } catch (JSONException e) {
                printLine(Log.ERROR, tag, e.getCause().getMessage() + "\n" + msg);
            } finally {
                if (!isJson) {
                    printLine(priority, tag, logStr + msg);
                }
            }
        }
    }

    private static void printLine(int priority, String tag, boolean isTop) {
        if (isTop) {
            printLine(priority, tag, "╔═══════════════════════════════════════════════════════════════════════════════════════");
        } else {
            printLine(priority, tag, "╚═══════════════════════════════════════════════════════════════════════════════════════");
        }
    }

    private static void printLine(int priority, String tag, String line) {
        Log.println(priority, tag, line);
        switch (priority) {
            case d:
                LogBoy.d(tag, line);
                break;
            case i:
                LogBoy.i(tag, line);
                break;
            case e:
                LogBoy.e(tag, line);
                break;
            case w:
                LogBoy.w(tag, line);
                break;
        }
    }
}
