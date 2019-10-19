package com.youga.baking.ps.dao.impl;


import com.youga.baking.ps.dao.AddressDao;
import com.youga.baking.ps.dao.BaseDao;
import com.youga.baking.ps.obj.AddreesInfo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AddreessDaoImpl implements AddressDao {
    @Override
    public void InsertAddressInfo(AddreesInfo addreesInfo) {
        Connection conn = null;
        try {

            if(getCountOfAdress(addreesInfo.getOpenid()).equals("0"))
            {
                addreesInfo.setIsChoice("1");
            }else
                {
                    addreesInfo.setIsChoice("0");
                }

            conn = BaseDao.getConnection();
            String sql;
            sql = "insert into youga_pet.address_info (addressid,openid,msisdn,username,province,city,area,addreesdetail,ecode,isChoice) " +
                    "value ( \"" +addreesInfo.getAddressid()+"\""+
                    ",\"" +addreesInfo.getOpenid()+"\""+
                    ",\"" +addreesInfo.getMsisdn()+"\""+
                    ",\"" +addreesInfo.getUsername()+
                    "\",\"" +addreesInfo.getProvince()+
                    "\",\"" +addreesInfo.getCity()+
                    "\",\"" +addreesInfo.getArea()+
                    "\",\"" +addreesInfo.getAddreesdetail()+
                    "\",\"" +addreesInfo.getEcode()+
                    "\",\"" +addreesInfo.getIsChoice()+"\");";

            PreparedStatement stmt = conn.prepareStatement(sql);
            int result = stmt.executeUpdate();
            BaseDao.closeAll(conn, stmt);

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    @Override
    public List<AddreesInfo> getAllAddress(String openid) {
        Connection conn = null;
        List<AddreesInfo> addreesInfoList = new ArrayList<>();
        try {
            conn = BaseDao.getConnection();
            String sql = "select * from youga_pet.address_info where openid = \""+openid+"\";";
            PreparedStatement stmt = conn.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();
            while(rs.next())
            {

                AddreesInfo addreesInfo=new AddreesInfo(
                        rs.getString("addressid"),
                        rs.getString("openid"),
                        rs.getString("msisdn"),
                        rs.getString("username"),
                        rs.getString("province"),
                        rs.getString("city"),
                        rs.getString("area"),
                        rs.getString("addreesdetail"),
                        rs.getString("ecode")
                );
                addreesInfo.setIsChoice(rs.getString("isChoice"));
                addreesInfoList.add(addreesInfo);
            }
            BaseDao.closeAll(conn, stmt, rs);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return addreesInfoList;
    }

    @Override
    public AddreesInfo getSingleAddress(String addressid) {
        Connection conn = null;
        String Tablename = "";
        AddreesInfo addreesInfo = null;
        try {
            conn = BaseDao.getConnection();
            String sql = "select * from  youga_pet.address_info where addressid = \""+addressid+"\";";
            PreparedStatement stmt = conn.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();
            if (rs.next())
            {
                rs.previous();
            }
            else {
                return new AddreesInfo();
            }
            while(rs.next())
            {
                addreesInfo = new AddreesInfo(
                        rs.getString("addressid"),
                        rs.getString("openid"),
                        rs.getString("msisdn"),
                        rs.getString("username"),
                        rs.getString("province"),
                        rs.getString("city"),
                        rs.getString("area"),
                        rs.getString("addreesdetail"),
                        rs.getString("ecode")
                );
                addreesInfo.setIsChoice(rs.getString("isChoice"));
            }
            BaseDao.closeAll(conn, stmt, rs);

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return addreesInfo;
    }


    @Override
    public String getSingleAddressId(String addressid) {
        return null;
    }

    @Override
    public String getCountOfAdress(String openid) {
        Connection conn = null;
        String result = "";
        AddreesInfo addreesInfo = null;
        try {
            conn = BaseDao.getConnection();
            String sql = "select count(*) from  youga_pet.address_info where openid = \""+openid+"\";";
            PreparedStatement stmt = conn.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();
            if (rs.next())
            {
                rs.previous();
            }
            else {
                return null;
            }
            while(rs.next())
            {
                result = rs.getString(1);
            }
            BaseDao.closeAll(conn, stmt, rs);

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public String getIDByDefaultAddress(String openid) {
        Connection conn = null;
        String result = "";
        AddreesInfo addreesInfo = null;
        try {
            conn = BaseDao.getConnection();
            String sql = "select addressid from  youga_pet.address_info where openid = \""+openid+"\"" +
                    "and isChoice = 1;";
            PreparedStatement stmt = conn.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();
            if (rs.next())
            {
                rs.previous();
            }
            else {
                return null;
            }
            while(rs.next())
            {
                result = rs.getString(1);
            }
            BaseDao.closeAll(conn, stmt, rs);

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    public void updateAddress(AddreesInfo addreesInfo)
    {
        String retrunCode = null;
        Connection conn = null;
        try {
            conn = BaseDao.getConnection();
            String sql;
            sql = "update youga_pet.address_info set msisdn = \""
                    +addreesInfo.getMsisdn()+"\","+
                    "username = \"" +addreesInfo.getUsername()+
                    "\",province = \"" +addreesInfo.getProvince()+
                    "\",city = \"" +addreesInfo.getCity()+
                    "\",area = \"" +addreesInfo.getArea()+
                    "\",addreesdetail = \"" +addreesInfo.getAddreesdetail()+
                    "\",ecode = \"" +addreesInfo.getEcode()+
                    "\",isChoice = \"" +addreesInfo.getIsChoice()+
                    "\" Where openid = \""+addreesInfo.getOpenid()+
                    "\" and addressid = \""+addreesInfo.getAddressid()+"\";";

            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.executeUpdate();

            BaseDao.closeAll(conn, stmt);

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    @Override
    public String getAddressNum(String openid) {
        Connection conn = null;
        String result = "";
        AddreesInfo addreesInfo = null;
        try {
            conn = BaseDao.getConnection();
            String sql = "select count(*) from  youga_pet.address_info where openid = \""+openid+"\"";
            PreparedStatement stmt = conn.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();
            if (rs.next())
            {
                rs.previous();
            }
            else {
                return null;
            }
            while(rs.next())
            {
                result = rs.getString(1);
            }
            BaseDao.closeAll(conn, stmt, rs);

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

}
