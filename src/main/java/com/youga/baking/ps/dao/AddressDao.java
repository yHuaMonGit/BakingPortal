package com.youga.baking.ps.dao;

import com.youga.baking.ps.obj.AddreesInfo;


import java.util.List;

public interface AddressDao {

    /**
     * Insert Address Info
     */
    public void InsertAddressInfo(AddreesInfo addreesInfo);

    /**
     * Select All Address
     */
    public List<AddreesInfo> getAllAddress(String openid);

    /**
     * select single Address
     */
    public AddreesInfo getSingleAddress(String addressid);

    /**
     * select AddressId
     */
    public String getSingleAddressId(String addressid);


    /**
     * select user address count
     */
    public String getCountOfAdress(String openid);

    /**
     * get the default address
     */
    public String getIDByDefaultAddress(String openid);

    /**
     * modify address
     */
    public void updateAddress(AddreesInfo addreesInfo);

    /***
     * Dao层实现
     * @param openid
     * @return
     */
    String getAddressNum(String openid);
}
