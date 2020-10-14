package com.example.common;

import java.sql.*;

public class JdbcUtil {
    public static Connection connection = null;

    public static void getConnection() throws Exception{
        Class.forName("com.mysql.jdbc.Driver");//加载数据库驱动

        String url = "jdbc:mysql://localhost/mydatabase?useUnicode=true&characterEncoding=utf-8&serverTimezone=GMT%2B8";
        String user = "root";
        String password = "281215";
        if(connection == null){
            connection = DriverManager.getConnection(url, user, password);
        }


    }
    public static void close(ResultSet rs, Statement st, Connection conn) {
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
            } finally {
                if (conn != null) {
                    try {
                        conn.close();
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }
}
