package com.youga.baking.ps.service.impl;

import com.youga.baking.ps.dao.MemberDao;
import com.youga.baking.ps.dao.impl.MemberDaoImpl;
import com.youga.baking.ps.obj.MemberInfo;
import com.youga.baking.ps.obj.ReturnCode;
import com.youga.baking.ps.obj.WxInfo;
import com.youga.baking.ps.service.MemberService;

import java.util.Map;

public class MemberServiceImpl implements MemberService {

    MemberDao memberDao = new MemberDaoImpl();
    @Override
    public String isMember(String openid) {

        String result = memberDao.isOpenidExist(openid);

        if (ReturnCode.SUCCESS_CODE.equals(result))
        {
            return ReturnCode.OPENID_EXIST;
        }
        else if(ReturnCode.OPENID_REPEAT.equals(result)){
            return ReturnCode.OPENID_REPEAT;
        }
        else if (ReturnCode.OPENID_NOD_FOUND.equals(result))
        {
            return ReturnCode.OPENID_NOD_FOUND;
        }
        return ReturnCode.NORMAL_ERROR_CODE;

    }

    @Override
    public String insertUser(WxInfo user) {
        if (ReturnCode.SUCCESS_CODE.equals(memberDao.insertWxInfo(user)))
        {
            return ReturnCode.OPENID_EXIST;
        }
        else
        {
            return ReturnCode.INSERT_ERROR;
        }
    }

    @Override
    public String updataUser(WxInfo user) {
        if (ReturnCode.SUCCESS_CODE.equals(memberDao.modifyWxInfo(user)))
        {
            return ReturnCode.OPENID_EXIST;
        }
        else
        {
            return ReturnCode.MODIFY_ERROR;
        }
    }

    @Override
    public WxInfo getWxInfo(String openid) {

        return memberDao.getWxInfo(openid);

    }

    @Override
    public String sendVerificationSMS() {
        return null;
    }

    @Override
    public String registerMember(MemberInfo member) {

        String inserResult = memberDao.insertMemberInfo(member);
        if (ReturnCode.SUCCESS_CODE.equals(inserResult))
        {
            //2019-05-10 临时新增认证会员抽奖次数增加功能；
            memberDao.synchronizationLottery(member.getOpenid());
            return ReturnCode.SUCCESS_CODE;
        }
        else
        {
            return ReturnCode.INSERT_ERROR;
        }


    }

    @Override
    public MemberInfo getMemberInfo(String openid) {
        return memberDao.getMemberInfo(openid);
    }

    @Override
    public String CheckMember(String openid) {
        String result = memberDao.isMemberExist(openid);

        if (ReturnCode.MEMBER_EXIST.equals(result))
        {
            return ReturnCode.MEMBER_EXIST;
        }
        else if(ReturnCode.MEMBER_REPEAT.equals(result)){
            return ReturnCode.MEMBER_REPEAT;
        }
        else if (ReturnCode.MEMBER_NOD_FOUND.equals(result))
        {
            return ReturnCode.MEMBER_NOD_FOUND;
        }
        return ReturnCode.NORMAL_ERROR_CODE;
    }

    @Override
    public boolean checkOpenidLeagle(String openid) {

        if(null == memberDao.getOpenidMd5(openid))
        {
            return false;
        }else {
            return true;
        }

    }

    @Override
    public boolean checkOfflineMember(String msisdn) {

        if (memberDao.isOfflineMemberExist(msisdn)){
            return true;
        }else {
            return false;
        }
    }

    @Override
    public MemberInfo setMemberByOffline(String openid, String msisdn) {

        //构建一个member通过线下会员（memberId/memberLevel/memberBalance/memberIntegral）
        MemberInfo member = memberDao.getMemberInfoByOffline(msisdn);
        member.setOpenid(openid);
        member.setMemberFlag("1");
        member.setNickName(memberDao.getNickName(openid));

        return member;
    }

    @Override
    public MemberInfo createOnlineMember(String openid, String msisdn) {

        MemberInfo memberOn = new MemberInfo();
        memberOn.setOpenid(openid);
        memberOn.setMemberID(this.createNewMemberId());
        //新用户注册都是1
        memberOn.setMemberLevel("1");
        memberOn.setMemberFlag("1");
        memberOn.setBalance("0");
        memberOn.setNickName(memberDao.getNickName(openid));
        memberOn.setMsisdn(msisdn);
        memberOn.setIntegral("0");
        return memberOn;
    }

    @Override
    public MemberInfo createOfflineMember(String msisdn, String memberId) {
        MemberInfo memberOff = new MemberInfo();
        memberOff.setMemberID(memberId);
        //新用户注册都是1
        memberOff.setMemberLevel("1");
        memberOff.setMemberFlag("1");
        memberOff.setBalance("0");
        memberOff.setMsisdn(msisdn);
        memberOff.setIntegral("0");
        //线上注册折扣均不打折
        memberOff.setServiceOff("1");
        memberOff.setGoodsOff("1");
        memberOff.setHisAmount("0");
        memberOff.setAmount("0");
        return memberOff;
    }

    @Override
    public void insertOfflineMember(MemberInfo offlineMember) {
        memberDao.insertOfflineMemberInfo(offlineMember);
    }

    @Override
    public void insertOnlineMember(MemberInfo onlineMember) {
        memberDao.insertMemberInfo(onlineMember);
    }

    @Override
    public boolean isMemberByMD5(String openid) {
        if (memberDao.isMemberExistByMD5(openid)){
            return true;
        }else return false;
    }

    @Override
    public String getOpenidByMD5(String openidMD5) {
        String openid = String.valueOf(memberDao.getOpenidMd5(openidMD5));
        if (null == openid){
            return "";
        }
        return openid;
    }

    @Override
    public String createNewMemberId() {
        String lastId = memberDao.getOfflineLastId();
        String head = "yg-";
        String rear = lastId.replace(head,"");
        int Num = Integer.valueOf(rear);
        Num++;
        String outStr = String.format("%05d", Num);
        outStr = head + outStr;
        return outStr;
    }

    @Override
    public boolean checkMemberAuth(String openid) {


        if (!memberDao.checkMemberAuth(openid)){
            return false;
        }else {
            return true;
        }

    }

    @Override
    public void setUnAuthMember(Map<String, String> usermap, String openid) {

        WxInfo wxInfo = this.getWxInfo(openid);
        usermap.put("auth","unAuth");
        usermap.put("level",MemberInfo.getUnAuthLevel());
        usermap.put("integral",MemberInfo.getUnAuthInter());
        usermap.put("balance",MemberInfo.getUnAuthBalence());
        usermap.put("userName",wxInfo.getUserName());
        usermap.put("headIconUrl",wxInfo.getHeadimgurl());
        usermap.put("openid",openid);

    }

    @Override
    public void setAuthMember(Map<String, String> usermap, String openid) {

        WxInfo wxInfo = this.getWxInfo(openid);
        usermap.put("auth","Auth");
        MemberInfo member = this.getMemberInfo(openid);
        usermap.put("level",member.getMemberLevel());
        usermap.put("integral",member.getIntegral());
        usermap.put("balance",member.getBalance());

        usermap.put("userName",wxInfo.getUserName());
        usermap.put("headIconUrl",wxInfo.getHeadimgurl());
        usermap.put("openid",openid);


    }

    public String getMemberId() {

        String lastId = memberDao.getLastId();
        if (lastId.equals("")){
            return "yg-00001";
        }
        String head = "yg-";
        String rear = lastId.replace(head,"");
        int Num = Integer.valueOf(rear);
        Num++;
        String outStr = String.format("%05d", Num);
        outStr = head + outStr;
        return outStr;
    }

    @Override
    public MemberInfo getMemberInfoByMsisdn(String msisdn) {
        return memberDao.getMemberInfoByOffline(msisdn);
    }

    @Override
    public void synchronizationMember(MemberInfo member) {
        memberDao.updateMemberInfo(member);

        //2019-05-10 临时新增认证会员抽奖次数增加功能；
        memberDao.synchronizationLottery(member.getOpenid());

    }

    @Override
    public boolean checkMemberExist(String msisdn) {
        if (memberDao.checkMemberExist(msisdn)){
            //存在则返回true
            return true;
        }else{
            return false;
        }
    }

}
