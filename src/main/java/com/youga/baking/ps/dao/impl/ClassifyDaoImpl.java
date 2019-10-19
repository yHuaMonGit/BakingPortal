package com.youga.baking.ps.dao.impl;

import com.youga.baking.ps.dao.BaseDao;
import com.youga.baking.ps.dao.ClassifyDao;
import com.youga.baking.ps.obj.ComDetailBase;
import com.youga.baking.ps.obj.ComListBase;
import com.youga.baking.ps.obj.StanderBase;
import com.youga.baking.ps.obj.shoppingcartBase;


import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ClassifyDaoImpl implements ClassifyDao {
    @Override
    public String getAnouncement(String shopid) {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        String announce = null;

        try {
            conn = BaseDao.getConnection();
            String sql = "select announce from youga_pet.portal_store_announcement_info where shop_id = \""+shopid+"\";";
            stmt = conn.prepareStatement(sql);
            rs = stmt.executeQuery();

            if (rs.next())
            {
                rs.previous();
            }
            else {
                return announce;
            }

            while(rs.next())
            {
                announce = rs.getString("announce");
            }
            BaseDao.closeAll(conn, stmt, rs);

        } catch (SQLException e) {
            e.printStackTrace();
            try {
                BaseDao.closeAll(conn, stmt, rs);
            } catch (SQLException e1) {
                //加日志
                e1.printStackTrace();
            }
        }
        return announce;
    }

    @Override
    public List<ComListBase> getComList(String shopid, String openid) {
        List<ComListBase> goodsBaseList = new ArrayList<>();
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {
            conn = BaseDao.getConnection();
            String sql = "select * from  youga_pet.portal_store_commodity_type_info where shop_id = \""+shopid+"\";";
            stmt = conn.prepareStatement(sql);
            rs =  stmt.executeQuery();
//goodsid,goodsname,goodsprice,instock,stander,classify

            if (rs.next())
            {
                rs.previous();
            }
            else {
                return goodsBaseList;
            }
            while(rs.next())
            {
                ComListBase goodsBase = new ComListBase(
                        rs.getString("shop_id"),
                rs.getInt("cls_in_id"),
                rs.getString("cls_name"),
                rs.getString("cls_desc")
                );
                goodsBaseList.add(goodsBase);
            }

            BaseDao.closeAll(conn, stmt);
        } catch (SQLException e) {
            e.printStackTrace();
            try {
                BaseDao.closeAll(conn, stmt);
            } catch (SQLException e1) {
                e1.printStackTrace();
            }
        }
        return goodsBaseList;
    }

    @Override
    public List<ComDetailBase> getComDetailListInfo(String shopid, String clsInId) {
        List<ComDetailBase> goodsBaseList = new ArrayList<>();

        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {
            conn = BaseDao.getConnection();
            String sql = "select * from  youga_pet.portal_store_commodity_detail_info where shop_id = \""+shopid+"\" and " +
                    "cls_in_id = \""+clsInId+"\" and com_display = 1;";
            stmt = conn.prepareStatement(sql);
            rs =  stmt.executeQuery();
//goodsid,goodsname,goodsprice,instock,stander,classify

            if (rs.next())
            {
                rs.previous();
            }
            else {
                return goodsBaseList;
            }
            while(rs.next())
            {
                ComDetailBase goodsBase = new ComDetailBase(
                        rs.getString("com_id"),
                        rs.getString("com_name"),
                        rs.getString("com_type"),
                        //这里获得的是页面显示的初始价格，根据规格不同有不同的价位
                        rs.getFloat("com_price"),
                        rs.getString("com_icon_src"),
                        rs.getString("cls_name")
                );
                goodsBase.setCom_stander_id(rs.getString("com_stander_id"));
                goodsBaseList.add(goodsBase);
            }
            BaseDao.closeAll(conn, stmt);
        } catch (SQLException e) {
            e.printStackTrace();
            try {
                BaseDao.closeAll(conn, stmt);
            } catch (SQLException e1) {
                e1.printStackTrace();
            }
        }
        return goodsBaseList;
    }

    @Override
    public List<StanderBase> getStanderBase(String shopid, String stander_id, String com_id) {
        List<StanderBase> standerBasesList = new ArrayList<>();

        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {
            conn = BaseDao.getConnection();
            String sql = "select * from  youga_pet.portal_store_commodity_stander_info where stander_id = \""+stander_id+"\";";
            stmt = conn.prepareStatement(sql);
            rs =  stmt.executeQuery();
//goodsid,goodsname,goodsprice,instock,stander,classify

            if (rs.next())
            {
                rs.previous();
            }
            else {
                return standerBasesList;
            }
            while(rs.next())
            {
                StanderBase stander = new StanderBase(
                        rs.getString("stander_id"),
                        rs.getString("stander_inner_id"),
                        rs.getString("stander_col"),
                        //这里获得的是页面显示的初始价格，根据规格不同有不同的价位
                        rs.getString("price")
                );
                stander.setInstock(getComInstock(com_id));
                standerBasesList.add(stander);
            }
            BaseDao.closeAll(conn, stmt);
        } catch (SQLException e) {
            e.printStackTrace();
            try {
                BaseDao.closeAll(conn, stmt);
            } catch (SQLException e1) {
                e1.printStackTrace();
            }
        }
        return standerBasesList;
    }

    private String getComInstock(String com_id) {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        String result = "";
        try {
            conn = BaseDao.getConnection();
            String sql = "select instock from  youga_pet.portal_store_commodity_detail_info where com_id = \""+com_id+"\";";
            stmt = conn.prepareStatement(sql);
            rs =  stmt.executeQuery();
//goodsid,goodsname,goodsprice,instock,stander,classify

            if (rs.next())
            {
                rs.previous();
            }
            else {
                return "0";
            }
            while(rs.next())
            {
                result = rs.getString("instock");
            }
            BaseDao.closeAll(conn, stmt);
        } catch (SQLException e) {
            e.printStackTrace();
            try {
                BaseDao.closeAll(conn, stmt);
            } catch (SQLException e1) {
                e1.printStackTrace();
            }
        }
        return result;

    }

    @Override
    public boolean addComToShoppingCart(String memberID, String openid, String goodsid, String com_stander, String stander_inner_id, String comNum) {

        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;

        //定义使用变量

        try {
            conn = BaseDao.getConnection();
            //第一个sql获取价位

            CallableStatement c=conn.prepareCall("{call AddComToShoppingCart(?,?,?,?,?,?)}");
            c.setString(1,openid);
            c.setString(2,goodsid);
            c.setInt(3,Integer.valueOf(comNum));
            c.setString(4,com_stander);
            c.setString(5,memberID);
            c.setInt(6,Integer.valueOf(stander_inner_id));
            c.execute();

            //存储过程插入,存储过程直接判断数据库中是否有相同规格的商品；


            BaseDao.closeAll(conn, stmt);
        } catch (SQLException e) {
            e.printStackTrace();
            try {
                BaseDao.closeAll(conn, stmt);
            } catch (SQLException e1) {
                e1.printStackTrace();
            }
        }
        return true;

    }

    @Override
    public List<shoppingcartBase> getShopCart(String shopid, String openid) {
        List<shoppingcartBase> shoppingcartBaseList = new ArrayList<>();

        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {
            conn = BaseDao.getConnection();
            String sql = "select * from  youga_pet.shoppingcart_viewer where openid = \""+openid+"\";";
            stmt = conn.prepareStatement(sql);
            rs =  stmt.executeQuery();
//goodsid,goodsname,goodsprice,instock,stander,classify

            if (rs.next())
            {
                rs.previous();
            }
            else {
                return shoppingcartBaseList;
            }
            while(rs.next())
            {
                shoppingcartBase sigleLine = new shoppingcartBase(
                        rs.getString("comid"),
                        rs.getString("memberid"),
                        rs.getString("openid"),
                        rs.getString("com_name"),
                        rs.getString("comnum"),
                        rs.getString("comstander"),
                        rs.getString("comstanderinnerid"),
                        rs.getString("stander_col"),
                        //这里获得的是页面显示的初始价格，根据规格不同有不同的价位
                        rs.getString("comprice")
                );
                shoppingcartBaseList.add(sigleLine);
            }
            BaseDao.closeAll(conn, stmt);
        } catch (SQLException e) {
            e.printStackTrace();
            try {
                BaseDao.closeAll(conn, stmt);
            } catch (SQLException e1) {
                e1.printStackTrace();
            }
        }
        return shoppingcartBaseList;
    }

    @Override
    public String getShopCartNum(String openid) {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        String result = null;

        try {
            conn = BaseDao.getConnection();
            String sql = "select sum(comnum),sum(comprice*comnum) from youga_pet.shoppingcart_viewer where openid = \""+openid+"\";";
            stmt = conn.prepareStatement(sql);
            rs =  stmt.executeQuery();
//goodsid,goodsname,goodsprice,instock,stander,classify

            if (rs.next())
            {
                rs.previous();
            }
            else {
                return "0";
            }
            while(rs.next())
            {
                String value1 = rs.getString(1);
                if (null == value1){
                    result = null;
                }else {
                    result = value1 +","+ rs.getString(2);
                }
            }
            BaseDao.closeAll(conn, stmt);
        } catch (SQLException e) {
            e.printStackTrace();
            try {
                BaseDao.closeAll(conn, stmt);
            } catch (SQLException e1) {
                e1.printStackTrace();
            }
        }
        if (result != null){
            return result;
        }else {
            return "0,0";
        }

    }

    @Override
    public String ModifySingleShoppingCart(String openid, String comid, String innerId, int opadd) {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;

        String result = "0";

        //定义使用变量

        try {
            conn = BaseDao.getConnection();
            //第一个sql获取价位

            CallableStatement c=conn.prepareCall("{call modifyShoppingCartRecord(?,?,?,?)}");
            c.setString(1,openid);
            c.setString(2,comid);
            c.setString(3,innerId);
            c.setInt(4,opadd);
            c.execute();

            //存储过程执行完后，获取单个商品价值总和；

            String sql = "select sum(comprice*comnum) from youga_pet.shoppingcart_viewer where openid = \""+openid+"\" and " +
                    "comid = \""+comid+"\" and comstanderinnerid = \""+innerId+"\";";

            stmt = conn.prepareStatement(sql);
            rs =  stmt.executeQuery();
            if (rs.next())
            {
                rs.previous();
            }
            else {
                return "0";
            }
            while(rs.next())
            {
                result = rs.getString(1);
            }


            BaseDao.closeAll(conn, stmt);
        } catch (SQLException e) {
            e.printStackTrace();
            try {
                BaseDao.closeAll(conn, stmt);
            } catch (SQLException e1) {
                e1.printStackTrace();
            }
        }

        if (null == result||result.equals("null")){
            return "0";
        }
        return result;
    }
}
