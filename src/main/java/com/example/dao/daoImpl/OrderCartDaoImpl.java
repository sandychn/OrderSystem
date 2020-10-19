package com.example.dao.daoImpl;

import com.example.common.JdbcUtil;
import com.example.common.Status;
import com.example.common.SystemException;
import com.example.dao.OrderCartDao;
import com.example.pojo.OrderCart;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class OrderCartDaoImpl implements OrderCartDao {
    @Override
    public boolean isExist(String userID, String foodID) throws SystemException {
        return getOrderCart(userID, foodID) != null;
    }

    @Override
    public OrderCart getOrderCart(String userID, String foodID) throws SystemException {
        Connection connection = JdbcUtil.getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            ps = connection.prepareStatement("select * from t_order_cart where u_id = ? and f_id = ?");
            ps.setString(1, userID);
            ps.setString(2, foodID);
            rs = ps.executeQuery();
            if (rs.next()) {
                OrderCart orderCart = new OrderCart();
                orderCart.setUserID(rs.getString("u_id"));
                orderCart.setFoodID(rs.getString("f_id"));
                orderCart.setFoodCount(rs.getInt("food_count"));
                return orderCart;
            } else {
                return null;
            }
        } catch (SQLException e){
            throw new SystemException(e.getMessage());
        } finally {
            JdbcUtil.close(rs, ps);
        }
    }

    @Override
    public List<OrderCart> getOrderCarts(String userID) throws SystemException {
        Connection connection = JdbcUtil.getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            ps = connection.prepareStatement("select * from t_order_cart where u_id = ?");
            ps.setString(1, userID);
            rs = ps.executeQuery();
            List<OrderCart> list = new ArrayList<>();
            while (rs.next()) {
                OrderCart orderCart = new OrderCart();
                orderCart.setUserID(rs.getString("u_id"));
                orderCart.setFoodID(rs.getString("f_id"));
                orderCart.setFoodCount(rs.getInt("food_count"));
                list.add(orderCart);
            }
            return list;
        } catch (SQLException e) {
            throw new SystemException(e.getMessage());
        } finally {
            JdbcUtil.close(rs, ps);
        }
    }

    @Override
    public Status update(OrderCart orderCart) throws SystemException {
        if (orderCart.getFoodID() == null || orderCart.getUserID() == null) {
            return Status.ORDERCART_UPDATE_FAIL;
        }

        Connection connection = JdbcUtil.getConnection();
        PreparedStatement ps = null;
        try {
            if (!isExist(orderCart.getUserID(),orderCart.getFoodID())) {
                ps = connection.prepareStatement("insert into t_order_cart(u_id, f_id, food_count) values(?, ?, ?)");
                ps.setString(1, orderCart.getUserID());
                ps.setString(2, orderCart.getFoodID());
                ps.setInt(3, orderCart.getFoodCount());
                int affacted = ps.executeUpdate();
                return affacted > 0 ? Status.ORDERCART_UPDATE_SUCCESS : Status.ORDERCART_UPDATE_FAIL;
            }

            int affacted;
            if (orderCart.getFoodCount() <= 0) {
                ps = connection.prepareStatement("delete from t_order_cart where u_id = ? and f_id = ?");
                ps.setString(1, orderCart.getUserID());
                ps.setString(2, orderCart.getFoodID());
                affacted = ps.executeUpdate();
            } else {
                ps = connection.prepareStatement("update t_order_cart set food_count = ? where u_id = ? and f_id = ?");
                ps.setInt(1, orderCart.getFoodCount());
                ps.setString(2, orderCart.getUserID());
                ps.setString(3, orderCart.getFoodID());
                affacted = ps.executeUpdate();
            }
            return affacted > 0 ? Status.ORDERCART_UPDATE_SUCCESS : Status.ORDERCART_UPDATE_FAIL;
        } catch (SQLException e) {
            throw new SystemException(e.getMessage());
        } finally {
            JdbcUtil.close(null, ps);
        }
    }

    @Override
    public Status deleteByUserID(String userID) throws SystemException {
        Connection connection = JdbcUtil.getConnection();
        PreparedStatement ps = null;
        try {
            ps = connection.prepareStatement("delete from t_order_cart where u_id = ?");
            ps.setString(1, userID);
            int affected = ps.executeUpdate();
            return Status.ORDERCART_DELETE_SUCCESS;
        } catch (SQLException e) {
            throw new SystemException(e.getMessage());
        } finally {
            JdbcUtil.close(null, ps);
        }
    }

    @Override
    public Status delete(String userID, String foodID) throws SystemException {
        Connection connection = JdbcUtil.getConnection();
        PreparedStatement ps = null;
        try {
            if (!isExist(userID, foodID)) {
                return Status.ORDERCART_NOT_EXISTS;
            }
            ps = connection.prepareStatement("delete from t_order_cart where u_id = ? and f_id = ?");
            ps.setString(1, userID);
            ps.setString(2, foodID);
            int affected = ps.executeUpdate();
            return affected > 0 ? Status.ORDERCART_DELETE_SUCCESS : Status.ORDERCART_DELETE_FAIL;
        } catch (SQLException e) {
            throw new SystemException(e.getMessage());
        } finally {
            JdbcUtil.close(null, ps);
        }
    }
}
