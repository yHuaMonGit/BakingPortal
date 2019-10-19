package com.youga.baking.ps.obj;

public class PaymentInfo {

    /***
     * 支付信息封装
     */

    public String actAmount = "";
    public String orderId = "";
    public String AdressOut = "";
    public String AdressId = "";
    public String openid = "";
    public String memberid = "";
    public String memberBalance = "";

    //新增字段
    public String ShopName = "";
    public String transTime = "";


    public PaymentInfo(String actAmount, String adressOut, String adressId, String openid, String memberid) {
        this.actAmount = actAmount;
        AdressOut = adressOut;
        AdressId = adressId;
        this.openid = openid;
        this.memberid = memberid;
    }

    public String getShopName() {
        return ShopName;
    }

    public void setShopName(String shopName) {
        ShopName = shopName;
    }

    public String getTransTime() {
        return transTime;
    }

    public void setTransTime(String transTime) {
        this.transTime = transTime;
    }

    public PaymentInfo() {

    }

    public String getMemberBalance() {
        return memberBalance;
    }

    public void setMemberBalance(String memberBalance) {
        this.memberBalance = memberBalance;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public String getActAmount() {
        return actAmount;
    }

    public void setActAmount(String actAmount) {
        this.actAmount = actAmount;
    }

    public String getAdressOut() {
        return AdressOut;
    }

    public void setAdressOut(String adressOut) {
        AdressOut = adressOut;
    }

    public String getAdressId() {
        return AdressId;
    }

    public void setAdressId(String adressId) {
        AdressId = adressId;
    }

    public String getOpenid() {
        return openid;
    }

    public void setOpenid(String openid) {
        this.openid = openid;
    }

    public String getMemberid() {
        return memberid;
    }

    public void setMemberid(String memberid) {
        this.memberid = memberid;
    }

    @Override
    public String toString() {
        return "PaymentInfo{" +
                "actAmount='" + actAmount + '\'' +
                ", AdressOut='" + AdressOut + '\'' +
                ", AdressId='" + AdressId + '\'' +
                ", openid='" + openid + '\'' +
                ", memberid='" + memberid + '\'' +
                '}';
    }
}
