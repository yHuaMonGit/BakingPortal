package com.youga.baking.ps.service.impl;

import com.youga.baking.ps.obj.MemberInfo;
import com.youga.baking.ps.obj.ReturnCode;
import com.youga.baking.ps.util.StringUtil;
import com.youga.baking.ps.service.AuthenMemberService;
import com.youga.baking.ps.service.MemberService;
import com.youga.baking.ps.service.VerifyService;
import com.youga.baking.ps.util.DateUtil;
import net.sf.json.JSONObject;

import java.text.ParseException;

import static com.youga.baking.ps.util.StringUtil.getBirthDayByIdCard;

public class AuthenMemberServiceImpl implements AuthenMemberService {

    VerifyService verifyService = new VerifyServiceImpl();
    MemberService mbservice = new MemberServiceImpl();

    @Override
    public boolean checkVerifyCode(String verifyCode, String openid, String msisdn) {
        String veriResult = verifyService.checkVerifyCode(openid,verifyCode);

        if (ReturnCode.VERIFY_ACCESS.equals(veriResult))
        {
            return true;
        }else return false;
    }

    @Override
    public boolean checkIDCard(JSONObject identityInfo) {

        JSONObject resultJson = identityInfo.getJSONObject("result");
        String res = resultJson.getString("res");
        if (res.equals("1")){
            return true;
        }else return false;

    }

    @Override
    public void memberRegist(String openid, String msisdn, JSONObject identityInfo)  {

        JSONObject resultJson = identityInfo.getJSONObject("result");

        String name = resultJson.getString("name");
        String idCard = resultJson.getString("idcard");
        String birthday = StringUtil.getBirthDayByIdCard(idCard);
        //String sex = resultJson.getString("sex");
        //String nativeAddress = resultJson.getString("nativeAddress");

        MemberInfo memberInfo = new MemberInfo(
                mbservice.getMemberId(),
                "1","0","1",msisdn,openid,"0",
                "0","1","1","0",
                name,
                idCard,
                birthday,
                "",
                "",
                String.valueOf(DateUtil.getAgeByBirth(birthday))
        );

        mbservice.registerMember(memberInfo);

    }

    @Override
    public void synchronizationMember(String openid, String msisdn, JSONObject identityInfo) {

        //同步数据
        //获取用户
        MemberInfo member = mbservice.getMemberInfoByMsisdn(msisdn);

        //获取身份认证信息
        JSONObject resultJson = identityInfo.getJSONObject("result");
        String name = resultJson.getString("name");
        String idCard = resultJson.getString("idcard");
        String birthday = getBirthDayByIdCard(idCard);

        //2019-09-24调用接口变更，无法获取性别及归属地信息
        //String sex = resultJson.getString("sex");
        //String nativeAddress = resultJson.getString("nativeAddress");
        String age = String.valueOf(DateUtil.getAgeByBirth(birthday));

        member.setOpenid(openid);
        member.setRealname(name);
        member.setIdCard(idCard);
        member.setBirthday(birthday);
        //member.setSex(sex);
        member.setAge(age);
        //member.setNativeAddress(nativeAddress);

        //同步数据
        mbservice.synchronizationMember(member);


    }
}
