package com.example.common;

import java.sql.*;

public class JdbcUtil {
    private static Connection connection;

    static {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            //Class.forName("com.mysql.cj.jdbc.Driver");//加载数据库驱动
            String url = "jdbc:mysql://192.168.106.128/ordering?useUnicode=true&characterEncoding=utf-8&serverTimezone=GMT%2B8";
            String user = "root";
            String password = "123456";
            connection = DriverManager.getConnection(url, user, password);
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
            connection = null;
        }
    }

    public static Connection getConnection() {
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
