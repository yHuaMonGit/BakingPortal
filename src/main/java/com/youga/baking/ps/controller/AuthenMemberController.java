package com.youga.baking.ps.controller;


import com.youga.baking.ps.obj.MemberInfo;
import com.youga.baking.ps.obj.ReturnCode;
import com.youga.baking.ps.service.AuthenMemberService;
import com.youga.baking.ps.service.MemberService;
import com.youga.baking.ps.service.impl.AuthenMemberServiceImpl;
import com.youga.baking.ps.service.impl.MemberServiceImpl;
import com.youga.baking.ps.util.IDAuthUtil;
import net.sf.json.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Controller
@RequestMapping("/member")
public class AuthenMemberController {

    MemberService mbService = new MemberServiceImpl();
    AuthenMemberService amservice = new AuthenMemberServiceImpl();

    @RequestMapping("/authen")
    public String authen(HttpServletRequest req, HttpServletResponse resp, ModelMap model) throws IOException {

        return "authenpage";

    }

    @RequestMapping("/submitauthen")
    public void submitauthen(HttpServletRequest req, HttpServletResponse resp, ModelMap model) throws IOException {

        String openidMD5 = req.getParameter("openid");
        if (openidMD5.endsWith("#")){
            openidMD5 = openidMD5.substring(0,openidMD5.length()-1);
        }
        String idcard = req.getParameter("idcard");
        String msisdn = req.getParameter("msisdn");
        String name = req.getParameter("name");
        String verifyCode = req.getParameter("verifyCode");

        String openid = mbService.getOpenidByMD5(openidMD5);

        //测试身份证验证
        JSONObject IdentityInfo= IDAuthUtil.doGetJson(idcard,name);

        //身份信息提取

        if (!amservice.checkVerifyCode(verifyCode,openid,msisdn)){
            resp.getWriter().print(ReturnCode.VERIFY_INVALID);
            return;
        }else if(!amservice.checkIDCard(IdentityInfo)){
            resp.getWriter().print(ReturnCode.IDCARD_INVALID);
            return;
        }else {

            //新用户认证，插入信息
            //判定用户是否存在

            if(mbService.checkMemberExist(msisdn)){
                //存在则校验是否认证
                if (!mbService.checkMemberAuth(openid)){
                    //未认证则同步信息
                    amservice.synchronizationMember(openid,msisdn,IdentityInfo);
                    resp.getWriter().print(ReturnCode.MEMBER_SYNCHRONIZATION_SUCCESS);
                }
            }else {
                //不存在则直接注册
                amservice.memberRegist(openid,msisdn,IdentityInfo);
                resp.getWriter().print(ReturnCode.MEMBER_REGIST_SUCCESS);
            }

        }

    }
}
