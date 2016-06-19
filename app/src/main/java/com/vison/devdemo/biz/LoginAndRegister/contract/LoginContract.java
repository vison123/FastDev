package com.vison.devdemo.biz.LoginAndRegister.contract;

import com.vison.devdemo.system.BasePresenter;
import com.vison.devdemo.system.BaseView;

/**
 * Created by vison on 2016/5/1.
 * 契约类
 */
public class LoginContract {

    public interface View extends BaseView<Presenter> {
        void sendSmsCodeError(String msg);
        void sendSmsCodeSuccess();
        String getMobileNo();
    }

    public interface Presenter extends BasePresenter{
        void sendSmsCode();
    }
}
