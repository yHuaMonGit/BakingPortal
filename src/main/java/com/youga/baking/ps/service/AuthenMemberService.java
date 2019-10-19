package com.youga.baking.ps.service;

import net.sf.json.JSONObject;

public interface AuthenMemberService {
    /***
     * 校验验证码是否通过
     * @param verifyCode
     * @param openid
     * @param msisdn
     * @return
     */
    boolean checkVerifyCode(String verifyCode, String openid, String msisdn);

    /***
     * 校验身份证是否通过
     * @param identityInfo
     * @return
     */
    boolean checkIDCard(JSONObject identityInfo);

    /***
     * 新用户注册绑定
     * @param openid
     * @param msisdn
     * @param identityInfo
     */
    void memberRegist(String openid, String msisdn, JSONObject identityInfo);

    /***
     * 已开通会员进行认证绑定
     * @param openid
     * @param msisdn
     * @param identityInfo
     */
    void synchronizationMember(String openid, String msisdn, JSONObject identityInfo);
}
