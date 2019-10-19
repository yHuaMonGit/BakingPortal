package com.youga.baking.ps.service;

public interface WeChatMessageService {

    /***
     * 2019-07-29
     * 当用户购买成功后，反馈回用户购买成功消息。
     * @param shopid
     * @param orderid
     * @param openid
     * @param openidMD5
     */
    void sendPaymentMsg(String shopid, String orderid, String openid, String openidMD5);
}
