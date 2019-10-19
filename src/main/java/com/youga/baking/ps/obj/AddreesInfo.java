package com.youga.baking.ps.obj;

public class AddreesInfo {

    String addressid ;
    String openid ;
    String msisdn ;
    String username ;
    String province;
    String city ;
    String area ;
    String addreesdetail;
    String ecode;

    //默认地址标识
    String isChoice;

    public String getIsChoice() {
        return isChoice;
    }

    public void setIsChoice(String isChoice) {
        this.isChoice = isChoice;
    }

    public AddreesInfo(String addressid, String openid, String msisdn, String username, String province, String city, String area, String addreesdetail, String ecode) {
        this.addressid = addressid;
        this.openid = openid;
        this.msisdn = msisdn;
        this.username = username;
        this.province = province;
        this.city = city;
        this.area = area;
        this.addreesdetail = addreesdetail;
        this.ecode = ecode;
    }

    public AddreesInfo() {

    }

    public String getAddressid() {
        return addressid;
    }

    public void setAddressid(String addressid) {
        this.addressid = addressid;
    }

    public String getOpenid() {
        return openid;
    }

    public void setOpenid(String openid) {
        this.openid = openid;
    }

    public String getMsisdn() {
        return msisdn;
    }

    public void setMsisdn(String msisdn) {
        this.msisdn = msisdn;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public String getAddreesdetail() {
        return addreesdetail;
    }

    public void setAddreesdetail(String addreesdetail) {
        this.addreesdetail = addreesdetail;
    }

    public String getEcode() {
        return ecode;
    }

    public void setEcode(String ecode) {
        this.ecode = ecode;
    }
}
