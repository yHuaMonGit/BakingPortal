package com.youga.baking.ps.controller;

import com.youga.baking.ps.obj.AddreesInfo;
import com.youga.baking.ps.service.AdreesService;
import com.youga.baking.ps.service.MemberService;
import com.youga.baking.ps.service.impl.AddreesServiceImpl;
import com.youga.baking.ps.service.impl.MemberServiceImpl;
import com.youga.baking.ps.util.OrderIdUtil;
import com.youga.baking.ps.util.StringUtil;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;


@Controller
public class AdressController {

    AdreesService adreesService = new AddreesServiceImpl();
    MemberService memberService = new MemberServiceImpl();

    @RequestMapping("/addressManage")
    public String addressManage(HttpServletRequest req, HttpServletResponse resp, ModelMap model) throws IOException {

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

        return "addressManage";

    }

    @RequestMapping("/addressmanagement")
    public String addressmanagement(HttpServletRequest req, HttpServletResponse resp, ModelMap model) throws IOException {

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
        String openid = memberService.getOpenidByMD5(openidMD5);


        List<AddreesInfo> addreesInfoList = adreesService.getAllAddrees(openid);

        String addreeslistString = net.sf.json.JSONArray.fromObject(addreesInfoList).toString();
        model.addAttribute("addresslistJson",addreeslistString);


        return "addressmanagement";

    }


    @RequestMapping("/addAdress")
    public String addAdress(HttpServletRequest req, HttpServletResponse resp) throws IOException {

        String openidMD5 = req.getParameter("openid");
        String openid = memberService.getOpenidByMD5(openidMD5);

        return "addressManage";
    }

    @RequestMapping("/addAddressment")
    public void addAddressment(HttpServletRequest req, HttpServletResponse resp) throws IOException {

        String openidMD5 = req.getParameter("openid");
        String openid = memberService.getOpenidByMD5(openidMD5);
        String msisdn = req.getParameter("msisdn");
        String username = req.getParameter("username");
        String addressPCC = req.getParameter("addressPCC");
        String addressDetail = req.getParameter("addressDetail");
        String ecode = req.getParameter("ecode");
        if(ecode.equals("") || null == ecode)
        {
            ecode = "710000";
        }
        String province = "";
        String city = "";
        String area = "";

        String [] addressPccArr = StringUtil.getAddress(addressPCC);
        if(addressPccArr.length < 3)
        {
            resp.getWriter().print("2");
            return;
        }
        else
        {
            province = addressPccArr[0];
            city = addressPccArr[1];
            area = addressPccArr[2];
        }
        String addressid = OrderIdUtil.getAddreesID(adreesService.getAddressNum(openid),openid);
        AddreesInfo addreesInfo = new AddreesInfo(
                addressid,
                openid,
                msisdn,
                username,
                province,
                city,
                area,
                addressDetail,
                ecode
        );
        adreesService.InsertAddreesInfo(addreesInfo);
        resp.getWriter().print("1");
    }

    @RequestMapping("/modifyDefault")
    public void modifyDefault(HttpServletRequest req, HttpServletResponse resp) throws IOException {

        String openidMD5 = req.getParameter("openid");
        String openid = memberService.getOpenidByMD5(openidMD5);
        String addressid = req.getParameter("addressid");

        AddreesInfo modifyInfo = adreesService.getSingleAddrees(openid,addressid);
        String  defaultd = adreesService.getDefaultId(openid);
        AddreesInfo defaultInfo = adreesService.getSingleAddrees(openid,defaultd);

        adreesService.modifyDefault(modifyInfo,defaultInfo);

        resp.getWriter().print("1");
    }



}
