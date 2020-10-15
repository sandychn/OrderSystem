package com.example.common;

import java.sql.*;

public class JdbcUtil {
    private static Connection connection = null;


    public static Connection getConnection() throws SystemException{
        try{
            Class.forName("com.mysql.jdbc.Driver");//加载数据库驱动
            String url = "jdbc:mysql://localhost/ordering?useUnicode=true&characterEncoding=utf-8&serverTimezone=GMT%2B8";
            String user = "root";
            String password = "281215";
            if(connection == null){
                    connection = DriverManager.getConnection(url, user, password);
            }
        } catch (Exception e){
            throw new SystemException(e.getMessage());
        }
        return connection;

    }

    public static void close(ResultSet rs, Statement st) throws SystemException{
        try {
            if (rs != null) {
                rs.close();
            }
        } catch (SQLException e) {
            throw new SystemException(e.getMessage());
        } finally {
            try {
                if (st != null) {
                    st.close();
                }
            } catch (SQLException e) {
                throw new SystemException(e.getMessage());
            }
        }
    }
}
