package com.vison.devdemo.system;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.hardware.display.DisplayManager;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v7.app.AppCompatActivity;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;


import com.orhanobut.logger.Logger;

import java.util.Locale;


/**
 * Created by vison on 16/5/8.
 */
public abstract class BaseActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setLanguage();
        // 竖屏锁定
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        AppManager.getAppManager().addActivity(this);
        Logger.init("TJ_FAS");
        initParams();
        setContentView();
        initView();
        initToolbar();
        initData();
        initListeners();
    }


    // 绑定Presenter
    public abstract void initParams();

    //绑定布局文件
    public abstract void setContentView();

    //初始化数据
    public abstract void initView();

    //初始化toolbar
    public abstract void initToolbar();

    //初始化数据
    public abstract void initData();

    //设置监听事件
    public abstract void initListeners();

    @Override
    public void onClick(View v) {
        setClick(v);
    }

    //处理点击事件
    public abstract void setClick(View view);

    @Override
    protected void onDestroy() {
        super.onDestroy();
        AppManager.getAppManager().finishActivity(this);
    }

    private boolean isZh() {
        Locale locale = getResources().getConfiguration().locale;
        String language = locale.getLanguage();
        if (language.endsWith("zh"))
            return true;
        else
            return false;
    }

    public void setLanguage(){
        Locale locale = getResources().getConfiguration().locale;
        String systemLanguage = locale.getLanguage();
        Configuration config = getResources().getConfiguration();
        Resources resources = getResources();
        DisplayMetrics displayMetrics = resources.getDisplayMetrics();
        if(systemLanguage.equals("zh")){
            config.locale = Locale.CHINA;
        }else{
            config.locale = Locale.ENGLISH;
        }
        resources.updateConfiguration(config, displayMetrics);
    }
}
