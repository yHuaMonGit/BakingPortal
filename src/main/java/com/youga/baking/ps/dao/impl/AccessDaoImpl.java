package com.youga.baking.ps.dao.impl;

import com.youga.baking.ps.dao.AccessDao;
import com.youga.baking.ps.dao.BaseDao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class AccessDaoImpl implements AccessDao {
    @Override
    public String getAccessToken() {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;

        String access = null;
        try {
            conn = BaseDao.getConnection();
            String sql = "select * from  youga_pet.access_token ;";
            stmt = conn.prepareStatement(sql);
            rs = stmt.executeQuery();
            if (rs.next())
            {
                rs.previous();
            }
            else {
                access = null;
            }
            while(rs.next())
            {
                access = rs.getString("access_token");
            }
            BaseDao.closeAll(conn, stmt, rs);
        } catch (SQLException e) {
            try {
                BaseDao.closeAll(conn, stmt, rs);
            } catch (SQLException e1) {
                e1.printStackTrace();
            }
            e.printStackTrace();
        }

        return access;
    }
}
