package com.jdbc.api.statement;

import com.mysql.cj.jdbc.Driver;

import java.sql.*;

public class query {
    public static void main(String[] args) throws SQLException {
        // 注册驱动
        DriverManager.registerDriver(new Driver());  // 静态方法，可以通过类直接调用,cj.jdbc.Driver

        // 获取连接
        Connection connection = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/jdbc", "root", "12345");

        //创建statement
        Statement statement = connection.createStatement();

        // 发送sql语句， 获取返回结果
        String sql = "select * from t_user;";
        ResultSet resultSet = statement.executeQuery(sql);

        // 结果解析
        while (resultSet.next()) {
            int id = resultSet.getInt("id");
            String account = resultSet.getString("account");
            String password = resultSet.getString("password");
            String nickname = resultSet.getString("nickname");
            System.out.println(id + "--" + account + "-- " + password + "--" + nickname);
        }

        // 关闭资源
        resultSet.close();
        statement.close();
        connection.close();
    }
}
