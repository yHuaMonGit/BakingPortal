package com.youga.baking.ps.dao.impl;



import com.youga.baking.ps.dao.BaseDao;
import com.youga.baking.ps.dao.VerifyDao;
import com.youga.baking.ps.obj.VerifyCode;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class VerifyDaoImpl implements VerifyDao {
    @Override
    public void insertVerifyCode(VerifyCode code) {
        Connection conn = null;
        try {
            conn = BaseDao.getConnection();
            String sql;
            sql = "insert into youga_pet.verify_code (openid,verify_code,timestamp,veriFlag) " +
                    "value ( \"" +code.getOpenid()+"\""+
                    ",\"" +code.getCode()+"\""+
                    ",\"" +code.getStartTime()+
                    "\",\"" +code.getVeriFlag()+ "\");";
            PreparedStatement stmt = conn.prepareStatement(sql);
            int result = stmt.executeUpdate();
            BaseDao.closeAll(conn, stmt);
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    @Override
    public VerifyCode getVerifyCode(String openid) {
        Connection conn = null;
        VerifyCode verifyCode = null;
        try {
            conn = BaseDao.getConnection();
            String sql = "select * from youga_pet.verify_code where openid = \""+openid+"\";";
            PreparedStatement stmt = conn.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();

            if (rs.next())
            {
                rs.previous();
            }
            else {
                return new VerifyCode();
            }

            while(rs.next())
            {
                verifyCode=new VerifyCode(
                        rs.getString("verify_code"),
                        rs.getString("timestamp"),
                        rs.getString("openid"),
                        rs.getString("veriFlag")
                );
            }
            BaseDao.closeAll(conn, stmt, rs);

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return verifyCode;
    }

    @Override
    public void deleteVerifyCode(String openid) {

        Connection conn = null;
        try {
            conn = BaseDao.getConnection();
            String sql;
            sql = "delete from youga_pet.verify_code where openid = " +"\"" +openid+ "\";";
            PreparedStatement stmt = conn.prepareStatement(sql);
            int result = stmt.executeUpdate();
            BaseDao.closeAll(conn, stmt);
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
}
