package com.youga.baking.ps.controller;

import com.alibaba.fastjson.JSONArray;
import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;
import com.youga.baking.ps.obj.OrderNotesBase;
import com.youga.baking.ps.obj.PaymentInfo;
import com.youga.baking.ps.obj.WxPament;
import com.youga.baking.ps.obj.WxPaySendData;
import com.youga.baking.ps.service.MemberService;
import com.youga.baking.ps.service.OrderNotesService;
import com.youga.baking.ps.service.PaymentService;
import com.youga.baking.ps.service.WeChatMessageService;
import com.youga.baking.ps.service.impl.MemberServiceImpl;
import com.youga.baking.ps.service.impl.OrderNotesServiceImpl;
import com.youga.baking.ps.service.impl.PaymemtServiceImpl;
import com.youga.baking.ps.service.impl.WeChatMessageServiceImpl;
import com.youga.baking.ps.util.EnCodingUtil;
import com.youga.baking.ps.util.StringUtil;
import com.youga.baking.ps.util.wePay.WxPayUtil;
import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.InputStream;
import java.net.URLEncoder;
import java.util.Map;
import java.util.SortedMap;
import java.util.TreeMap;

@Controller
public class paymentController {

    private final Logger logger = LoggerFactory.getLogger(paymentController.class);

    MemberService mbService = new MemberServiceImpl();
    OrderNotesService orderNotesService = new OrderNotesServiceImpl();

    PaymentService paymentService = new PaymemtServiceImpl();
    WeChatMessageService weChatMessageService = new WeChatMessageServiceImpl();

    @RequestMapping("/submitpayment")
    public String submitpayment(HttpServletRequest req, HttpServletResponse resp, ModelMap model) throws IOException {


        //需要参数列表：
        //1.openid;
        //2.shopid;
        //3.orderid
        //4.notes -- 用户备注

        String openidMD5 = req.getParameter("openid");
        if (openidMD5.endsWith("#")){
            openidMD5 = openidMD5.substring(0,openidMD5.length()-1);
        }
        String shopid = req.getParameter("shopid");
        String orderid = req.getParameter("orderid");
        String notes = req.getParameter("notes");
        String utfNotes = "";
        //中文处理
        if (null!=notes && !notes.equals("")){
            utfNotes = EnCodingUtil.gbEncoding(notes);
        }

        if (shopid.endsWith("#")){
            shopid = shopid.substring(0,shopid.length()-1);
        }
        String openid = mbService.getOpenidByMD5(openidMD5);
        PaymentInfo paymentInfo = paymentService.getPaymentInfo(openid,shopid,orderid,utfNotes);
        //第5个参数 ： 2019-08-07 微信支付支持，新增用户IP
        String userip = req.getParameter("userip");

        String JsonValue = JSONArray.toJSONString(paymentInfo);
        model.addAttribute("pagesInfo",JsonValue);



        /***
         * 2019-08-07
         * 增加微信支付 支付信息预置到cookie
         */

        //创建新的seesion
        HttpSession hs=req.getSession(true);

        //写入cookie逻辑
        try {
            Map<String,String> result = null;

            Float tmpAmount = Float.valueOf(paymentInfo.getActAmount());
            tmpAmount = tmpAmount *100;
            String amount = String.valueOf(tmpAmount.intValue());
            result =  WxPayUtil.getPreparPay_id(new WxPament(openid,
                    userip,
                    orderid,
                    amount));
            {
                if(result.get("result").equals("success"))
                {
                    Cookie appId = new Cookie("appId",URLEncoder.encode(result.get("appId"), "UTF-8"));
                    Cookie timeStamp = new Cookie("timeStamp",URLEncoder.encode(result.get("timeStamp"), "UTF-8"));
                    Cookie nonceStr = new Cookie("nonceStr",URLEncoder.encode(result.get("nonceStr"), "UTF-8"));
                    Cookie packageStr = new Cookie("packageStr",URLEncoder.encode(result.get("packageStr"), "UTF-8"));
                    Cookie sign = new Cookie("sign",URLEncoder.encode(result.get("sign"), "UTF-8"));
                    Cookie signType = new Cookie("signType",URLEncoder.encode(result.get("signType"), "UTF-8"));
//                    Cookie timeStamp = new Cookie("timeStamp",result.get("timeStamp"));
//                    Cookie nonceStr = new Cookie("nonceStr",result.get("nonceStr"));
//                    Cookie packageStr = new Cookie("packageStr",result.get("packageStr"));
//                    Cookie sign = new Cookie("sign",result.get("sign"));
//                    Cookie signType = new Cookie("signType",result.get("signType"));

                    resp.addCookie(appId);
                    resp.addCookie(timeStamp);
                    resp.addCookie(nonceStr);
                    resp.addCookie(packageStr);
                    resp.addCookie(sign);
                    resp.addCookie(signType);

//                    hs.setAttribute("orderid",orderid);
                    Cookie orderidByShopCart = new Cookie("orderidByShopCart",orderid);
                    resp.addCookie(orderidByShopCart);
//                    hs.setAttribute("orderfee",paymentInfo.getActAmount());

//                    resp.getWriter().print("1");
                }else{
                    //若获取prepareid失败则返回，购物车订单不删除;
                    //修改，若返回失败，则返回失败页面
                    logger.error("Get prepareId Failed !");
                    return "prepareidError";
                }
            }
        } catch (Exception e) {
            logger.error(e.getMessage());
            //若订单下失败，则返回,购物车商品不删除
            return "prepareidError";
        }


        return "payment";

    }

    //payCallBack
    @RequestMapping("/payCallBack")
    public void payCallBack(HttpServletRequest request, HttpServletResponse response) throws IOException {


        //当微信支付成功后，在此判断是否交易成功，是否扣款成功；

        /***
         *  1.若扣款成功则将订单状态刷为已支付状态;
         *  2.新增微信支付成功标记字段
         *  wePay_status:
         *          --- 0 :支付成功
         *          --- 1 :支付失败
         *  获取订单状态刷新该状态；
         *  3.新增表 wePayOrder_SelfOrder,用于记录微信支付订单与本地订单关系,完成判定填充该表；
         *  4.重复调用时，判断该订单是否已经支付
         *  5.成功则跳转至成功页面，失败跳转至失败页面；
         */


        StringBuffer url = request.getRequestURL();
        String tempContextUrl = url.delete(url.length() - request.getRequestURI().length(), url.length()).append("/").toString();

        String addressRetrurnUrl = "";
        response.setContentType("text/xml;charset=UTF-8");
        try {
            InputStream is = request.getInputStream();
            String result = IOUtils.toString(is, "UTF-8");
            if("".equals(result)){
                response.getWriter().write("<xm><return_code><![CDATA[FAIL]]></return_code><return_msg><![CDATA[参数错误！]]></return_msg></xml>");
                return ;
            }
            //解析xml
            XStream stream = new XStream(new DomDriver());
            stream.alias("xml", WxPaySendData.class);
            WxPaySendData wxPaySendData = (WxPaySendData)stream.fromXML(result);
            System.out.println(wxPaySendData.toString());

            String appid = wxPaySendData.getAppid();
            String mch_id =wxPaySendData.getMch_id();
            String nonce_str = wxPaySendData.getNonce_str();
            //商户号码用来对内部订单
            String out_trade_no = wxPaySendData.getOut_trade_no();
            //金额用来对账
            String total_fee = wxPaySendData.getTotal_fee();
            //double money = DBUtil.getDBDouble(DBUtil.getDBInt(wxPaySendData.getTotal_fee())/100.0);
            String trade_type = wxPaySendData.getTrade_type();
            //openid要用
            String openid =wxPaySendData.getOpenid();
            //returncode用来检测是否成功
            String return_code = wxPaySendData.getReturn_code();
            String result_code = wxPaySendData.getResult_code();
            String bank_type = wxPaySendData.getBank_type();
            Integer cash_fee = wxPaySendData.getCash_fee();
            String fee_type = wxPaySendData.getFee_type();
            String is_subscribe = wxPaySendData.getIs_subscribe();
            String time_end = wxPaySendData.getTime_end();
            String transaction_id = wxPaySendData.getTransaction_id();
            String sign = wxPaySendData.getSign();

            //签名验证
            SortedMap<String,String> parameters = new TreeMap<String,String>();
            parameters.put("appid",appid);
            parameters.put("mch_id",mch_id);
            parameters.put("nonce_str",nonce_str);
            parameters.put("out_trade_no",out_trade_no);
            parameters.put("total_fee",total_fee);
            parameters.put("trade_type",trade_type);
            parameters.put("openid",openid);
            parameters.put("return_code",return_code);
            parameters.put("result_code",result_code);
            parameters.put("bank_type",bank_type);
            parameters.put("cash_fee",String.valueOf(cash_fee));
            parameters.put("fee_type",fee_type);
            parameters.put("is_subscribe",is_subscribe);
            parameters.put("time_end",time_end);
            parameters.put("transaction_id",transaction_id);
            //以下4个参数针对优惠券(鼓励金之类的)这个坑真的弄了好久
//            parameters.put("coupon_count",wxPaySendData.getCoupon_count());
//            parameters.put("coupon_fee",wxPaySendData.getCoupon_fee());
//            parameters.put("coupon_id_0",wxPaySendData.getCoupon_id_0());
//            parameters.put("coupon_fee_0",wxPaySendData.getCoupon_fee_0());

            String sign2 = WxPayUtil.createSign(parameters);
            String shopid = orderNotesService.getShopidByOrderNo(out_trade_no);

            if(sign.equals(sign2)){//校验签名，两者需要一致，防止别人绕过支付操作，不付钱直接调用你的业务，不然，哈哈，你老板会很开心的 233333.。。。
                if(return_code.equals("SUCCESS") && result_code.equals("SUCCESS")){
                    //业务逻辑(先判断数据库中订单号是否存在，并且订单状态为未支付状态)
                    //do something ...
                    OrderNotesBase order = orderNotesService.getOrderInfoByOrderID(openid,shopid,out_trade_no);

                    if ( order.getOrder_status().equals("0") )
                    {
                        //未支付状态
                        //通知微信.异步确认成功.必写.不然会一直通知后台.八次之后就认为交易失败了.
                        response.getWriter().write("<xml><return_code><![CDATA[SUCCESS]]></return_code><return_msg><![CDATA[OK]]></return_msg></xml>");
                        paymentService.payForOrderByWechat(openid,shopid,out_trade_no);

                        //消息推送
                        String openidMD5 = StringUtil.encryptOpenid(openid);
                        weChatMessageService.sendPaymentMsg(shopid,out_trade_no,openid,openidMD5);

                        //跳转
                        //paymentSuccess

                        addressRetrurnUrl = tempContextUrl+"Portal/paymentSuccess?openid="+openid+"&shopid="+shopid+"&orderid="+out_trade_no;


                    }
                    else if (order.getOrder_status().equals("1"))
                    {
                        //订单为已支付订单
                        //直接返回
                        addressRetrurnUrl = tempContextUrl+"Portal/paymentSuccess?openid="+openid+"&shopid="+shopid+"&orderid="+out_trade_no;


                    }


                }else{
                    response.getWriter().write("<xml><return_code><![CDATA[FAIL]]></return_code><return_msg><![CDATA[交易失败]]></return_msg></xml>");
                }
            }else{
                //通知微信.异步确认成功.必写.不然会一直通知后台.八次之后就认为交易失败了.
                response.getWriter().write("<xml><return_code><![CDATA[FAIL]]></return_code><return_msg><![CDATA[签名校验失败]]></return_msg></xml>");
            }
            response.getWriter().flush();
            response.getWriter().close();
            if(!addressRetrurnUrl.equals(""))
            {
                response.sendRedirect(addressRetrurnUrl);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
        logger.info("pay callback was here ");

    }

    //payOrder
    @RequestMapping("/payOrder")
    public void payOrder(HttpServletRequest req, HttpServletResponse resp) throws IOException {


        //需要参数列表：
        //1.openid;
        //2.shopid;
        //3.orderid
        String openidMD5 = req.getParameter("openid");
        String shopid = req.getParameter("shopid");
        String orderid = req.getParameter("orderid");
        String paymentyType = req.getParameter("paymentyType");
        if (shopid.endsWith("#")){
            shopid = shopid.substring(0,shopid.length()-1);
        }
        String openid = mbService.getOpenidByMD5(openidMD5);

        //给返回值增加UTF8编码格式，防止前端乱码
        resp.setContentType("text/html;charset=UTF-8");

        /***
         * 2019-07-20 新增后台校验，避免用户返回操作以后重复提交表单。
         */
        if (paymentService.checkIsOrderPayment(shopid,orderid))
        {
            /***
             * 若用户已经支付成功，则返回201，前端提示用户已经支付过，并跳转到首页；
             */
            resp.getWriter().print(201);

        }

        String result = paymentService.payForOrder(openid,shopid,orderid,paymentyType);


        if (null == result)
        {
            resp.getWriter().print(500);
        }else
            {
                resp.getWriter().print(result);
                //2019-07-29 新增微信公众号消息推送
                weChatMessageService.sendPaymentMsg(shopid,orderid,openid,openidMD5);
                return;
            }

    }

    //paymentSuccess
    @RequestMapping("/paymentSuccess")
    public String paymentSuccess(HttpServletRequest req, HttpServletResponse resp, ModelMap model) throws IOException {


        //需要参数列表：
        //1.openid;
        //2.shopid;
        //3.orderid

        String openidMD5 = req.getParameter("openid");
        if (openidMD5.endsWith("#")){
            openidMD5 = openidMD5.substring(0,openidMD5.length()-1);
        }
        String shopid = req.getParameter("shopid");
        String orderid = req.getParameter("orderid");

        if (shopid.endsWith("#")){
            shopid = shopid.substring(0,shopid.length()-1);
        }
        String openid = mbService.getOpenidByMD5(openidMD5);
        PaymentInfo paymentInfo = paymentService.getResultPaymentInfo(openid,shopid,orderid);

        String JsonValue = JSONArray.toJSONString(paymentInfo);
        model.addAttribute("pagesInfo",JsonValue);

        return "paymentresult";

    }
}
