package com.youga.baking.ps.obj;

public class shoppingcartBase {

    /***
     * 购物车信息封装
     */

    public String comid = null;
    public String memberid = null;
    public String openid = null;
    public String comname = null;
    public String comnum = null;
    public String comstanderid = null;
    public String comstanderinnerid = null;
    public String comstandername = null;
    public String comprice = null;

    public shoppingcartBase(String comid, String memberid, String openid, String comname, String comnum, String comstanderid, String comstanderinnerid, String comstandername, String comprice) {
        this.comid = comid;
        this.memberid = memberid;
        this.openid = openid;
        this.comname = comname;
        this.comnum = comnum;
        this.comstanderid = comstanderid;
        this.comstanderinnerid = comstanderinnerid;
        this.comstandername = comstandername;
        this.comprice = comprice;
    }

    @Override
    public String toString() {
        return "shoppingcartBase{" +
                "comid='" + comid + '\'' +
                ", memberid='" + memberid + '\'' +
                ", openid='" + openid + '\'' +
                ", comname='" + comname + '\'' +
                ", comnum='" + comnum + '\'' +
                ", comstanderid='" + comstanderid + '\'' +
                ", comstanderinnerid='" + comstanderinnerid + '\'' +
                ", comstandername='" + comstandername + '\'' +
                ", comprice='" + comprice + '\'' +
                '}';
    }

    public String getComid() {
        return comid;
    }

    public void setComid(String comid) {
        this.comid = comid;
    }

    public String getMemberid() {
        return memberid;
    }

    public void setMemberid(String memberid) {
        this.memberid = memberid;
    }

    public String getOpenid() {
        return openid;
    }

    public void setOpenid(String openid) {
        this.openid = openid;
    }

    public String getComname() {
        return comname;
    }

    public void setComname(String comname) {
        this.comname = comname;
    }

    public String getComnum() {
        return comnum;
    }

    public void setComnum(String comnum) {
        this.comnum = comnum;
    }

    public String getComstanderid() {
        return comstanderid;
    }

    public void setComstanderid(String comstanderid) {
        this.comstanderid = comstanderid;
    }

    public String getComstanderinnerid() {
        return comstanderinnerid;
    }

    public void setComstanderinnerid(String comstanderinnerid) {
        this.comstanderinnerid = comstanderinnerid;
    }

    public String getComstandername() {
        return comstandername;
    }

    public void setComstandername(String comstandername) {
        this.comstandername = comstandername;
    }

    public String getComprice() {
        return comprice;
    }

    public void setComprice(String comprice) {
        this.comprice = comprice;
    }
}
