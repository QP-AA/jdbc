package com.jdbc.api.statement;

import com.mysql.cj.jdbc.Driver;

import java.sql.*;
import java.util.Properties;
import java.util.Scanner;

public class UserLoginPart {
    public static void main(String[] args) throws SQLException, ClassNotFoundException {
        Scanner scanner = new Scanner(System.in);
        String user_account = scanner.nextLine();
        String user_password = scanner.nextLine();
        scanner.close();
        // 注册驱动
//        DriverManager.registerDriver(new Driver());  // 静态方法，可以通过类直接调用,cj.jdbc.Driver
        Class.forName("com.mysql.cj.jdbc.Driver");
        // 获取连接
        Connection connection = DriverManager.getConnection("jdbc:mysql:///jdbc", "root", "12345");
//        Properties  info = new Properties();
//        info.put("user", "sf");
//        info.put("ac", "dgfd");
//        DriverManager.getConnection("jdbc:mysql:///jdbc", info);

        //创建statement
        Statement statement = connection.createStatement();

        // 发送sql语句， 获取返回结果
        String sql = "select * from t_user where account = '"+user_account+"' and password = '"+user_password+"';";
        ResultSet resultSet = statement.executeQuery(sql);

        // 结果解析
//        System.out.println(!sql.isEmpty() ? "登陆成功" : "登录失败");
        System.out.println(!resultSet.next() ? "登陆成功" : "登录失败");
        // 关闭资源
        resultSet.close();
        statement.close();
        connection.close();
    }
}
