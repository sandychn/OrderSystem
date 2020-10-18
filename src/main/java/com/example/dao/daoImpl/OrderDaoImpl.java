package com.example.dao.daoImpl;

import com.example.common.JdbcUtil;
import com.example.common.Status;
import com.example.common.SystemException;
import com.example.dao.OrderDao;
import com.example.pojo.Order;
import com.example.pojo.OrderDetail;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class OrderDaoImpl implements OrderDao {
    @Override
    public Order getOrder(String orderID) throws SystemException {
        Order order = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try{
            Connection connection = JdbcUtil.getConnection();
            String sql = "select * from t_order where o_id = ?";
            ps = connection.prepareStatement(sql);

            ps.setString(1,orderID);
            rs = ps.executeQuery();
            if(rs.next()){
                order = new Order();
                order.setOrderID(rs.getString("o_id"));
                order.setStartTime(rs.getLong("o_start_time"));
                order.setFinishTime(rs.getLong("o_finish_time"));
                order.setNumber(rs.getInt("o_table_number"));
                order.setStatus(rs.getInt("o_status"));
                order.setUserId(rs.getString("u_id"));
            }

        }catch (SQLException e){
            throw new SystemException(e.getMessage());
        }finally {
            JdbcUtil.close(rs,ps);
        }
        return order;
    }
    @Override
    public String addOrder(Order order) throws SystemException {
        PreparedStatement ps = null;
        ResultSet rs = null;
        Statement statement = null;
        try {
            Connection connection = JdbcUtil.getConnection();
            String sql = "select * from t_order where o_id = ?";
            ps = connection.prepareStatement(sql);
            ps.setString(1, order.getOrderID());
            rs = ps.executeQuery();
            if(rs.next()){
                return null;
            }
            String orderId = getOrderId();
            sql = "insert into t_order(o_id,o_start_time,o_finish_time,o_table_number,o_status,u_id) " +
                    "values('" + orderId + "','" + order.getStartTime() + "','" + order.getFinishTime() + "','" +
                    order.getNumber() + "'," + order.getStatus() + order.getUserId() +")";
            statement = connection.createStatement();
            int resultNum = statement.executeUpdate(sql);

            if (resultNum > 0) {
                return orderId;
            } else {
                return null;
            }
        }catch (SQLException e){
            throw new SystemException(e.getMessage());
        }finally {
            JdbcUtil.close(rs,ps);
            JdbcUtil.close(null, statement);
        }
    }
    @Override
    public Status update(Order order) throws SystemException {
        Statement statement = null;
        try {
            if(!isExist(order.getOrderID())){
                return Status.ORDER_NOT_EXISTS;
            }
            Connection connection = JdbcUtil.getConnection();
            statement = connection.createStatement();
            String sql = "update t_order set o_table_number ='"+ order.getNumber() +"',o_status='"+ order.getStatus() +
                    " where o_id='"+order.getOrderID()+";";
            int resultNum = statement.executeUpdate(sql);
            if(resultNum > 0){
                return Status.ORDER_UPDATE_SUCCESS;
            }else{
                return Status.ORDER_UPDATE_FAIL;
            }
        }catch (SQLException e){
            throw new SystemException(e.getMessage());
        }finally {
            JdbcUtil.close(null,statement);
        }
    }


    private String getOrderId() throws SystemException{
        PreparedStatement ps = null;
        ResultSet rs = null;
        try{
            Connection connection = JdbcUtil.getConnection();
            String sql =  "select getOrderId(?);";
            ps = connection.prepareStatement(sql);
            ps.setString(1,"seq_order_id");
            rs = ps.executeQuery();
            if(rs.next()){
                return rs.getString(1);
            }
        }catch (SQLException e){
            throw new SystemException(e.getMessage());
        }finally {
            JdbcUtil.close(rs,ps);
        }
        return null;
    }

    @Override
    public boolean isExist(String orderID) throws SystemException {
        PreparedStatement ps = null;
        ResultSet rs = null;
        try{
            Connection connection = JdbcUtil.getConnection();
            String sql = "select * from t_order where o_id= ?";

            ps = connection.prepareStatement(sql);
            ps.setString(1,orderID);
            rs = ps.executeQuery();
            if(rs.next()){
                return true;
            }else{
                return false;  //订单不存在
            }
        }catch (SQLException e){
            throw new SystemException(e.getMessage());
        }finally {
            JdbcUtil.close(rs,ps);
        }
    }

    @Override
    public Status updateStatus(String orderId) throws SystemException{
        Statement statement = null;
        try {
            if(!isExist(orderId)){
                return Status.ORDER_NOT_EXISTS;
            }
            Connection connection = JdbcUtil.getConnection();
            statement = connection.createStatement();

            String sql = "update t_order set o_status=1 where o_id='" + orderId + "';";
            int resultNum = statement.executeUpdate(sql);
            if(resultNum > 0){
                return Status.ORDER_UPDATE_SUCCESS;
            }else{
                return Status.ORDER_UPDATE_FAIL;
            }
        }catch (SQLException e){
            throw new SystemException(e.getMessage());
        }finally {
            JdbcUtil.close(null,statement);
        }
    }

    @Override
    public List<Order> getUserAllOrders(String userId) throws SystemException{
        List<Order> orders = new ArrayList<>();
        Statement statement = null;
        ResultSet rs = null;
        try{
            Connection connection = JdbcUtil.getConnection();
            String sql = "select * from t_order where u_id = '"+ userId +"';";
            statement = connection.createStatement();
            rs = statement.executeQuery(sql);
            while(rs.next()){
                Order order = new Order();
                order.setOrderID(rs.getString("o_id"));
                order.setStartTime(rs.getLong("o_start_time"));
                order.setFinishTime(rs.getLong("o_finish_time"));
                order.setNumber(rs.getInt("o_table_number"));
                order.setStatus(rs.getInt("o_status"));
                order.setUserId(rs.getString("u_id"));
                orders.add(order);
            }
            return orders;
        }catch (SQLException e){
            throw new SystemException(e.getMessage());
        }finally {
            JdbcUtil.close(rs,statement);
        }

    }
}
