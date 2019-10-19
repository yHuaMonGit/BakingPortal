package com.youga.baking.ps.service;

import com.youga.baking.ps.obj.OrderNotesBase;

import java.util.List;

public interface OrderNotesService {

    /***
     * 生成预订单
     * @param openid
     * @param shopid
     * @return
     */
    OrderNotesBase createNewPreOrder(String openid, String shopid);


    /***
     * 点击保护，若用户购物车内没有商品，不进行结算
     * @param openid
     * @return
     */
    boolean isShoppingCartNull(String openid);

    /***
     * 删除订单，删除order及对应的商品对应表
     * @param openid
     * @param shopid
     * @param orderid
     * @return
     */
    boolean cancelOrder(String openid, String shopid, String orderid);

    /****
     * 获取Order信息
     * @param openid
     * @param shopid
     * @param orderid
     * @return
     */
    OrderNotesBase getOrderInfoByOrderID(String openid, String shopid, String orderid);

    /***
     * 获取orderlist
     * @param openid
     * @return
     */
    List<OrderNotesBase> getTotalOrderList(String openid);

    /***
     * 2019-08-02
     * 用户确认收货
     * @param openid
     * @param shopid
     * @param orderid
     * @return
     */
    String confirmOrder(String openid, String shopid, String orderid);

    /****
     * 通过orderId来获取SHopid
     * @param out_trade_no
     * @return
     */
    String getShopidByOrderNo(String out_trade_no);
}
