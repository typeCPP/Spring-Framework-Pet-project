package com.company.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class JDBCUtils {
    public static Connection getNewConnection() throws SQLException {
        String url = "jdbc:sqlite:departmentDB.sqlite";
        Connection connection = DriverManager.getConnection(url);

        if (connection.isValid(3)) {
            System.out.println("Connection successful!");
        }

        return connection;
    }
}
