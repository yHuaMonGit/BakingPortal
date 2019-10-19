package com.youga.baking.ps.obj;

public class VerifyCode {

    public String code = null;
    public String startTime = null;
    public String openid =null;
    public String veriFlag = null;

    public VerifyCode(String code, String startTime, String openid, String veriFlag) {
        this.code = code;
        this.startTime = startTime;
        this.openid = openid;
        this.veriFlag = veriFlag;
    }
    public VerifyCode(){}

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getOpenid() {
        return openid;
    }

    public void setOpenid(String openid) {
        this.openid = openid;
    }

    public String getVeriFlag() {
        return veriFlag;
    }

    public void setVeriFlag(String veriFlag) {
        this.veriFlag = veriFlag;
    }
}
