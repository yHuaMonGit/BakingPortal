package com.youga.baking.ps.service.impl;


import com.youga.baking.ps.obj.ConfigFile;
import com.youga.baking.ps.obj.ReturnCode;
import com.youga.baking.ps.obj.WxInfo;
import com.youga.baking.ps.service.AuthService;
import com.youga.baking.ps.util.StringUtil;
import net.sf.json.JSONObject;


public class AuthServiceImpl implements AuthService {

    ConfigFile conf = new ConfigFile();
    @Override
    public String getCallBack(String callBackType) {

        switch (callBackType)
        {
            //注册
            case "0": return conf.getRegistUrl();
            //会员中心
            case "1": return conf.getMemberCenter();
            //认证页面
            case "2": ;
            //商城首页
            case "3": return conf.getShoppingCenterURL();
            //地址
            case "4": return conf.getAddressURL();
            //抽奖活动
            case "5": return  conf.getLottery();
            default:;
        }

        return null;
    }

    @Override
    public WxInfo SetWxUserInfo(JSONObject userInfo) {
        String userName = userInfo.getString("nickname");
        String userId = userInfo.getString("openid");
        String headimgurl = userInfo.getString("headimgurl");
        String country = userInfo.getString("country");
        String city = userInfo.getString("city");
        String province = userInfo.getString("province");
        String sex = userInfo.getString("sex");
        WxInfo newWxUser = new WxInfo(userName,userId,headimgurl,country,city,province,sex);

        //2019-01-02 新增openid加密处理
        newWxUser.setOpenidMD5(StringUtil.encryptOpenid(userId));


        return newWxUser;
    }

    @Override
    public boolean checkFirstEnter(WxInfo tmpUser) {

        if(ReturnCode.SUCCESS_CODE.equals(member.isOpenidExist(tmpUser.getUserId())))
        {
            return false;
        }else {
            return true;
        }

    }

    @Override
    public void insertWxInfo(WxInfo tmpUser) {
        member.insertWxInfo(tmpUser);
    }

    @Override
    public void updateWxInfo(WxInfo tmpUser) {
        member.modifyWxInfo(tmpUser);
    }

    @Override
    public boolean checkIsMember(WxInfo tmpUser) {

        if (ReturnCode.MEMBER_EXIST.equals(member.isMemberExist(tmpUser.getUserId()))){
            return true;
        }else {
            return false;
        }

    }
}
