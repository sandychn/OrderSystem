package com.example.dao.daoImpl;

import com.example.common.JdbcUtil;
import com.example.common.Status;
import com.example.common.SystemException;
import com.example.dao.OrderDetailDao;
import com.example.pojo.Food;
import com.example.pojo.OrderDetail;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class OrderDetailDaoImpl implements OrderDetailDao {
    @Override
    public List<OrderDetail> getOrderDetails(String orderID) throws SystemException {
        try{
            Connection connection = JdbcUtil.getConnection();
            String sql = "select * from t_order_detail where o_id = ?";

            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(sql);
            List<OrderDetail> list = new ArrayList<>();
            while(rs.next()){
                OrderDetail orderDetail = new OrderDetail();
                orderDetail.setOrderID(rs.getString("o_id"));
                orderDetail.setFoodID(rs.getString("f_id"));
                orderDetail.setFoodCount(rs.getInt("food_count"));
                list.add(orderDetail);
            }
            JdbcUtil.close(rs,statement);
            return list;
        }catch (SQLException e){
            throw new SystemException(e.getMessage());
        }
    }

    @Override
    public Status addOrderDetail(OrderDetail orderDetail) throws SystemException {
        try{
            Connection connection = JdbcUtil.getConnection();
            String sql = "select * from t_order_detail where o_id = ? and f_id = ?";
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, orderDetail.getOrderID());
            ps.setString(2,orderDetail.getFoodID());
            ResultSet rs = ps.executeQuery();
            if(rs.next()){
                orderDetail.setFoodCount(orderDetail.getFoodCount()+1);
            }

            sql = "insert into t_order_detail(o_id,f_id,food_count) " +
                    "values('" + orderDetail.getOrderID() + "','" + orderDetail.getFoodID() + "'," + orderDetail.getFoodCount() + ")";
            Statement statement = connection.createStatement();
            int resultNum = statement.executeUpdate(sql);
            JdbcUtil.close(null, statement);
            if (resultNum > 0) {
                return Status.ORDERDETAIL_ADD_SUCCESS;
            } else {
                return Status.ORDERDETAIL_ADD_FAIL;
            }
        }catch (SQLException e){
            throw new SystemException(e.getMessage());
        }
    }

    @Override
    public Status update(OrderDetail orderDetail) throws SystemException {
        try {
            if(!isExist(orderDetail.getOrderID(),orderDetail.getFoodID())){
                return Status.ORDER_NOT_EXISTS;
            }

            Connection connection = JdbcUtil.getConnection();
            Statement statement = connection.createStatement();
            String sql = "update t_order_detail set f_id ='"+ orderDetail.getFoodID() +"',food_count='"+ orderDetail.getFoodCount() +
                    " where o_id='"+ orderDetail.getOrderID() +";";
            int resultNum = statement.executeUpdate(sql);
            JdbcUtil.close(null,statement);
            if(resultNum > 0){
                return Status.ORDERDETAIL_UPDATE_SUCCESS;
            }else{
                return Status.ORDERDETAIL_UPDATE_FAIL;
            }
        }catch (SQLException e){
            throw new SystemException(e.getMessage());
        }
    }

    @Override
    public Status delete(String orderID,String foodID) throws SystemException {
        try{
            if(!isExist(orderID,foodID)){
                return Status.ORDERDETAIL_NOT_EXISTS;
            }
            Connection connection = JdbcUtil.getConnection();
            String sql = "delete from t_order_detail where o_id = '"+orderID+ "'and f_id = '" +foodID+"'";
            Statement statement = connection.createStatement();
            int resultNum = statement.executeUpdate(sql);
            JdbcUtil.close(null,statement);
            if(resultNum > 0){
                return Status.ORDERDETAIL_DELETE_SUCCESS;
            }else {
                return Status.ORDERDETAIL_DELETE_FAIL;
            }
        }catch (SQLException e){
            throw new SystemException(e.getMessage());
        }
    }

    @Override
    public boolean isExist(String orderID,String foodID) throws SystemException {
        try{
            Connection connection = JdbcUtil.getConnection();
            String sql = "select * from t_order_detail where o_id = ? and f_id = ?";
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1,orderID);
            ps.setString(2,foodID);
            ResultSet rs = ps.executeQuery();
            if(rs.next()){
                JdbcUtil.close(rs,ps);
                return true;
            }else{
                JdbcUtil.close(rs,ps);
                return false;
            }
        }catch(SQLException e){
            throw new SystemException(e.getMessage());
        }
    }
}
