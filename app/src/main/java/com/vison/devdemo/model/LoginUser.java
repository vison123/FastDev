package com.vison.devdemo.model;


import java.sql.Date;

/**
 * Created by vison on 16/6/30.
 */
public class LoginUser {
    private int userId;
    private String address;
    private String realName;
    private String weChatNo;
    private String email;
    private String nameCardFileUrl;
    private String qqNo;
    private String department;
    private String mobileNumber;
    private String jobTitle;
    private String phoneNumber;
    private String photoFileUrl;
    private boolean publicTitle;
    private boolean publicMobile;
    private boolean publicDepart;
    private boolean publicPhone;
    private boolean publicEmail;
    private boolean publicAddress;
    private boolean publicWeChat;
    private boolean publicQQ;
    private int orgId;
    private Date lastLoginTime; // 本地增加,用于多用户登陆排序
    private String token;
    private Date lastSyncTime;
    private boolean certified;
    private String friendList;

    public LoginUser(boolean publicQQ, int userId, String address, String realName, String weChatNo,
                     String email, String nameCardFileUrl, String qqNo, String department, String mobileNumber,
                     String jobTitle, String phoneNumber, String photoFileUrl, boolean publicTitle,
                     boolean publicMobile, boolean publicDepart, boolean publicPhone, boolean publicEmail,
                     boolean publicAddress, boolean publicWeChat, int orgId, Date lastLoginTime,
                     String token, Date lastSyncTime, boolean certified, String friendList) {
        this.publicQQ = publicQQ;
        this.userId = userId;
        this.address = address;
        this.realName = realName;
        this.weChatNo = weChatNo;
        this.email = email;
        this.nameCardFileUrl = nameCardFileUrl;
        this.qqNo = qqNo;
        this.department = department;
        this.mobileNumber = mobileNumber;
        this.jobTitle = jobTitle;
        this.phoneNumber = phoneNumber;
        this.photoFileUrl = photoFileUrl;
        this.publicTitle = publicTitle;
        this.publicMobile = publicMobile;
        this.publicDepart = publicDepart;
        this.publicPhone = publicPhone;
        this.publicEmail = publicEmail;
        this.publicAddress = publicAddress;
        this.publicWeChat = publicWeChat;
        this.orgId = orgId;
        this.lastLoginTime = lastLoginTime;
        this.token = token;
        this.lastSyncTime = lastSyncTime;
        this.certified = certified;
        this.friendList = friendList;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getRealName() {
        return realName;
    }

    public void setRealName(String realName) {
        this.realName = realName;
    }

    public String getWeChatNo() {
        return weChatNo;
    }

    public void setWeChatNo(String weChatNo) {
        this.weChatNo = weChatNo;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getNameCardFileUrl() {
        return nameCardFileUrl;
    }

    public void setNameCardFileUrl(String nameCardFileUrl) {
        this.nameCardFileUrl = nameCardFileUrl;
    }

    public String getQqNo() {
        return qqNo;
    }

    public void setQqNo(String qqNo) {
        this.qqNo = qqNo;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public String getMobileNumber() {
        return mobileNumber;
    }

    public void setMobileNumber(String mobileNumber) {
        this.mobileNumber = mobileNumber;
    }

    public String getJobTitle() {
        return jobTitle;
    }

    public void setJobTitle(String jobTitle) {
        this.jobTitle = jobTitle;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getPhotoFileUrl() {
        return photoFileUrl;
    }

    public void setPhotoFileUrl(String photoFileUrl) {
        this.photoFileUrl = photoFileUrl;
    }

    public boolean isPublicTitle() {
        return publicTitle;
    }

    public void setPublicTitle(boolean publicTitle) {
        this.publicTitle = publicTitle;
    }

    public boolean isPublicMobile() {
        return publicMobile;
    }

    public void setPublicMobile(boolean publicMobile) {
        this.publicMobile = publicMobile;
    }

    public boolean isPublicDepart() {
        return publicDepart;
    }

    public void setPublicDepart(boolean publicDepart) {
        this.publicDepart = publicDepart;
    }

    public boolean isPublicPhone() {
        return publicPhone;
    }

    public void setPublicPhone(boolean publicPhone) {
        this.publicPhone = publicPhone;
    }

    public boolean isPublicEmail() {
        return publicEmail;
    }

    public void setPublicEmail(boolean publicEmail) {
        this.publicEmail = publicEmail;
    }

    public boolean isPublicAddress() {
        return publicAddress;
    }

    public void setPublicAddress(boolean publicAddress) {
        this.publicAddress = publicAddress;
    }

    public boolean isPublicWeChat() {
        return publicWeChat;
    }

    public void setPublicWeChat(boolean publicWeChat) {
        this.publicWeChat = publicWeChat;
    }

    public boolean isPublicQQ() {
        return publicQQ;
    }

    public void setPublicQQ(boolean publicQQ) {
        this.publicQQ = publicQQ;
    }

    public int getOrgId() {
        return orgId;
    }

    public void setOrgId(int orgId) {
        this.orgId = orgId;
    }

    public Date getLastLoginTime() {
        return lastLoginTime;
    }

    public void setLastLoginTime(Date lastLoginTime) {
        this.lastLoginTime = lastLoginTime;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public Date getLastSyncTime() {
        return lastSyncTime;
    }

    public void setLastSyncTime(Date lastSyncTime) {
        this.lastSyncTime = lastSyncTime;
    }

    public boolean isCertified() {
        return certified;
    }

    public void setCertified(boolean certified) {
        this.certified = certified;
    }

    public String getFriendList() {
        return friendList;
    }

    public void setFriendList(String friendList) {
        this.friendList = friendList;
    }

    @Override
    public String toString() {
        return "LoginUser{" +
                "userId=" + userId +
                ", address='" + address + '\'' +
                ", realName='" + realName + '\'' +
                ", weChatNo='" + weChatNo + '\'' +
                ", email='" + email + '\'' +
                ", nameCardFileUrl='" + nameCardFileUrl + '\'' +
                ", qqNo='" + qqNo + '\'' +
                ", department='" + department + '\'' +
                ", mobileNumber='" + mobileNumber + '\'' +
                ", jobTitle='" + jobTitle + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", photoFileUrl='" + photoFileUrl + '\'' +
                ", publicTitle=" + publicTitle +
                ", publicMobile=" + publicMobile +
                ", publicDepart=" + publicDepart +
                ", publicPhone=" + publicPhone +
                ", publicEmail=" + publicEmail +
                ", publicAddress=" + publicAddress +
                ", publicWeChat=" + publicWeChat +
                ", publicQQ=" + publicQQ +
                ", orgId=" + orgId +
                ", lastLoginTime=" + lastLoginTime +
                ", token='" + token + '\'' +
                ", lastSyncTime=" + lastSyncTime +
                ", certified=" + certified +
                ", friendList='" + friendList + '\'' +
                '}';
    }
}
