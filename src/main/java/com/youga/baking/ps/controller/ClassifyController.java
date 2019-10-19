package com.youga.baking.ps.controller;


import com.alibaba.fastjson.JSONArray;
import com.youga.baking.ps.obj.*;
import com.youga.baking.ps.service.ClassifyService;
import com.youga.baking.ps.service.MemberService;
import com.youga.baking.ps.service.impl.ClassifyServiceImpl;
import com.youga.baking.ps.service.impl.MemberServiceImpl;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.*;

@Controller
public class ClassifyController {

    MemberService mbService = new MemberServiceImpl();

    ClassifyService cServcie = new ClassifyServiceImpl();

    @RequestMapping("/classify")
    public String classify(HttpServletRequest req, HttpServletResponse resp, ModelMap model) throws IOException {

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
        MemberInfo memberInfo = mbService.getMemberInfo(openid);

        if (null == memberInfo.memberID){
            model.addAttribute("memberFlg","0");
        }

        return "classify";

    }

    //./classify/getNotice
    @RequestMapping("/classify/getNotice")
    public void getNotice(HttpServletRequest req, HttpServletResponse resp) throws IOException {

        //需要参数列表：
        //1.shopid;
        String shopid = req.getParameter("shopid");
        if (shopid.endsWith("#")){
            shopid = shopid.substring(0,shopid.length()-1);
        }
        resp.setContentType("text/html;charset=UTF-8");
        resp.getWriter().print(cServcie.getAnnouncementInfo(shopid));
        return;

    }

    //./classify/ShoppingcartNum
    @RequestMapping("/classify/ShoppingcartNum")
    public void ShoppingcartNum(HttpServletRequest req, HttpServletResponse resp) throws IOException {

        //需要参数列表：
        //1.openid;
        String openidMD5 = req.getParameter("openid");
        //获取真实openid
        String openid = mbService.getOpenidByMD5(openidMD5);
        String result = cServcie.getShopCartNum(openid);
        resp.setContentType("text/html;charset=UTF-8");
        resp.getWriter().print(result);

        return;
    }

    //./classify/getComList
    @RequestMapping("/classify/getComList")
    public void getComList(HttpServletRequest req, HttpServletResponse resp) throws IOException {

        //modelMap携带其他参数返回
        Map<String,String> pageMap = new HashMap();

        //需要参数列表：
        //1.shopid;
        //2.openid;
        String shopid = req.getParameter("shopid");
        String openidMD5 = req.getParameter("openidMD5");
        if (shopid.endsWith("#")){
            shopid = shopid.substring(0,shopid.length()-1);
        }
        //获取真实openid
        String openid = mbService.getOpenidByMD5(openidMD5);

        List<ComListBase> clbList = cServcie.getComList(shopid,openid);
        resp.setContentType("text/html;charset=UTF-8");
        String JsonValue = JSONArray.toJSONString(clbList);
        resp.getWriter().print(JsonValue);


        return;
    }

    //./classify/ChangeType
    @RequestMapping("/classify/ChangeType")
    public void ChangeType(HttpServletRequest req, HttpServletResponse resp) throws IOException {

        //需要参数列表：
        //1.shopid;
        String shopid = req.getParameter("shopid");
        if (shopid.endsWith("#")){
            shopid = shopid.substring(0,shopid.length()-1);
        }
        String clsInId = req.getParameter("clsInId");

        List<ComDetailBase> comDetailBaseList = cServcie.getComDetailBaseList(shopid,clsInId);

        resp.setContentType("text/html;charset=UTF-8");
        String JsonValue = JSONArray.toJSONString(comDetailBaseList);

        resp.getWriter().print(JsonValue);
        return;
    }

    //./classify/getStander
    @RequestMapping("/classify/getStander")
    public void getStander(HttpServletRequest req, HttpServletResponse resp) throws IOException {

        //需要参数列表：
        //1.shopid;
        //2.goodsid;
        String shopid = req.getParameter("shopid");
        String standerid = req.getParameter("standerid");
        if (shopid.endsWith("#")){
            shopid = shopid.substring(0,shopid.length()-1);
        }
        if (standerid.endsWith("#")){
            standerid = standerid.substring(0,shopid.length()-1);
        }

        //3.com_id
        String com_id = req.getParameter("com_id");


        List<StanderBase> standerBases = cServcie.getStanderBase(shopid,standerid,com_id);


        Collections.sort(standerBases);

        resp.setContentType("text/html;charset=UTF-8");
        String JsonValue = JSONArray.toJSONString(standerBases);
        resp.getWriter().print(JsonValue);
        return;
    }

    //./classify/addToShoppingCart
    @RequestMapping("/classify/addToShoppingCart")
    public void addToShoppingCart(HttpServletRequest req, HttpServletResponse resp) throws IOException {

        //需要参数列表：
        //1.shopid;
        //2.goodsid;
        //3.openid;
        //4.goodsstander+innerid;
        String shopid = req.getParameter("shopid");
        String goodsid = req.getParameter("goodsid");
        String openidMD5 = req.getParameter("openidMD5");
        String comstander = req.getParameter("comstander");
        String comNum = req.getParameter("comNum");
        if (shopid.endsWith("#")){
            shopid = shopid.substring(0,shopid.length()-1);
        }

        String openid = mbService.getOpenidByMD5(openidMD5);

        boolean isSuccess = cServcie.addComToShoppingCart(openid,comstander,goodsid,shopid,comNum);

        resp.setContentType("text/html;charset=UTF-8");
        if (isSuccess){
            resp.getWriter().print(0);
        }else {
            resp.getWriter().print(1);
        }

        return;
    }


    //./classify/getShopCart
    @RequestMapping("/classify/getShopCart")
    public void getShopCart(HttpServletRequest req, HttpServletResponse resp) throws IOException {

        //需要参数列表：
        //1.openid;
        //2.shopid;
        String shopid = req.getParameter("shopid");
        String openidMD5 = req.getParameter("openid");
        if (shopid.endsWith("#")){
            shopid = shopid.substring(0,shopid.length()-1);
        }
        //获取真实openid
        String openid = mbService.getOpenidByMD5(openidMD5);

        List<shoppingcartBase> shoppingcartBaseList = cServcie.getShopCart(shopid,openid);

        resp.setContentType("text/html;charset=UTF-8");
        String JsonValue = JSONArray.toJSONString(shoppingcartBaseList);
        resp.getWriter().print(JsonValue);
        return;
    }


    //./classify/shoppingcartAdd
    @RequestMapping("/classify/shoppingcartAdd")
    public void shoppingcartAdd(HttpServletRequest req, HttpServletResponse resp) throws IOException {

        //需要参数列表：
        //1.openid;
        //2.comid;
        //3.innerId
        String comid = req.getParameter("comid");
        String openidMD5 = req.getParameter("openid");
        String innerId = req.getParameter("innerId");
        String optype = req.getParameter("optype");

        //获取真实openid
        String openid = mbService.getOpenidByMD5(openidMD5);

        //应用变量
        String rear= "";

        if (optype.equals(9)){
            cServcie.ModifySingleShoppingCart(openid,"0","0",Integer.valueOf(optype));
        }else {
            rear = ","+cServcie.ModifySingleShoppingCart(openid,comid,innerId,Integer.valueOf(optype));
        }

        String result = cServcie.getShopCartNum(openid);
        result += rear;

        resp.setContentType("text/html;charset=UTF-8");
        resp.getWriter().print(result);
        return;
    }

}
