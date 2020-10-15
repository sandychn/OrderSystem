package com.example.dao.daoImpl;

import com.example.common.JdbcUtil;
import com.example.common.Status;
import com.example.common.SystemException;
import com.example.dao.UserDao;
import com.example.pojo.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserDaoImpl implements UserDao {

    @Override
    public User getUser(String phoneNumber) throws SystemException {
        User user = null;
        try(Connection connection = JdbcUtil.getConnection()){
            String sql = "select * from t_user where phoneNumber = ?";
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1,phoneNumber);
            ResultSet  rs = ps.executeQuery();
            if(rs.next()){
                user = new User();
                user.setUserID(rs.getString("u_id"));
                user.setUserName(rs.getString("u_name"));
                user.setPassword(rs.getString("u_password"));
                user.setPhoneNumber(rs.getString("u_phone_number"));
                user.setIdentity(rs.getInt("u_identity"));
                user.setBalance(rs.getDouble("u_balance"));
            }
            JdbcUtil.close(rs,ps);
        }catch(SQLException e){
            throw new SystemException(e.getMessage());
        }
        return user;
    }

    @Override
    public Status addUser(User user) throws SystemException{
        
        return null;

    }
}
