package com.youga.baking.ps.obj;

public class ReturnCode {

    public static final String SUCCESS_CODE = "000001";
    public static final String NORMAL_ERROR_CODE = "100000";

    //openid not found
    public static final String OPENID_NOD_FOUND = "100001";
    //wx user repeat
    public static final String OPENID_REPEAT = "100002";
    //insert wx user error
    public static final String INSERT_ERROR = "100003";
    //openid was exist
    public static final String OPENID_EXIST = "000002";
    //openid was exist
    public static final String OPENID_NOT_EXIST = "100004";
    //modify wx user error
    public static final String MODIFY_ERROR = "100004";
    //member was exist
    public static final String MEMBER_EXIST = "000002";
    //member was repeat
    public static final String MEMBER_REPEAT = "200002";
    //member not found
    public static final String MEMBER_NOD_FOUND = "200001";
    public static final String MEMBER_REGIST_SUCCESS = "200000";
    public static final String MEMBER_SYNCHRONIZATION_SUCCESS = "200010";
    //time is null
    public static final String TIME_NULL = "910001";
    //verify code status
    public static final String VERIFY_EFFECTIVE = "120001";
    public static final String VERIFY_INVALID = "120002";
    //exception inside
    public static final String VERIFY_ERROR = "120003";
    public static final String VERIFY_ACCESS = "120000";
    public static final String SMS_SUCCESS = "121000";
    public static final String SQL_EXCEPTION = "191000";
    public static final String IDCARD_INVALID = "130001";


}
