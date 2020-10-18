package com.example.dao.daoImpl;

import com.example.common.JdbcUtil;
import com.example.common.Status;
import com.example.common.SystemException;
import com.example.dao.UserDao;
import com.example.pojo.User;
import sun.misc.BASE64Encoder;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.*;

public class UserDaoImpl implements UserDao {

    @Override
    public User getUser(String phoneNumber) throws SystemException {
        User user = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try{
            Connection connection = JdbcUtil.getConnection();
            String sql = "select * from t_user where u_phone_Number = ?";
            ps = connection.prepareStatement(sql);

            ps.setString(1,phoneNumber);
            rs = ps.executeQuery();
            if(rs.next()){
                user = new User();
                user.setUserID(rs.getString("u_id"));
                user.setUserName(rs.getString("u_name"));
                user.setPassword(rs.getString("u_password"));
                user.setPhoneNumber(rs.getString("u_phone_number"));
                user.setIdentity(rs.getInt("u_identity"));
                user.setBalance(rs.getDouble("u_balance"));
            }

        }catch(SQLException e){
            throw new SystemException(e.getMessage());
        }finally {
            JdbcUtil.close(rs,ps);
        }
        return user;
    }

    @Override
    public Status addUser(User user) throws SystemException{
        PreparedStatement ps = null;
        ResultSet rs = null;
        Statement statement = null;
        try{
            Connection connection = JdbcUtil.getConnection();
            String sql = "select * from t_user where u_phone_number = ?";

            ps = connection.prepareStatement(sql);
            ps.setString(1,user.getPhoneNumber());
            rs = ps.executeQuery();
            if(rs.next()){
                return Status.USER_EXISTS;
            }
            sql = "insert into t_user(u_id,u_name,u_phone_number,u_password,u_identity,u_balance) " +
                    "values('"+ getUserId() +"','"+ user.getUserName()+"','"+user.getPhoneNumber() +"','" +
                    EncoderByMd5(user.getPassword()) +"',"+ user.getIdentity() +","+ user.getBalance() +")";
            statement = connection.createStatement();
            int resultNum = statement.executeUpdate(sql);

            if(resultNum > 0){
                return Status.USER_ADD_SUCCESS;
            }else{
                return Status.USER_ADD_FAIL;
            }
        }catch(SQLException e){
            throw new SystemException(e.getMessage());
        }finally {
            JdbcUtil.close(rs,ps);
            JdbcUtil.close(null,statement);
        }
    }

    @Override
    public Status update(User user) throws SystemException {
        Statement statement = null;
        try{
            if(!isExist(user.getUserID())){
                return Status.USER_NOT_EXISTS;
            }
            Connection connection = JdbcUtil.getConnection();
            statement = connection.createStatement();
            String sql = "update t_user set u_name='"+user.getUserName() +"',u_password='"+EncoderByMd5(user.getPassword())+
                    "',u_phone_number='"+user.getPhoneNumber()+"',u_identity="+user.getIdentity()+",u_balance="+user.getBalance()+
                    " where u_id='"+user.getUserID()+"'";
            int resultNum = statement.executeUpdate(sql);

            if(resultNum > 0){
                return Status.USER_UPDATE_SUCCESS;
            }else{
                return Status.USER_UPDATE_FAIL;
            }

        }catch(SQLException e){
            throw new SystemException(e.getMessage());
        }finally {
            JdbcUtil.close(null,statement);
        }
    }
    @Override
    public Status delete(String userId) throws SystemException {
        Statement statement = null;
        try{
            if(!isExist(userId)){
                return Status.USER_NOT_EXISTS;
            }
            Connection connection = JdbcUtil.getConnection();
            statement = connection.createStatement();
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
        }finally {
            JdbcUtil.close(null,statement);
        }
    }
    @Override
    public boolean isTruePassword(String phoneNumber,String password) throws SystemException{
        PreparedStatement ps = null;
        ResultSet rs = null;
        try{
            Connection connection = JdbcUtil.getConnection();
            String sql = "select u_password from t_user where u_phone_number = ?";
            ps = connection.prepareStatement(sql);
            ps.setString(1,phoneNumber);
            rs = ps.executeQuery();
            if(rs.next()){
                String truePassword = rs.getString("u_password");
                if(!EncoderByMd5(password).equals(truePassword)){
                    return false;  //密码错误
                }
            }else{
                JdbcUtil.close(rs,ps);
                return false;  //用户不存在
            }

        }catch (SQLException e){
            throw new SystemException(e.getMessage());
        }finally {
            JdbcUtil.close(rs,ps);
        }
        return true;
    }
    @Override
    public boolean isExist(String userId) throws SystemException{
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            Connection connection = JdbcUtil.getConnection();
            String sql = "select * from t_user where u_id= ?";
            ps = connection.prepareStatement(sql);
            ps.setString(1,userId);
            rs = ps.executeQuery();
            if(rs.next()){
                return true;
            }else{
                return false;  //用户不存在
            }
        }catch (SQLException e){
            throw new SystemException(e.getMessage());
        }finally {
            JdbcUtil.close(rs,ps);
        }

    }

    private String getUserId() throws SystemException{  //获取用户ID
        PreparedStatement ps = null;
        ResultSet rs = null;
        try{
            Connection connection = JdbcUtil.getConnection();
            String sql =  "select getUserId(?);";
            ps = connection.prepareStatement(sql);
            ps.setString(1,"seq_user_id");
            rs = ps.executeQuery();
            if(rs.next()){
                return rs.getString(1);
            }else{
                return null;
            }
        }catch (SQLException e){
            throw new SystemException(e.getMessage());
        }finally {
            JdbcUtil.close(rs,ps);
        }

    }


    private static String EncoderByMd5(String str) throws SystemException{  //密码加密
        try{
            MessageDigest md5=MessageDigest.getInstance("MD5");
            BASE64Encoder base64en = new BASE64Encoder();
            //加密后的字符串
            return base64en.encode(md5.digest(str.getBytes(StandardCharsets.UTF_8)));
        }catch(NoSuchAlgorithmException e){
            throw new SystemException(e.getMessage());
        }
    }
}
