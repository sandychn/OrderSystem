package com.example.dao.daoImpl;

import com.example.common.JdbcUtil;
import com.example.common.Status;
import com.example.common.SystemException;
import com.example.dao.CookieDao;
import com.example.dao.UserDao;
import com.example.pojo.Cookie;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

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
}
