package com.youga.baking.ps.service;

import com.youga.baking.ps.obj.PaymentInfo;

public interface PaymentService {

    /***
     * 初始化页面时，同步提交表单数据，且获取页面需要的信息
     * @param openid
     * @param shopid
     * @param orderid
     * @param utfNotes
     * @return
     */
    PaymentInfo getPaymentInfo(String openid, String shopid, String orderid, String utfNotes);

    /***
     * 支付逻辑，包含支付类型，微信支付或会员卡支付
     * @param openid
     * @param shopid
     * @param orderid
     * @param paymentyType
     * @return
     */
    String payForOrder(String openid, String shopid, String orderid, String paymentyType);

    /***
     * 微信支付
     * @param openid
     * @param shopid
     * @param orderid
     * @return
     */
    String payForOrderByWechat(String openid, String shopid, String orderid);

    /***
     * 会员卡支付
     * @param openid
     * @param shopid
     * @param orderid
     * @return
     */
    String payForOrderByMemberCard(String openid, String shopid, String orderid);

    /***
     * 获取结果页面
     * @param openid
     * @param shopid
     * @param orderid
     * @return
     */
    PaymentInfo getResultPaymentInfo(String openid, String shopid, String orderid);

    /****
     * 2019-07-20 新增功能 检查用户订单是否已经付款；
     * @param shopid
     * @param orderid
     * @return
     */
    boolean checkIsOrderPayment(String shopid, String orderid);
}
