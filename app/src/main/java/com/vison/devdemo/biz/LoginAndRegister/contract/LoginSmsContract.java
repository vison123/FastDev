package com.vison.devdemo.biz.LoginAndRegister.contract;

import com.vison.devdemo.system.BasePresenter;
import com.vison.devdemo.system.BaseView;

/**
 * Created by vison on 2016/5/1.
 * 契约类
 */
public class LoginSmsContract {

    public interface View extends BaseView<Presenter> {
        void loginSuccess();
        void loginFailed(String msg);
        void sendSmsCodeSuccess();
        void sendSmsCodeError(String msg);
        void beginTimer();
        void finishTimer();
        String getSmsCode();
        String getMobileNo();
    }
    public interface Presenter extends BasePresenter{
        void sendSmsCode();
        void login();
    }
}
