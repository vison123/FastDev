package com.vison.devdemo.api;

/**
 * Created by Administrator on 2016/5/2.
 */
public class AppLink {
    public static String API = "/app/api";
    public static String PUB = "/app/pub";
    public static String SEND_SMS_LOGIN_MOBILE = Config.HOST + PUB + "/sendSmsCodeToLoginMobile";
    public static String LOGIN = Config.HOST + PUB +  "/login";
    public static String SENDSMSCODETOLOGINMOBILE = Config.HOST + PUB + "/sendSmsCodeToLoginMobile";
    public static String SENDSMSCODETOREGISTERMOBILE = Config.HOST + PUB + "/app/pub/sendSmsCodeToRegisterMobile";
    public static String VALIDATESMSCODE = Config.HOST + PUB +"/validatesmscode";
    public static String VALIDATEEMAIL = Config.HOST + PUB +"/validateemail";
    public static String LOGOUT = Config.HOST + API +"/Account/logout";
    public static String REGISTERPROTOCAL = Config.HOST + "/protocal.html";
    public static String REGISTER = Config.HOST + PUB +"/register";
    public static String UPLOADFILE = Config.HOST + PUB +"/File/upload";
    public static String DOWNLOADFILE = Config.HOST + PUB +"/File/upload";
}
