package com.youga.baking.ps.controller;


import com.youga.baking.ps.obj.ConfigFile;
import com.youga.baking.ps.service.MemberService;
import com.youga.baking.ps.service.impl.MemberServiceImpl;
import net.sf.json.JSONArray;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;


@Controller
@RequestMapping("/member")
public class MemberController {

    MemberService mbService = new MemberServiceImpl();
    ConfigFile config = new ConfigFile();


    @RequestMapping("/center")
    public String center(HttpServletRequest req, HttpServletResponse resp, ModelMap model) throws IOException {

        //页面参数
        Map<String,String> usermap = new HashMap();
        String openidMD5 = req.getParameter("openid");
        String openid = mbService.getOpenidByMD5(openidMD5);

        /**
         * 检查是否为认证会员；
         * 若为认证会员，则读取会员信息；
         * 若非认证会员，则仅显示头像、姓名；
         */

        if(!mbService.checkMemberAuth(openid)){
            mbService.setUnAuthMember(usermap,openid);
        }else {
            mbService.setAuthMember(usermap,openid);
        }

        String userInfoOut = JSONArray.fromObject(usermap).toString();
        model.addAttribute("userInfoList",userInfoOut);

        //2018-11-03设置地址页面cookie
        StringBuffer urlstrem = req.getRequestURL();
        String tempContextUrl = urlstrem.delete(urlstrem.length() - req.getRequestURI().length(), urlstrem.length()).append("/").toString();
        String addressRetrurnUrl = tempContextUrl+"SAstation/member/auth";
        Cookie returnUrl = new Cookie("AddressReturnUrl",addressRetrurnUrl);
        returnUrl.setPath("/");
        //returnUrl.setDomain(".youga-pet.com");
        resp.addCookie(returnUrl);

        return "memcenter";
    }

    @RequestMapping("/ShowCenter")
    public void showCenter( HttpServletResponse resp) throws IOException {

        String url = "http://www.youga-pet.com/SAstation/member/membercenter";
        resp.sendRedirect(url);
    }

    @RequestMapping("/membercenter")
    public String membercenter(){
        return  "sellCenter";
    }

}
