package com.rocketboys.hd.Database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class JDBC {
    //mysql驱动包名
    private static final String DRIVER_NAME = "com.mysql.cj.jdbc.Driver";
    //数据库连接地址
    private static final String URL = "jdbc:mysql://114.116.92.12:3306/rocketboyscloud";
    //用户名
    private static final String USER_NAME = "root";
    //密码
    private static final String PASSWORD = "RocketBoys123!";
    //连接数据库
    private static Connection connection=null;

    public JDBC()
    {
        try {
            //加载mysql的驱动类
            Class.forName(DRIVER_NAME);
            //获取数据库连接
            connection = DriverManager.getConnection(URL, USER_NAME, PASSWORD);
        } catch (Exception e) {
        e.printStackTrace();
        }
    }

    public Connection getConnection(){
        return connection;
    }

    public void close()
    {
        if (connection != null) {
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
