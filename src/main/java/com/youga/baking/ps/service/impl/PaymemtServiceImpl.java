package com.youga.baking.ps.service.impl;

import com.youga.baking.ps.dao.AddressDao;
import com.youga.baking.ps.dao.MemberDao;
import com.youga.baking.ps.dao.OrderNotesDao;
import com.youga.baking.ps.dao.impl.AddreessDaoImpl;
import com.youga.baking.ps.dao.impl.MemberDaoImpl;
import com.youga.baking.ps.dao.impl.OrderNotesDaoImpl;
import com.youga.baking.ps.obj.AddreesInfo;
import com.youga.baking.ps.obj.MemberInfo;
import com.youga.baking.ps.obj.OrderNotesBase;
import com.youga.baking.ps.obj.PaymentInfo;
import com.youga.baking.ps.service.PaymentService;
import com.youga.baking.ps.util.EnCodingUtil;
import com.youga.baking.ps.util.OrderIdUtil;

public class PaymemtServiceImpl implements PaymentService {

    OrderNotesDao orderNotesDao = new OrderNotesDaoImpl();
    MemberDao memberDao = new MemberDaoImpl();
    AddressDao addressDao = new AddreessDaoImpl();


    @Override
    public PaymentInfo getPaymentInfo(String openid, String shopid, String orderid, String utfNotes) {
        /***
         *  返回支付信息
         *  1.先将获取到的Notes信息插入到表中
         *  2.将支付信息提取出来（order_amount_total）
         *  3.拉取用户的地址信息；
         *  4.拉取用户信息（会员卡余额信息）；
         */

        PaymentInfo paymemtPage = new PaymentInfo();
        String EnUtfNotes = "";
        //notes 为空保护
        if ( null != utfNotes&&!utfNotes.equals("")){
            EnUtfNotes =   EnCodingUtil.decodeUnicode(utfNotes);
            orderNotesDao.InsertNotes(orderid,EnUtfNotes);
        }
        //2
        paymemtPage.setActAmount(orderNotesDao.getActAmount(orderid));
        //3 获取地址信息
        String addId = addressDao.getIDByDefaultAddress(openid);
        if (null != addId && !addId.equals("")){
            paymemtPage.setAdressId(addId);
            AddreesInfo add = addressDao.getSingleAddress(paymemtPage.getAdressId());
            String address = add.getProvince()+add.getCity()+add.getArea()+add.getAddreesdetail();
            paymemtPage.setAdressOut(address);
        }

        //4.
        MemberInfo member = memberDao.getMemberInfo(openid);
        paymemtPage.setMemberid(member.getMemberID());
        paymemtPage.setMemberBalance(member.getBalance());

        paymemtPage.setOpenid(openid);
        paymemtPage.setOrderId(orderid);

        return paymemtPage;
    }

    @Override
    public String payForOrder(String openid, String shopid, String orderid, String paymentyType) {

        /***
         * 微信支付，暂不开发，还未获取微信支付接口
         */

        String result =null;

        if (paymentyType.equals("1"))
        {
            result = this.payForOrderByWechat(openid,shopid,orderid);
        }
        else if (paymentyType.equals("2"))
        {
            result = this.payForOrderByMemberCard(openid,shopid,orderid);
        }

        return result;

    }

    @Override
    public String payForOrderByWechat(String openid, String shopid, String orderid) {
        String result = null;

        /***
         * 微信支付：
         *  1.获取会员信息
         *  2.获取订单信息
         *  3.会员余额扣除
         *  4.订单信息更新（刷新订单状态，刷入用户默认地址ID,生成物流单ID,刷入支付渠道、若为微信，则支付成功后刷入追踪单号
         *                  、刷入用户支付时间）；
         *  5.刷入订单信息；
         *  --- 2019-07-16更新
         *  6.新增支付成功后商品库存扣减；
         *  --- 2019-07-20
         *  商品支付前应当检查库存，若库存足够扣减再进行金额扣减计算；
         */

        //先判断库存，若库存不足，则返回错误，不能进行订单计算；
        result = orderNotesDao.updateComInstock(orderid);

        if (null!=result)
        {
            String [] res_arr = result.split(",");

            if (res_arr[0].equals("200"))
            {
                MemberInfo memberInfo = memberDao.getMemberInfo(openid);
                OrderNotesBase orderInfo = orderNotesDao.getOrderInfoByOrderID(openid,orderid,shopid);
                memberInfo.settlement(orderInfo.getActAmount());
                orderInfo.settlementByWechatPay(
                        addressDao.getSingleAddress(addressDao.getIDByDefaultAddress(openid)),
                        OrderIdUtil.getlogisticsId());

                orderNotesDao.updatePaymentOrderInfo(orderInfo);
                memberDao.updateMemberBalance(memberInfo);
            }
        }
        return  result;
    }

    @Override
    public String payForOrderByMemberCard(String openid, String shopid, String orderid) {

        String result = null;

        /***
         * 会员卡支付：
         *  1.获取会员信息
         *  2.获取订单信息
         *  3.会员余额扣除
         *  4.订单信息更新（刷新订单状态，刷入用户默认地址ID,生成物流单ID,刷入支付渠道、若为微信，则支付成功后刷入追踪单号
         *                  、刷入用户支付时间）；
         *  5.刷入订单信息；
         *  --- 2019-07-16更新
         *  6.新增支付成功后商品库存扣减；
         *  --- 2019-07-20
         *  商品支付前应当检查库存，若库存足够扣减再进行金额扣减计算；
         */

        //先判断库存，若库存不足，则返回错误，不能进行订单计算；
        result = orderNotesDao.updateComInstock(orderid);



        if (null!=result)
        {
            String [] res_arr = result.split(",");

            if (res_arr[0].equals("200"))
            {
                MemberInfo memberInfo = memberDao.getMemberInfo(openid);
                OrderNotesBase orderInfo = orderNotesDao.getOrderInfoByOrderID(openid,orderid,shopid);
                memberInfo.settlement(orderInfo.getActAmount());
                orderInfo.settlementByMemberCard(
                        addressDao.getSingleAddress(addressDao.getIDByDefaultAddress(openid)),
                        OrderIdUtil.getlogisticsId());

                orderNotesDao.updatePaymentOrderInfo(orderInfo);
                memberDao.updateMemberBalance(memberInfo);
            }
        }
        return  result;
    }

    @Override
    public PaymentInfo getResultPaymentInfo(String openid, String shopid, String orderid) {


        PaymentInfo paymemtPage = new PaymentInfo();
        //2
        paymemtPage.setActAmount(orderNotesDao.getActAmount(orderid));
        //4.
        MemberInfo member = memberDao.getMemberInfo(openid);
        paymemtPage.setMemberid(member.getMemberID());
        paymemtPage.setMemberBalance(member.getBalance());

        paymemtPage.setOpenid(openid);
        paymemtPage.setOrderId(orderid);

        paymemtPage.setTransTime(orderNotesDao.getTransTime(orderid));
        paymemtPage.setShopName("宝塔区河庄坪友佳烘焙");

        return paymemtPage;
    }

    @Override
    public boolean checkIsOrderPayment(String shopid, String orderid) {
        boolean checkstatus = false;

        int status = orderNotesDao.getOrderStatus(shopid,orderid);

        if (status == 1)
        {
            checkstatus = true;
        }
        else if (status == 0)
        {
            checkstatus = false;
        }else if (status == -1)
        {
            checkstatus = false;
        }

        return checkstatus;

    }
}
