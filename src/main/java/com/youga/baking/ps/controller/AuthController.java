package com.youga.baking.ps.controller;


import com.youga.baking.ps.obj.ConfigFile;
import com.youga.baking.ps.obj.WxInfo;
import com.youga.baking.ps.service.AuthService;
import com.youga.baking.ps.service.impl.AuthServiceImpl;
import com.youga.baking.ps.util.AuthUtil;
import com.youga.baking.ps.util.StringUtil;
import net.sf.json.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("/auth2")
public class AuthController {

    //声明service;
    AuthService authService = new AuthServiceImpl();
    ConfigFile config = new ConfigFile();

    /***
     * 重定向鉴权-鉴权获取从前置页面得到的参数判断重定向到哪个页面
     */
    @RequestMapping("/authredirect")
    public void authredirect(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String prama = req.getQueryString();
        //callbackType可以随着state参数传递到重定向链接
        String callbackType = StringUtil.getUrlPrama(prama,"callType");

        String callBack = config.getCallBack_Auth();
        String url = "https://open.weixin.qq.com/connect/oauth2/authorize?appid="
                + AuthUtil.APPID
                + "&redirect_uri=" + URLEncoder.encode(callBack)
                + "&response_type=code"
                + "&scope=snsapi_userinfo"
                + "&state="+callbackType
                + "#wechat_redirect";
        resp.sendRedirect(url);
    }


    @RequestMapping("/getauth")
    public void getauth(HttpServletRequest req, HttpServletResponse resp, ModelMap model) throws IOException {

        //页面参数
        Map<String,String> usermap = new HashMap();
        String callbackType = req.getParameter("state");
        String code = req.getParameter("code");
        String url = "https://api.weixin.qq.com/sns/oauth2/access_token?appid=" +AuthUtil.APPID
                +"&secret="+AuthUtil.APPSECRET
                + "&code="+code
                + "&grant_type=authorization_code";
        JSONObject jsonObject = AuthUtil.doGetJson(url);
        String openid  = jsonObject.getString("openid");
        String token  = jsonObject.getString("access_token");

        //拉取用户信息
        String infourl = "https://api.weixin.qq.com/sns/userinfo?access_token=" + token
                + "&openid=" + openid
                + "&lang=zh_CN";
        JSONObject userInfo = AuthUtil.doGetJson(infourl);
        //装载拉取信息入临时用户对象
        WxInfo tmpUser = authService.SetWxUserInfo(userInfo);
        String rearParam ="?openid="+tmpUser.getOpenidMD5();
        rearParam+="&shopid=yg000001";

        //拉取用户信息后，将wxinfo先存入临时表

        //增加会员认证绑定页面；认证会员有认证提示
        //未认证会员

        //check is first time enter;
        if (authService.checkFirstEnter(tmpUser))
        {
            //新用户首先先插入WxInfo;
            authService.insertWxInfo(tmpUser);
        }else {
            //老用户需要更新WX用户信息先
            authService.updateWxInfo(tmpUser);
        }


        //重定向到指定页面;
        String redirectUrl = authService.getCallBack(callbackType);
        //重定向需要携带用户的参数，用户已经注册存在则仅携带openid
        resp.sendRedirect(redirectUrl+rearParam);
        return ;


    }



}
