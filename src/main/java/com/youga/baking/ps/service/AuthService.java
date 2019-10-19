package com.youga.baking.ps.service;


import com.youga.baking.ps.dao.MemberDao;
import com.youga.baking.ps.dao.impl.MemberDaoImpl;
import com.youga.baking.ps.obj.WxInfo;
import net.sf.json.JSONObject;

public interface AuthService {


    MemberDao member = new MemberDaoImpl();

    /***
     * this method to get call back type of callback URL;
     * the callBackType is in {1,2,3,4,5,6,7} means different service Type;
     * @param callBackType
     * @return callbackUrl
     */
    public String getCallBack(String callBackType);

    public WxInfo SetWxUserInfo(JSONObject userInfo);

    /***
     * this method to check user is first in;
     * if user first in return true;
     * if user not ,return false;
     * @param tmpUser
     * @return true/false;
     */
    boolean checkFirstEnter(WxInfo tmpUser);

    /***
     * this method to insert new user into user_wx_info table;
     * @param tmpUser
     */
    void insertWxInfo(WxInfo tmpUser);


    /***
     * this method to update old user wxInfo,
     * cause they're change them headIcon or nicName;
     * @param tmpUser
     */
    void updateWxInfo(WxInfo tmpUser);

    /***
     * this method to check did user register member;
     * if it register,return true;
     * if not return false;
     * @param tmpUser
     * @return
     */
    boolean checkIsMember(WxInfo tmpUser);
}
