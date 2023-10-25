package com.jdbc.api.druid;

import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.pool.DruidDataSourceFactory;
import org.junit.Test;

import javax.sql.DataSource;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.util.Properties;

public class DruidUsedPart {
    public void testHard() {
        DruidDataSource dataSource = new DruidDataSource();  // 创建连接池对象

        // 设置参数
        // 必须： 连接数据库驱动类的全限定符[注册驱动] |url|user|password
        // 非必须： 初始化连接池数量，最大连接池数量
        dataSource.setUrl("jdbc:mysql:///t_user");
        dataSource.setUsername("root");
        dataSource.setPassword("12345");
        dataSource.setDriverClassName("com.mysql.cj.jdbc.Driver"); // 驱动地址
    }

    @Test
    public void testSoft() throws Exception {
        // 读取外部配置文件Properties
        Properties properties = new Properties();
        InputStream ips = DruidUsedPart.class.getClassLoader().getResourceAsStream("druid.properties");
        properties.load(ips);
        // 使用连接池的工具类的工厂模式创建连接池
        DataSource dataSource = DruidDataSourceFactory.createDataSource(properties);
        Connection connection = dataSource.getConnection();
        connection.close();
    }
}
