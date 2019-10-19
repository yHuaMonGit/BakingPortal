package com.youga.baking.ps.dao.impl;

import com.youga.baking.ps.dao.BaseDao;
import com.youga.baking.ps.dao.OrderNotesDao;
import com.youga.baking.ps.obj.ComDetailBase;
import com.youga.baking.ps.obj.OrderNotesBase;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class OrderNotesDaoImpl implements OrderNotesDao {
    @Override
    public OrderNotesBase createNewPreOrder(String openid, String orderId, String orderCreateTime, String shopid) {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        OrderNotesBase preOrder = null;
        //定义使用变量

        try {
            conn = BaseDao.getConnection();

            /***
             * 存储过程执行，在数据库层面生成预订单，将购物车、商品、会员信息等集合插入到预订单表。
             * 但最初生成的预订单表，不包含订单金额（订单金额为最后结算、支付时的金额），商品总价可以生成。
             * 订单金额在生成页面时，JS发送请求获取新的结算信息再生成。
             */
            CallableStatement c=conn.prepareCall("{call createNewPreOrder(?,?,?,?)}");
            c.setString(1,openid);
            c.setString(2,orderId);
            c.setString(3,orderCreateTime);
            c.setString(4,shopid);
            c.execute();

            /***
             * 存储过程执行完成后，第一次读取预订单表，将生成的表单信息，返回给order_notes页面；
             * order_notes页面加载订单内：订单号、总价、分类商品信息等
             * 1.获取订单基本信息；
             * 2.获取商品列表信息；（重新封装一个独立方法、历史记录调用时也可以使用）
             */

            String sql = "select order_no,shop_id,order_status,product_count,product_amount_total,createTime,member_id,openid " +
                    "from youga_pet.portal_store_order_prepayment_notes " +
                    "where order_no = \""+orderId+"\";";

            stmt = conn.prepareStatement(sql);
            rs =  stmt.executeQuery();
            if (rs.next())
            {
                rs.previous();
            }
            else {
                return null;
            }
            while(rs.next())
            {
                preOrder = new OrderNotesBase(
                        rs.getString("order_no"),
                        rs.getString("shop_id"),
                        rs.getString("order_status"),
                        rs.getString("product_count"),
                        rs.getString("product_amount_total"),
                        rs.getString("createTime"),
                        rs.getString("member_id"),
                        rs.getString("openid")
                );
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
        preOrder.setComList(this.getOrderComList(preOrder.order_no));
        return preOrder;
    }

    @Override
    public List<ComDetailBase> getOrderComList(String order_no) {


        List<ComDetailBase> goodsBaseList = new ArrayList<>();
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {
            conn = BaseDao.getConnection();
            String sql = "select com_id,com_name,com_price,com_stander,com_stander_inner_id,com_stander_col,com_num,cls_in_id,is_com_off " +
                    "from  youga_pet.portal_store_order_commodity_detail_info where order_no = \""+order_no+"\";";
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
                        "",
                        rs.getFloat("com_price"),
                        "",
                        ""
                );

                goodsBase.setCls_in_id(rs.getInt("cls_in_id"));
                goodsBase.setCom_stander_id(rs.getString("com_stander"));
                goodsBase.setStander_inner_id(rs.getInt("com_stander_inner_id"));
                goodsBase.setStander_col(rs.getString("com_stander_col"));
                goodsBase.setCom_num(rs.getInt("com_num"));
                goodsBase.setIsOffCom(rs.getInt("is_com_off"));

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
    public boolean isShoppingCartNull(String openid) {

        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        Boolean result = false;

        try {
            conn = BaseDao.getConnection();
            String sql = "select count(*) from youga_pet.shoppingcart_viewer where openid = \""+openid+"\";";
            stmt = conn.prepareStatement(sql);
            rs =  stmt.executeQuery();
//goodsid,goodsname,goodsprice,instock,stander,classify

            if (rs.next())
            {
                rs.previous();
            }
            else {
                result =  true;
            }
            while(rs.next())
            {
                if (rs.getInt(1) > 0){
                    result = false;
                }else result = true;
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
        if (null == result){
            return false;
        }else {
            return result;
        }
    }

    @Override
    public boolean cancelOrder(String openid, String orderid) {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;

        Boolean result = false;

        //定义使用变量

        try {
            conn = BaseDao.getConnection();

            /***
             * 调度存储过程删除对应信息
             */
            CallableStatement c=conn.prepareCall("{call deleteOrder(?,?)}");
            c.setString(1,openid);
            c.setString(2,orderid);
            c.execute();
            result = true;
            BaseDao.closeAll(conn, stmt);
        } catch (SQLException e) {
            e.printStackTrace();
            try {
                BaseDao.closeAll(conn, stmt);
                result = false;
            } catch (SQLException e1) {
                e1.printStackTrace();
            }
        }
        return result;

    }

    @Override
    public void InsertAmount(String orderId, String actAmount) {

        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        String sql;

        //定义使用变量
        try {
            conn = BaseDao.getConnection();

            //Insert Sql
            sql = "update  youga_pet.portal_store_order_prepayment_notes set order_amount_total = \"" +actAmount+"\" " +
                    "where order_no = \""+orderId+"\";";
            stmt = conn.prepareStatement(sql);
            stmt.executeUpdate();

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
    public void InsertNotes(String orderid, String utfNotes) {

        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        String sql;

        //定义使用变量
        try {
            conn = BaseDao.getConnection();
            //Insert Sql
            sql = "update  youga_pet.portal_store_order_prepayment_notes set user_remark = \"" +utfNotes+"\" " +
                    "where order_no = \""+orderid+"\";";
            stmt = conn.prepareStatement(sql);
            stmt.executeUpdate();

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

    /****
     * 返回实际消费金额
     * @param orderid
     * @return
     */
    @Override
    public String getActAmount(String orderid) {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        //定义使用变量
        String result = "";

        try {
            conn = BaseDao.getConnection();

            String sql = "select order_amount_total " +
                    "from youga_pet.portal_store_order_prepayment_notes " +
                    "where order_no = \""+orderid+"\";";

            stmt = conn.prepareStatement(sql);
            rs =  stmt.executeQuery();
            if (rs.next())
            {
                rs.previous();
            }
            else {
                return result;
            }
            while(rs.next()) {
                result = rs.getString("order_amount_total");
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
    public OrderNotesBase getOrderInfoByOrderID(String openid, String orderid, String shopid) {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        OrderNotesBase preOrder = null;
        //定义使用变量

        try {
            conn = BaseDao.getConnection();

            String sql = "select order_no,shop_id,order_status,product_count,product_amount_total,order_amount_total,createTime,member_id,openid " +
                    "from youga_pet.portal_store_order_prepayment_notes " +
                    "where order_no = \""+orderid+"\";";

            stmt = conn.prepareStatement(sql);
            rs =  stmt.executeQuery();
            if (rs.next())
            {
                rs.previous();
            }
            else {
                return null;
            }
            while(rs.next())
            {
                preOrder = new OrderNotesBase(
                        rs.getString("order_no"),
                        rs.getString("shop_id"),
                        rs.getString("order_status"),
                        rs.getString("product_count"),
                        rs.getString("product_amount_total"),
                        rs.getString("createTime"),
                        rs.getString("member_id"),
                        rs.getString("openid")
                );
                preOrder.setActAmount(rs.getString("order_amount_total"));
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
        preOrder.setComList(this.getOrderComList(preOrder.order_no));

        return preOrder;
    }

    @Override
    public void updatePaymentOrderInfo(OrderNotesBase orderInfo) {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        String sql;

        //定义使用变量
        try {
            conn = BaseDao.getConnection();
            //Insert Sql
            sql = "update  youga_pet.portal_store_order_prepayment_notes set order_status = \"" +orderInfo.getOrder_status()+"\" " +
                    ",address_id = \""+orderInfo.getAddress_id()+"\" "+
                    ",orderlogistics_id = \""+orderInfo.getOrderlogistics_id()+"\" "+
                    ",pay_channel = \""+orderInfo.getPay_channel()+"\" "+
                    ",payment_time = \""+orderInfo.getPayment_time()+"\" "+
                    "where order_no = \""+orderInfo.getOrder_no()+"\";";
            stmt = conn.prepareStatement(sql);
            stmt.executeUpdate();

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
    public String getTransTime(String orderid) {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        //定义使用变量
        String result = "";

        try {
            conn = BaseDao.getConnection();

            String sql = "select payment_time " +
                    "from youga_pet.portal_store_order_prepayment_notes " +
                    "where order_no = \""+orderid+"\";";

            stmt = conn.prepareStatement(sql);
            rs =  stmt.executeQuery();
            if (rs.next())
            {
                rs.previous();
            }
            else {
                return result;
            }
            while(rs.next()) {
                result = rs.getString("payment_time");
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
    public List<OrderNotesBase> getTotalOrderList(String openid) {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        OrderNotesBase preOrder = null;

        List<OrderNotesBase> orderlist = new ArrayList<>();

        //定义使用变量

        try {
            conn = BaseDao.getConnection();

            String sql = "select order_no,shop_id,order_status,product_count,product_amount_total,order_amount_total,createTime,member_id,openid " +
                    "from youga_pet.portal_store_order_prepayment_notes " +
                    "where openid = \""+openid+"\";";

            stmt = conn.prepareStatement(sql);
            rs =  stmt.executeQuery();
            if (rs.next())
            {
                rs.previous();
            }
            else {
                return null;
            }
            while(rs.next())
            {
                preOrder = new OrderNotesBase(
                        rs.getString("order_no"),
                        rs.getString("shop_id"),
                        rs.getString("order_status"),
                        rs.getString("product_count"),
                        rs.getString("product_amount_total"),
                        rs.getString("createTime"),
                        rs.getString("member_id"),
                        rs.getString("openid")
                );
                preOrder.setActAmount(rs.getString("order_amount_total"));

                orderlist.add(preOrder);
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

        return orderlist;
    }

    @Override
    public String updateComInstock(String orderid) {
        Connection conn = null;
        PreparedStatement stmt = null;
        String result=null;
        //定义使用变量
        try {
            conn = BaseDao.getConnection();

            /***
             * 存储过程执行，在数据库层面刷新商品库存数据，并同步到线下表。
             */

            /***
             * 2019-07-20 修改：存储过程增加返回参数，若返回501则说明库存不够减少
             */
            CallableStatement c=conn.prepareCall("{call update_portal_commondity_instock(?,?,?)}");
            c.setString(1,orderid);
            c.registerOutParameter("result",Types.INTEGER);
            c.registerOutParameter("comName",Types.VARCHAR);
            c.execute();
            result = c.getString("result")+","+c.getString("comName");
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
    public int getOrderStatus(String shopid, String orderid) {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        OrderNotesBase preOrder = null;

        int result = 0;

        //定义使用变量

        try {
            conn = BaseDao.getConnection();

            String sql = "select order_status from youga_pet.portal_store_order_prepayment_notes " +
                    "where shop_id = \""+shopid+"\" and order_no = \""+orderid+"\";";

            stmt = conn.prepareStatement(sql);
            rs =  stmt.executeQuery();
            if (rs.next())
            {
                rs.previous();
            }
            else {
                return -1;
            }
            while(rs.next())
            {
                result = rs.getInt("order_status");
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
    public String confirmOrder(String openid, String shopid, String orderid) {
        Connection conn = null;
        PreparedStatement stmt = null;
        String result=null;
        //定义使用变量
        try {
            conn = BaseDao.getConnection();

            /***
             * 存储过程执行，在数据库层面刷新商品库存数据，并同步到线下表。
             */

            /***
             * 2019-07-20 修改：存储过程增加返回参数，若返回501则说明库存不够减少
             */
            CallableStatement c=conn.prepareCall("{call order_confirm_receipt(?,?)}");
            c.setString(1,orderid);
            c.registerOutParameter("result",Types.INTEGER);
            c.execute();
            result = c.getString("result");
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
    public String getShopidByOrderNo(String orderid) {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        OrderNotesBase preOrder = null;

        String result = null;

        //定义使用变量

        try {
            conn = BaseDao.getConnection();

            String sql = "select shop_id from youga_pet.portal_store_order_prepayment_notes " +
                    "where order_no = \""+orderid+"\";";

            stmt = conn.prepareStatement(sql);
            rs =  stmt.executeQuery();
            if (rs.next())
            {
                rs.previous();
            }
            else {
                return result;
            }
            while(rs.next())
            {
                result = rs.getString("shop_id");
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
}
