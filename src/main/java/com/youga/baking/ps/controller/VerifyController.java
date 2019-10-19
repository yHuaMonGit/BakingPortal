package com.youga.baking.ps.controller;


import com.youga.baking.ps.obj.ReturnCode;
import com.youga.baking.ps.service.MemberService;
import com.youga.baking.ps.service.VerifyService;
import com.youga.baking.ps.service.impl.MemberServiceImpl;
import com.youga.baking.ps.service.impl.VerifyServiceImpl;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


@Controller
@RequestMapping("/member")
public class VerifyController {

    VerifyService verifyService = new VerifyServiceImpl();
    MemberService memberService = new MemberServiceImpl();


    @RequestMapping("/getVerify")
    public void center(HttpServletRequest req, HttpServletResponse resp, ModelMap model) throws IOException {


        String openidMD5 = req.getParameter("openid");
        String openid = memberService.getOpenidByMD5(openidMD5);

        String msisdn = req.getParameter("msisdn");
        verifyService.createVerifyCode(openid);
        verifyService.sendSMS(msisdn,openid);

        resp.getWriter().print(ReturnCode.SMS_SUCCESS);

    }



}
