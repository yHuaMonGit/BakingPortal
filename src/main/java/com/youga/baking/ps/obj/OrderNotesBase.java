package com.youga.baking.ps.obj;

import com.youga.baking.ps.util.DateUtil;

import java.util.ArrayList;
import java.util.List;

public class OrderNotesBase {

    /***
     * 订单信息封装
     */

    public String order_no = null;
    public String shop_id = null;
    public String order_status = null;
    public String product_count = null;
    public String product_amount_total = null;
    public String order_amount_total = null;
    public String logistics_fee = null;
    public String address_id = null;
    public String orderlogistics_id = null;
    public String pay_channel = null;
    public String out_trade_no = null;
    public String createTime = null;
    public String payment_time = null;
    public String send_time = null;
    public String member_id = null;
    public String openid = null;
    public String user_remark = null;
    public String order_settlement_time = null;

    /***
     * 会员折扣相关信息存储
     */
    public String memberoff = null;
    public String memberOffAmount = null;
    public String actAmount = null;


    /***
     * 商品信息集合，也要封装在这个对象中，在加载页面、及未来加载历史记录时，都需要使用
     */
    List<ComDetailBase> comList = new ArrayList<>();


   //order_no,shop_id,order_status,product_count,product_amount_total,createTime,member_id,openid


    /***
     * 预订单生成时的构造方法
     * @param order_no
     * @param shop_id
     * @param order_status
     * @param product_count
     * @param product_amount_total
     * @param createTime
     * @param member_id
     * @param openid
     */
    public OrderNotesBase(String order_no, String shop_id, String order_status, String product_count, String product_amount_total, String createTime, String member_id, String openid) {
        this.order_no = order_no;
        this.shop_id = shop_id;
        this.order_status = order_status;
        this.product_count = product_count;
        this.product_amount_total = product_amount_total;
        this.createTime = createTime;
        this.member_id = member_id;
        this.openid = openid;

    }


    public String getMemberoff() {
        return memberoff;
    }

    public void setMemberoff(String memberoff) {
        this.memberoff = memberoff;
    }

    public String getMemberOffAmount() {
        return memberOffAmount;
    }

    public void setMemberOffAmount(String memberOffAmount) {
        this.memberOffAmount = memberOffAmount;
    }

    public String getActAmount() {
        return actAmount;
    }

    public void setActAmount(String actAmount) {
        this.actAmount = actAmount;
    }

    public List<ComDetailBase> getComList() {
        return comList;
    }

    public void setComList(List<ComDetailBase> comList) {
        this.comList = comList;
    }

    public String getOrder_no() {
        return order_no;
    }

    public void setOrder_no(String order_no) {
        this.order_no = order_no;
    }

    public String getShop_id() {
        return shop_id;
    }

    public void setShop_id(String shop_id) {
        this.shop_id = shop_id;
    }

    public String getOrder_status() {
        return order_status;
    }

    public void setOrder_status(String order_status) {
        this.order_status = order_status;
    }

    public String getProduct_count() {
        return product_count;
    }

    public void setProduct_count(String product_count) {
        this.product_count = product_count;
    }

    public String getProduct_amount_total() {
        return product_amount_total;
    }

    public void setProduct_amount_total(String product_amount_total) {
        this.product_amount_total = product_amount_total;
    }

    public String getOrder_amount_total() {
        return order_amount_total;
    }

    public void setOrder_amount_total(String order_amount_total) {
        this.order_amount_total = order_amount_total;
    }

    public String getLogistics_fee() {
        return logistics_fee;
    }

    public void setLogistics_fee(String logistics_fee) {
        this.logistics_fee = logistics_fee;
    }

    public String getAddress_id() {
        return address_id;
    }

    public void setAddress_id(String address_id) {
        this.address_id = address_id;
    }

    public String getOrderlogistics_id() {
        return orderlogistics_id;
    }

    public void setOrderlogistics_id(String orderlogistics_id) {
        this.orderlogistics_id = orderlogistics_id;
    }

    public String getPay_channel() {
        return pay_channel;
    }

    public void setPay_channel(String pay_channel) {
        this.pay_channel = pay_channel;
    }

    public String getOut_trade_no() {
        return out_trade_no;
    }

    public void setOut_trade_no(String out_trade_no) {
        this.out_trade_no = out_trade_no;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getPayment_time() {
        return payment_time;
    }

    public void setPayment_time(String payment_time) {
        this.payment_time = payment_time;
    }

    public String getSend_time() {
        return send_time;
    }

    public void setSend_time(String send_time) {
        this.send_time = send_time;
    }

    public String getMember_id() {
        return member_id;
    }

    public void setMember_id(String member_id) {
        this.member_id = member_id;
    }

    public String getOpenid() {
        return openid;
    }

    public void setOpenid(String openid) {
        this.openid = openid;
    }

    public String getUser_remark() {
        return user_remark;
    }

    public void setUser_remark(String user_remark) {
        this.user_remark = user_remark;
    }

    public String getOrder_settlement_time() {
        return order_settlement_time;
    }

    public void setOrder_settlement_time(String order_settlement_time) {
        this.order_settlement_time = order_settlement_time;
    }

    @Override
    public String toString() {
        return "OrderNotesBase{" +
                "order_no='" + order_no + '\'' +
                ", shop_id='" + shop_id + '\'' +
                ", order_status='" + order_status + '\'' +
                ", product_count='" + product_count + '\'' +
                ", product_amount_total='" + product_amount_total + '\'' +
                ", order_amount_total='" + order_amount_total + '\'' +
                ", logistics_fee='" + logistics_fee + '\'' +
                ", address_id='" + address_id + '\'' +
                ", orderlogistics_id='" + orderlogistics_id + '\'' +
                ", pay_channel='" + pay_channel + '\'' +
                ", out_trade_no='" + out_trade_no + '\'' +
                ", createTime='" + createTime + '\'' +
                ", payment_time='" + payment_time + '\'' +
                ", send_time='" + send_time + '\'' +
                ", member_id='" + member_id + '\'' +
                ", openid='" + openid + '\'' +
                ", user_remark='" + user_remark + '\'' +
                ", order_settlement_time='" + order_settlement_time + '\'' +
                '}';
    }

    /***
     * 计算自身立减金额以及合计金额
     */
    public void culFinalAmount() {

        float inOffAmount = 0f;
        float actAmount = 0f;
        float memberDecAmount = 0f;

        for (ComDetailBase c : this.comList){
            if ( 1 == c.getIsOffCom() ){
                //2019-07-17 商品打折价钱需要乘以商品计量；
                inOffAmount += c.getCom_price()*c.getCom_num();
            }
        }

        memberDecAmount = inOffAmount * (1-Float.valueOf(this.getMemberoff()));
        this.setMemberOffAmount(String.format("%.2f",memberDecAmount));
        actAmount = Float.valueOf(this.getProduct_amount_total()) - memberDecAmount;
        this.setActAmount(String.format("%.2f",actAmount));
    }

    /***
     * 会员卡消费，刷新订单信息
     * @param singleAddress
     * @param logicid
     */
    public void settlementByMemberCard(AddreesInfo singleAddress, String logicid) {

        //刷新订单状态，刷入用户默认地址ID,生成物流单ID,刷入支付渠道、若为微信，则支付成功后刷入追踪单号
        //         *                  、刷入用户支付时间
        this.setOrder_status("1");
        this.setAddress_id(singleAddress.getAddressid());
        this.setOrderlogistics_id(logicid);
        this.setPay_channel("2");
        this.setPayment_time(DateUtil.getNowTimeByFormatA());

    }

    public void settlementByWechatPay(AddreesInfo singleAddress, String logicid) {

        //刷新订单状态，刷入用户默认地址ID,生成物流单ID,刷入支付渠道、若为微信，则支付成功后刷入追踪单号
        //         *                  、刷入用户支付时间
        this.setOrder_status("1");
        this.setAddress_id(singleAddress.getAddressid());
        this.setOrderlogistics_id(logicid);
        this.setPay_channel("1");
        this.setPayment_time(DateUtil.getNowTimeByFormatA());

    }
}
