package com.vison.devdemo.system;

import android.app.Application;
import android.content.Context;
import android.os.Handler;

import com.beardedhen.androidbootstrap.TypefaceProvider;
import com.orhanobut.logger.Logger;
import com.vison.devdemo.utils.CrashHandler;
import com.zhy.http.okhttp.OkHttpUtils;

import org.acra.ACRA;
import org.acra.annotation.ReportsCrashes;

import java.util.Stack;

import okhttp3.MediaType;
import okhttp3.OkHttpClient;

/**
 * Created by vison on 16/5/8.
 */

//崩溃报告发送地址
@ReportsCrashes(
        formUri = "http://www.backendofyourchoice.com/reportpath"
)
public class BaseApplication extends Application{
    private static Context context;
    private static Stack<BaseActivity> activityStack;
    private static int mainTid;
    private static Handler handler;
    private static MediaType parse;
    private static OkHttpClient mClient;
    private static boolean useThemeBlack = true;


    @Override
    public void onCreate() {
        super.onCreate();
        Logger.d("Application Creat");
        context = getApplicationContext();
        mainTid = android.os.Process.myTid();
        handler = new Handler();
        parse = MediaType.parse("application/json");
        mClient = OkHttpUtils.getInstance().getOkHttpClient();
        CrashHandler mCrashHandler = CrashHandler.getInstance();
        TypefaceProvider.registerDefaultIconSets();
        //崩溃报告
        ACRA.init(this);
    }

    public static Context getContext() {
        return context;
    }

    public static OkHttpClient getClient() {
        return mClient;
    }

    public static MediaType getParse() {
        return parse;
    }

}
