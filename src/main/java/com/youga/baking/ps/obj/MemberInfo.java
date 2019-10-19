package com.youga.baking.ps.obj;

public class MemberInfo {


    public static String MEMBER_LEVEL_1 = "青铜";
    public static String MEMBER_LEVEL_2 = "白银";
    public static String MEMBER_LEVEL_3 = "黄金";
    public static String MEMBER_LEVEL_4 = "白金";
    public static String MEMBER_LEVEL_5 = "钻石";
    public static String MEMBER_LEVEL_6 = "王者";


    public String memberID = null ;
    public String memberLevel = null ;
    //积分
    public String integral = null ;
    public String memberFlag = null ;
    public String msisdn = null ;
    public String openid = null ;
    //累计消费金额
    public String amount = "";
    public String balance = null;

    //2019-01-02 移植字段 兼容offline
    public String serviceOff = null;
    public String goodsOff = null;
    public String petName = "";
    public String hisAmount = null;
    public String memberLevelId = null;

    //2019-01-02 新增用户昵称；
    public String nickName = null;

    //2019-04-22 新增身份相关字段
    String realname = null;
    String idCard = null;
    String birthday = null;
    String sex = null;
    String age = null;
    String nativeAddress = null;

    public MemberInfo(String memberID, String memberLevel, String integral, String memberFlag, String msisdn, String openid, String amount, String balance) {
        this.memberID = memberID;
        this.memberLevel = memberLevel;
        this.integral = integral;
        this.memberFlag = memberFlag;
        this.msisdn = msisdn;
        this.openid = openid;
        this.amount = amount;
        this.balance = balance;
    }
    public MemberInfo(){}

    public MemberInfo(String memberID, String memberLevel, String integral, String memberFlag, String msisdn, String openid,
                      String amount, String balance, String serviceOff, String goodsOff,
                      String hisAmount, String realname,
                      String idCard, String birthday, String sex, String nativeAddress,String age) {
        this.memberID = memberID;
        this.memberLevel = memberLevel;
        this.integral = integral;
        this.memberFlag = memberFlag;
        this.msisdn = msisdn;
        this.openid = openid;
        this.amount = amount;
        this.balance = balance;
        this.serviceOff = serviceOff;
        this.goodsOff = goodsOff;
        this.hisAmount = hisAmount;
        this.realname = realname;
        this.idCard = idCard;
        this.birthday = birthday;
        this.sex = sex;
        this.nativeAddress = nativeAddress;
        this.age = age;
    }

    public static String getUnAuthLevel() {
        return "游客";
    }

    public static String getUnAuthInter() {
        return "0";
    }

    public static String getUnAuthBalence() {
        return "0";
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public String getBalance() {
        return balance;
    }

    public void setBalance(String balance) {
        this.balance = balance;
    }

    public String getMemberID() {
        return memberID;
    }

    public void setMemberID(String memberID) {
        this.memberID = memberID;
    }

    public String getMemberLevel() {
        return memberLevel;
    }

    public void setMemberLevel(String memberLevel) {
        this.memberLevelId = memberLevel;
        this.memberLevel = this.checkMemberLeval(memberLevel);
    }

    public String getMemberLevelId() {
        return memberLevelId;
    }

    public void setMemberLevelId(String memberLevelId) {
        this.memberLevelId = memberLevelId;
    }

    public String getIntegral() {
        return integral;
    }

    public void setIntegral(String integral) {
        this.integral = integral;
    }

    public String getMemberFlag() {
        return memberFlag;
    }

    public void setMemberFlag(String memberFlag) {
        this.memberFlag = memberFlag;
    }

    public String getMsisdn() {
        return msisdn;
    }

    public void setMsisdn(String msisdn) {
        this.msisdn = msisdn;
    }

    public String getOpenid() {
        return openid;
    }

    public void setOpenid(String openid) {
        this.openid = openid;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        amount = amount;
    }


    public String getServiceOff() {
        return serviceOff;
    }

    public void setServiceOff(String serviceOff) {
        this.serviceOff = serviceOff;
    }

    public String getGoodsOff() {
        return goodsOff;
    }

    public void setGoodsOff(String goodsOff) {
        this.goodsOff = goodsOff;
    }

    public String getPetName() {
        return petName;
    }

    public void setPetName(String petName) {
        this.petName = petName;
    }

    public String getHisAmount() {
        return hisAmount;
    }

    public void setHisAmount(String hisAmount) {
        this.hisAmount = hisAmount;
    }

    private String checkMemberLeval(String memberLevel) {

        if(memberLevel.equals("1"))
        {
            return MEMBER_LEVEL_1;
        }else if(memberLevel.equals("2"))
        {

            return MEMBER_LEVEL_2;
        }else if(memberLevel.equals("3"))
        {

            return MEMBER_LEVEL_3;
        }else if(memberLevel.equals("4"))
        {

            return MEMBER_LEVEL_4;
        }else if(memberLevel.equals("5"))
        {
            return MEMBER_LEVEL_5;
        }else if(memberLevel.equals("6"))
        {

            return MEMBER_LEVEL_6;
        }else
        {
            return "Error";
        }

    }

    public String getRealname() {
        return realname;
    }

    public void setRealname(String realname) {
        this.realname = realname;
    }

    public String getIdCard() {
        return idCard;
    }

    public void setIdCard(String idCard) {
        this.idCard = idCard;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getNativeAddress() {
        return nativeAddress;
    }

    public void setNativeAddress(String nativeAddress) {
        this.nativeAddress = nativeAddress;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public void settlement(String actAmount) {

        float balance = Float.valueOf(this.balance);
        float act = Float.valueOf(actAmount);
        this.balance = String.format("%.2f",balance-act);
    }
}
