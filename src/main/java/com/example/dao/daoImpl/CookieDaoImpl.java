package com.example.dao.daoImpl;

import com.example.common.JdbcUtil;
import com.example.common.Status;
import com.example.common.SystemException;
import com.example.dao.CookieDao;
import com.example.dao.UserDao;
import com.example.pojo.Cookie;

import java.sql.*;
import java.util.Random;

public class CookieDaoImpl implements CookieDao {
    private UserDao userDao = new UserDaoImpl();

    public Cookie getCookieInfo(String userId) throws SystemException{
        Cookie cookie = new Cookie();
        try{
            if(!userDao.isExist(userId)){
                return null;  //为空则为用户不存在
            }
            Connection connection = JdbcUtil.getConnection();
            Statement statement = connection.createStatement();
            String sql = "select * from t_cookie where u_id='"+userId+"'";

            ResultSet rs = statement.executeQuery(sql);
            if(rs.next()){
                cookie.setUserID(rs.getString("u_id"));
                cookie.setCookieString(rs.getString("cookie_string"));
                cookie.setExpireTime(rs.getLong("expire_time"));
            }
            JdbcUtil.close(rs,statement);
            return cookie;
        }catch (SQLException e){
            throw new SystemException(e.getMessage());
        }
    }

    @Override
    public Status addCookie(String userId) throws SystemException {
        try{
            Connection connection = JdbcUtil.getConnection();
            String sql = "select * from t_cookie where u_id = ?";
            PreparedStatement ps = connection.prepareStatement(sql);

            ps.setString(1,userId);
            ResultSet rs = ps.executeQuery();
            if(rs.next()){
                JdbcUtil.close(rs,ps);
                return Status.COOKIE_EXISTS;
            }

            sql = "select unix_timestamp(now())";
            rs = ps.executeQuery();
            long now_time = rs.getLong(1);
            long expire_time = now_time + 86400000 * 3;
            sql = "insert into t_cookie(u_id,cookie_string,expire_time) " +
                    "values('"+ userId +"','"+ getRandomString() +"',"+ expire_time +")";
            Statement statement = connection.createStatement();
            int resultNum = statement.executeUpdate(sql);
            JdbcUtil.close(null,statement);
            if(resultNum > 0){
                return Status.COOKIE_ADD_SUCCESS;
            }else{
                return Status.COOKIE_ADD_FAIL;
            }
        }catch (SQLException e){
            throw new SystemException(e.getMessage());
        }
    }

    @Override
    public Status delectCookie(String userId) throws SystemException {
        try{
            if(!isExist(userId)){
                return Status.COOKIE_NOT_EXISTS;
            }
            Connection connection = JdbcUtil.getConnection();
            String sql = "delete from t_cookie where u_id = '"+userId+"'";
            Statement statement = connection.createStatement();
            int resultNum = statement.executeUpdate(sql);
            JdbcUtil.close(null,statement);
            if(resultNum > 0){
                return Status.COOKIE_DELETE_SUCCESS;
            }else {
                return Status.COOKIE_DELETE_FAIL;
            }
        }catch (SQLException e){
            throw new SystemException(e.getMessage());
        }
    }

    @Override
    public boolean isExist(String userId) throws SystemException {
        try{
            Connection connection = JdbcUtil.getConnection();
            String sql = "select * from t_cookie where u_id = ?";

            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1,userId);
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
    public boolean isValid(String userId) throws SystemException {
        try{
            long expire_time = 0;
            long now_time = 0;
            Connection connection = JdbcUtil.getConnection();
            String sql1 = "select expire_time from t_cookie where u_id = ?";
            String sql2 = "select unix_timestamp(now())";
            PreparedStatement ps1 = connection.prepareStatement(sql1);
            PreparedStatement ps2 = connection.prepareStatement(sql2);
            ps1.setString(1,userId);
            ResultSet rs1 = ps1.executeQuery();
            ResultSet rs2 = ps2.executeQuery();
            if(rs1.next()){
                expire_time = rs1.getLong("expire_time");
            }
            if(rs2.next()){
                now_time = rs2.getLong(1);
            }
            JdbcUtil.close(rs1,ps1);
            JdbcUtil.close(rs2,ps2);
            if(expire_time <= now_time){
                return false;
            }else{
                return true;
            }
        }catch (SQLException e){
            throw new SystemException(e.getMessage());
        }
    }

    private String getRandomString() {
        String str = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
        Random random = new Random();
        int length = random.nextInt(100) + 20;
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < length; i++) {
            int number = random.nextInt(62);
        sb.append(str.charAt(number));
        }
        return sb.toString();
    }
}
