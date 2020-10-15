package com.example.common;

import java.sql.*;

public class JdbcUtil {
    private static Connection connection = null;

    public static Connection getConnection() throws Exception {
        Class.forName("com.mysql.jdbc.Driver");//加载数据库驱动

        String url = "jdbc:mysql://localhost/ordering?useUnicode=true&characterEncoding=utf-8&serverTimezone=GMT%2B8";
        String user = "root";
        String password = "281215";
        if(connection == null){
            connection = DriverManager.getConnection(url, user, password);
        }
        return connection;

    }
    public static void close(ResultSet rs, Statement st) {
        try {
            if (rs != null) {
                rs.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (st != null) {
                    st.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
