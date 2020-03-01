package helpers;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


public class MakeConnection {
    private static final String URL = "jdbc:mysql://localhost:3306/mysqldb";
    private static final String USER = "appuser";
    private static final String PASSWORD = "yes";

    public static java.sql.Connection getConnection() {
        try {
            return DriverManager.getConnection(URL, USER, PASSWORD);
        } catch (SQLException e) {
            throw new RuntimeException("failed to connect to db");
        }
    }
}

