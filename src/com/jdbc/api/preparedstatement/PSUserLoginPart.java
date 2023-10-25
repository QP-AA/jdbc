package com.jdbc.api.preparedstatement;

import java.sql.*;
import java.util.Scanner;

public class PSUserLoginPart {
    public static void main(String[] args) throws ClassNotFoundException, SQLException {
        Scanner scanner = new Scanner(System.in);
        String user_account = scanner.nextLine();
        String user_password = scanner.nextLine();
        scanner.close();

        Class.forName("com.mysql.cj.jdbc.Driver");

        Connection connection = DriverManager.getConnection("jdbc:mysql:///jdbc", "root", "12345");

        // preparedStatement
        String sql = "select * from t_user where account = ? and password = ?;";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setObject(1, user_account);
        preparedStatement.setObject(2, user_password);

        // 发送并获取
        ResultSet resultSet = preparedStatement.executeQuery();

        System.out.println(!resultSet.next() ? "登陆成功" : "登录失败");
        resultSet.close();
        preparedStatement.close();
        connection.close();
    }
}
