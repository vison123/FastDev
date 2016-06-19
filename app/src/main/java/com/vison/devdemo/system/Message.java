package com.vison.devdemo.system;

/**
 * Created by vison on 2016/5/2.
 */
public class Message {
    int msgCode;
    String msgContent;

    public Message(int msgCode, String msgContent) {
        this.msgCode = msgCode;
        this.msgContent = msgContent;
    }

    public int getMsgCode() {
        return msgCode;
    }

    public String getMsgContent() {
        return msgContent;
    }

    public void setMsgCode(int msgCode) {
        this.msgCode = msgCode;
    }

    public void setMsgContent(String msgContent) {
        this.msgContent = msgContent;
    }
}
