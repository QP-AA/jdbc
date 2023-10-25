package com.jdbc.api.druid;

import com.jdbc.api.utils.JdbcUtils;
import org.junit.Test;

import java.sql.Connection;
import java.sql.SQLException;

public class CrudPart {
    @Test
    public void testInsert() throws SQLException {
        Connection connection = JdbcUtils.getConnection();

        JdbcUtils.freeConnection(connection);
    }
}
