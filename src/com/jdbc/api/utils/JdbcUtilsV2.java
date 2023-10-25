package com.jdbc.api.utils;

import com.alibaba.druid.pool.DruidDataSourceFactory;

import javax.sql.DataSource;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;

public class JdbcUtilsV2 {
    private static DataSource dataSource = null;  // 连接池对象

    private static ThreadLocal<Connection> tl = new ThreadLocal<>();

    static {
        // 初始化连接池对象
        Properties properties = new Properties();
        InputStream ips = JdbcUtils.class.getClassLoader().getResourceAsStream("druid.properties");
        try {
            properties.load(ips);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        try {
            dataSource = DruidDataSourceFactory.createDataSource(properties);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static Connection getConnection() throws SQLException {
        Connection connection = tl.get();
        if (connection == null) {
            // 线程本地变量没有，连接池获取
            connection = dataSource.getConnection();
            tl.set(connection);
        }
        return getConnection();
    }

    public static void freeConnection() throws SQLException {
        Connection connection = tl.get();
        if (connection != null) {
            tl.remove(); // 清空线程本地变量数据
            connection.setAutoCommit(true);
            connection.close();
        }
    }
}
