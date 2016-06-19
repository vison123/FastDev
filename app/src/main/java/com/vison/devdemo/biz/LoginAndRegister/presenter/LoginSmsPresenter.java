package com.vison.devdemo.biz.LoginAndRegister.presenter;

import com.alibaba.fastjson.JSONObject;
import com.vison.devdemo.api.AppLink;
import com.vison.devdemo.biz.LoginAndRegister.contract.LoginSmsContract;
import com.orhanobut.logger.Logger;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import okhttp3.Call;
import okhttp3.Request;

/**
 * Created by vison on 16/5/29.
 */
public class LoginSmsPresenter implements LoginSmsContract.Presenter  {

    private final LoginSmsContract.View mView;

    public LoginSmsPresenter(LoginSmsContract.View mView) {
        this.mView = mView;
        //if the view is fragment,next is needed
        // mView.setPresenter(this);
    }

    @Override
    public void sendSmsCode() {
        String url = AppLink.SEND_SMS_LOGIN_MOBILE;
        OkHttpUtils
                .post()
                .url(url)
                .addParams("mobileNo", mView.getMobileNo())
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e) {
                        mView.sendSmsCodeError("短信发送错误!");
                    }

                    @Override
                    public void onResponse(String response) {
                        Logger.d(response);
                        if (response.startsWith("{")) {
                            JSONObject jsonObject = (JSONObject) JSONObject.parse(response);
                            if (jsonObject.containsKey("msgContent")) {
                                mView.sendSmsCodeError(jsonObject.getString("msgContent"));
                            }
                        } else if (response.equals("")) {
                            //解析登录信息保存到数据库
                            mView.sendSmsCodeSuccess();
                        } else {
                            mView.sendSmsCodeError("短信发送错误!");
                        }
                    }

                    @Override
                    public void onBefore(Request request) {
                        super.onBefore(request);
                    }

                    @Override
                    public void onAfter() {
                        super.onAfter();
                    }
                });
    }

    @Override
    public void login() {

    }

    @Override
    public void start() {

    }
}
