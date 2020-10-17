package com.example.dao.daoImpl;

import com.example.common.JdbcUtil;
import com.example.common.Status;
import com.example.common.SystemException;
import com.example.dao.OrderDao;
import com.example.pojo.Order;

import java.sql.*;

public class OrderDaoImpl implements OrderDao {
    @Override
    public Order getOrder(String orderID) throws SystemException {
        Order order = null;
        try{
            Connection connection = JdbcUtil.getConnection();
            String sql = "select * from t_order where o_id = ?";
            PreparedStatement ps = connection.prepareStatement(sql);

            ps.setString(1,orderID);
            ResultSet rs = ps.executeQuery();
            if(rs.next()){
                order = new Order();
                order.setOrderID(rs.getString("o_id"));
                order.setStartTime(rs.getLong("o_start_time"));
                order.setFinishTime(rs.getLong("o_finish_time"));
                order.setNumber(rs.getInt("o_table_number"));
                order.setStatus(rs.getInt("o_status"));
            }
            JdbcUtil.close(rs,ps);
        }catch (SQLException e){
            throw new SystemException(e.getMessage());
        }
        return order;
    }
    @Override
    public Status addOrder(Order order) throws SystemException {
        try {
            Connection connection = JdbcUtil.getConnection();
            String sql = "select * from t_order where o_id = ?";
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, order.getOrderID());
            ResultSet rs = ps.executeQuery();
            if(rs.next()){
                JdbcUtil.close(rs,ps);
                return Status.ORDER_EXISTS;
            }

            sql = "insert into t_order(o_id,o_start_time,o_finish_time,o_table_number,o_status) " +
                    "values('" + order.getOrderID() + "','" + order.getStartTime() + "','" + order.getFinishTime() + "','" +
                    order.getNumber() + "'," + order.getStatus() + ")";
            Statement statement = connection.createStatement();
            int resultNum = statement.executeUpdate(sql);
            JdbcUtil.close(null, statement);
            if (resultNum > 0) {
                return Status.ORDER_ADD_SUCCESS;
            } else {
                return Status.ORDER_ADD_FAIL;
            }
        }catch (SQLException e){
                throw new SystemException(e.getMessage());
        }
    }
    @Override
    public Status update(Order order) throws SystemException {
        try {
            if(!isExist(order.getOrderID())){
                return Status.ORDER_NOT_EXISTS;
            }
            Connection connection = JdbcUtil.getConnection();
            Statement statement = connection.createStatement();
            String sql = "update t_order set o_table_number ='"+ order.getNumber() +"',o_status='"+ order.getStatus() +
                    " where o_id='"+order.getOrderID()+";";
            int resultNum = statement.executeUpdate(sql);
            JdbcUtil.close(null,statement);
            if(resultNum > 0){
                return Status.ORDER_UPDATE_SUCCESS;
            }else{
                return Status.ORDER_UPDATE_FAIL;
            }
        }catch (SQLException e){
            throw new SystemException(e.getMessage());
        }
    }


    private String getOrderId() throws SystemException{
        try{
            Connection connection = JdbcUtil.getConnection();
            String sql =  "select getOrderId(?);";
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1,"seq_order_id");
            ResultSet rs = ps.executeQuery();
            if(rs.next()){
                return rs.getString(1);
            }
            JdbcUtil.close(rs,ps);
        }catch (SQLException e){
            throw new SystemException(e.getMessage());
        }
        return null;
    }
    private boolean isExist(String orderID) throws SystemException {
        try{
            Connection connection = JdbcUtil.getConnection();
            String sql = "select * from t_order where o_id= ?";

            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1,orderID);
            ResultSet rs = ps.executeQuery();
            if(rs.next()){
                JdbcUtil.close(rs,ps);
                return true;
            }else{
                JdbcUtil.close(rs,ps);
                return false;  //订单不存在
            }
        }catch (SQLException | SystemException e){
            throw new SystemException(e.getMessage());
        }
    }
}
