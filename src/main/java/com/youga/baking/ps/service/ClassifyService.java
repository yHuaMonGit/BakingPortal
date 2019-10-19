package com.youga.baking.ps.service;

import com.youga.baking.ps.obj.ComDetailBase;
import com.youga.baking.ps.obj.ComListBase;
import com.youga.baking.ps.obj.StanderBase;
import com.youga.baking.ps.obj.shoppingcartBase;

import java.util.List;

public interface ClassifyService {

    /***
     * 获取公告
     * @param shopid
     * @return
     */
    String getAnnouncementInfo(String shopid);


    /***
     * 获取商品分类列表
     * @param shopid
     * @param openid
     * @return
     */
    List<ComListBase> getComList(String shopid, String openid);

    /***
     * 获取商品详细列表
     * @param shopid
     * @param clsInId
     * @return
     */
    List<ComDetailBase> getComDetailBaseList(String shopid, String clsInId);

    /***
     * 获取商品规格信息
     * @param shopid
     * @param stander_id
     * @param com_id
     * @return
     */
    List<StanderBase> getStanderBase(String shopid, String stander_id, String com_id);


    /***
     * 添加商品信息到购物车列表
     * @param openid
     * @param comstander
     * @param goodsid
     * @param shopid
     * @param comNum
     * @return
     */
    boolean addComToShoppingCart(String openid, String comstander, String goodsid, String shopid, String comNum);

    /***
     * 获取购物车商品信息列表
     * @param shopid
     * @param openid
     * @return
     */
    List<shoppingcartBase> getShopCart(String shopid, String openid);

    /***
     * 获取Shoppingcart 总数量
     * @param openid
     * @return
     */
    String getShopCartNum(String openid);

    /***
     * 修改购物车数据，并返回修改后单品价值
     * @param openid
     * @param comid
     * @param innerId
     * @param opadd
     * @return
     */
    String ModifySingleShoppingCart(String openid, String comid, String innerId, int opadd);
}
