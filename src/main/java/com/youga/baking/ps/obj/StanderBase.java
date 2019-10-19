package com.youga.baking.ps.obj;

public class StanderBase implements Comparable<StanderBase>{

    /***
     * 规格标准封装
     */

    private String stander_id;

    private String stander_inner_id;

    private String stander_col;

    private String price;

    //2019-07-20 规格中新增instock字段,增加商品对应规格库存描述
    //下一个版本优化stander整体的逻辑
    private String instock;

    public StanderBase(String stander_id, String stander_inner_id, String stander_col, String price) {
        this.stander_id = stander_id;
        this.stander_inner_id = stander_inner_id;
        this.stander_col = stander_col;
        this.price = price;
    }

    @Override
    public String toString() {
        return "StanderBase{" +
                "stander_id='" + stander_id + '\'' +
                ", stander_inner_id='" + stander_inner_id + '\'' +
                ", stander_col='" + stander_col + '\'' +
                ", price='" + price + '\'' +
                '}';
    }

    public String getInstock() {
        return instock;
    }

    public void setInstock(String instock) {
        this.instock = instock;
    }

    public String getStander_id() {
        return stander_id;
    }

    public void setStander_id(String stander_id) {
        this.stander_id = stander_id;
    }

    public String getStander_inner_id() {
        return stander_inner_id;
    }

    public void setStander_inner_id(String stander_inner_id) {
        this.stander_inner_id = stander_inner_id;
    }

    public String getStander_col() {
        return stander_col;
    }

    public void setStander_col(String stander_col) {
        this.stander_col = stander_col;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    @Override
    public int compareTo(StanderBase o) {
        if (Integer.valueOf(this.stander_inner_id) > Integer.valueOf(o.stander_inner_id)){
            return 1;
        }else {
            return -1;
        }
    }
}
