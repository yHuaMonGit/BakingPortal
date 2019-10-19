package com.youga.baking.ps.obj;

public class WxInfo {

    public String userName = null;
    public String userId = null;
    public String headimgurl = null;
    public String country = null;
    public String city = null;
    public String province = null;
    public String sex = null;
    //2019-01-02 新增MD5校验码字段，用于openid加密传输
    public String openidMD5 = null;


    public WxInfo(String userName, String userId, String headimgurl, String country, String city, String province, String sex) {
        this.userName = userName;
        this.userId = userId;
        this.headimgurl = headimgurl;
        this.country = country;
        this.city = city;
        this.province = province;
        this.sex = sex;
    }
    public WxInfo(){}

    public String getOpenidMD5() {
        return openidMD5;
    }

    public void setOpenidMD5(String openidMD5) {
        this.openidMD5 = openidMD5;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getHeadimgurl() {
        return headimgurl;
    }

    public void setHeadimgurl(String headimgurl) {
        this.headimgurl = headimgurl;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }
}
