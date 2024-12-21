package com.vehicle;

import java.sql.*;

public class DatabaseConnection {

	private static DatabaseConnection instance;

	private Connection connection;

	private DatabaseConnection() {
		try {
			String url = "jdbc:mysql://localhost:3306/vehicle_insurance";
			String username = "root";
			String password = "root";
			connection = DriverManager.getConnection(url, username, password);
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println(" Error in connecting to the db");
			e.printStackTrace();
		}
	}

	public static DatabaseConnection getInstance() throws SQLException {

		if (instance == null || instance.getConnection().isClosed()) {
			instance = new DatabaseConnection();
		}
		return instance;

	}

	public void closeConnection() throws SQLException {
		if (connection != null && !connection.isClosed() ) {
			connection.close();
		}
	}

	public  Connection getConnection() {
		return this.connection;

	}
}
