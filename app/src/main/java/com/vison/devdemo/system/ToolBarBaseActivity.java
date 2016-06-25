package com.vison.devdemo.system;

import android.content.pm.ActivityInfo;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.DisplayMetrics;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.nostra13.universalimageloader.cache.memory.impl.WeakMemoryCache;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.assist.ImageScaleType;
import com.orhanobut.logger.Logger;
import com.vison.devdemo.utils.UILImageLoader;

import java.util.Locale;

import butterknife.ButterKnife;
import cn.finalteam.galleryfinal.CoreConfig;
import cn.finalteam.galleryfinal.FunctionConfig;
import cn.finalteam.galleryfinal.GalleryFinal;
import cn.finalteam.galleryfinal.ThemeConfig;


/**
 * Created by vison on 16/6/21.
 * 所有需要使用TooBar的Activity继承此类
 */

public abstract class ToolBarBaseActivity extends AppCompatActivity implements View.OnClickListener{
    private static ImageLoader imageLoader;
    private ToolBarHelper mToolBarHelper;
    public Toolbar toolbar;
    public TextView toolBarTitle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void setContentView(int layoutResID) {
        mToolBarHelper = new ToolBarHelper(this, layoutResID);
        toolbar = mToolBarHelper.getToolBar();
        toolBarTitle = mToolBarHelper.getTitle();
        setContentView(mToolBarHelper.getContentView());
        ButterKnife.bind(this);
        /*自定义的一些toolbar操作*/
        onCreateCustomToolBar(toolbar);
        setLanguage();
        // 竖屏锁定
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        Logger.init("TJ_FAS");
        initPresenter();
        initView();
        initToolbar(toolbar,toolBarTitle);
        initData();
        initListeners();
        initImageLoader();
        initGallery();
    }

    // 绑定Presenter
    public abstract void initPresenter();

    //初始化数据
    public abstract void initView();

    //初始化toolbar
    public abstract void initToolbar(Toolbar toolbar,TextView toolBarTitle);

    //初始化数据
    public abstract void initData();

    //设置监听事件
    public abstract void initListeners();

    //处理点击事件
    public abstract void setClick(View view);

    @Override
    public void onClick(View v) {
        setClick(v);
    }


    public void onCreateCustomToolBar(Toolbar toolbar) {
        toolbar.setContentInsetsRelative(0, 0);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
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

    private boolean isZh() {
        Locale locale = getResources().getConfiguration().locale;
        String language = locale.getLanguage();
        if (language.endsWith("zh"))
            return true;
        else
            return false;
    }

    private void initGallery() {
        //设置主题
        ThemeConfig theme = new ThemeConfig.Builder()
                .setTitleBarBgColor(Color.rgb(58, 58, 58))
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
        CoreConfig coreConfig = new CoreConfig.Builder(getApplicationContext(), imageLoader, theme)
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
