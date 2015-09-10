/**
 *知我者为我心忧，不知我者谓我何求！
 *linwoain@outlook.com
 *作者 linwoain
 *日期 2014/10/25 10:04
 */
package com.linwoain.library;


import java.io.PrintWriter;
import java.io.StringWriter;
import java.io.Writer;

import com.linwoain.util.LLogUtils;
import com.linwoain.util.PackageUtil;
import com.linwoain.util.PhoneUtil;

/**
 * 继承此类并重写其doSomeThingWhenFC方法
 * 并在主线程中使用Thread.setDefaultUncaughtExceptionHandler()方法
 * 并将当前类的对象传入，在程序异常崩溃时将调用doSomeThingWhenFC此方法
 */
public abstract class LinExceptionHandler implements Thread.UncaughtExceptionHandler {
    private Thread.UncaughtExceptionHandler defaultUEH;

    public String stacktrace;

    public LinExceptionHandler() {
        this.defaultUEH = Thread.getDefaultUncaughtExceptionHandler();
    }

    @Override
    public void uncaughtException(Thread thread, Throwable ex) {
        String text = "哎呦！遇到异常了！崩溃了啦!!!\n" + PhoneUtil.getUserAgent() + "\n" + PhoneUtil.getDeviceId() + "\n软件版本：" + PackageUtil.getVersionName();
        LLogUtils.e(text);

        final Writer result = new StringWriter();

        final PrintWriter printWriter = new PrintWriter(result);
        //获取跟踪的栈信息，除了系统栈信息，还把手机型号、系统版本、编译版本的唯一标示
        StackTraceElement[] trace = ex.getStackTrace();
        StackTraceElement[] trace2 = new StackTraceElement[trace.length + 3];
        System.arraycopy(trace, 0, trace2, 0, trace.length);
        trace2[trace.length + 0] = new StackTraceElement("Android", "MODEL", android.os.Build.MODEL, -1);
        trace2[trace.length + 1] = new StackTraceElement("Android", "VERSION", android.os.Build.VERSION.RELEASE, -1);
        trace2[trace.length + 2] = new StackTraceElement("Android", "FINGERPRINT", android.os.Build.FINGERPRINT, -1);
        //追加信息，因为后面会回调默认的处理方法
        ex.setStackTrace(trace2);
        ex.printStackTrace(printWriter);
        //把上面获取的堆栈信息转为字符串，打印出来
        stacktrace = result.toString();
        printWriter.close();
//        LLogUtils.e(stacktrace);
        doSomeThingWhenFC();
       defaultUEH.uncaughtException(thread,ex);
    }

    /**
     * 实现此方法在程序异常终止时调用
     * 当前错误信息已保存到字段stacktrace中

     */
    public abstract void doSomeThingWhenFC();

}
