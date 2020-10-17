package com.example.dao.daoImpl;

import com.example.common.JdbcUtil;
import com.example.common.Status;
import com.example.common.SystemException;
import com.example.dao.UserDao;
import com.example.pojo.User;
import sun.misc.BASE64Encoder;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.*;

public class UserDaoImpl<Static> implements UserDao {

    @Override
    public User getUser(String phoneNumber) throws SystemException {
        User user = null;
        try{
            Connection connection = JdbcUtil.getConnection();
            String sql = "select * from t_user where u_phone_Number = ?";
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
        try{
            Connection connection = JdbcUtil.getConnection();
            String sql = "select * from t_user where u_phone_number = ?";

            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1,user.getPhoneNumber());
            ResultSet rs = ps.executeQuery();
            if(rs.next()){
                JdbcUtil.close(rs,ps);
                return Status.USER_EXISTS;
            }
            sql = "insert into t_user(u_id,u_name,u_phone_number,u_password,u_identity,u_balance) " +
                    "values('"+ getUserId() +"','"+ user.getUserName()+"','"+user.getPhoneNumber() +"','" +
                    EncoderByMd5(user.getPassword()) +"',"+ user.getIdentity() +","+ user.getBalance() +")";
            Statement statement = connection.createStatement();
            int resultNum = statement.executeUpdate(sql);
            JdbcUtil.close(null,statement);
            if(resultNum > 0){
                return Status.USER_ADD_SUCCESS;
            }else{
                return Status.USER_ADD_FAIL;
            }
        }catch(SQLException e){
            throw new SystemException(e.getMessage());
        }
    }

    @Override
    public Status update(User user) throws SystemException {
        try{
            if(!isExist(user.getUserID())){
                return Status.USER_NOT_EXISTS;
            }
            Connection connection = JdbcUtil.getConnection();
            Statement statement = connection.createStatement();
            String sql = "update t_user set u_name='"+user.getUserName() +"',u_password='"+EncoderByMd5(user.getPassword())+
                    "',u_phone_number='"+user.getPhoneNumber()+"',u_identity="+user.getIdentity()+",u_balance="+user.getBalance()+
                    " where u_id='"+user.getUserID()+"'";
            int resultNum = statement.executeUpdate(sql);
            JdbcUtil.close(null,statement);
            if(resultNum > 0){
                return Status.USER_UPDATE_SUCCESS;
            }else{
               return Status.USER_UPDATE_FAIL;
            }

        }catch(SQLException e){
            throw new SystemException(e.getMessage());
        }
    }
    @Override
    public Status delete(String userId) throws SystemException {
        try{
            if(!isExist(userId)){
                return Status.USER_NOT_EXISTS;
            }
            Connection connection = JdbcUtil.getConnection();
            Statement statement = connection.createStatement();
            String sql = "delete from t_user where u_id='"+userId+"'";
            statement.executeUpdate(sql);
            int resultNum = statement.executeUpdate(sql);
            JdbcUtil.close(null,statement);
            if(resultNum > 0){
                return Status.USER_DELETE_SUCCESS;
            }else{
                return Status.USER_DELETE_FAIL;
            }
        }catch(SQLException e){
            throw new SystemException(e.getMessage());
        }
    }
    @Override
    public boolean isTruePassword(String phoneNumber,String password) throws SystemException{
        try{
            Connection connection = JdbcUtil.getConnection();
            String sql = "select u_password from t_user where u_phone_number = ?";
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1,phoneNumber);
            ResultSet rs = ps.executeQuery();
            if(rs.next()){
                String truePassword = rs.getString("u_password");
                JdbcUtil.close(rs,ps);
                if(!EncoderByMd5(password).equals(truePassword)){
                    return false;  //密码错误
                }
            }else{
                JdbcUtil.close(rs,ps);
                return false;  //用户不存在
            }

        }catch (SQLException e){
            throw new SystemException(e.getMessage());
        }
        return true;
    }
    @Override
    public boolean isExist(String userId) throws SystemException{
        try {
            Connection connection = JdbcUtil.getConnection();
            String sql = "select * from t_user where u_id= ?";

            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1,userId);
            ResultSet rs = ps.executeQuery();
            if(rs.next()){
                JdbcUtil.close(rs,ps);
                return true;
            }else{
                JdbcUtil.close(rs,ps);
                return false;  //用户不存在
            }
        }catch (SQLException e){
            throw new SystemException(e.getMessage());
        }

    }

    private String getUserId() throws SystemException{  //获取用户ID
        try{
            Connection connection = JdbcUtil.getConnection();
            String sql =  "select getUserId(?);";
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1,"seq_user_id");
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


    private static String EncoderByMd5(String str) throws SystemException{  //密码加密
        try{
            MessageDigest md5=MessageDigest.getInstance("MD5");
            BASE64Encoder base64en = new BASE64Encoder();
            //加密后的字符串
            String newstr=base64en.encode(md5.digest(str.getBytes("utf-8")));
            return newstr;
        }catch(NoSuchAlgorithmException | UnsupportedEncodingException e){
            throw new SystemException(e.getMessage());
        }
    }
}
