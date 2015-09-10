/**
 * Created by linwoain(linwoain@outlook.com)
 * Time  2014/10/13 16:20
 * 知我者谓我心忧，不知我者谓我何求！
 */
package com.linwoain.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;

import android.content.Context;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Environment;

import com.linwoain.library.LApplication;

/**
 * @author linwoain
 * @version 2014/10/13 16:20
 */
public class PackageUtil {


    /**
     * 获取清单文件中meta_data标签下自定义的键值对<br>
     * 在Application节点下
     *@param key 要获取内容的键
     * @return 获取到的值
     */
    public static String getMetaData(String key) {
        ApplicationInfo info1 = null;
        try {
            info1 = LApplication.getContext().getPackageManager().getApplicationInfo(LApplication.getContext().getPackageName(), PackageManager.GET_META_DATA);
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        return info1.metaData.getString(key);


    }

    /**
     * 获取版本名
     *
     * @return 版本名
     */
    public static String getVersionName() {
        return getPackageInfo().versionName;
    }

    /**
     * 获取版本号
     *
     * @return 版本号
     */
    public static int getVersionCode() {
        return getPackageInfo().versionCode;
    }

    /**
     * 获取包信息
     *
     * @return 包信息
     */
    public static PackageInfo getPackageInfo() {
        Context context = LApplication.getContext();
        // 获取packagemanager的实例
        PackageManager packageManager = context.getPackageManager();
        // getPackageName()是你当前类的包名，0代表是获取版本信息
        PackageInfo packInfo = null;
        try {
            packInfo = packageManager.getPackageInfo(context.getPackageName(), 0);
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        return packInfo;
    }

    /**
     * 此方法描述的是：根据文件名获取保存到本地的地址
     *
     * @param path 传入的路径
     * @return cache路径+传入的路径
     */

    public static String getpath(String path) {
        Context context = LApplication.getContext();
        boolean is = Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED);
        if (is && context.getExternalCacheDir().canWrite())
            return context.getExternalCacheDir().getPath() + "/" + path;
        else {
            return context.getCacheDir().getPath() + path;
        }

    }


    /**
     * 调用系统应用安装APK程序
     *
     * @param path apk文件的路径
     */
    public static void installApk(String path) {

        Intent intent = new Intent();
        intent.setAction(Intent.ACTION_VIEW);
        File file = new File(path);
        intent.setDataAndType(Uri.fromFile(file), "application/vnd.android.package-archive");
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        LApplication.getContext().startActivity(intent);
    }

    /**
     * 根据应用包名打开应用
     *
     * @param packageName 要打开的应用
     */
    public static void startApp(String packageName) {
        if (LLStringTools.isEmpty(packageName)) {
            throw new RuntimeException("传入的包名不能为空");
        }
        PackageManager manager = LApplication.getContext().getPackageManager();
        Intent intent = manager.getLaunchIntentForPackage(packageName);
        LApplication.getContext().startActivity(intent);
    }

    /**
     * 调用系统自带卸载API
     *
     * @param packageName 要卸载文件包名
     */
    public static void uninstall(String packageName) {

        if (LLStringTools.isEmpty(packageName)) {
            ToastUtil.show("卸载失败！传入的包名为空");
            return;
        }
//12-11 15:19:59.274: I/ActivityManager(598): 
// START {act=android.intent.action.DELETE dat=package cmp=com.android.packageinstaller/.UninstallerActivity u=0} from pid 31719

        Intent intent = new Intent();
        intent.setAction(Intent.ACTION_DELETE);
        intent.setData(Uri.parse("package:" + packageName));
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        LApplication.getContext().startActivity(intent);
    }


    /**
     * 判断手机是否root，不弹出root请求框
     * @return 是否root
     */
    public static boolean isRoot() {
        String binPath = "/system/bin/su";
        String xBinPath = "/system/xbin/su";
        boolean f1 = new File(binPath).exists();
        boolean f1executable = isExecutable(binPath);
        boolean f2 = new File(xBinPath).exists();
        boolean f2executable = isExecutable(xBinPath);
        return (f1 && f1executable) || (f2 && f2executable);
    }

    private static boolean isExecutable(String filePath) {
        Process p = null;
        try {
            p = Runtime.getRuntime().exec("ls -l " + filePath);            // 获取返回内容
            BufferedReader in = new BufferedReader(new InputStreamReader(p.getInputStream()));
            String str = in.readLine();
            if (str != null && str.length() >= 4) {
                char flag = str.charAt(3);
                if (flag == 's' || flag == 'x') return true;
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (p != null) {
                p.destroy();
            }
        }
        return false;
    }
}
