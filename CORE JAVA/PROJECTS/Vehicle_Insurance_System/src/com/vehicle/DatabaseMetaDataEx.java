package com.vehicle;

import java.sql.*;

public class DatabaseMetaDataEx {
    public static void main(String[] args) {
        // Database connection details
        String url = "jdbc:mysql://localhost:3306/vehicle_insurance";
        String user = "root";
        String password = "root";

        Connection connection = null;
        DatabaseMetaData databaseMetaData = null;

        try {
            // Establish the connection to the database
            connection = DriverManager.getConnection(url, user, password);

            // Get the DatabaseMetaData object
            databaseMetaData = connection.getMetaData();

            // Get database information
            String dbProductName = databaseMetaData.getDatabaseProductName();
            String dbProductVersion = databaseMetaData.getDatabaseProductVersion();
            System.out.println("Database Product: " + dbProductName);
            System.out.println("Version: " + dbProductVersion);

            // Get tables metadata
            System.out.println("\nTables in the database:");
            ResultSet tables = databaseMetaData.getTables("vehicle_insurance",null, "%", new String[]{"TABLE"});
            while (tables.next()) {
                String tableName = tables.getString("TABLE_NAME");
                System.out.println("Table: " + tableName);

                // Get columns metadata for each table
                ResultSet columns = databaseMetaData.getColumns(null, null, tableName, "%");
                System.out.println("Columns in table " + tableName + ":");
                while (columns.next()) {
                    String columnName = columns.getString("COLUMN_NAME");
                    String columnType = columns.getString("TYPE_NAME");
                    int columnSize = columns.getInt("COLUMN_SIZE");
                    boolean isNullable = columns.getInt("NULLABLE") == DatabaseMetaData.columnNullable;

                    System.out.println("  Column: " + columnName + ", Type: " + columnType +
                            ", Size: " + columnSize + ", Nullable: " + isNullable);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (connection != null) connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
