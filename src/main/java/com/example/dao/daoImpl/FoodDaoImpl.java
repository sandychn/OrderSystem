package com.example.dao.daoImpl;

import com.example.common.JdbcUtil;
import com.example.common.Status;
import com.example.common.SystemException;
import com.example.dao.FoodDao;
import com.example.pojo.Food;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class FoodDaoImpl implements FoodDao {
    @Override
    public Food getFood(String foodID) throws SystemException {
        Food food = null;
        try {
            Connection connection = JdbcUtil.getConnection();
            String sql = "select * from t_food where f_id = ?";
            PreparedStatement ps = connection.prepareStatement(sql);

            ps.setString(1,foodID);
            ResultSet rs = ps.executeQuery();
            if(rs.next()){
                food = new Food();
                food.setFoodID(rs.getString("f_id"));
                food.setFoodName(rs.getString("f_name"));
                food.setImageUrl(rs.getString("f_image_url"));
                food.setDescription(rs.getString("f_description"));
                food.setPrice(rs.getDouble("f_price"));
                food.setKind(rs.getInt("k_id"));
            }
            JdbcUtil.close(rs,ps);
        } catch (SQLException e){
            throw new SystemException(e.getMessage());
        }
        return food;
    }

    @Override
    public Status addFood(Food food) throws SystemException {
        try{
            Connection connection = JdbcUtil.getConnection();
            String sql = "select * from t_food where f_id = ?";
            PreparedStatement ps = connection.prepareStatement(sql);

            ps.setString(1,food.getFoodID());
            ResultSet rs = ps.executeQuery();
            if(rs.next()){
                JdbcUtil.close(rs,ps);
                return Status.FOOD_EXISTS;
            }

            sql = "insert into t_food(f_id,f_name,f_image_url,f_descripition,f_price) " +
                    "values('"+ food.getFoodID() +"','"+ food.getFoodName() +"','"+ food.getImageUrl() +"','" +
                     food.getDescription() +"',"+ food.getPrice() +")";
            Statement statement = connection.createStatement();
            int resultNum = statement.executeUpdate(sql);
            JdbcUtil.close(null,statement);
            if(resultNum > 0){
                return Status.FOOD_ADD_SUCCESS;
            }else{
                return Status.FOOD_ADD_FAIL;
            }
        }catch (SQLException e){
            throw new SystemException(e.getMessage());
        }
    }

    @Override
    public Status update(Food food) throws SystemException {
        try {
            if(!isExist(food.getFoodID())){
                return Status.FOOD_NOT_EXISTS;
            }
            Connection connection = JdbcUtil.getConnection();
            String sql = "update t_food set f_name='"+ food.getFoodName() +"',f_image_url ='"+ food.getImageUrl() +
                    "', f_description ='"+ food.getDescription() +"',f_price ="+ food.getPrice() +",k_id ="+ food.getKind() +
                    " where f_id='"+ food.getFoodID() +"'";
            Statement statement = connection.createStatement();
            int resultNum = statement.executeUpdate(sql);
            JdbcUtil.close(null,statement);
            if(resultNum > 0){
                return Status.FOOD_UPDATE_SUCCESS;
            }else{
                return Status.FOOD_UPDATE_FAIL;
            }
        }catch (SQLException e){
            throw new SystemException(e.getMessage());
        }
    }

    @Override
    public Status delete(String foodID) throws SystemException {
        try{
            if(!isExist(foodID)){
                return Status.FOOD_NOT_EXISTS;
            }
            Connection connection = JdbcUtil.getConnection();
            String sql = "delete from t_food where f_id = '"+foodID+"'";
            Statement statement = connection.createStatement();
            int resultNum = statement.executeUpdate(sql);
            JdbcUtil.close(null,statement);
            if(resultNum > 0){
                return Status.FOOD_DELETE_SUCCESS;
            }else {
                return Status.FOOD_DELETE_FAIL;
            }
        }catch (SQLException e){
            throw new SystemException(e.getMessage());
        }
    }

    @Override
    public boolean isExist(String foodID) throws SystemException {
        try{
            Connection connection = JdbcUtil.getConnection();
            String sql = "select * from t_food where f_id = ?";

            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1,foodID);
            ResultSet rs = ps.executeQuery();
            if(rs.next()){
                JdbcUtil.close(rs,ps);
                return true;
            }else {
                JdbcUtil.close(rs,ps);
                return false;
            }
        }catch (SQLException e){
            throw new SystemException(e.getMessage());
        }
    }

    @Override
    public List<Food> getAllFood() throws SystemException {
        try{
            Connection connection = JdbcUtil.getConnection();
            String sql = "select f_id,f_name,f_image_url,f_description,f_price,k_id from t_food";
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(sql);
            List<Food> list = new ArrayList<>();
            while(rs.next()){
                Food food = new Food();
                food.setFoodID(rs.getString("f_id"));
                food.setFoodName(rs.getString("f_name"));
                food.setImageUrl(rs.getString("f_image_url"));
                food.setDescription(rs.getString("f_description"));
                food.setPrice(rs.getDouble("f_price"));
                food.setKind(rs.getInt("k_id"));
                list.add(food);
            }
            return list;
        }catch (SQLException e){
            throw new SystemException(e.getMessage());
        }
    }
}
