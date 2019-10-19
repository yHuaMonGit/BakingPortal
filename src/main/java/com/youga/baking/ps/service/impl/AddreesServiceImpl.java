package com.youga.baking.ps.service.impl;


import com.youga.baking.ps.dao.AddressDao;
import com.youga.baking.ps.dao.impl.AddreessDaoImpl;
import com.youga.baking.ps.obj.AddreesInfo;
import com.youga.baking.ps.service.AdreesService;

import java.util.List;

public class AddreesServiceImpl implements AdreesService {

    AddressDao addressDao = new AddreessDaoImpl();

    @Override
    public List<AddreesInfo> getAllAddrees(String openid) {
        return addressDao.getAllAddress(openid);
    }

    @Override
    public AddreesInfo getSingleAddrees(String openid, String addreesid) {
        return addressDao.getSingleAddress(addreesid);
    }

    @Override
    public String getSingleAddreesId(String openid, String addreesid) {
        return null;
    }

    @Override
    public void InsertAddreesInfo(AddreesInfo addreesInfo) {

        addressDao.InsertAddressInfo(addreesInfo);

    }

    @Override
    public String getDefaultId(String openid) {
        return addressDao.getIDByDefaultAddress(openid);
    }

    @Override
    public void modifyDefault(AddreesInfo modifyInfo, AddreesInfo defaultInfo) {

        modifyInfo.setIsChoice("1");
        defaultInfo.setIsChoice("0");

        addressDao.updateAddress(modifyInfo);
        addressDao.updateAddress(defaultInfo);


    }

    @Override
    public String getAddressNum(String openid) {
        return addressDao.getAddressNum(openid);
    }
}
