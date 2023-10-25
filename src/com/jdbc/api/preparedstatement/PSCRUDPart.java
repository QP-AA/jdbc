package com.jdbc.api.preparedstatement;

import org.junit.Test;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PSCRUDPart {
    @Test
    public void testInsert() throws ClassNotFoundException, SQLException {
        Class.forName("com.mysql.cj.jdbc.Driver");

        Connection connection = DriverManager.getConnection("jdbc:mysql:///jdbc", "root", "12345");

        String sql = "insert into t_user(account, password, nickname) values(?,?,?);";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setObject(1, "test");
        preparedStatement.setObject(2, "test");
        preparedStatement.setObject(3, "部长");
        int ok = preparedStatement.executeUpdate();
        System.out.println(ok > 0 ? "更新成功" : "更新失败");
        preparedStatement.close();
        connection.close();
    }
    @Test
    public void testUpdate() throws ClassNotFoundException, SQLException {
        Class.forName("com.mysql.cj.jdbc.Driver");

        Connection connection = DriverManager.getConnection("jdbc:mysql:///jdbc", "root", "12345");

        String sql = "update t_user set nickname = ? where nickname = ?";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setObject(2, "部长");
        preparedStatement.setObject(1, "二部长");

        int ok = preparedStatement.executeUpdate();
        System.out.println(ok > 0 ? "成功" : "失败");

        preparedStatement.close();
        connection.close();

    }
    
    @Test
    public void testDelete() throws ClassNotFoundException, SQLException {
        Class.forName("com.mysql.cj.jdbc.Driver");

        Connection connection = DriverManager.getConnection("jdbc:mysql:///jdbc", "root", "12345");

        String sql = "delete from t_user where nickname = ?";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
//        preparedStatement.setObject(2, "部长");
        preparedStatement.setObject(1, "二部长");

        int ok = preparedStatement.executeUpdate();
        System.out.println(ok > 0 ? "成功" : "失败");

        preparedStatement.close();
        connection.close();
    }
    
    @Test
    public void testSelect() throws ClassNotFoundException, SQLException {
        Class.forName("com.mysql.cj.jdbc.Driver");

        Connection connection = DriverManager.getConnection("jdbc:mysql:///jdbc", "root", "12345");

        // preparedStatement
        String sql = "select * from t_user;";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);

        // 发送并获取
        ResultSet resultSet = preparedStatement.executeQuery();
        ResultSetMetaData metaData = resultSet.getMetaData();
        List<Map> list = new ArrayList<>();
        while (resultSet.next()) {
            HashMap<Object, Object> map = new HashMap<>();
//            map.put("id", resultSet.getInt("id"));
//            map.put("account", resultSet.getString("account"));
//            map.put("password", resultSet.getString("password"));
//            map.put("nickname", resultSet.getString("nickname"));
            for (int i = 1; i <= metaData.getColumnCount(); i++) {
                Object obj = resultSet.getObject(i);
                map.put(metaData.getColumnLabel(i), obj);

            }
            list.add(map);
        }

        System.out.println(list);
        resultSet.close();
        preparedStatement.close();
        connection.close();
    }
}
