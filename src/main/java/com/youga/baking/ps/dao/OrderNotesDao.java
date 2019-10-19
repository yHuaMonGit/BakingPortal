package com.youga.baking.ps.dao;

import com.youga.baking.ps.obj.ComDetailBase;
import com.youga.baking.ps.obj.OrderNotesBase;

import java.util.List;

public interface OrderNotesDao {


    /***
     * 创建新的preOrder并返回
     * @param openid
     * @param orderId
     * @param orderCreateTime
     * @param shopid
     * @return
     */
    OrderNotesBase createNewPreOrder(String openid, String orderId, String orderCreateTime, String shopid);

    /***
     * 获取OrderId对应的商品信息，此表需要建立分区
     * @param order_no
     * @return
     */
    List<ComDetailBase> getOrderComList(String order_no);

    /***
     * Dao层实现
     * @param openid
     * @return
     */
    boolean isShoppingCartNull(String openid);

    /***
     * 调度存储过程实现
     * @param openid
     * @param orderid
     * @return
     */
    boolean cancelOrder(String openid, String orderid);

    /****
     * 将计算好的Amount金额刷入表里
     * @param orderId
     * @param actAmount
     */
    void InsertAmount(String orderId, String actAmount);

    /***
     * 填入用户备注
     * @param orderid
     * @param utfNotes
     */
    void InsertNotes(String orderid, String utfNotes);

    /***
     * 获取实际扣减金额
     * @param orderid
     * @return
     */
    String getActAmount(String orderid);

    /****
     * 获取order信息
     * @param openid
     * @param orderid
     * @param shopid
     * @return
     */
    OrderNotesBase getOrderInfoByOrderID(String openid, String orderid, String shopid);

    /***
     * 刷入订单信息
     * @param orderInfo
     */
    void updatePaymentOrderInfo(OrderNotesBase orderInfo);

    /***
     * 获取
     * @param orderid
     * @return
     */
    String getTransTime(String orderid);

    /***
     * 返回一个用户所有的orderlist信息
     * @param openid
     * @return
     */
    List<OrderNotesBase> getTotalOrderList(String openid);

    /***
     * 2019-07-16 新增功能
     * 通过存储过程刷新支付订单后商品库存数据
     * @param orderid
     */
    String updateComInstock(String orderid);

    /***
     * 2019-07-20 返回订单状态
     * @param shopid
     * @param orderid
     * @return
     */
    int getOrderStatus(String shopid, String orderid);

    /****
     * 2019-08-02 用户确认订单已经收货
     * 存储过程实现，若用户已经确认收货，则返回501，若没有，更改状态并返回200
     * @param openid
     * @param shopid
     * @param orderid
     * @return
     */
    String confirmOrder(String openid, String shopid, String orderid);

    /***
     * Dao层实现
     * @param orderid
     * @return
     */
    String getShopidByOrderNo(String orderid);
}
