package com.youga.baking.ps.service;



import com.youga.baking.ps.obj.AddreesInfo;

import java.util.List;

public interface AdreesService {

    /**
     * 获取所有地址信息
     */
    public List<AddreesInfo> getAllAddrees(String openid);

    /**
     * 获取单个地址（用户下单）
     */
    public AddreesInfo getSingleAddrees(String openid, String addreesid);

    /**
     * 获取单个地址id
     */
    public String getSingleAddreesId(String openid, String addreesid);

    /**
     * 插入一条地址信息
     */
    public void InsertAddreesInfo(AddreesInfo addreesInfo);


    public String getDefaultId(String openid);

    public void modifyDefault(AddreesInfo modifyInfo, AddreesInfo defaultInfo);

    /***
     * 获取当前用户地址数量
     * @param openid
     * @return
     */
    String getAddressNum(String openid);
}
