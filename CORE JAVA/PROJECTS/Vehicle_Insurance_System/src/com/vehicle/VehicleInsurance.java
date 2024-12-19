package com.vehicle;

import java.sql.*;

public class VehicleInsurance {
    public static void addVehicle(String ownerName, String vehicleModel, String licensePlate, String policyNumber) {
        //String query = "INSERT INTO vehicles (owner_name, vehicle_model, license_plate, insurance_policy_number) VALUES (?, ?, ?, ?)";
        
    	String query =  "INSERT INTO vehicles (owner_name, vehicle_model, license_plate, insurance_policy_number) VALUES ('"+ownerName+"','"+vehicleModel+"','"+licensePlate+"','"+policyNumber+"')";
    	
    	System.out.println(query);
        try (
        		//Step 1
        		Connection conn = DatabaseConnection.getConnection();
        		//Step 2
        		Statement stmt = conn.createStatement()){
           //  PreparedStatement stmt = conn.prepareStatement(query)) {
            
         //   stmt.setString(1, ownerName);
           // stmt.setString(2, vehicleModel);
           // stmt.setString(3, licensePlate);
           // stmt.setString(4, policyNumber);
            
        	//Step 3 - execute the query
            int rowsAffected = stmt.executeUpdate(query);
            
            //Step 4 - processing the result
            System.out.println(" Rows Affected :" +rowsAffected);
            if (rowsAffected > 0) {
                System.out.println("Vehicle record added successfully.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
