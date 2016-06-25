package com.vison.devdemo.biz.LoginAndRegister.activity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.vison.devdemo.R;
import com.vison.devdemo.biz.LoginAndRegister.contract.LoginContract;
import com.vison.devdemo.biz.LoginAndRegister.presenter.LoginPresenter;
import com.vison.devdemo.system.Alert;
import com.vison.devdemo.system.ToolBarBaseActivity;
import com.vison.devdemo.utils.PatternUtils;

import java.util.List;

import butterknife.BindView;
import cn.finalteam.galleryfinal.FunctionConfig;
import cn.finalteam.galleryfinal.GalleryFinal;
import cn.finalteam.galleryfinal.model.PhotoInfo;


public class LoginActivity extends ToolBarBaseActivity implements LoginContract.View {

    @BindView(R.id.logo)
    ImageView logo;
    @BindView(R.id.tv_platform)
    TextView tvPlatform;
    @BindView(R.id.et_mobileNo)
    EditText etMobileNo;
    @BindView(R.id.btn_login)
    Button btnLogin;
    @BindView(R.id.btn_register)
    Button btnRegister;

    private LoginContract.Presenter mPresenter;
    private final FunctionConfig functionConfig = new FunctionConfig.Builder().build();
    private final int REQUEST_CODE_CAMERA = 1000;
    private final int REQUEST_CODE_GALLERY = 1001;
    private final int REQUEST_CODE_CROP = 1002;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
    }

    @Override
    public void initPresenter() {
        mPresenter = new LoginPresenter(this);
    }

    @Override
    public void initView() {

    }

    @Override
    public void initToolbar(Toolbar toolbar,TextView toolBarTitle) {
        toolBarTitle.setText("login");
        toolbar.setTitle("");
        toolbar.setNavigationIcon(R.drawable.ic_return);
        setSupportActionBar(toolbar);
    }

    @Override
    public void initData() {

    }

    @Override
    public void initListeners() {
        btnLogin.setOnClickListener(this);
        btnRegister.setOnClickListener(this);
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
        return etMobileNo.getText().toString();
    }

    @Override
    public void setPresenter(LoginContract.Presenter presenter) {

    }

    private void showSelectDdialog() {
        String[] items = {"本地上传", "拍照上传"};
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
            switch (reqeustCode) {
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
