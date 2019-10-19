package com.youga.baking.ps.obj;

public class ComListBase {

    /***
     * 商品分类封装
     */

    public String shopid = null;
    public int clsInId = 0;
    public String clsName = null;
    public String clsDesc = null;

    //2019-06-23 新增购物车数量
    public int shoppingcartNum = 0;

    public ComListBase(String shopid, int clsInId, String clsName, String clsDesc) {
        this.shopid = shopid;
        this.clsInId = clsInId;
        this.clsName = clsName;
        this.clsDesc = clsDesc;
    }

    public int getShoppingcartNum() {
        return shoppingcartNum;
    }

    public void setShoppingcartNum(int shoppingcartNum) {
        this.shoppingcartNum = shoppingcartNum;
    }

    public String getShopid() {
        return shopid;
    }

    public void setShopid(String shopid) {
        this.shopid = shopid;
    }

    public int getClsInId() {
        return clsInId;
    }

    public void setClsInId(int clsInId) {
        this.clsInId = clsInId;
    }

    public String getClsName() {
        return clsName;
    }

    public void setClsName(String clsName) {
        this.clsName = clsName;
    }

    public String getClsDesc() {
        return clsDesc;
    }

    public void setClsDesc(String clsDesc) {
        this.clsDesc = clsDesc;
    }

    @Override
    public String toString() {
        return "ComListBase{" +
                "shopid='" + shopid + '\'' +
                ", clsInId=" + clsInId +
                ", clsName='" + clsName + '\'' +
                ", clsDesc='" + clsDesc + '\'' +
                '}';
    }
}
