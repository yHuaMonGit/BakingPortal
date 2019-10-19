package com.youga.baking.ps.service.impl;

import com.youga.baking.ps.dao.AccessDao;
import com.youga.baking.ps.dao.impl.AccessDaoImpl;
import com.youga.baking.ps.service.AccessService;

public class AccessServiceImpl implements AccessService {

    AccessDao accessDao = new AccessDaoImpl();

    @Override
    public String getAccessToken() {
        return accessDao.getAccessToken();
    }
}
