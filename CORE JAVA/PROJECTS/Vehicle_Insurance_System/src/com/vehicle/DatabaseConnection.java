package com.vehicle;

import java.sql.*;

public class DatabaseConnection {
    public static Connection getConnection() throws SQLException {
        String url = "jdbc:mysql://localhost:3306/vehicle_insurance";
        String username = "root";
        String password = "root";
        return DriverManager.getConnection(url, username, password);
    }
}

