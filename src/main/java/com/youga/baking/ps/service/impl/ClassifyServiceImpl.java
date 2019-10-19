package com.youga.baking.ps.service.impl;

import com.youga.baking.ps.dao.ClassifyDao;
import com.youga.baking.ps.dao.impl.ClassifyDaoImpl;
import com.youga.baking.ps.obj.*;
import com.youga.baking.ps.service.ClassifyService;
import com.youga.baking.ps.service.MemberService;

import java.util.List;

public class ClassifyServiceImpl implements ClassifyService {

    ClassifyDao classifyDao = new ClassifyDaoImpl();
    MemberService mbService = new MemberServiceImpl();

    @Override
    public String getAnnouncementInfo(String shopid) {

        String anounce = classifyDao.getAnouncement(shopid);
        if (null == anounce){
            return "";
        }else {
            return anounce;
        }
    }

    @Override
    public List<ComListBase> getComList(String shopid, String openid) {
        return classifyDao.getComList(shopid,openid);
    }

    @Override
    public List<ComDetailBase> getComDetailBaseList(String shopid, String clsInId) {
        return classifyDao.getComDetailListInfo(shopid,clsInId);
    }

    @Override
    public List<StanderBase> getStanderBase(String shopid, String stander_id, String com_id) {
        return classifyDao.getStanderBase(shopid,stander_id,com_id);
    }

    /***
     * 添加商品到购物车实现方法
     * 成功添加返回true;
     * 否则返回false;
     * @param openid
     * @param comstander
     * @param goodsid
     * @param shopid
     * @param comNum
     * @return
     */
    @Override
    public boolean addComToShoppingCart(String openid, String comstander, String goodsid, String shopid, String comNum) {

        //1.根据openid获取对应的用户信息
        MemberInfo member = mbService.getMemberInfo(openid);

        //2.拆解comstander拆接出对应规格价位
        //2019-07-10 新增业务逻辑：若com_stander为空，
        String com_stander = comstander.substring(0,8);
        String stander_inner_id = comstander.substring(8,comstander.length());

        //3.将商品信息插入shoppingcart；

        if (classifyDao.addComToShoppingCart(member.getMemberID(),openid,goodsid,com_stander,stander_inner_id,comNum)) {
            return true;
        }else return false;

    }

    @Override
    public List<shoppingcartBase> getShopCart(String shopid, String openid) {
        return classifyDao.getShopCart(shopid,openid);
    }

    @Override
    public String getShopCartNum(String openid) {
        return classifyDao.getShopCartNum(openid);
    }

    @Override
    public String ModifySingleShoppingCart(String openid, String comid, String innerId, int opadd) {
        return classifyDao.ModifySingleShoppingCart(openid,comid,innerId,opadd);
    }
}
