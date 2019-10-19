package com.youga.baking.ps.service.impl;

import com.youga.baking.ps.dao.MemberDao;
import com.youga.baking.ps.dao.OrderNotesDao;
import com.youga.baking.ps.dao.impl.MemberDaoImpl;
import com.youga.baking.ps.dao.impl.OrderNotesDaoImpl;
import com.youga.baking.ps.obj.MemberInfo;
import com.youga.baking.ps.obj.OrderNotesBase;
import com.youga.baking.ps.service.OrderNotesService;
import com.youga.baking.ps.util.DateUtil;
import com.youga.baking.ps.util.OrderIdUtil;

import java.util.List;

public class OrderNotesServiceImpl implements OrderNotesService {

    MemberDao memberDao = new MemberDaoImpl();
    OrderNotesDao orderNotesDao = new OrderNotesDaoImpl();

    @Override
    public OrderNotesBase createNewPreOrder(String openid, String shopid) {

        //创建OrderID
        String orderId = OrderIdUtil.getOrderId();
        //创建Order的时间
        String orderCreateTime = DateUtil.getNowTimeByFormatA();

        OrderNotesBase preOrder =  orderNotesDao.createNewPreOrder(openid,orderId,orderCreateTime,shopid);

        /**
         * 新增字段 :
         *  1.memberOff
         *  2.actAcount
         *  3.isOffGoods -> comBaseInfo
         */
        //从用户表获取用户折扣等信息；

        MemberInfo memberInfo = memberDao.getMemberInfo(openid);
        preOrder.setMemberoff(memberInfo.getGoodsOff());
        preOrder.culFinalAmount();

        orderNotesDao.InsertAmount(orderId,preOrder.getActAmount());

        return preOrder;

    }

    /***
     * 若shoppincart有记录，则返回false,为空则返回true
     * @param openid
     * @return
     */
    @Override
    public boolean isShoppingCartNull(String openid) {
        return orderNotesDao.isShoppingCartNull(openid);
    }

    /***
     * 删除，调度存储过程
     * @param openid
     * @param shopid
     * @param orderid
     * @return
     */
    @Override
    public boolean cancelOrder(String openid, String shopid, String orderid) {
        return orderNotesDao.cancelOrder(openid,orderid);
    }

    @Override
    public OrderNotesBase getOrderInfoByOrderID(String openid, String shopid, String orderid) {

        //生成preOrder
        OrderNotesBase preOrder =  orderNotesDao.getOrderInfoByOrderID(openid,orderid,shopid);

        preOrder.setActAmount(orderNotesDao.getActAmount(orderid));

        Float decAmount = Float.valueOf(preOrder.getProduct_amount_total()) - Float.valueOf(preOrder.getActAmount());

        preOrder.setMemberOffAmount(String.format("%.2f",decAmount));

        return preOrder;

    }

    @Override
    public List<OrderNotesBase> getTotalOrderList(String openid) {
        return orderNotesDao.getTotalOrderList(openid);
    }

    @Override
    public String confirmOrder(String openid, String shopid, String orderid) {
        return orderNotesDao.confirmOrder(openid,shopid,orderid);
    }

    @Override
    public String getShopidByOrderNo(String orderid) {
        return orderNotesDao.getShopidByOrderNo(orderid);
    }
}
