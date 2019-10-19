package com.youga.baking.ps.obj;

public class WxPament {

    String openid;


    String userip;
    String orderid;
    String amount;


    public WxPament(String openid, String userip, String orderid, String amount) {
        this.openid = openid;
        this.userip = userip;
        this.orderid = orderid;
        this.amount = amount;
    }


    public String getOpenid() {
        return openid;
    }

    public void setOpenid(String openid) {
        this.openid = openid;
    }

    public String getUserip() {
        return userip;
    }

    public void setUserip(String userip) {
        this.userip = userip;
    }

    public String getOrderid() {
        return orderid;
    }

    public void setOrderid(String orderid) {
        this.orderid = orderid;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }
}
