package com.example.dao.daoImpl;

import com.example.common.JdbcUtil;
import com.example.common.Status;
import com.example.common.SystemException;
import com.example.dao.OrderCartDao;
import com.example.pojo.OrderCart;
import com.example.pojo.OrderDetail;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class OrderCartDaoImpl implements OrderCartDao {
    @Override
    public boolean isExist(String userID, String foodID) throws SystemException {
        try{
            Connection connection = JdbcUtil.getConnection();
            String sql = "select * from t_order_cart where u_id = ? and f_id = ?";
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1,userID);
            ps.setString(2,foodID);
            ResultSet rs = ps.executeQuery();
            if(rs.next()){
                JdbcUtil.close(rs,ps);
                return true;
            }else{
                JdbcUtil.close(rs,ps);
                return false;
            }
        }catch (SQLException e){
            throw new SystemException(e.getMessage());
        }
    }

    @Override
    public List<OrderCart> getOrderCarts(String userID) throws SystemException {
        try{
            Connection connection = JdbcUtil.getConnection();
            String sql = "select * from t_order_cart where u_id = ?";

            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(sql);
            List<OrderCart> list = new ArrayList<>();
            while(rs.next()){
                OrderCart orderCart = new OrderCart();
                orderCart.setUserID(rs.getString("u_id"));
                orderCart.setFoodID(rs.getString("f_id"));
                orderCart.setFoodCount(rs.getInt("food_count"));
                list.add(orderCart);
            }
            JdbcUtil.close(rs,statement);
            return list;
        }catch (SQLException e){
            throw new SystemException(e.getMessage());
        }
    }

    @Override
    public Status update(OrderCart orderCart) throws SystemException {
        try{
            if(!isExist(orderCart.getUserID(),orderCart.getFoodID())){
                return Status.ORDERCART_NOT_EXISTS;
            }
            Connection connection = JdbcUtil.getConnection();
            Statement statement = connection.createStatement();
            String sql = "update t_order_cart set food_count ='"+ orderCart.getFoodCount() +"'";
            String sql1 = "delete from t_order_cart where u_id = '"+orderCart.getUserID()+ "'and f_id = '" +orderCart.getFoodID()+"'";
            int resultNum;
            if(orderCart.getFoodCount() <= 0){
                resultNum = statement.executeUpdate(sql1);
                JdbcUtil.close(null,statement);
            }else{
                resultNum = statement.executeUpdate(sql);
                JdbcUtil.close(null,statement);
            }
            if(resultNum > 0){
                return Status.ORDERCART_UPDATE_SUCCESS;
            }else{
                return Status.ORDERCART_UPDATE_FAIL;
            }
        }catch (SQLException e){
            throw new SystemException(e.getMessage());
        }
    }

    @Override
    public Status addOrderCart(OrderCart orderCart) throws SystemException {
        return null;
    }

    @Override
    public Status delete(String userID, String foodID) throws SystemException {
        try{
            if(!isExist(userID,foodID)){
                return Status.ORDERCART_NOT_EXISTS;
            }
            Connection connection = JdbcUtil.getConnection();
            String sql = "delete from t_order_cart where u_id = '"+userID+ "'and f_id = '" +foodID+"'";
            Statement statement = connection.createStatement();
            int resultNum = statement.executeUpdate(sql);
            JdbcUtil.close(null,statement);
            if(resultNum > 0){
                return Status.ORDERCART_DELETE_SUCCESS;
            }else {
                return Status.ORDERCART_DELETE_FAIL;
            }
        }catch (SQLException e){
            throw new SystemException(e.getMessage());
        }
    }
}
