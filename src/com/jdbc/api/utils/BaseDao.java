package com.jdbc.api.utils;

import java.lang.reflect.Field;
import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BaseDao {

    public int executeUpdate(String sql, Object... params) throws SQLException {
        // 获取连接
        Connection connection = JdbcUtilsV2.getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        for (int i = 1; i <= params.length; i++) {
            preparedStatement.setObject(i, params[i - 1]);
        }

        int rows = preparedStatement.executeUpdate();

        if (connection.getAutoCommit()) {
            connection.close(); // 不是事务，关闭连接
        }
        return rows;
    }

    public <T> List<T> executeQuery(Class<T> clazz, String sql, Object... params) throws SQLException, InstantiationException, IllegalAccessException, NoSuchFieldException {

        Connection connection = JdbcUtilsV2.getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        for (int i = 1; i <= params.length; i++) {
            preparedStatement.setObject(i, params[i - 1]);
        }
        ResultSet resultSet = preparedStatement.executeQuery();

        List<T> list = new ArrayList<>();


        ResultSetMetaData metaData = resultSet.getMetaData();
        while (resultSet.next()) {
            T t = clazz.newInstance();

            for (int i = 1; i <= metaData.getColumnCount(); i++) {
                Object value = resultSet.getObject(i);
                String propertyName = metaData.getColumnLabel(i);

                // 反射
                Field field = clazz.getDeclaredField(propertyName);
                field.setAccessible(true);
                field.set(t, value);
            }
            list.add(t);
        }
        resultSet.close();
        preparedStatement.close();
        if(connection.getAutoCommit()) {
            connection.close();
        }
        return list;
    }
}
