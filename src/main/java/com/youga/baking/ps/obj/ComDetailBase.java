package com.youga.baking.ps.obj;

import java.util.List;

public class ComDetailBase {

    /***
     * 商品详细信息封装
     */

    String shop_id = null;
    String com_id = null;
    String com_name	= null;
    String com_type	= null;
    float com_price = 0f;
    String com_icon_src	= null;
    int com_stander_flg = 0;
    String com_stander_id = null;
    int cls_in_id = 0;
    String cls_name	= null;
    int instock = 0;
    String family = null;
    String brand = null;
    List<String> standerList = null;

    //2019-06-24 新增一个商品数量统计
    int com_num = 0;
    String stander_col;
    int stander_inner_id = 0;

    //2019-06-24 新增商品折扣识别标识
    int isOffCom = 0;

    public ComDetailBase(String com_id, String com_name, String com_type, float com_price, String com_icon_src, String cls_name) {
        this.com_id = com_id;
        this.com_name = com_name;
        this.com_type = com_type;
        this.com_price = com_price;
        this.com_icon_src = com_icon_src;
        this.cls_name = cls_name;
    }

    public int getIsOffCom() {
        return isOffCom;
    }

    public void setIsOffCom(int isOffCom) {
        this.isOffCom = isOffCom;
    }

    public int getStander_inner_id() {
        return stander_inner_id;
    }

    public void setStander_inner_id(int stander_inner_id) {
        this.stander_inner_id = stander_inner_id;
    }

    public String getStander_col() {
        return stander_col;
    }

    public void setStander_col(String stander_col) {
        this.stander_col = stander_col;
    }

    public int getCom_num() {
        return com_num;
    }

    public void setCom_num(int com_num) {
        this.com_num = com_num;
    }

    public String getShop_id() {
        return shop_id;
    }

    public void setShop_id(String shop_id) {
        this.shop_id = shop_id;
    }

    public String getCom_id() {
        return com_id;
    }

    public void setCom_id(String com_id) {
        this.com_id = com_id;
    }

    public String getCom_name() {
        return com_name;
    }

    public void setCom_name(String com_name) {
        this.com_name = com_name;
    }

    public String getCom_type() {
        return com_type;
    }

    public void setCom_type(String com_type) {
        this.com_type = com_type;
    }

    public float getCom_price() {
        return com_price;
    }

    public void setCom_price(float com_price) {
        this.com_price = com_price;
    }

    public String getCom_icon_src() {
        return com_icon_src;
    }

    public void setCom_icon_src(String com_icon_src) {
        this.com_icon_src = com_icon_src;
    }

    public int getCom_stander_flg() {
        return com_stander_flg;
    }

    public void setCom_stander_flg(int com_stander_flg) {
        this.com_stander_flg = com_stander_flg;
    }

    public String getCom_stander_id() {
        return com_stander_id;
    }

    public void setCom_stander_id(String com_stander_id) {
        this.com_stander_id = com_stander_id;
    }

    public int getCls_in_id() {
        return cls_in_id;
    }

    public void setCls_in_id(int cls_in_id) {
        this.cls_in_id = cls_in_id;
    }

    public String getCls_name() {
        return cls_name;
    }

    public void setCls_name(String cls_name) {
        this.cls_name = cls_name;
    }

    public int getInstock() {
        return instock;
    }

    public void setInstock(int instock) {
        this.instock = instock;
    }

    public String getFamily() {
        return family;
    }

    public void setFamily(String family) {
        this.family = family;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public List<String> getStanderList() {
        return standerList;
    }

    public void setStanderList(List<String> standerList) {
        this.standerList = standerList;
    }

    @Override
    public String toString() {
        return "ComDetailBase{" +
                "shop_id='" + shop_id + '\'' +
                ", com_id='" + com_id + '\'' +
                ", com_name='" + com_name + '\'' +
                ", com_type='" + com_type + '\'' +
                ", com_price=" + com_price +
                ", com_icon_src='" + com_icon_src + '\'' +
                ", com_stander_flg=" + com_stander_flg +
                ", com_stander_id='" + com_stander_id + '\'' +
                ", cls_in_id=" + cls_in_id +
                ", cls_name='" + cls_name + '\'' +
                ", instock=" + instock +
                ", family='" + family + '\'' +
                ", brand='" + brand + '\'' +
                '}';
    }
}
