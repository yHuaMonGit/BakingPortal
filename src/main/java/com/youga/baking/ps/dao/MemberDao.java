package com.youga.baking.ps.dao;

import com.youga.baking.ps.obj.MemberInfo;
import com.youga.baking.ps.obj.WxInfo;


public interface MemberDao {

    /***
     * 检查openid是否存在
     */
    public String isOpenidExist(String openid);

    /***
     * 插入WxInfo
     */
    public String insertWxInfo(WxInfo wxInfo);

    /***
     * 修改WxInfo(信息变更)
     */
    public  String modifyWxInfo(WxInfo wxInfo);

    /***
     * 查询验证码
     */
    public String getVeriCode();

    /***
     * 插入会员信息
     */
    public String insertMemberInfo(MemberInfo memberInfo);


    /***
     * 查询会员信息
     */
    public MemberInfo getMemberInfo(String openid);

    /***
     * 会员信息变更(手机号变更)
     */
    public String updateMemberInfo(MemberInfo memberInfo);

    /**
     * 查询是否注册会员
     * @param openid
     * @return
     */
    public String isMemberExist(String openid);

    /**
     * 获取用户微信信息
     * @param openid
     * @return
     */
    public WxInfo getWxInfo(String openid);

    /***
     * 获取用户id用于生成订单
     * @param openid
     * @return
     */
    public String getWxUid(String openid);

    /***
     * 2019-01-02
     * this method is to get openid md5 ;
     * if does not exist ,return null;
     * @param openid
     * @return
     */
    Object getOpenidMd5(String openid);

    /***
     * 2019-01-02
     * this method for check member exist by md5 openid;
     * @param openid
     * @return
     */
    boolean isMemberExistByMD5(String openid);

    /***
     * 2019-01-02
     * this method for check offline member if exist;
     * exist:true;
     * not : false;
     * @param msisdn
     * @return
     */
    boolean isOfflineMemberExist(String msisdn);

    /***
     * 2019-01-02
     * this method for get member info by offline member;
     * @param msisdn
     * @return
     */
    MemberInfo getMemberInfoByOffline(String msisdn);


    /***
     * 2019-01-02
     * this method for get nickName by WXInfo;
     * @param openid
     * @return
     */
    String getNickName(String openid);

    /***
     * 2019-01-02
     * this method for get last id in offline tbl.
     * @return
     */
    String getOfflineLastId();


    /***
     * 2019-01-02
     * this method create offline member
     * @param offlineMember
     */
    void insertOfflineMemberInfo(MemberInfo offlineMember);

    /***
     * 2019-04-20 判定认证会员是否存在
     * @param openid
     * @return
     */
    boolean checkMemberAuth(String openid);

    /***
     * 获取最后一个ID
     * @return
     */
    String getLastId();

    /***
     * 检测用户是否存在
     * @param msisdn
     * @return
     */
    boolean checkMemberExist(String msisdn);

    /***
     * 同步会员验证后抽奖信息
     * @param openid
     */
    void synchronizationLottery(String openid);

    /***
     * 用户消费后余额扣除
     * @param memberInfo
     */
    void updateMemberBalance(MemberInfo memberInfo);
}
