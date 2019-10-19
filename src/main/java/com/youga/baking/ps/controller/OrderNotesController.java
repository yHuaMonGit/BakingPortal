package com.youga.baking.ps.controller;

import com.alibaba.fastjson.JSONArray;
import com.youga.baking.ps.obj.OrderNotesBase;
import com.youga.baking.ps.service.MemberService;
import com.youga.baking.ps.service.OrderNotesService;
import com.youga.baking.ps.service.impl.MemberServiceImpl;
import com.youga.baking.ps.service.impl.OrderNotesServiceImpl;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class OrderNotesController {

    MemberService mbService = new MemberServiceImpl();

    OrderNotesService orderNotesService = new OrderNotesServiceImpl();

    @RequestMapping("/ordernotes")
    public String ordernotes(HttpServletRequest req, HttpServletResponse resp, ModelMap model) throws IOException {

        //需要参数列表：
        //1.openid;
        //2.shopid;
        String openidMD5 = req.getParameter("openid");
        if (openidMD5.endsWith("#")){
            openidMD5 = openidMD5.substring(0,openidMD5.length()-1);
        }
        String shopid = req.getParameter("shopid");
        if (shopid.endsWith("#")){
            shopid = shopid.substring(0,shopid.length()-1);
        }
        String openid = mbService.getOpenidByMD5(openidMD5);


        if (orderNotesService.isShoppingCartNull(openid)){
            return "ordernotesWithoutCom";
        }else {
            //生成用户订单
            OrderNotesBase orderNotesBase = orderNotesService.createNewPreOrder(openid,shopid);

            //返回数据：
            String JsonValue = JSONArray.toJSONString(orderNotesBase);
            model.addAttribute("pagesInfo",JsonValue);
        }

        return "ordernotes";
    }


    @RequestMapping("/showOrder")
    public String showOrder(HttpServletRequest req, HttpServletResponse resp, ModelMap model) throws IOException {

        //需要参数列表：
        //1.openid;
        //2.shopid;
        //3.orderid;
        String openidMD5 = req.getParameter("openid");
        if (openidMD5.endsWith("#")){
            openidMD5 = openidMD5.substring(0,openidMD5.length()-1);
        }
        String shopid = req.getParameter("shopid");
        if (shopid.endsWith("#")){
            shopid = shopid.substring(0,shopid.length()-1);
        }
        String openid = mbService.getOpenidByMD5(openidMD5);
        String orderid = req.getParameter("orderid");

        //生成用户订单
        OrderNotesBase orderNotesBase = orderNotesService.getOrderInfoByOrderID(openid,shopid,orderid);

        //返回数据：
        String JsonValue = JSONArray.toJSONString(orderNotesBase);
        model.addAttribute("pagesInfo",JsonValue);

        return "ordernotes";
    }


    @RequestMapping("/historyOrder")
    public String historyOrder(HttpServletRequest req, HttpServletResponse resp, ModelMap model) throws IOException {

        //需要参数列表：
        //1.openid;
        //2.shopid;
        //3.orderid;
        String openidMD5 = req.getParameter("openid");
        if (openidMD5.endsWith("#")){
            openidMD5 = openidMD5.substring(0,openidMD5.length()-1);
        }
        String shopid = req.getParameter("shopid");
        if (shopid.endsWith("#")){
            shopid = shopid.substring(0,shopid.length()-1);
        }
        String openid = mbService.getOpenidByMD5(openidMD5);
        String orderid = req.getParameter("orderid");

        //获取用户订单状态
        OrderNotesBase orderNotesBase = orderNotesService.getOrderInfoByOrderID(openid,shopid,orderid);

        //返回数据：
        String JsonValue = JSONArray.toJSONString(orderNotesBase);
        model.addAttribute("pagesInfo",JsonValue);

        return "ordernotesHistory";
    }

    @RequestMapping("/confirm")
    public String confirm(HttpServletRequest req, HttpServletResponse resp, ModelMap model) throws IOException {

        //需要参数列表：
        //1.openid;
        //2.shopid;
        //3.orderid;
        String openidMD5 = req.getParameter("openid");
        if (openidMD5.endsWith("#")){
            openidMD5 = openidMD5.substring(0,openidMD5.length()-1);
        }
        String shopid = req.getParameter("shopid");
        if (shopid.endsWith("#")){
            shopid = shopid.substring(0,shopid.length()-1);
        }
        String openid = mbService.getOpenidByMD5(openidMD5);
        String orderid = req.getParameter("orderid");

        //获取用户订单状态
        OrderNotesBase orderNotesBase = orderNotesService.getOrderInfoByOrderID(openid,shopid,orderid);

        //返回数据：
        String JsonValue = JSONArray.toJSONString(orderNotesBase);
        model.addAttribute("pagesInfo",JsonValue);

        return "confirmReceipt";
    }

    @RequestMapping("/transportOrder")
    public String transportOrder(HttpServletRequest req, HttpServletResponse resp, ModelMap model) throws IOException {

        //需要参数列表：
        //1.openid;
        //2.shopid;
        //3.orderid;
        String openidMD5 = req.getParameter("openid");
        if (openidMD5.endsWith("#")){
            openidMD5 = openidMD5.substring(0,openidMD5.length()-1);
        }
        String shopid = req.getParameter("shopid");
        if (shopid.endsWith("#")){
            shopid = shopid.substring(0,shopid.length()-1);
        }
        String openid = mbService.getOpenidByMD5(openidMD5);
        String orderid = req.getParameter("orderid");
        //获取用户订单状态
        OrderNotesBase orderNotesBase = orderNotesService.getOrderInfoByOrderID(openid,shopid,orderid);
        //返回数据：
        String JsonValue = JSONArray.toJSONString(orderNotesBase);
        model.addAttribute("pagesInfo",JsonValue);
        return "transportOrder";
    }


    @RequestMapping("/orderList")
    public String orderList(HttpServletRequest req, HttpServletResponse resp, ModelMap model) throws IOException {

        //需要参数列表：
        //1.openid;
        //2.shopid;
        //3.orderid;
        String openidMD5 = req.getParameter("openid");
        if (openidMD5.endsWith("#")){
            openidMD5 = openidMD5.substring(0,openidMD5.length()-1);
        }
        String openid = mbService.getOpenidByMD5(openidMD5);

        //获取用户订单状态
        List<OrderNotesBase> orderlist = new ArrayList<>();
        orderlist = orderNotesService.getTotalOrderList(openid);

        //返回数据：
        String JsonValue = JSONArray.toJSONString(orderlist);
        model.addAttribute("pagesInfo",JsonValue);

        return "ordernotesList";
    }



    //cancelOrder
    @RequestMapping("/order/cancelOrder")
    public void cancelOrder(HttpServletRequest req, HttpServletResponse resp) throws IOException {

        //需要参数列表：
        //1.openid;
        //2.shopid;
        //3.orderid;
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



        //删除order
        if(orderNotesService.cancelOrder(openid,shopid,orderid)){
            resp.getWriter().print(0);
        }else {
            resp.getWriter().print(1);
        }

    }

    //confirmReceipt
    @RequestMapping("/order/confirmReceipt")
    public void confirmReceipt(HttpServletRequest req, HttpServletResponse resp) throws IOException {

        //需要参数列表：
        //1.openid;
        //2.shopid;
        //3.orderid;
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



        String result = orderNotesService.confirmOrder(openid,shopid,orderid);
        resp.getWriter().print(result);


    }


}
