package com.vison.devdemo.system;

import android.app.Application;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.os.Handler;

import com.beardedhen.androidbootstrap.TypefaceProvider;
import com.vison.devdemo.BuildConfig;
import com.vison.devdemo.R;
import com.vison.devdemo.utils.CrashHandler;
import com.vison.devdemo.utils.UILImageLoader;
import com.nostra13.universalimageloader.cache.memory.impl.WeakMemoryCache;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.assist.ImageScaleType;
import com.zhy.http.okhttp.OkHttpUtils;

import org.acra.ACRA;
import org.acra.annotation.ReportsCrashes;

import java.util.Stack;
import java.util.concurrent.TimeUnit;

import cn.finalteam.galleryfinal.CoreConfig;
import cn.finalteam.galleryfinal.FunctionConfig;
import cn.finalteam.galleryfinal.GalleryFinal;
import cn.finalteam.galleryfinal.ThemeConfig;
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
    private static ImageLoader imageLoader;
    private static DisplayImageOptions options;


    @Override
    public void onCreate() {
        super.onCreate();
        context = getApplicationContext();
        mainTid = android.os.Process.myTid();
        handler = new Handler();
        parse = MediaType.parse("application/json");
        mClient = OkHttpUtils.getInstance().getOkHttpClient();
        CrashHandler mCrashHandler = CrashHandler.getInstance();
        TypefaceProvider.registerDefaultIconSets();
        //崩溃报告
        ACRA.init(this);
        initGallery();
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

    private void initGallery() {
        //设置主题
        ThemeConfig theme = new ThemeConfig.Builder()
                .setTitleBarBgColor(Color.rgb(68, 188, 178))
                .build();
        //配置功能
        FunctionConfig functionConfig = new FunctionConfig.Builder()
                .setEnableCamera(true)
                .setEnableEdit(true)
                .setEnableCrop(true)
                .setEnableRotate(true)
                .setCropSquare(true)
                .setEnablePreview(true)
                .setMutiSelectMaxSize(8)
                .build();

        //配置imageloader
        cn.finalteam.galleryfinal.ImageLoader imageLoader = new UILImageLoader();
        CoreConfig coreConfig = new CoreConfig.Builder(context, imageLoader, theme)
                .setFunctionConfig(functionConfig)
                .setNoAnimcation(false)
                .build();
        GalleryFinal.init(coreConfig);
    }

    //初始化Imageloader配置
    private void initImageLoader() {
        imageLoader = ImageLoader.getInstance();
        ImageLoaderConfiguration config = new ImageLoaderConfiguration.Builder(this)
                .threadPoolSize(3)
                .defaultDisplayImageOptions(getDisplayOptions())
                .memoryCache(new WeakMemoryCache())
                .build();
        imageLoader.init(config);
    }

    //加载图片的配置
    private DisplayImageOptions getDisplayOptions() {
        DisplayImageOptions options = new DisplayImageOptions.Builder()
                .bitmapConfig(Bitmap.Config.RGB_565)
                .imageScaleType(ImageScaleType.IN_SAMPLE_INT)
                .build();
        return options;
    }

}
