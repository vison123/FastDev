package com.vison.devdemo.biz.LoginAndRegister.activity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.vison.devdemo.biz.LoginAndRegister.contract.LoginContract;
import com.vison.devdemo.biz.LoginAndRegister.presenter.LoginPresenter;
import com.vison.devdemo.R;
import com.vison.devdemo.system.Alert;
import com.vison.devdemo.system.BaseActivity;
import com.vison.devdemo.utils.PatternUtils;
import com.vison.devdemo.utils.UILImageLoader;
import com.nostra13.universalimageloader.cache.memory.impl.WeakMemoryCache;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.assist.ImageScaleType;

import java.util.List;

import cn.finalteam.galleryfinal.CoreConfig;
import cn.finalteam.galleryfinal.FunctionConfig;
import cn.finalteam.galleryfinal.GalleryFinal;
import cn.finalteam.galleryfinal.ThemeConfig;
import cn.finalteam.galleryfinal.model.PhotoInfo;


public class LoginActivity extends BaseActivity implements LoginContract.View {

    private EditText et_mobileNo;
    private Button btn_login, btn_register;
    private TextView tv_title;
    private LoginContract.Presenter mPresenter;
    private static ImageLoader imageLoader;
    private final FunctionConfig functionConfig = new FunctionConfig.Builder().build();
    private final int REQUEST_CODE_CAMERA = 1000;
    private final int REQUEST_CODE_GALLERY = 1001;
    private final int REQUEST_CODE_CROP = 1002;

    @Override
    public void initParams() {
        mPresenter = new LoginPresenter(this);
    }

    @Override
    public void setContentView() {
        setContentView(R.layout.activity_login);
    }

    @Override
    public void initView() {
        tv_title = (TextView) findViewById(R.id.tv_title);
        tv_title.setText(this.getString(R.string.title_activity_login));
        et_mobileNo = (EditText) findViewById(R.id.et_mobileNo);
        btn_login = (Button) findViewById(R.id.btn_login);
        btn_register = (Button) findViewById(R.id.btn_register);
    }

    @Override
    public void initToolbar() {

    }

    @Override
    public void initData() {
        initImageLoader();
        initGallery();
    }

    @Override
    public void initListeners() {
        btn_login.setOnClickListener(this);
        btn_register.setOnClickListener(this);
    }


    public void setClick(View view) {
        switch (view.getId()) {
            case R.id.btn_login:
                if (!PatternUtils.isMobileNo(getMobileNo())) {
                    Alert.showOKDialog("提醒", this.getString(R.string.error_invalid_mobile), this);
                } else {
                    mPresenter.sendSmsCode();
                }
                break;
            case R.id.btn_register:
                showSelectDdialog();
                //startActivity(new Intent(LoginActivity.this, RegisterActivity.class));
        }
    }

    @Override
    public void sendSmsCodeError(String msg) {
        Toast.makeText(LoginActivity.this, msg, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void sendSmsCodeSuccess() {
        Toast.makeText(LoginActivity.this, this.getString(R.string.success_send_mobile), Toast.LENGTH_SHORT).show();
        startActivity(new Intent(LoginActivity.this, LoginSmsActivity.class));
    }

    @Override
    public String getMobileNo() {
        return et_mobileNo.getText().toString();
    }

    @Override
    public void setPresenter(LoginContract.Presenter presenter) {

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

    private void showSelectDdialog() {
        String[] items = { "本地上传","拍照上传"};
        new AlertDialog.Builder(this)
                .setItems(items, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        switch (which) {
                            case 0:
                                GalleryFinal.openGallerySingle(REQUEST_CODE_GALLERY, functionConfig, mOnHanlderResultCallback);
                                break;
                            case 1:
                                GalleryFinal.openCamera(REQUEST_CODE_CAMERA, functionConfig, mOnHanlderResultCallback);
                                break;
                        }
                    }

                })
                .setNegativeButton("取消", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                }).show();
    }

    //选择图片或拍照后回调
    private GalleryFinal.OnHanlderResultCallback mOnHanlderResultCallback = new GalleryFinal.OnHanlderResultCallback() {
        @Override
        public void onHanlderSuccess(int reqeustCode, List<PhotoInfo> resultList) {
            PhotoInfo info = resultList.get(0);
            String path = info.getPhotoPath();
            switch (reqeustCode){
                case REQUEST_CODE_CAMERA:
                    GalleryFinal.openCrop(REQUEST_CODE_CROP, path, mOnHanlderResultCallback);
                    break;
                case REQUEST_CODE_GALLERY:
                    GalleryFinal.openCrop(REQUEST_CODE_CROP, path, mOnHanlderResultCallback);
                    break;
                case REQUEST_CODE_CROP:

                    break;
                default:
                    break;
            }
        }

        @Override
        public void onHanlderFailure(int requestCode, String errorMsg) {
            Toast.makeText(LoginActivity.this, errorMsg, Toast.LENGTH_SHORT).show();
        }
    };
}
