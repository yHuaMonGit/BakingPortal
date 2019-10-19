package com.youga.baking.ps.service;

import com.youga.baking.ps.obj.MemberInfo;
import com.youga.baking.ps.obj.WxInfo;

import java.util.Map;


public interface MemberService {

    /***
     * 检查用户是否会员
     * 功能点:检测首次登陆
     */
    public String isMember(String openid);

    /***
     * 微信信息入库
     */
    public String insertUser(WxInfo user);

    /***
     * 更新用户信息
     */
    public String updataUser(WxInfo user);

    /**
     * 获取用户微信信息
     */
    public WxInfo getWxInfo(String openid);

    /***
     * 发送验证码
     * 功能点:首次登陆用户/未注册用户手机绑定短信验证码
     */
    public String sendVerificationSMS();

    /***
     * 注册会员信息
     */
    public String registerMember(MemberInfo member);

    /***
     * 查询会员信息
     * 功能点:初始化会员信息页面
     */
    public MemberInfo getMemberInfo(String openid);

    /***
     * 查询是否为会员/是否绑定手机号
     * 注册信息
     */
    String CheckMember(String userId);

    /***
     * this method to check the enter user's openid was correct or not;
     * if is correct ,return true;
     * if not return false;
     * @param openid
     * @return
     */
    boolean checkOpenidLeagle(String openid);


    /***
     * 2019-01-02
     * this method for check is Member already in offline DB;
     * if has offline data return true;
     * else return false;
     * @param msisdn
     * @return
     */
    boolean checkOfflineMember(String msisdn);

    /***
     * 2019-01-02
     * this method for set Online member with Offline Data;
     *
     * @param openid
     * @param msisdn
     * @return
     */
    MemberInfo setMemberByOffline(String openid, String msisdn);


    /***
     * 2019-01-02
     * this method for create new Online Member;
     * @param openid
     * @param msisdn
     * @return
     */
    MemberInfo createOnlineMember(String openid, String msisdn);


    /***
     * 2019-01-02
     * this method for create a new Offline Member;
     *
     * @param
     * @param msisdn
     * @return
     */
    MemberInfo createOfflineMember(String msisdn, String memberID);


    /***
     * 2019-01-02
     * this method for insert offline member into DB;
     * @param offlineMember
     */
    void insertOfflineMember(MemberInfo offlineMember);


    /***
     * 2019-01-02
     * this method for insert online member into DB；
     * @param onlineMember
     */
    void insertOnlineMember(MemberInfo onlineMember);

    /***
     * 2019-01-02
     * this method for check is member exist;
     * for defend register repeat;
     * if exist return true;
     * else return false;
     * @param openid
     * @return
     */
    boolean isMemberByMD5(String openid);

    /***
     * 2019-01-02
     * this method for exchange openidMD5 -> openid
     * @param openidMD5
     * @return
     */
    String getOpenidByMD5(String openidMD5);

    /***
     * 2019-01-02 this method to construct new member ID;
     * @return
     */
    String createNewMemberId();

    /***
     * 2019-04-20 检查用户是否为认证会员
     * @return
     */
    boolean checkMemberAuth(String openid);

    /***
     * 2019-04-20 返回非认证会员信息；
     * @param usermap
     * @param openid
     */
    void setUnAuthMember(Map<String,String> usermap, String openid);

    /***
     * 2019-04-20 返回认证会员信息；
     */
    void setAuthMember(Map<String,String> usermap, String openid);


    /***
     * 生成memberid
     * @return
     */
    public String getMemberId();

    /***
     * 通过手机号码获取MSISDN
     * @return
     * @param msisdn
     */
    MemberInfo getMemberInfoByMsisdn(String msisdn);

    /***
     * 同步数据
     * @param member
     */
    void synchronizationMember(MemberInfo member);

    /***
     * 检查用户是否存在
     * @param msisdn
     * @return
     */
    public boolean checkMemberExist(String msisdn);
}
