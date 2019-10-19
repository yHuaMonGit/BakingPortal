package com.youga.baking.ps.service;

public interface VerifyService {
    /**
     * 生成验证码
     */
    public void createVerifyCode(String openid);

    /**
     * 发送验证码
     */
    public void sendSMS(String msisdn, String openid);

    /**
     * 校验验证码
     */
    public String checkVerifyCode(String openid, String vcode);
}
