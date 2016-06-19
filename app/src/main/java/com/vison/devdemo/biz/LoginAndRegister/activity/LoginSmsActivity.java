package com.vison.devdemo.biz.LoginAndRegister.activity;

import android.view.View;

import com.vison.devdemo.R;
import com.vison.devdemo.biz.LoginAndRegister.contract.LoginSmsContract;
import com.vison.devdemo.system.BaseActivity;

public class LoginSmsActivity extends BaseActivity implements LoginSmsContract.View {

    @Override
    public void initParams() {

    }

    @Override
    public void setContentView() {
        setContentView(R.layout.activity_login_sms);
    }

    @Override
    public void initView() {

    }

    @Override
    public void initToolbar() {

    }

    @Override
    public void initData() {

    }

    @Override
    public void initListeners() {

    }

    @Override
    public void setClick(View view) {

    }

    @Override
    public void loginSuccess() {

    }

    @Override
    public void loginFailed(String msg) {

    }

    @Override
    public void sendSmsCodeSuccess() {

    }

    @Override
    public void sendSmsCodeError(String msg) {

    }

    @Override
    public void beginTimer() {

    }

    @Override
    public void finishTimer() {

    }

    @Override
    public String getSmsCode() {
        return null;
    }

    @Override
    public String getMobileNo() {
        return null;
    }

    @Override
    public void setPresenter(LoginSmsContract.Presenter presenter) {

    }
}
