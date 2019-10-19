package com.youga.baking.ps.dao.impl;

import com.youga.baking.ps.dao.BaseDao;
import com.youga.baking.ps.dao.MemberDao;
import com.youga.baking.ps.obj.MemberInfo;
import com.youga.baking.ps.obj.ReturnCode;
import com.youga.baking.ps.obj.WxInfo;

import java.sql.*;

public class MemberDaoImpl implements MemberDao {
    @Override
    public String isOpenidExist(String openid) {
        String retrunCode = null;
        Connection conn = null;
        try {
            conn = BaseDao.getConnection();
            String sql = "select count(1) from youga_pet.user_wx_info where openid = \""+openid+"\";";
            PreparedStatement stmt = conn.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();
            while(rs.next())
            {
                int rows = rs.getInt(1);
                if ( rows > 0 && rows <= 1 )
                {
                    retrunCode = ReturnCode.SUCCESS_CODE;
                }
                else if ( rows == 0 )
                {
                    retrunCode =  ReturnCode.OPENID_NOD_FOUND;
                }
                else if ( rows > 1 )
                {
                    retrunCode = ReturnCode.OPENID_REPEAT;
                }
            }
            BaseDao.closeAll(conn, stmt, rs);

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return retrunCode;
    }

    @Override
    public String insertWxInfo(WxInfo wxInfo) {

        String retrunCode = null;
        Connection conn = null;
        try {
            conn = BaseDao.getConnection();
            String sql;
            sql = "insert into youga_pet.user_wx_info (openid,username,headimgurl,country,province,city,sex,openidMD5) " +
                    "value ( \"" +wxInfo.getUserId()+"\""+
                    ",\"" +wxInfo.getUserName()+"\""+
                    ",\"" +wxInfo.getHeadimgurl()+
                    "\",\"" +wxInfo.getCountry()+
                    "\",\"" +wxInfo.getProvince()+
                    "\",\"" +wxInfo.getCity()+
                    "\",\""+wxInfo.getSex()+
                    "\",\""+wxInfo.getOpenidMD5()
                    + "\");";

            PreparedStatement stmt = conn.prepareStatement(sql);
            int result = stmt.executeUpdate();

            if ( 1 == result )
            {
                retrunCode = ReturnCode.SUCCESS_CODE;
            }
            else {
                retrunCode = ReturnCode.INSERT_ERROR;
            }

            BaseDao.closeAll(conn, stmt);

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return retrunCode;
    }

    @Override
    public String modifyWxInfo(WxInfo wxInfo) {
        String retrunCode = null;
        Connection conn = null;
        try {
            conn = BaseDao.getConnection();
            String sql;
            sql = "update youga_pet.user_wx_info set username = \""
                    +wxInfo.getUserName()+"\","+
                    "headimgurl = \"" +wxInfo.getHeadimgurl()+
                    "\",country = \"" +wxInfo.getCountry()+
                    "\",province = \"" +wxInfo.getProvince()+
                    "\",city = \"" +wxInfo.getCity()+
                    "\" Where openid = \""+wxInfo.getUserId()+"\";";

            PreparedStatement stmt = conn.prepareStatement(sql);
            int result = stmt.executeUpdate();

            if ( 0 == result )
            {
                retrunCode = ReturnCode.SUCCESS_CODE;
            }
            else
            {
                retrunCode = ReturnCode.MODIFY_ERROR;
            }

            BaseDao.closeAll(conn, stmt);

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return retrunCode;
    }

    @Override
    public String getVeriCode() {
        return null;
    }

    @Override
    public String insertMemberInfo(MemberInfo memberInfo) {
        String retrunCode = null;
        Connection conn = null;
        try {
            conn = BaseDao.getConnection();
            String sql;
            sql = "insert into youga_pet.baking_member_info (memberID,username,msisdn,integral,memberFlag," +
                    "memberLevel,Amount,balance,serviceOff,goodsOff,openid,idcard,birthday" +
                    ",sex,age,nativeAddress,hisAmount) " +
                    "value ( \"" +memberInfo.getMemberID()+"\""+
                    ",\"" +memberInfo.getRealname()+"\""+
                    ",\"" +memberInfo.getMsisdn()+
                    "\",\"" +memberInfo.getIntegral()+
                    "\",\"" +memberInfo.getMemberFlag()+
                    "\",\"" +memberInfo.getMemberLevel()+
                    "\",\"" +memberInfo.getAmount()+
                    "\",\"" +memberInfo.getBalance()+
                    "\",\"" +memberInfo.getServiceOff()+
                    "\",\"" +memberInfo.getGoodsOff()+
                    "\",\"" +memberInfo.getOpenid()+
                    "\",\"" +memberInfo.getIdCard()+
                    "\",\"" +memberInfo.getBirthday()+
                    "\",\"" +memberInfo.getSex()+
                    "\",\"" +memberInfo.getAge()+
                    "\",\"" +memberInfo.getNativeAddress()+
                    "\",\"" +memberInfo.getHisAmount()+ "\");";

            PreparedStatement stmt = conn.prepareStatement(sql);
            int result = stmt.executeUpdate();

            if ( 1 == result )
            {
                retrunCode = ReturnCode.SUCCESS_CODE;
            }
            else {
                retrunCode = ReturnCode.INSERT_ERROR;
            }

            BaseDao.closeAll(conn, stmt);

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return retrunCode;
    }

    @Override
    public MemberInfo getMemberInfo(String openid) {
        Connection conn = null;
        MemberInfo member = null;
        try {
            conn = BaseDao.getConnection();
            String sql = "select * from youga_pet.baking_member_info where openid = \""+openid+"\";";
            PreparedStatement stmt = conn.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();

            if (rs.next())
            {
                rs.previous();
            }
            else {
                return new MemberInfo();
            }

            while(rs.next())
            {
                member =new MemberInfo(
                        rs.getString("memberID"),
                        rs.getString("memberLevel"),
                        rs.getString("integral"),
                        rs.getString("memberFlag"),
                        rs.getString("msisdn"),
                        rs.getString("openid"),
                        rs.getString("Amount"),
                        rs.getString("balance")
                );
                member.setGoodsOff(rs.getString("goodsOff"));
            }
            BaseDao.closeAll(conn, stmt, rs);

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return member;
    }

    @Override
    public String updateMemberInfo(MemberInfo memberInfo) {
        String retrunCode = null;
        Connection conn = null;
        try {
            conn = BaseDao.getConnection();
            String sql;
            sql = "update youga_pet.baking_member_info set username = \""
                    +memberInfo.getRealname()+"\","+
                    "openid = \"" +memberInfo.getOpenid()+
                    "\",idcard = \"" +memberInfo.getIdCard()+
                    "\",birthday = \"" +memberInfo.getBirthday()+
                    "\",age = \"" +memberInfo.getAge()+
                    "\" Where memberID = \""+memberInfo.getMemberID()+"\";";

            PreparedStatement stmt = conn.prepareStatement(sql);
            int result = stmt.executeUpdate();

            if ( 0 == result )
            {
                retrunCode = ReturnCode.SUCCESS_CODE;
            }
            else
            {
                retrunCode = ReturnCode.MODIFY_ERROR;
            }

            BaseDao.closeAll(conn, stmt);

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return retrunCode;
    }

    @Override
    public String isMemberExist(String openid) {
        String retrunCode = null;
        Connection conn = null;
        try {
            conn = BaseDao.getConnection();
            String sql = "select count(1) from youga_pet.member_info where openid = \""+openid+"\";";
            PreparedStatement stmt = conn.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();
            while(rs.next())
            {
                int rows = rs.getInt(1);
                if ( rows > 0 && rows <= 1 )
                {
                    retrunCode = ReturnCode.MEMBER_EXIST;
                }
                else if ( rows == 0 )
                {
                    retrunCode =  ReturnCode.MEMBER_NOD_FOUND;
                }
                else if ( rows > 1 )
                {
                    retrunCode = ReturnCode.MEMBER_REPEAT;
                }
            }
            BaseDao.closeAll(conn, stmt, rs);

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return retrunCode;
    }

    @Override
    public WxInfo getWxInfo(String openid) {
        Connection conn = null;
        WxInfo wxInfo = null;
        try {
            conn = BaseDao.getConnection();
            String sql = "select * from youga_pet.user_wx_info where openid = \""+openid+"\";";
            PreparedStatement stmt = conn.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();

            if (rs.next())
            {
                rs.previous();
            }
            else {
                return new WxInfo();
            }

            while(rs.next())
            {
                wxInfo =new WxInfo(
                        rs.getString("username"),
                        rs.getString("openid"),
                        rs.getString("headimgurl"),
                        rs.getString("country"),
                        rs.getString("city"),
                        rs.getString("province"),
                        rs.getString("sex")
                );
            }
            BaseDao.closeAll(conn, stmt, rs);

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return wxInfo;
    }

    @Override
    public String getWxUid(String openid) {
        Connection conn = null;
        String result="";
        try {
            conn = BaseDao.getConnection();
            String sql = "select userid from youga_pet.user_wx_info where openid = \""+openid+"\";";
            PreparedStatement stmt = conn.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();

            if (rs.next())
            {
                rs.previous();
            }
            else {
                return "0";
            }

            while(rs.next())
            {

                   result = rs.getString("userid");

            }
            BaseDao.closeAll(conn, stmt, rs);

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public Object getOpenidMd5(String openidMD5) {
        Connection conn = null;
        String result="";
        try {
            conn = BaseDao.getConnection();
            String sql = "select openid from youga_pet.user_wx_info where openidMD5 = \""+openidMD5+"\";";
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

                result = rs.getString("openid");

            }
            BaseDao.closeAll(conn, stmt, rs);

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public boolean isMemberExistByMD5(String openid) {
        Boolean retrunCode = true;
        Connection conn = null;
        try {
            conn = BaseDao.getConnection();
            String sql = "select count(1) from youga_pet.member_info where openid = \""+openid+"\";";
            PreparedStatement stmt = conn.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();
            while(rs.next())
            {
                int rows = rs.getInt(1);
                if ( rows > 0 && rows <= 1 )
                {
                    retrunCode = true;
                }
                else if ( rows == 0 )
                {
                    retrunCode =  false;
                }
                else if ( rows > 1 )
                {
                    retrunCode = true;
                }
            }
            BaseDao.closeAll(conn, stmt, rs);

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return retrunCode;
    }

    @Override
    public boolean isOfflineMemberExist(String msisdn) {
        Boolean retrunCode = true;
        Connection conn = null;
        try {
            conn = BaseDao.getConnection();
            String sql = "select count(1) from youga_pet.member_offline_info where msisdn = \""+msisdn+"\";";
            PreparedStatement stmt = conn.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();
            while(rs.next())
            {
                int rows = rs.getInt(1);
                if ( rows > 0 && rows <= 1 )
                {
                    retrunCode = true;
                }
                else if ( rows == 0 )
                {
                    retrunCode =  false;
                }else {
                    retrunCode = false;
                }
            }
            BaseDao.closeAll(conn, stmt, rs);

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return retrunCode;
    }

    @Override
    public MemberInfo getMemberInfoByOffline(String msisdn) {
        Connection conn = null;
        MemberInfo member = null;
        try {
            conn = BaseDao.getConnection();
            String sql = "select * from youga_pet.baking_member_info where msisdn = \""+msisdn+"\";";
            PreparedStatement stmt = conn.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();

            if (rs.next())
            {
                rs.previous();
            }
            else {
                return new MemberInfo();
            }

            while(rs.next())
            {
                member = new MemberInfo();
                member.setBalance(rs.getString("balance"));
                member.setMsisdn(msisdn);
                member.setIntegral(rs.getString("integral"));
                member.setMemberID(rs.getString("memberID"));
                member.setMemberLevel(rs.getString("memberLevel"));

            }
            BaseDao.closeAll(conn, stmt, rs);

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return member;
    }

    @Override
    public String getNickName(String openid) {
        Connection conn = null;
        String result="";
        try {
            conn = BaseDao.getConnection();
            String sql = "select username from youga_pet.user_wx_info where openid = \""+openid+"\";";
            PreparedStatement stmt = conn.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();

            if (rs.next())
            {
                rs.previous();
            }
            else {
                return "0";
            }

            while(rs.next())
            {

                result = rs.getString("username");

            }
            BaseDao.closeAll(conn, stmt, rs);

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public String getOfflineLastId() {
        Connection conn = null;
        String result = null;
        try {
            conn = BaseDao.getConnection();
            String sql = "select memberID from member_offline_info order by memberId desc limit 1;";
            PreparedStatement stmt = conn.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();

            if (rs.next())
            {
                rs.previous();
            }
            else {
                return "";
            }

            while(rs.next())
            {
                result = rs.getString("memberID");

            }
            BaseDao.closeAll(conn, stmt, rs);

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public void insertOfflineMemberInfo(MemberInfo memberInfo) {
        Connection conn = null;
        try {
            conn = BaseDao.getConnection();
            String sql;
            sql = "insert into youga_pet.member_offline_info (memberID,username,msisdn,integral,memberFlag,memberLevel,Amount,balance,serviceOff,goodsOff,petName,hisAmount) " +
                    "value ( \"" +memberInfo.getMemberID()+"\""+
                    ",\"" +""+
                    "\",\"" +memberInfo.getMsisdn()+
                    "\",\"" +memberInfo.getIntegral()+
                    "\",\"" +memberInfo.getMemberFlag()+
                    "\",\"" +memberInfo.getMemberLevelId()+
                    "\",\"" +memberInfo.getAmount()+
                    "\",\"" +memberInfo.getBalance()+
                    "\",\"" +memberInfo.getServiceOff()+
                    "\",\"" +memberInfo.getGoodsOff()+
                    "\",\"" +memberInfo.getPetName()+
                    "\",\"" +memberInfo.getHisAmount()+
                    "\");";

            PreparedStatement stmt = conn.prepareStatement(sql);
            int result = stmt.executeUpdate();

            BaseDao.closeAll(conn, stmt);

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public boolean checkMemberAuth(String openid) {
        String retrunCode = null;
        Connection conn = null;
        try {
            conn = BaseDao.getConnection();
            String sql = "select count(1) from youga_pet.baking_member_info where openid = \""+openid+"\";";
            PreparedStatement stmt = conn.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();
            while(rs.next())
            {
                int rows = rs.getInt(1);
                if ( rows > 0 && rows <= 1 )
                {
                    retrunCode = ReturnCode.MEMBER_EXIST;
                }
                else if ( rows == 0 )
                {
                    retrunCode =  ReturnCode.MEMBER_NOD_FOUND;
                }
            }
            BaseDao.closeAll(conn, stmt, rs);

        } catch (SQLException e) {
            e.printStackTrace();
        }

        if (retrunCode ==  ReturnCode.MEMBER_NOD_FOUND){
            return false;
        }else {
            return true;
        }

    }

    @Override
    public String getLastId() {
        Connection conn = null;
        String result = null;
        try {
            conn = BaseDao.getConnection();
            String sql = "select memberID from youga_pet.baking_member_info order by memberId desc limit 1;";
            PreparedStatement stmt = conn.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();
            if (rs.next())
            {
                rs.previous();
            }
            else {
                return "";
            }
            while(rs.next())
            {
                result = rs.getString("memberID");

            }
            BaseDao.closeAll(conn, stmt, rs);

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public boolean checkMemberExist(String msisdn) {
        String retrunCode = null;
        Connection conn = null;
        try {
            conn = BaseDao.getConnection();
            String sql = "select count(1) from youga_pet.baking_member_info where msisdn = \""+msisdn+"\";";
            PreparedStatement stmt = conn.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();
            while(rs.next())
            {
                int rows = rs.getInt(1);
                if ( rows > 0 && rows <= 1 )
                {
                    retrunCode = ReturnCode.MEMBER_EXIST;
                }
                else if ( rows == 0 )
                {
                    retrunCode =  ReturnCode.MEMBER_NOD_FOUND;
                }
                else if ( rows > 1 )
                {
                    retrunCode = ReturnCode.MEMBER_REPEAT;
                }
            }
            BaseDao.closeAll(conn, stmt, rs);

        } catch (SQLException e) {
            e.printStackTrace();
        }

        if ( retrunCode.equals(ReturnCode.MEMBER_NOD_FOUND) )
        {
            return false;
        }else {
            return true;
        }
    }

    @Override
    public void synchronizationLottery(String openid) {

        Connection conn = null;
        PreparedStatement stmt = null;

        try {
            //存储过程执行
            conn = BaseDao.getConnection();

            CallableStatement c = conn.prepareCall("{call sychronizationLottery(?)}");
            c.setString(1, openid);
            c.execute();

            BaseDao.closeAll(conn, stmt);
        } catch (SQLException e) {
            e.printStackTrace();
            try {
                BaseDao.closeAll(conn, stmt);
            } catch (SQLException e1) {
                e1.printStackTrace();
            }
        }

    }

    @Override
    public void updateMemberBalance(MemberInfo memberInfo) {
        Boolean returnCode=false;
        String seach_table = "youga_pet.baking_member_info";
        Connection conn = null;
        try {
            conn = BaseDao.getConnection();
            String sql;
            sql = "update "+seach_table+" set balance = \""
                    +memberInfo.getBalance()+"\" , integral =\""+memberInfo.getIntegral()+
                    "\" Where msisdn = \""+memberInfo.getMsisdn()+"\";";

            //System.out.println(sql);
            PreparedStatement stmt = conn.prepareStatement(sql);
            int result = stmt.executeUpdate();

            BaseDao.closeAll(conn, stmt);

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
