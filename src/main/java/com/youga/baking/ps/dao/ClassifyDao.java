package com.youga.baking.ps.dao;

import com.youga.baking.ps.obj.ComDetailBase;
import com.youga.baking.ps.obj.ComListBase;
import com.youga.baking.ps.obj.StanderBase;
import com.youga.baking.ps.obj.shoppingcartBase;

import java.util.List;

public interface ClassifyDao {

    /***
     * dao层实现
     * @param shopid
     * @return
     */
    String getAnouncement(String shopid);

    /***
     * 获取商品分类列表dao层实现
     * @param shopid
     * @param openid
     * @return
     */
    List<ComListBase> getComList(String shopid, String openid);

    /***
     * 根据所选分类ID获取商品详细列表
     * @param shopid
     * @param clsInId
     * @return
     */
    List<ComDetailBase> getComDetailListInfo(String shopid, String clsInId);

    /***
     * 获取商品规格信息Dao层实现
     * @param shopid
     * @param stander_id
     * @param com_id
     * @return
     */
    List<StanderBase> getStanderBase(String shopid, String stander_id, String com_id);

    /***
     * Dao层实现，若插入失败则返回false
     * @param memberID
     * @param openid
     * @param goodsid
     * @param com_stander
     * @param stander_inner_id
     * @param comNum
     * @return
     */
    boolean addComToShoppingCart(String memberID, String openid, String goodsid, String com_stander, String stander_inner_id, String comNum);

    /***
     * Dao层实现
     * @param shopid
     * @param openid
     * @return
     */
    List<shoppingcartBase> getShopCart(String shopid, String openid);

    /***
     * 获取总数
     * @param openid
     * @return
     */
    String getShopCartNum(String openid);

    /***
     * Dao层实现，存储过程修改数据、返回修改后单品记录
     * @param openid
     * @param comid
     * @param innerId
     * @param opadd
     * @return
     */
    String ModifySingleShoppingCart(String openid, String comid, String innerId, int opadd);
}
